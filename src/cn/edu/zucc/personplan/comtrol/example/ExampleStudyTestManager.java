package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.ISchoolManager;
import cn.edu.zucc.personplan.itf.IStudentManager;
import cn.edu.zucc.personplan.itf.IStudyTestManager;
import cn.edu.zucc.personplan.itf.ISubjectManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;


public class ExampleStudyTestManager implements IStudyTestManager {

	@Override
	
	//添加
	public BeanStudyTest add(BeanStudent student, int subjectId, String grade) throws BaseException {
		// TODO Auto-generated method stub	

		Session session=HibernateUtil.getSession();
		String hql="from BeanStudyTest s where s.subject_id=:subject_id and s.student_id=:student_id";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setInteger("subject_id",subjectId);
		query.setString("student_id",student.getStudent_id());
		List<BeanSelectTest> aList = new ArrayList<BeanSelectTest>();
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("已存在该学科成绩！");
		}
		
		BeanStudyTest result = new BeanStudyTest();
		result.setSubject_id(subjectId);
		result.setStudyTest_grade(grade);
		result.setStudent_id(student.getStudent_id());

		
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			session.save(result);
			transaction .commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			session.close();
		}
		return result;
		
	}
	
	@Override
	public List<BeanStudyTest> loadAll(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanStudyTest> aList = new ArrayList<BeanStudyTest>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanStudyTest s where s.student_id=:student_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setString("student_id",student.getStudent_id());
			aList = query.list();
			transaction.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			session.close();
		}
		return aList;

	}

	@Override
	public void delete(BeanStudyTest studyTest) throws BaseException { 
		Session session=HibernateUtil.getSession();

		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanStudyTest p=(BeanStudyTest)session.get(BeanStudyTest.class, studyTest.getStudyTest_order());
			session.delete(p);

			transaction .commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			session.close();
		}
		
	}
	
	public BeanStudyTest modify(BeanStudyTest studyTest, int newSubjectId, String newGrade) throws BaseException { 	
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		studyTest.setStudyTest_grade(newGrade);
		studyTest.setSubject_id(newSubjectId);
		
		try {
			session.update(studyTest);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return studyTest;
	}

}
