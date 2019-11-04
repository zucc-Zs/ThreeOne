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
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmModifyMajor2 extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");
	
	
	private BeanSchool curSchool=null;
	private JLabel newName = new JLabel("������");
	private JLabel newType = new JLabel("��רҵ���");
	private JLabel newAdvantage = new JLabel("������רҵ");
	private JTextField edtName = new JTextField(20);
	private JComboBox Majortype= new JComboBox(new String[] { "����", "ҽѧ", "��ѧ", "��ѧ", "��ʷ", "��ѧ"});
	private JComboBox advantage= new JComboBox(new String[] { "��", "��"});
	
	List<BeanSchool> allShool=null;
	public BeanSchool school;
	public BeanMajor major;
	public int order;
	public String change;
	
	public FrmModifyMajor2(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		if("�޸�����".equals(s)) {
			order = 1;
			workPane.add(newName);
			workPane.add(edtName);
			}
		if("�޸�רҵ���".equals(s)) {
			order = 2;
			workPane.add(newType);
			workPane.add(Majortype);
		}
		if("�޸�����רҵ".equals(s)) {
			order = 3;
			workPane.add(newAdvantage);
			workPane.add(advantage);
		}
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		// ��Ļ������ʾ
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
			try {
				if(order == 1)	change = this.edtName.getText();
				if(order == 2)	change = this.Majortype.getSelectedItem().toString();;
				if(order == 3)	change = this.advantage.getSelectedItem().toString();;
				PersonPlanUtil.majorManager.modifyMajor(major, order, change);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
			
		
	}

}
