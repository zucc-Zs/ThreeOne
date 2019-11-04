package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmAddStudent extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelId = new JLabel("身份证号");
	private JLabel labelTestId = new JLabel("准考证号");
	private JLabel labelName = new JLabel("姓名");
	private JLabel labelSex = new JLabel("性别");
	private JComboBox cmbSex= new JComboBox(new String[] { "男", "女" });
	private JLabel labelPhoneNumber = new JLabel("手机号码");
	private JLabel labelMiddleSchool = new JLabel("所在中学");
	private JLabel labelProvince = new JLabel("省份");
	private JComboBox cmbProvince= new JComboBox(new String[] { "浙江", "上海", "四川", "北京", "广东", "湖北", "哈尔滨" });

	
	private JTextField edtId = new JTextField(20);
	private JTextField edtTestId = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtPhoneNumber = new JTextField(20);
	private JTextField edtMiddleSchool = new JTextField(20);
	
	public FrmAddStudent(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelId.setBounds(18, 8, 68, 15);
		workPane.add(labelId);
		edtId.setBounds(124, 5, 126, 21);
		workPane.add(edtId);
		labelTestId.setBounds(18, 42, 68, 15);
		workPane.add(labelTestId);
		edtTestId.setBounds(124, 39, 126, 21);
		workPane.add(edtTestId);
		labelName.setBounds(18, 85, 68, 15);
		workPane.add(labelName);
		edtName.setBounds(124, 82, 126, 21);
		workPane.add(edtName);		
		labelSex.setBounds(18, 116, 68, 15);
		workPane.add(labelSex);
		cmbSex.setBounds(124, 113, 54, 21);
		workPane.add(cmbSex);
		labelPhoneNumber.setBounds(18, 147, 68, 15);
		workPane.add(labelPhoneNumber);
		edtPhoneNumber.setBounds(124, 144, 126, 21);
		workPane.add(edtPhoneNumber);
		labelMiddleSchool.setBounds(18, 178, 68, 15);
		workPane.add(labelMiddleSchool);
		edtMiddleSchool.setBounds(124, 175, 126, 21);
		workPane.add(edtMiddleSchool);
		labelProvince.setBounds(18, 209, 68, 15);
		workPane.add(labelProvince);
		cmbProvince.setBounds(124, 206, 126, 21);
		workPane.add(cmbProvince);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(280, 400);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String Id= this.edtId.getText();
			int TestId= Integer.valueOf(this.edtTestId.getText());
			String Name= this.edtName.getText();
			String Province = this.cmbProvince.getSelectedItem().toString();
			String Sex = this.cmbSex.getSelectedItem().toString();
			String MiddleSchool = this.edtMiddleSchool.getText();
			String PhoneNumber = this.edtPhoneNumber.getText();
			try {
				PersonPlanUtil.studentManager.addStudent(Id, TestId, Name, Sex, PhoneNumber, MiddleSchool, Province);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	
	
}
