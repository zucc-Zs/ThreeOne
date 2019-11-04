package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("注册");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelUserID = new JLabel("编号");
	private JLabel labelUserName = new JLabel("姓名");
	private JLabel labelPwd = new JLabel("密码");
	private JLabel labelPwd2 = new JLabel("确认密码");
	private JTextField edtUserID= new JTextField(20);
	private JTextField edtUserName = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "员工"});
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelUserID.setBounds(17, 22, 48, 15);
		workPane.add(labelUserID);
		edtUserID.setBounds(86, 19, 126, 21);
		workPane.add(edtUserID);
		labelUserName.setBounds(17, 61, 48, 15);
		workPane.add(labelUserName);
		edtUserName.setBounds(86, 58, 126, 21);
		workPane.add(edtUserName);
		labelPwd.setBounds(17, 107, 48, 15);
		workPane.add(labelPwd);
		edtPwd.setBounds(86, 104, 126, 21);
		workPane.add(edtPwd);
		labelPwd2.setBounds(17, 145, 59, 15);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(86, 142, 126, 21);
		workPane.add(edtPwd2);
		cmbUsertype.setBounds(88, 186, 124, 21);
		workPane.add(cmbUsertype);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(312, 400);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String userid=this.edtUserID.getText();
			String username=this.edtUserName.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			String usertype=this.cmbUsertype.getSelectedItem().toString();
			try {
				BeanUser user=PersonPlanUtil.userManager.reg(userid,username,pwd1,pwd2,usertype);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}
