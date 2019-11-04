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
import cn.edu.zucc.personplan.util.BaseException;

public class FrmAddMajor extends JDialog implements ActionListener {
	public BeanSchool school=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");
	private JLabel labelID = new JLabel("רҵID��");
	private JLabel labelName = new JLabel("רҵ���ƣ�");
	private JLabel labelType = new JLabel("רҵ���");
	private JComboBox Majortype= new JComboBox(new String[] { "����", "ҽѧ", "��ѧ", "��ѧ", "��ʷ", "��ѧ"});
	private JLabel labelAdvantage = new JLabel("�Ƿ�Ϊ����רҵ��");
	private JComboBox advantage= new JComboBox(new String[] { "��", "��"});
	

	private JTextField edtID = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	public FrmAddMajor(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelID.setBounds(10, 8, 96, 15);
		workPane.add(labelID);
		edtID.setBounds(129, 5, 126, 21);
		workPane.add(edtID);
		labelName.setBounds(10, 45, 96, 15);
		workPane.add(labelName);
		edtName.setBounds(129, 42, 126, 21);
		workPane.add(edtName);
		labelType.setBounds(10, 76, 96, 15);
		workPane.add(labelType);
		Majortype.setBounds(179, 73, 76, 21);
		workPane.add(Majortype);
		labelAdvantage.setBounds(10, 112, 126, 15);
		workPane.add(labelAdvantage);	
		advantage.setBounds(179, 109, 76, 21);
		workPane.add(advantage);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(360, 347);
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
			int ID=Integer.valueOf(this.edtID.getText());
			String name=this.edtName.getText();
			String majortype=this.Majortype.getSelectedItem().toString();
			String advantage=this.advantage.getSelectedItem().toString();
			try {
				PersonPlanUtil.majorManager.addMajor(school, ID, name, majortype, advantage);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}
