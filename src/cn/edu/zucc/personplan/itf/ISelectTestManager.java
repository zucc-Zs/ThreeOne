package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.util.BaseException;

public interface ISelectTestManager {
	public BeanSelectTest add(BeanStudent student, int subjectId, String grade) throws BaseException;
	
	public List<BeanSelectTest> loadAll(BeanStudent student) throws BaseException;
	
	public void delete(BeanSelectTest selectTest) throws BaseException;
	
	public BeanSelectTest modify(BeanSelectTest selectTest, int newSubjectId, String newGrade) throws BaseException;
}
