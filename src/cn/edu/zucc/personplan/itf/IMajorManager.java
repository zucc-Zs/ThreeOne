package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.util.BaseException;

public interface IMajorManager {
	
	public BeanMajor addMajor(BeanSchool school, int id, String name, String type, String advantage) throws BaseException;
	
	public List<BeanMajor> loadAllMajor() throws BaseException;
	
	public void deleteMajor(BeanMajor major) throws BaseException;
	
	public BeanMajor modifyMajor(BeanMajor major, int order, String New) throws BaseException;
	
	public List<BeanMajor> loadMajors(BeanSchool school) throws BaseException;
	
	public List<BeanMajor> searchMajor(String name) throws BaseException;
	
	public List<BeanSchool> advantageSchool(String name) throws BaseException;
}

