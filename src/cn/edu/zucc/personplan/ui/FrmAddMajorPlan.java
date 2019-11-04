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

import com.sun.prism.paint.Gradient;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmAddMajorPlan extends JDialog implements ActionListener {
	public BeanMajor major = null;
	public BeanMajorPlan majorPlan = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelYear = new JLabel("年份：");
	private JTextField edtYear = new JTextField(20);
	private JLabel labelSubject = new JLabel("选考科目：");
//	private JTextField edtSubject = new JTextField(20);
	private JComboBox Gradetype1= new JComboBox(new String[] { "", "物理", "化学", "生物", "政治", "历史", "地理", "技术"});
	private JComboBox Gradetype2= new JComboBox(new String[] { "", "物理", "化学", "生物", "政治", "历史", "地理", "技术"});
	private JComboBox Gradetype3= new JComboBox(new String[] { "", "物理", "化学", "生物", "政治", "历史", "地理", "技术"});
	private JLabel labelGrade = new JLabel("分数：");
	private JTextField edtGrade = new JTextField(20);
	private JLabel labelNum = new JLabel("计划数：");
	private JTextField edtNum = new JTextField(20);
	private JLabel labelTuition = new JLabel("学费：");
	private JTextField edtTuition = new JTextField(20);
	private JLabel labelAsk = new JLabel("其他要求：");
	private JTextField edtAsk = new JTextField(20);

	public FrmAddMajorPlan(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelYear.setBounds(10, 8, 60, 15);
		workPane.add(labelYear);
		edtYear.setBounds(107, 5, 187, 21);
		workPane.add(edtYear);
		labelSubject.setBounds(10, 36, 87, 15);
		workPane.add(labelSubject);
		Gradetype1.setBounds(107, 34, 52, 19);
		workPane.add(Gradetype1);
		Gradetype2.setBounds(169, 34, 55, 19);
		workPane.add(Gradetype2);
		Gradetype3.setBounds(234, 34, 60, 19);
		workPane.add(Gradetype3);
		labelGrade.setBounds(10, 63, 60, 15);
		workPane.add(labelGrade);
		edtGrade.setBounds(107, 60, 187, 21);
		workPane.add(edtGrade);
		labelNum.setBounds(10, 88, 60, 15);
		workPane.add(labelNum);
		edtNum.setBounds(107, 83, 187, 21);
		workPane.add(edtNum);
		labelTuition.setBounds(10, 112, 60, 15);
		workPane.add(labelTuition);
		edtTuition.setBounds(107, 109, 187, 21);
		workPane.add(edtTuition);
		labelAsk.setBounds(10, 143, 87, 15);
		workPane.add(labelAsk);
		edtAsk.setBounds(107, 140, 187, 21);
		workPane.add(edtAsk);

		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(356, 337);
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
			String subject1=this.Gradetype1.getSelectedItem().toString();
			String subject2=this.Gradetype2.getSelectedItem().toString();
			String subject3=this.Gradetype3.getSelectedItem().toString();
			if(subject1.equals("")&&subject2.equals("")&&subject3.equals("")) {
				try {
					throw new BaseException("至少选择一门选考科目!");
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(subject1.equals(subject2)||subject2.equals(subject3)||subject3.equals(subject1)) {
				try {
					throw new BaseException("选考科目不得重复！");
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			String subject = subject1+" "+subject2+" "+subject3;
			String num=this.edtNum.getText();
			String tuition=this.edtTuition.getText();
			String grade=this.edtGrade.getText();
			int year=Integer.valueOf(this.edtYear.getText());
			
			try {
				PersonPlanUtil.majorPlanManager.add(major, year, subject, num, tuition, ask, grade);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}

