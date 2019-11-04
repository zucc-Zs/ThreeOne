package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmAddSchoolPlan extends JDialog implements ActionListener {
	public BeanSchool school = null;
	public BeanSchoolPlan schoolPlan = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelAsk = new JLabel("报名要求：");
	private JTextField edtAsk = new JTextField(20);
	private JLabel labelStartTime = new JLabel("报名开始时间：");
	private JTextField edtStartTime = new JTextField(20);
	private JLabel labelEndTime = new JLabel("报名结束时间：");
	private JTextField edtEndTime = new JTextField(20);
	private JLabel labelMethod = new JLabel("报名方式：");
	private JComboBox cmbMethod= new JComboBox(new String[] { "官网报名", "现场报名", "电话报名"});

	public FrmAddSchoolPlan(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelAsk.setBounds(11, 8, 93, 15);
		workPane.add(labelAsk);
		edtAsk.setBounds(127, 5, 126, 21);
		workPane.add(edtAsk);
		labelStartTime.setBounds(11, 48, 93, 15);
		workPane.add(labelStartTime);
		edtStartTime.setBounds(127, 84, 126, 21);
		workPane.add(edtStartTime);
		labelEndTime.setBounds(11, 87, 93, 15);
		workPane.add(labelEndTime);
		edtEndTime.setBounds(127, 45, 126, 21);
		workPane.add(edtEndTime);
		labelMethod.setBounds(11, 129, 93, 15);
		workPane.add(labelMethod);
		cmbMethod.setBounds(152, 126, 101, 21);
		workPane.add(cmbMethod);

		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 319);
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
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			
			String ask=this.edtAsk.getText();
			String startTime=this.edtStartTime.getText();
			String endTime=this.edtEndTime.getText();
			String method=this.cmbMethod.getSelectedItem().toString();
			
			try {
				PersonPlanUtil.schoolPlanManager.add(school, ask, startTime, endTime, method);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}

