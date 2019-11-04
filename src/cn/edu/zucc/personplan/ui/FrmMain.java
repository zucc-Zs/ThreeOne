package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.omg.CORBA.Current;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanRecommend;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;



public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_school=new JMenu("学校管理");
    private JMenu menu_major=new JMenu("专业管理");
    private JMenu menu_student=new JMenu("考生管理");
    private JMenu menu_user=new JMenu("用户管理");
    private JMenu menu_grade=new JMenu("成绩管理");
    private JMenu menu_statistics=new JMenu("查询统计");
    private JMenu menu_more=new JMenu("推荐功能");
    
    private JMenuItem  menuItem_AddSchool=new JMenuItem("添加学校");
    private JMenuItem  menuItem_ModifySchool=new JMenuItem("修改学校");
    private JMenuItem  menuItem_DeleteSchool=new JMenuItem("删除学校");
    private JMenuItem  menuItem_SchoolPlan=new JMenuItem("报名信息");
    
    private JMenuItem  menuItem_AddMajor=new JMenuItem("添加专业");
    private JMenuItem  menuItem_ModifyMajor=new JMenuItem("修改专业");
    private JMenuItem  menuItem_DeleteMajor=new JMenuItem("删除专业");
    private JMenuItem  menuItem_MajorPlan=new JMenuItem("招生计划");
    
    
    private JMenuItem  menuItem_AddStudent=new JMenuItem("添加考生");
    private JMenuItem  menuItem_ModifyStudent=new JMenuItem("修改考生");
    private JMenuItem  menuItem_DeleteStudent=new JMenuItem("删除考生");
    private JMenuItem  menuItem_Speciality=new JMenuItem("考生特长");
    
    private JMenuItem  menuItem_DeleteUser=new JMenuItem("删除用户");
    private JMenuItem  menuItem_ResetPwd=new JMenuItem("重置密码");
    private JMenuItem  menuItem_ModifyPwd=new JMenuItem("修改密码");
    
    private JMenuItem  menuItem_AddStudyTestGrade=new JMenuItem("添加学考成绩");
    private JMenuItem  menuItem_ModifyStudyTestGrade=new JMenuItem("修改学考成绩");
    private JMenuItem  menuItem_DeleteStudyTestGrade=new JMenuItem("删除学考成绩");
    private JMenuItem  menuItem_AddSelectTestGrade=new JMenuItem("添加选考成绩");
    private JMenuItem  menuItem_ModifySelectTestGrade=new JMenuItem("修改选考成绩");
    private JMenuItem  menuItem_DeleteSelectTestGrade=new JMenuItem("删除选考成绩");
    
    private JMenuItem  menuItem_Statistics=new JMenuItem("查询搜索");
    
    private JMenuItem  menuItem_Recommend=new JMenuItem("智能推荐");
    private JMenuItem  menuItem_AdvantageQuery=new JMenuItem("优势查询");

    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblSchoolTitle[]=BeanSchool.tblSchoolTitle;
	private Object tblSchoolData[][];
	DefaultTableModel tabSchoolModel=new DefaultTableModel();
	private JTable dataTableSchool=new JTable(tabSchoolModel);
	
	
	private Object tblMajorTitle[]=BeanMajor.tblMajorTitle;
	private Object tblMajorData[][];
	DefaultTableModel tabMajorModel=new DefaultTableModel();
	private JTable dataTableMajor=new JTable(tabMajorModel);
	
	private Object tblUserTitle[]=BeanUser.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	private JTable dataTableUser=new JTable(tabUserModel);
	
	private Object tblSubjectTitle[]=BeanSubject.tblSubjectTitle;
	private Object tblSubjectData[][];
	DefaultTableModel tabSubjectModel=new DefaultTableModel();
	private JTable dataTableSubject=new JTable(tabSubjectModel);
	
	private Object tblStudentTitle[]=BeanStudent.tblStudentTitle;
	private Object tblStudentData[][];
	DefaultTableModel tabStudentModel=new DefaultTableModel();
	private JTable dataTableStudent=new JTable(tabStudentModel);
	
	private Object tblStudyTestTitle[]=BeanStudyTest.tblStudyTestTitle;
	private Object tblStudyTestData[][];
	DefaultTableModel tabStudyTestModel=new DefaultTableModel();
	private JTable dataTableStudyTest=new JTable(tabStudyTestModel);
	
	private Object tblSelectTestTitle[]=BeanSelectTest.tblSelectTestTitle;
	private Object tblSelectTestData[][];
	DefaultTableModel tabSelectTestModel=new DefaultTableModel();
	private JTable dataTableSelectTest=new JTable(tabSelectTestModel);
	
	private BeanUser curUser=null;
	List<BeanUser> allUser=null;
	private BeanSubject curSubject=null;
	List<BeanSubject> allSubject=null;
	static BeanStudent curStudent=null;
	List<BeanStudent> allStudent=null;
	static BeanSchool curSchool=null;
	static BeanMajor curMajor=null;
	List<BeanSchool> allSchool=null;
	List<BeanMajor> allMajor=null;
	List<BeanMajor> schoolMajors=null;
	List<BeanStudyTest> studentStudyTests=null;
	List<BeanSelectTest> studentSelectTests=null;
	
	
	public static BeanStudent getCurStudent() {
		return curStudent;
	}

	public static void setCurStudent(BeanStudent curStudent) {
		FrmMain.curStudent = curStudent;
	}

	public static BeanSchool getCurSchool() {
		return curSchool;
	}

	public static void setCurSchool(BeanSchool curSchool) {
		FrmMain.curSchool = curSchool;
	}

	public static BeanMajor getCurMajor() {
		return curMajor;
	}

	public static void setCurMajor(BeanMajor curMajor) {
		FrmMain.curMajor = curMajor;
	}

