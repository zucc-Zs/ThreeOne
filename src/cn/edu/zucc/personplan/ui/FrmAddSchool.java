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


public class FrmAddSchool extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelId = new JLabel("ID");
	private JLabel labelName = new JLabel("名称 ");
	private JLabel labelProvince = new JLabel("省份 ");
	private JComboBox cmbProvince= new JComboBox(new String[] { "浙江", "上海", "四川", "北京", "广东", "湖北"});
	private JLabel labelCity = new JLabel("城市");
	private JLabel labelEvaluate = new JLabel("描述");
	
	private JTextField edtId = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtCity = new JTextField(20);
	private JTextField edtEvaluate = new JTextField(20);
	
	public FrmAddSchool(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelId.setBounds(6, 8, 144, 15);
		workPane.add(labelId);
		edtId.setBounds(155, 5, 126, 21);
		workPane.add(edtId);
		labelName.setBounds(6, 54, 156, 15);
		workPane.add(labelName);
		edtName.setBounds(155, 51, 126, 21);
		workPane.add(edtName);
		labelProvince.setBounds(6, 103, 156, 15);
		workPane.add(labelProvince);
		cmbProvince.setBounds(155, 100, 126, 21);
		workPane.add(cmbProvince);
		labelCity.setBounds(6, 146, 156, 15);
		workPane.add(labelCity);
		edtCity.setBounds(155, 143, 126, 21);
		workPane.add(edtCity);
		labelEvaluate.setBounds(6, 205, 156, 15);
		workPane.add(labelEvaluate);
		edtEvaluate.setBounds(155, 202, 126, 21);
		workPane.add(edtEvaluate);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(362, 400);
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
			int Id= Integer.valueOf(this.edtId.getText());
			String Name= this.edtName.getText();
			String Province = this.cmbProvince.getSelectedItem().toString();
			String City = this.edtCity.getText();
			String Evaluate = this.edtEvaluate.getText();
			try {
				PersonPlanUtil.schoolManager.addSchool(Id, Name, Province, City, Evaluate);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	
	
}
