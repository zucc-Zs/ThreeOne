package cn.edu.zucc.personplan.ui;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmModifySchool extends JDialog implements ActionListener {
	public BeanSchool school = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelId = new JLabel("           ID           ");
	private JLabel labelName = new JLabel("           名称           ");
	private JLabel labelProvince = new JLabel("           省份           ");
	private JComboBox cmbProvince= new JComboBox(new String[] { "浙江", "上海", "四川", "北京", "广东", "湖北"});
	private JLabel labelEmpty = new JLabel("                         ");
	private JLabel labelCity = new JLabel("           城市           ");
	private JLabel labelEvaluate = new JLabel("           描述           ");
	
	private JTextField edtId = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtCity = new JTextField(20);
	private JTextField edtEvaluate = new JTextField(20);
	
	public FrmModifySchool(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelId);
		workPane.add(edtId);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelProvince);
		workPane.add(cmbProvince);
		workPane.add(labelEmpty);
		workPane.add(labelCity);
		workPane.add(edtCity);
		workPane.add(labelEvaluate);
		workPane.add(edtEvaluate);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 400);
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
			int Id= Integer.valueOf(this.edtId.getText());
			String Name= this.edtName.getText();
			String Province = this.cmbProvince.getSelectedItem().toString();
			String City = this.edtCity.getText();
			String Evaluate = this.edtEvaluate.getText();
			try {
				PersonPlanUtil.schoolManager.modifySchool(school, Name, Province, City, Evaluate);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
			
		
	}

}

