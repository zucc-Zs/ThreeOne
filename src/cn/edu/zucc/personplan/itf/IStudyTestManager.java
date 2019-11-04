package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.util.BaseException;

public interface IStudyTestManager {
	public BeanStudyTest add(BeanStudent student, int subjectId, String grade) throws BaseException;
	
	public List<BeanStudyTest> loadAll(BeanStudent student) throws BaseException;
	
	public void delete(BeanStudyTest studyTest) throws BaseException;
	
	public BeanStudyTest modify(BeanStudyTest studyTest, int newSubjectId, String newGrade) throws BaseException;
}