//	private void PicDemo(){
//
//		 JPanel main = new JPanel();
//		 JPanel p = new JPanel();
//
//		 JLabel l=new JLabel();
//		 Icon icon=new ImageIcon("C:/timg.jpg"); 
//		 l.setIcon(icon);
//		 
//		 l.setBounds(10, 10,main.getWidth(),main.getHeight());
//		 l.setDuration(3000);
//		 p.add(l,new Integer(Integer.MIN_VALUE));
//
//		 this.setVisible(true);
//
//		 this.add(main);
//		 main.add(p);
//		 //this.pack(); 
//	}
	
	private void reloadSchoolTable(){//这是测试数据，需要用实际数替换
		try {
			allSchool=PersonPlanUtil.schoolManager.loadAllSchool();
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
	
	private void reloadSchoolMajorTabel(int schoolIdx){
		if(schoolIdx<0) return;
		curSchool=allSchool.get(schoolIdx);
		try {
			schoolMajors=PersonPlanUtil.majorManager.loadMajors(curSchool);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMajorData =new Object[schoolMajors.size()][BeanMajor.tblMajorTitle.length];
		for(int i=0;i<schoolMajors.size();i++){
			for(int j=0;j<BeanMajor.tblMajorTitle.length;j++)
				tblMajorData[i][j]=schoolMajors.get(i).getCell(j);
		}
		
		tabMajorModel.setDataVector(tblMajorData,tblMajorTitle);
		this.dataTableMajor.validate();
		this.dataTableMajor.repaint();
	}
	
	private void reloadMajorTabel(int majorIdx){
		if(majorIdx<0) return;
		curMajor=schoolMajors.get(majorIdx);
	}
	
	
	private void reloadUserTable(){
		try {
			allUser=PersonPlanUtil.userManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[allUser.size()][BeanUser.tblUserTitle.length];
		for(int i=0;i<allUser.size();i++){
			for(int j=0;j<BeanUser.tblUserTitle.length;j++)
				tblUserData[i][j]=allUser.get(i).getCell(j);
		}
		tabUserModel.setDataVector(tblUserData,tblUserTitle);
		this.dataTableUser.validate();
		this.dataTableUser.repaint();
	}
	
	private void reloadSubjectTable(){
		try {
			allSubject=PersonPlanUtil.subjectManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblSubjectData =  new Object[allSubject.size()][BeanSubject.tblSubjectTitle.length];
		for(int i=0;i<allUser.size();i++){
			for(int j=0;j<BeanSubject.tblSubjectTitle.length;j++)
				tblSubjectData[i][j]=allSubject.get(i).getCell(j);
		}
		tabSubjectModel.setDataVector(tblSubjectData,tblSubjectTitle);
		this.dataTableSubject.validate();
		this.dataTableSubject.repaint();
	}
	
	private void reloadStudentTable(){
		try {
			allStudent=PersonPlanUtil.studentManager.loadAllStudent();
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

	private void reloadStudentStudyTestTabel(int studentIdx){
		if(studentIdx<0) return;
		curStudent=allStudent.get(studentIdx);
		try {
			studentStudyTests=PersonPlanUtil.studyTestManager.loadAll(curStudent);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStudyTestData =new Object[studentStudyTests.size()][BeanStudyTest.tblStudyTestTitle.length];
		for(int i=0;i<studentStudyTests.size();i++){
			for(int j=0;j<BeanStudyTest.tblStudyTestTitle.length;j++)
				tblStudyTestData[i][j]=studentStudyTests.get(i).getCell(j);
		}
		tabStudyTestModel.setDataVector(tblStudyTestData,tblStudyTestTitle);
		this.dataTableStudyTest.validate();
		this.dataTableStudyTest.repaint();
	}
	
	private void reloadStudentSelectTestTabel(int studentIdx){
		if(studentIdx<0) return;
		curStudent=allStudent.get(studentIdx);
		try {
			studentSelectTests=PersonPlanUtil.selectTestManager.loadAll(curStudent);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblSelectTestData =new Object[studentSelectTests.size()][BeanSelectTest.tblSelectTestTitle.length];
		for(int i=0;i<studentSelectTests.size();i++){
			for(int j=0;j<BeanSelectTest.tblSelectTestTitle.length;j++)
				tblSelectTestData[i][j]=studentSelectTests.get(i).getCell(j);
		}
		tabSelectTestModel.setDataVector(tblSelectTestData,tblSelectTestTitle);
		this.dataTableSelectTest.validate();
		this.dataTableSelectTest.repaint();
	}
	
	public FrmMain(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("三位一体系统");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
//		this.PicDemo();
		//菜单
		
	    this.menu_school.add(this.menuItem_AddSchool); this.menuItem_AddSchool.addActionListener(this);
	    this.menu_school.add(this.menuItem_ModifySchool); this.menuItem_ModifySchool.addActionListener(this);
	    this.menu_school.add(this.menuItem_DeleteSchool); this.menuItem_DeleteSchool.addActionListener(this);
	    this.menu_school.add(this.menuItem_SchoolPlan); this.menuItem_SchoolPlan.addActionListener(this);
	    
	    this.menu_major.add(this.menuItem_AddMajor); this.menuItem_AddMajor.addActionListener(this);
	    this.menu_major.add(this.menuItem_ModifyMajor); this.menuItem_ModifyMajor.addActionListener(this);
	    this.menu_major.add(this.menuItem_DeleteMajor); this.menuItem_DeleteMajor.addActionListener(this);
	    this.menu_major.add(this.menuItem_MajorPlan); this.menuItem_MajorPlan.addActionListener(this);
	    
	    this.menu_student.add(this.menuItem_AddStudent); this.menuItem_AddStudent.addActionListener(this);
	    this.menu_student.add(this.menuItem_ModifyStudent); this.menuItem_ModifyStudent.addActionListener(this);
	    this.menu_student.add(this.menuItem_DeleteStudent); this.menuItem_DeleteStudent.addActionListener(this);
	    this.menu_student.add(this.menuItem_Speciality); this.menuItem_Speciality.addActionListener(this);
	    
	    if("管理员".equals(BeanUser.currentLoginUser.getUser_grade())) {
	    	this.menu_user.add(this.menuItem_DeleteUser); this.menuItem_DeleteUser.addActionListener(this);
	    	this.menu_user.add(this.menuItem_ResetPwd); this.menuItem_ResetPwd.addActionListener(this);
	    }
	    
	    this.menu_user.add(this.menuItem_ModifyPwd); this.menuItem_ModifyPwd.addActionListener(this);
	    
	    this.menu_grade.add(this.menuItem_AddStudyTestGrade); this.menuItem_AddStudyTestGrade.addActionListener(this);
	    this.menu_grade.add(this.menuItem_ModifyStudyTestGrade); this.menuItem_ModifyStudyTestGrade.addActionListener(this);
	    this.menu_grade.add(this.menuItem_DeleteStudyTestGrade); this.menuItem_DeleteStudyTestGrade.addActionListener(this);
	    this.menu_grade.add(this.menuItem_AddSelectTestGrade); this.menuItem_AddSelectTestGrade.addActionListener(this);
	    this.menu_grade.add(this.menuItem_ModifySelectTestGrade); this.menuItem_ModifySelectTestGrade.addActionListener(this);
	    this.menu_grade.add(this.menuItem_DeleteSelectTestGrade); this.menuItem_DeleteSelectTestGrade.addActionListener(this);
	    
	    this.menu_statistics.add(this.menuItem_Statistics); this.menuItem_Statistics.addActionListener(this);
	    
	    this.menu_more.add(this.menuItem_Recommend); this.menuItem_Recommend.addActionListener(this);
	    this.menu_more.add(this.menuItem_AdvantageQuery); this.menuItem_AdvantageQuery.addActionListener(this);
	    
	    if("员工".equals(BeanUser.currentLoginUser.getUser_grade())) {
	    	menubar.add(menu_student);
	    	menubar.add(menu_user);
	    	menubar.add(menu_grade);
	    	menubar.add(menu_statistics);
	    	menubar.add(menu_more);
	    	
			this.setJMenuBar(menubar);
	    } 
		
	    
	    if("管理员".equals(BeanUser.currentLoginUser.getUser_grade())) {
	    	menubar.add(menu_user);
	    	menubar.add(menu_school);
	    	menubar.add(menu_major);
	    	menubar.add(menu_statistics);

	    	this.setJMenuBar(menubar);
	    }   
	    
	    
	    if("员工".equals(BeanUser.currentLoginUser.getUser_grade())) {
	    	JScrollPane paneStudent = new JScrollPane(this.dataTableStudent);
	    	paneStudent.setPreferredSize(new Dimension(960,0));
		   	this.getContentPane().add(paneStudent, BorderLayout.WEST);
		   	
		   	JScrollPane paneSelectTest = new JScrollPane(this.dataTableStudyTest);
		   	paneSelectTest.setPreferredSize(new Dimension(480,0));
		   	this.getContentPane().add(paneSelectTest, BorderLayout.EAST);
	
		   	this.getContentPane().add(new JScrollPane(this.dataTableSelectTest), BorderLayout.CENTER);
		   	
	    	this.dataTableStudent.addMouseListener(new MouseAdapter (){
	    		
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		int i=FrmMain.this.dataTableStudent.getSelectedRow();
		    		if(i<0) {
		    			return;
		    		}
		    		curStudent=allStudent.get(i);
		    		FrmMain.this.reloadStudentStudyTestTabel(i);
		    		FrmMain.this.reloadStudentSelectTestTabel(i);
		    	}
		    	
		    });
		    this.reloadStudentTable();
	    }
	    
	    if("管理员".equals(BeanUser.currentLoginUser.getUser_grade())) {
	    	
	    	this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.WEST);
	    	this.getContentPane().add(new JScrollPane(this.dataTableSchool), BorderLayout.CENTER);
	    	this.getContentPane().add(new JScrollPane(this.dataTableMajor), BorderLayout.EAST);
	    	
	    	this.dataTableSchool.addMouseListener(new MouseAdapter (){
	    		
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		int i=FrmMain.this.dataTableSchool.getSelectedRow();
		    		if(i<0) {
		    			return;
		    		}
		    		FrmMain.this.reloadSchoolMajorTabel(i);
		    	}
		    	
		    });
	    	
	    	this.dataTableMajor.addMouseListener(new MouseAdapter (){
	    		
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		int i=FrmMain.this.dataTableMajor.getSelectedRow();
		    		if(i<0) {
		    			return;
		    		}
		    		FrmMain.this.reloadMajorTabel(i);
		    	}
		    	
		    });
		    this.reloadUserTable();    
		    this.reloadSchoolTable();
	    }
		
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    JLabel label=new JLabel("您好! "+ BeanUser.currentLoginUser.getUser_name());//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_AddSchool){
			FrmAddSchool dlg=new FrmAddSchool(this,"添加学校",true);
			dlg.setVisible(true);
			reloadSchoolTable();
		}
		else if(e.getSource()==this.menuItem_ModifySchool){
			if(this.curSchool==null) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifySchool dlg=new FrmModifySchool(this,"修改学校",true);
			dlg.school=curSchool;
			dlg.setVisible(true);
			reloadSchoolTable();
		}
		else if(e.getSource()==this.menuItem_DeleteSchool){
			if(this.curSchool==null) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除学校吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.schoolManager.deleteSchool(curSchool);
					reloadSchoolTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		else if(e.getSource()==this.menuItem_SchoolPlan){
			int i=FrmMain.this.dataTableSchool.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSchoolPlan dlg=new FrmSchoolPlan(this,"报名信息",true);
			dlg.school=curSchool;
			dlg.setVisible(true);
		}
		
		else if(e.getSource()==this.menuItem_AddStudent){
			FrmAddStudent dlg=new FrmAddStudent(this,"添加考生",true);
			dlg.setVisible(true);
			reloadStudentTable();
		}
		else if(e.getSource()==this.menuItem_ModifyStudent){
			if(this.curStudent==null) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyStudent dlg=new FrmModifyStudent(this,"修改考生",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
			reloadStudentTable();
		}
		else if(e.getSource()==this.menuItem_DeleteStudent){
			if(this.curStudent==null) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除考生吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.studentManager.deleteStudent(this.curStudent);
					reloadStudentTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		else if(e.getSource()==this.menuItem_Speciality){
			int i=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmSpeciality dlg=new FrmSpeciality(this,"考生特长",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
		}
		
		else if(e.getSource()==this.menuItem_AddMajor){
			int i=FrmMain.this.dataTableSchool.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学校", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddMajor dlg=new FrmAddMajor(this,"添加专业",true);
			dlg.school=curSchool;
			dlg.setVisible(true);
			reloadSchoolMajorTabel(i);
			reloadSchoolTable();
		}
		else if(e.getSource()==this.menuItem_ModifyMajor){
			int i=FrmMain.this.dataTableMajor.getSelectedRow();
			int j=FrmMain.this.dataTableSchool.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择专业", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyMajor dlg=new FrmModifyMajor(this,"修改专业",true);
			dlg.major=this.schoolMajors.get(i);
			dlg.setVisible(true);
			
			reloadSchoolMajorTabel(j);

		}
		else if(e.getSource()==this.menuItem_DeleteMajor){
			int i=FrmMain.this.dataTableMajor.getSelectedRow();
			int j=FrmMain.this.dataTableSchool.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择专业", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除专业吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.majorManager.deleteMajor(this.schoolMajors.get(i));
					reloadSchoolMajorTabel(j);
					reloadSchoolTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		else if(e.getSource()==this.menuItem_MajorPlan){
			int i=FrmMain.this.dataTableMajor.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择专业", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmMajorPlan dlg=new FrmMajorPlan(this,"招生计划",true);
			dlg.major=curMajor;
			dlg.setVisible(true);
		}
		
		
		else if(e.getSource()==this.menuItem_ModifyPwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"密码修改",true);
			dlg.setVisible(true);
			reloadUserTable();
		}
		
		else if(e.getSource()==this.menuItem_ResetPwd){
			int i=FrmMain.this.dataTableUser.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定重置用户密码吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.userManager.resetPwd(this.allUser.get(i));
					reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		else if(e.getSource()==this.menuItem_DeleteUser){
			int i=FrmMain.this.dataTableUser.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除用户吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.userManager.delete(this.allUser.get(i));
					reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		else if(e.getSource()==this.menuItem_Statistics){
			FrmSearch dlg=new FrmSearch(this,"查询搜索",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_AdvantageQuery){
			FrmAdvantage dlg=new FrmAdvantage(this,"优势查询",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_Recommend){
			if(this.curStudent==null) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Session session=HibernateUtil.getSession();
			String hql = "from BeanRecommend s where s.student_id=:student_id";
			List<BeanRecommend> aList = new ArrayList<BeanRecommend>();
			org.hibernate.query.Query query =session.createQuery(hql);
			String student_id = curStudent.getStudent_id();
			query.setString("student_id",student_id);
			aList = query.list();
			if(aList.size()==0) {
				try {
					PersonPlanUtil.studentManager.recommend1(curStudent);
					PersonPlanUtil.studentManager.recommend2(curStudent);
					PersonPlanUtil.studentManager.recommend3(curStudent);
				}catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			FrmRecommend dlg=new FrmRecommend(this,"智能推荐",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
		}
		
		else if(e.getSource()==this.menuItem_AddStudyTestGrade){
			int i=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddStudyTestGrade dlg=new FrmAddStudyTestGrade(this,"添加学考成绩",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
			reloadStudentStudyTestTabel(i);
		}
		
		else if(e.getSource()==this.menuItem_AddSelectTestGrade){
			int i=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择考生", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmAddSelectTestGrade dlg=new FrmAddSelectTestGrade(this,"添加选考成绩",true);
			dlg.student=curStudent;
			dlg.setVisible(true);
			reloadStudentSelectTestTabel(i);
			reloadStudentStudyTestTabel(i);
		}
		
		else if(e.getSource()==this.menuItem_ModifyStudyTestGrade){
			int i=FrmMain.this.dataTableStudyTest.getSelectedRow();
			int j=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学考成绩", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyStudyTestGrade dlg=new FrmModifyStudyTestGrade(this,"修改学考成绩",true);
			dlg.studyTest=this.studentStudyTests.get(i);
			dlg.setVisible(true);
			
			reloadStudentStudyTestTabel(j);
		}
		
		else if(e.getSource()==this.menuItem_ModifySelectTestGrade){
			int i=FrmMain.this.dataTableSelectTest.getSelectedRow();
			int j=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择选考成绩", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifySelectTestGrade dlg=new FrmModifySelectTestGrade(this,"修改选考成绩",true);
			dlg.selectTest=this.studentSelectTests.get(i);
			dlg.setVisible(true);
			
			reloadStudentSelectTestTabel(j);
			reloadStudentStudyTestTabel(j);
		}
		
		else if(e.getSource()==this.menuItem_DeleteStudyTestGrade){
			int i=FrmMain.this.dataTableStudyTest.getSelectedRow();
			int j=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择学考成绩", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除学考成绩吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.studyTestManager.delete(this.studentStudyTests.get(i));
					reloadStudentStudyTestTabel(j);
					reloadSchoolTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		else if(e.getSource()==this.menuItem_DeleteSelectTestGrade){
			int i=FrmMain.this.dataTableSelectTest.getSelectedRow();
			int j=FrmMain.this.dataTableStudent.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择选考成绩", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除选考成绩吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PersonPlanUtil.selectTestManager.delete(this.studentSelectTests.get(i));
					reloadStudentSelectTestTabel(j);
					reloadSchoolTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

	}
}
