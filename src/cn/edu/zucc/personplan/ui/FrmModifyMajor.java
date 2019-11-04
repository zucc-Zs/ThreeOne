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

import com.sun.xml.internal.ws.Closeable;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import javassist.tools.framedump;


public class FrmModifyMajor extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");
	private JComboBox cmbModifytype= new JComboBox(new String[] { "修改名字", "修改专业类别", "修改优势专业"});
	
	private BeanSchool curSchool=null;	
	List<BeanSchool> allSchool=null;
	public BeanSchool school;
	public BeanMajor major;
	public Frame k;
	public FrmModifyMajor(Frame f, String s, boolean b) {
		super(f, s, b);
		k = f ;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		cmbModifytype.setBounds(85, 41, 121, 47);
		
		workPane.add(cmbModifytype);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 220);
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
			if(JOptionPane.showConfirmDialog(this,"确定修改专业吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				FrmModifyMajor2 dlg=new FrmModifyMajor2(k,this.cmbModifytype.getSelectedItem().toString(),true);
				dlg.major = major;
				dlg.setVisible(true);
			}
		}
		
		
	}

}

