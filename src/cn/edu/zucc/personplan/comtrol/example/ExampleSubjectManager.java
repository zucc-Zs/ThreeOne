package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.ISchoolManager;
import cn.edu.zucc.personplan.itf.ISubjectManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;


public class ExampleSubjectManager implements ISubjectManager {

	@Override
	
	//添加
	public BeanSubject add(int id, String name) throws BaseException {
		// TODO Auto-generated method stub	
		
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("名字必须是1-20个字");
		}

		Session session=HibernateUtil.getSession();
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		BeanSubject u=(BeanSubject)session.get(BeanSubject.class, id);
		if(u!=null)
			throw new BaseException("ID重复!");
		
		String hql="from BeanSubject s where s.subject_name=:subject_name";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("subject_name",name);
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("名字重复");
		}
		
		BeanSubject result = new BeanSubject();
		result.setSubject_id(id);
		result.setSubject_name(name);

		
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
	public List<BeanSubject> loadAll() throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanSubject> aList = new ArrayList<BeanSubject>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanSubject s";
			org.hibernate.query.Query query =session.createQuery(hql);
			
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
	public void delete(BeanSubject subject) throws BaseException { 
		Session session=HibernateUtil.getSession();

		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanSubject p=(BeanSubject)session.get(BeanSubject.class, subject.getSubject_id());
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
	
	public BeanSubject modify(BeanSubject subject, int newId, String newName) throws BaseException { 
		if(newName==null || "".equals(newName) || newName.length()>20){
			throw new BusinessException("名字必须是1-20个字");
		}
		if(subject.getSubject_name().equals(newName) ){
			throw new BusinessException("与原名字相同!");
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		List<BeanSubject> aList = new ArrayList<BeanSubject>();
		String hql="from BeanSubject s where s.subject_name=:subject_name";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("subject_name",newName);
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("名字重复!");
		}
		
		subject.setSubject_name(newName);
		
		try {
			session.update(subject);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return subject;
	}
	
	
	
}
