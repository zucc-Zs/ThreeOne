package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmSearch extends JFrame implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnSearch = new JButton("搜索");
	private JButton btnCancel = new JButton("取消");
	private JButton btnSchoolPlan = new JButton("报名信息");
	private JButton btnMajorPlan = new JButton("招生计划");
	private JButton btnSpeciality = new JButton("学生特长");
	private JLabel labelName = new JLabel("关键字");
	private JTextField edtName = new JTextField(20);
	private JLabel labelType = new JLabel("查询类别");
	private JComboBox cmbType= new JComboBox(new String[] { "学校", "专业", "学生"});
	
	List<BeanSchool> allSchool=null;
	List<BeanMajor> allMajor=null;
	List<BeanStudent> allStudent=null;
	
	private Object tblSchoolTitle[]=BeanSchool.tblSchoolTitle;
	private Object tblSchoolData[][];
	DefaultTableModel tabSchoolModel=new DefaultTableModel();
	private JTable dataTableSchool=new JTable(tabSchoolModel);
	
	private Object tblMajorTitle[]=BeanMajor.tblMajorTitle;
	private Object tblMajorData[][];
	DefaultTableModel tabMajorModel=new DefaultTableModel();
	private JTable dataTableMajor=new JTable(tabMajorModel);
	
	private Object tblStudentTitle[]=BeanStudent.tblStudentTitle;
	private Object tblStudentData[][];
	DefaultTableModel tabStudentModel=new DefaultTableModel();
	private JTable dataTableStudent=new JTable(tabStudentModel);
	
	static BeanSchool curSchool=null;
	static BeanMajor curMajor=null;
	static BeanStudent curStudent=null;
	
	public FrmSearch(JFrame f, String s, boolean b) {
		super();
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnSearch);
		toolBar.add(btnCancel);
		this.setTitle("查询搜索");
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		workPane.add(labelName);
		workPane.add(edtName);		
		workPane.add(labelType);
		workPane.add(cmbType);

		
		this.getContentPane().add(workPane, BorderLayout.NORTH);
		this.setSize(1000, 800);
		
		this.dataTableStudent.addMouseListener(new MouseAdapter (){
    		
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		int i=FrmSearch.this.dataTableStudent.getSelectedRow();
	    		if(i<0) {
	    			return;
	    		}
	    		curStudent=allStudent.get(i);
	    	}
	    	
	    });
		
		this.dataTableMajor.addMouseListener(new MouseAdapter (){
    		
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		int i=FrmSearch.this.dataTableMajor.getSelectedRow();
	    		if(i<0) {
	    			return;
	    		}
	    		curMajor=allMajor.get(i);
	    	}
	    	
	    });
		
		this.dataTableSchool.addMouseListener(new MouseAdapter (){
    		
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		int i=FrmSearch.this.dataTableSchool.getSelectedRow();
	    		if(i<0) {
	    			return;
	    		}
	    		curSchool=allSchool.get(i);
	    	}
	    	
	    });
		
	    // 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnSearch.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnSchoolPlan.addActionListener(this);
		this.btnMajorPlan.addActionListener(this);
		this.btnSpeciality.addActionListener(this);
		
	}
	
	private void reSearchSchoolTable(String name){//这是测试数据，需要用实际数替换
		try {
			allSchool=PersonPlanUtil.schoolManager.searchSchool(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblSchoolData =  new Object[allSchool.size()][BeanSchool.tblSchoolTitle.length];
		for(int i=0;i<allSchool.size();i++){
			for(int j=0;j<BeanSchool.tblSchoolTitle.length;j++)
				tblSchoolData[i][j]=allSchool.get(i).getCell(j);
		}
		tabSchoolModel.setDataVector(tblSchoolData,tblSchoolTitle);
		this.dataTableSchool.validate();
		this.dataTableSchool.repaint();
	}
	
	private void reSearchStudentTable(String name){
		try {
			allStudent=PersonPlanUtil.studentManager.searchStudent(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStudentData =  new Object[allStudent.size()][BeanStudent.tblStudentTitle.length];
		for(int i=0;i<allStudent.size();i++){
			for(int j=0;j<BeanStudent.tblStudentTitle.length;j++)
				tblStudentData[i][j]=allStudent.get(i).getCell(j);
		}
		tabStudentModel.setDataVector(tblStudentData,tblStudentTitle);
		this.dataTableStudent.validate();
		this.dataTableStudent.repaint();
	}
	
	private void reSearchMajorTable(String name){
		try {
			allMajor=PersonPlanUtil.majorManager.searchMajor(name);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMajorData =  new Object[allMajor.size()][BeanMajor.tblMajorTitle.length];
		for(int i=0;i<allMajor.size();i++){
			for(int j=0;j<BeanMajor.tblMajorTitle.length;j++)
				tblMajorData[i][j]=allMajor.get(i).getCell(j);
		}
		tabMajorModel.setDataVector(tblMajorData,tblMajorTitle);
		this.dataTableMajor.validate();
		this.dataTableMajor.repaint();
	}
	
	JScrollPane paneSchool = new JScrollPane(this.dataTableSchool);
	JScrollPane paneMajor = new JScrollPane(this.dataTableMajor);
	JScrollPane paneStudent = new JScrollPane(this.dataTableStudent);
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnSearch){
			String type = this.cmbType.getSelectedItem().toString();
			String name= this.edtName.getText();
			if(type.equals("学校")) {
				this.getContentPane().remove(paneMajor);
				this.getContentPane().remove(paneStudent);
				this.getContentPane().add(paneSchool, BorderLayout.CENTER);
				this.reSearchSchoolTable(name);
				workPane.remove(btnMajorPlan);
				workPane.remove(btnSpeciality);
				workPane.add(btnSchoolPlan);
				
			}
			else if(type.equals("专业")) {
				this.getContentPane().remove(paneStudent);
				this.getContentPane().remove(paneSchool);
				this.getContentPane().add(paneMajor, BorderLayout.CENTER);
				this.reSearchMajorTable(name);
				workPane.remove(btnSchoolPlan);
				workPane.remove(btnSpeciality);
				workPane.add(btnMajorPlan);
			}
			else if(type.equals("学生")) {
				this.getContentPane().remove(paneMajor);
				this.getContentPane().remove(paneSchool);
				this.getContentPane().add(paneStudent, BorderLayout.CENTER);
				this.reSearchStudentTable(name);
				workPane.remove(btnSchoolPlan);
				workPane.remove(btnMajorPlan);
				workPane.add(btnSpeciality);
			}
			this.setVisible(true);
		}
		else if(e.getSource()==this.btnSchoolPlan){
			int i=FrmSearch.this.dataTableSchool.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSchoolPlan dlg=new FrmSchoolPlan(this,"报名信息",true);
			dlg.school=curSchool;
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnMajorPlan){
			int i=FrmSearch.this.dataTableMajor.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmMajorPlan dlg=new FrmMajorPlan(this,"招生计划",true);
			dlg.major=curMajor;
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnSpeciality){
			int i=FrmSearch.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSpeciality2 dlg=new FrmSpeciality2(this,"学生特长",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
		}
	}

}
