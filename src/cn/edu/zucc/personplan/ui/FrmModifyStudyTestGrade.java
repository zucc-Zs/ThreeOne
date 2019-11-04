package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Beans;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmModifyStudyTestGrade extends JDialog implements ActionListener {
	public BeanStudent student = null;
	public BeanStudyTest studyTest = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelSubject = new JLabel("科目：");
	private JComboBox Gradetype= new JComboBox(new String[] { "数学", "语文", "英语", "物理", "化学", "生物", "政治", "历史", "地理", "技术"});
	private JLabel labelGrade = new JLabel("等级：");
	

	private JTextField edtGrade = new JTextField(20);

	public FrmModifyStudyTestGrade(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelSubject.setBounds(28, 11, 61, 15);
		workPane.add(labelSubject);
		Gradetype.setBounds(141, 8, 126, 21);
		workPane.add(Gradetype);
		labelGrade.setBounds(28, 72, 61, 15);
		workPane.add(labelGrade);
		edtGrade.setBounds(141, 69, 126, 21);
		workPane.add(edtGrade);

		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 210);
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
			
			String grade=this.edtGrade.getText();
			String subject=this.Gradetype.getSelectedItem().toString();
			int subjectId = 0;
			if(subject.equals("数学"))	subjectId = 1;
			else if(subject.equals("语文"))	subjectId = 2;
			else if(subject.equals("英语"))	subjectId = 3;
			else if(subject.equals("物理"))	subjectId = 4;
			else if(subject.equals("化学"))	subjectId = 5;
			else if(subject.equals("生物"))	subjectId = 6;
			else if(subject.equals("政治"))	subjectId = 7;
			else if(subject.equals("历史"))	subjectId = 8;
			else if(subject.equals("地理"))	subjectId = 9;
			else if(subject.equals("技术"))	subjectId = 10;

			
			try {
				PersonPlanUtil.studyTestManager.modify(studyTest, subjectId, grade);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}
