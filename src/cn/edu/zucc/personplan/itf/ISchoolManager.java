package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.util.BaseException;

public interface ISchoolManager {
	
	public BeanSchool addSchool(int id, String name, String province, String city,String evaluate) throws BaseException;
	
	public void deleteSchool(BeanSchool school) throws BaseException;
	
	public List<BeanSchool> loadAllSchool() throws BaseException;
	
	public BeanSchool modifySchool(BeanSchool school,String newName, String province, String city,String evaluate) throws BaseException;
	
	public List<BeanSchool> searchSchool(String name) throws BaseException;
}

