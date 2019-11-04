package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmModifySchoolPlan extends JDialog implements ActionListener {
	public BeanSchool school = null;
	public BeanSchoolPlan schoolPlan = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");

	private JLabel labelAsk = new JLabel("����Ҫ��");
	private JTextField edtAsk = new JTextField(20);
	private JLabel labelStartTime = new JLabel("������ʼʱ�䣺");
	private JTextField edtStartTime = new JTextField(20);
	private JLabel labelEndTime = new JLabel("��������ʱ�䣺");
	private JTextField edtEndTime = new JTextField(20);
	private JLabel labelMethod = new JLabel("������ʽ��");
	private JComboBox cmbMethod= new JComboBox(new String[] { "��������", "�ֳ�����", "�绰����"});

	public FrmModifySchoolPlan(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelAsk.setBounds(30, 8, 98, 15);
		workPane.add(labelAsk);
		edtAsk.setBounds(157, 5, 126, 21);
		workPane.add(edtAsk);
		labelStartTime.setBounds(30, 52, 98, 15);
		workPane.add(labelStartTime);
		edtStartTime.setBounds(157, 49, 126, 21);
		workPane.add(edtStartTime);
		labelEndTime.setBounds(30, 93, 98, 15);
		workPane.add(labelEndTime);
		edtEndTime.setBounds(157, 90, 126, 21);
		workPane.add(edtEndTime);
		labelMethod.setBounds(30, 118, 80, 15);
		workPane.add(labelMethod);
		cmbMethod.setBounds(157, 115, 126, 21);
		workPane.add(cmbMethod);

		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 251);
		// ��Ļ������ʾ
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
				PersonPlanUtil.schoolPlanManager.modify(schoolPlan, ask, startTime, endTime, method);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}

