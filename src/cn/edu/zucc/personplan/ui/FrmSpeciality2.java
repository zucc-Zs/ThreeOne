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


public class FrmSpeciality2 extends JDialog {
	public BeanStudent student;
	private Object tblTitle[]={"学生ID","学生姓名","特长类别","特长等级","佐证材料"};
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
	
	public FrmSpeciality2(JFrame f, String s, boolean b) {
		super(f, s, b);
		//提取现有数据
		this.reloadSpecialityTable(FrmSearch.curStudent);
		this.getContentPane().add(new JScrollPane(this.specialityTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

}
