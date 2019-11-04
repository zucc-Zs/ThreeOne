package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class FrmAdvantage extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnSearch = new JButton("搜索");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("名称");
	private JTextField edtName = new JTextField(20);
	
	List<BeanSchool> allSchool=null;
	List<BeanMajor> allMajor=null;
	List<BeanStudent> allStudent=null;
	
	private Object tblSchoolTitle[]=BeanSchool.tblSchoolTitle;
	private Object tblSchoolData[][];
	DefaultTableModel tabSchoolModel=new DefaultTableModel();
	private JTable dataTableSchool=new JTable(tabSchoolModel);
	


	
	public FrmAdvantage(JFrame f, String s, boolean b) {
		super(f, s, b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnSearch);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		workPane.add(labelName);
		workPane.add(edtName);		
		this.getContentPane().add(workPane, BorderLayout.NORTH);
		this.setSize(1000, 800);
		
		//屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.repaint();
		this.btnSearch.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	
	private void reSearchSchoolTable(String name){//这是测试数据，需要用实际数替换
		try {
			allSchool=PersonPlanUtil.majorManager.advantageSchool(name);
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


	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnSearch){
			String name= this.edtName.getText();
			this.getContentPane().add(new JScrollPane(this.dataTableSchool), BorderLayout.CENTER);
			this.reSearchSchoolTable(name);
			this.setVisible(true);
		}
		
	}
	
	
}
