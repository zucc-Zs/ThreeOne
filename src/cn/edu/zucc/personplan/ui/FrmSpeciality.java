package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.comtrol.example.ExampleSpecialityManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmSpeciality extends JDialog implements ActionListener {
	public BeanStudent student;
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("����س�");
	private Button btnModify = new Button("�޸��س�");
	private Button btnDelete = new Button("ɾ���س�");
	private Object tblTitle[]={"ѧ��ID","ѧ������","�س����","�س��ȼ�","��֤����"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable specialityTable=new JTable(tablmod);
	private void reloadSpecialityTable(BeanStudent aStudent){
		try {
			List<BeanSpeciality> specialities=(new ExampleSpecialityManager()).loadAll(aStudent);
			tblData =new Object[specialities.size()][5];
			for(int i=0;i<specialities.size();i++){
				for(int j=0;j<BeanSpeciality.tblSpecialityTitle.length;j++)
					tblData[i][j]=specialities.get(i).getCell(j);
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.specialityTable.validate();
			this.specialityTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmSpeciality(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadSpecialityTable(FrmMain.curStudent);
		this.getContentPane().add(new JScrollPane(this.specialityTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmAddSpeciality dlg=new FrmAddSpeciality(this,"����س�",true);
			dlg.student=FrmMain.curStudent;
			dlg.setVisible(true);
			reloadSpecialityTable(FrmMain.curStudent);
		}
		else if(e.getSource()==this.btnModify){
			int i=this.specialityTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���س�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ���޸��س���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanSpeciality> specialities=(new ExampleSpecialityManager()).loadAll(FrmMain.curStudent);
					BeanSpeciality curSpecialities = specialities.get(i);
					FrmModifySpeciality dlg=new FrmModifySpeciality(this,"�޸��س�",true);
					dlg.speciality=curSpecialities;
					dlg.setVisible(true);
					reloadSpecialityTable(FrmMain.curStudent);
				}catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.specialityTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���س�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ���س���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					List<BeanSpeciality> specialities=(new ExampleSpecialityManager()).loadAll(FrmMain.curStudent);
					BeanSpeciality curSpecialities = specialities.get(i);
					(new ExampleSpecialityManager()).delete(curSpecialities);
					this.reloadSpecialityTable(FrmMain.curStudent);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}
