package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
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

import cn.edu.zucc.personplan.comtrol.example.ExampleSchoolPlanManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSpecialityManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmSchoolPlan extends JDialog implements ActionListener {
	public BeanSchool school;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("���ѧУ�ƻ�");
	private Button btnModify = new Button("�޸�ѧУ�ƻ�");
	private Button btnDelete = new Button("ɾ��ѧУ�ƻ�");
	private Object tblTitle[]={"ѧУID","ѧУ����","��������","������ʼʱ��","��������ʱ��","������ʽ"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable schoolPlanTable=new JTable(tablmod);
	private void reloadSchoolPlanTable(BeanSchool aSchool){
		try {
			List<BeanSchoolPlan> schoolPlans=(new ExampleSchoolPlanManager()).loadAll(aSchool);
			tblData =new Object[schoolPlans.size()][6];
			for(int i=0;i<schoolPlans.size();i++){
				for(int j=0;j<BeanSchoolPlan.tblSchoolPlanTitle.length;j++)
					tblData[i][j]=schoolPlans.get(i).getCell(j);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.schoolPlanTable.validate();
			this.schoolPlanTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmSchoolPlan(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		if("����Ա".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		if("����Ա".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.reloadSchoolPlanTable(FrmMain.curSchool);
		if("Ա��".equals(BeanUser.currentLoginUser.getUser_grade())) 
			this.reloadSchoolPlanTable(FrmSearch.curSchool);
		this.getContentPane().add(new JScrollPane(this.schoolPlanTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
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
			FrmAddSchoolPlan dlg=new FrmAddSchoolPlan(this,"���ѧУ�ƻ�",true);
			dlg.school=FrmMain.curSchool;
			dlg.setVisible(true);
			reloadSchoolPlanTable(FrmMain.curSchool);
		}
		else if(e.getSource()==this.btnModify){
			int i=this.schoolPlanTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ��ѧУ�ƻ�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ���޸�ѧУ�ƻ���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanSchoolPlan> schoolPlans=(new ExampleSchoolPlanManager()).loadAll(FrmMain.curSchool);
					BeanSchoolPlan schoolPlan = schoolPlans.get(i);
					FrmModifySchoolPlan dlg=new FrmModifySchoolPlan(this,"�޸�ѧУ�ƻ�",true);
					dlg.schoolPlan=schoolPlan;
					dlg.setVisible(true);
					reloadSchoolPlanTable(FrmMain.curSchool);
				}catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.schoolPlanTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ��ѧУ�ƻ�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ��ѧУ�ƻ���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanSchoolPlan> schoolPlans=(new ExampleSchoolPlanManager()).loadAll(FrmMain.curSchool);
					BeanSchoolPlan schoolPlan = schoolPlans.get(i);
					(new ExampleSchoolPlanManager()).delete(schoolPlan);
					this.reloadSchoolPlanTable(FrmMain.curSchool);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
