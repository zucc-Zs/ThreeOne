package cn.edu.zucc.personplan.model;

import java.util.Date;
import java.util.Set;

public class BeanUser {
	private String user_id;
	private String user_name;
	private String user_grade;
	private String user_pwd;
	private Set<BeanSchool> aSchools;
    

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	
	public Set<BeanSchool> getaSchools() {
		return aSchools;
	}

	public void setaSchools(Set<BeanSchool> aSchools) {
		this.aSchools = aSchools;
	}

	public static BeanUser currentLoginUser=null;
	
	public static BeanUser getCurrentLoginUser() {
		return currentLoginUser;
	}

	public static void setCurrentLoginUser(BeanUser currentLoginUser) {
		BeanUser.currentLoginUser = currentLoginUser;
	}
	
	public static final String[] tblUserTitle={"ID","Ãû×Ö","µÈ¼¶","ÃÜÂë"};
	public String getCell(int col){
		if(col==0) return String.valueOf(this.user_id);
		else if(col==1) return this.user_name;
		else if(col==2) return this.user_grade;
		else if(col==3) return this.user_pwd;
		else return "";
	}
}
