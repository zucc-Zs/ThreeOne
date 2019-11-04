package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.comtrol.example.ExampleMajorPlanManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSchoolPlanManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmMajorPlan extends JDialog implements ActionListener {
	public BeanMajor major;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加专业计划");
	private Button btnModify = new Button("修改专业计划");
	private Button btnDelete = new Button("删除专业计划");
	private Object tblTitle[]={"年份","专业编号","专业名字","选考科目","分数","计划数","学费","其他要求"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable majorPlanTable=new JTable(tablmod);
	private void reloadMajorPlanTable(BeanMajor aMajor){
		try {
			List<BeanMajorPlan> majorPlans=(new ExampleMajorPlanManager()).loadAll(aMajor);
			tblData =new Object[majorPlans.size()][8];
			for(int i=0;i<majorPlans.size();i++){
				for(int j=0;j<BeanMajorPlan.tblMajorPlanTitle.length;j++)
					tblData[i][j]=majorPlans.get(i).getCell(j);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.majorPlanTable.validate();
			this.majorPlanTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmMajorPlan(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		if("管理员".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		if("管理员".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.reloadMajorPlanTable(FrmMain.curMajor);
		if("员工".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.reloadMajorPlanTable(FrmSearch.curMajor);
		this.getContentPane().add(new JScrollPane(this.majorPlanTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmAddMajorPlan dlg=new FrmAddMajorPlan(this,"添加专业计划",true);
			dlg.major=FrmMain.curMajor;
			dlg.setVisible(true);
			reloadMajorPlanTable(FrmMain.curMajor);
		}
		else if(e.getSource()==this.btnModify){
			int i=this.majorPlanTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择专业计划","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定修改专业计划吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanMajorPlan> majorPlans=(new ExampleMajorPlanManager()).loadAll(FrmMain.curMajor);
					BeanMajorPlan majorPlan = majorPlans.get(i);
					FrmModifyMajorPlan dlg=new FrmModifyMajorPlan(this,"修改专业计划",true);
					dlg.majorPlan=majorPlan;
					dlg.setVisible(true);
					reloadMajorPlanTable(FrmMain.curMajor);
				}catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.majorPlanTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择专业计划","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除专业计划吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanMajorPlan> majorPlans=(new ExampleMajorPlanManager()).loadAll(FrmMain.curMajor);
					BeanMajorPlan majorPlan = majorPlans.get(i);
					(new ExampleMajorPlanManager()).delete(majorPlan);
					this.reloadMajorPlanTable(FrmMain.curMajor);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
