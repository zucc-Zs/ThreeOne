package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanRecommend;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;

public interface IStudentManager {

	public BeanStudent addStudent(String id, int testid, String name,String sex,String phoneNumber,String middleSchool,String province) throws BaseException;
	
	public BeanStudent modifyStudent(BeanStudent student,String newName) throws BaseException;
	
	public List<BeanStudent> loadAllStudent() throws BaseException;
	
	public void deleteStudent(BeanStudent student) throws BaseException;
	
	public List<BeanStudent> searchStudent(String name) throws BaseException;
	
	public void recommend1(BeanStudent student) throws BaseException;
	
	public void recommend2(BeanStudent student) throws BaseException;
	
	public void recommend3(BeanStudent student) throws BaseException;
	
	public List<BeanRecommend> loadRecommend(BeanStudent student) throws BaseException;
}
