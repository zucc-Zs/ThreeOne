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
import cn.edu.zucc.personplan.comtrol.example.ExampleStudentManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.model.BeanRecommend;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmRecommend extends JDialog {
	public BeanStudent student;
	private JPanel toolBar = new JPanel();

	private Object tblTitle[]={"身份证号","学生姓名","专业ID","专业名称","推荐理由"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable RecommendTable=new JTable(tablmod);
	private void reloadRecommendTable(BeanStudent aStudent){
		try {
			List<BeanRecommend> recommends=(new ExampleStudentManager()).loadRecommend(aStudent);
			tblData =new Object[recommends.size()][5];
			for(int i=0;i<recommends.size();i++){
				for(int j=0;j<BeanRecommend.tblRecommendTitle.length;j++)
					tblData[i][j]=recommends.get(i).getCell(j);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.RecommendTable.validate();
			this.RecommendTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmRecommend(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadRecommendTable(FrmMain.curStudent);
		this.getContentPane().add(new JScrollPane(this.RecommendTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(1400, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

 

}

