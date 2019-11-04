package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.util.BaseException;

public interface ISubjectManager {
	public BeanSubject add(int id,String name) throws BaseException;
	
	public BeanSubject modify(BeanSubject student, int newId, String newName) throws BaseException;
	
	public List<BeanSubject> loadAll() throws BaseException;
	
	public void delete(BeanSubject subject) throws BaseException;
}
