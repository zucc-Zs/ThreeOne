package cn.edu.zucc.personplan.model;

public class BeanMajor {
	private int major_id;
	private String major_name;
	private String major_type;
	private int school_id;
	private String major_advantage;
	private BeanSchool aSchool;
	
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getMajor_type() {
		return major_type;
	}
	public void setMajor_type(String major_type) {
		this.major_type = major_type;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getMajor_advantage() {
		return major_advantage;
	}
	public BeanSchool getaSchool() {
		return aSchool;
	}
	public void setaSchool(BeanSchool aSchool) {
		this.aSchool = aSchool;
	}
	public void setMajor_advantage(String major_advantage) {
		this.major_advantage = major_advantage;
	}
	
	public static String[] getTblsteptitle() {
		return tblMajorTitle;
	}
	public static final String[] tblMajorTitle={"专业ID","专业名字","专业类别","是否为优势专业"};
	
	public String getCell(int col){
		if(col==0)      return String.valueOf(this.major_id);
		else if(col==1) return this.major_name;
		else if(col==2) return this.major_type;
		else if(col==3) return this.major_advantage;
		else return "";
	}
}
