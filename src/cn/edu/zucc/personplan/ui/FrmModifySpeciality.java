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
import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;

public class FrmModifySpeciality extends JDialog implements ActionListener {
	public BeanSpeciality speciality = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelType = new JLabel("类别：");
	private JComboBox cmbType= new JComboBox(new String[] { "体育类", "音乐类", "文学类"});
	private JLabel labelGrade = new JLabel("等级：");
	private JComboBox cmbGrade= new JComboBox(new String[] { "国家级", "省级", "校级"});
	private JLabel labelEvidence = new JLabel("佐证材料：");
	

	private JTextField edtEvidence = new JTextField(20);

	public FrmModifySpeciality(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelType.setBounds(23, 8, 89, 15);
		workPane.add(labelType);
		cmbType.setBounds(153, 5, 126, 21);
		workPane.add(cmbType);
		labelGrade.setBounds(23, 54, 89, 15);
		workPane.add(labelGrade);
		cmbGrade.setBounds(153, 51, 126, 21);
		workPane.add(cmbGrade);
		labelEvidence.setBounds(23, 122, 89, 15);
		workPane.add(labelEvidence);
		edtEvidence.setBounds(153, 119, 126, 21);
		workPane.add(edtEvidence);

		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(387, 265);
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
			
			String evidence=this.edtEvidence.getText();
			String type=this.cmbType.getSelectedItem().toString();
			String grade=this.cmbGrade.getSelectedItem().toString();
			
			try {
				PersonPlanUtil.specialityManager.modify(speciality, type, grade, evidence);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}

