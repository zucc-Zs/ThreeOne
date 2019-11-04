package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.ISchoolManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DBUtil2;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;


public class ExampleSchoolManager implements ISchoolManager {

	@Override
	
	//添加学校
	public BeanSchool addSchool(int id, String name, String province, String city,String evaluate) throws BaseException {
		// TODO Auto-generated method stub	
		
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("学校名字必须是1-20个字");
		}
		Session session=HibernateUtil.getSession();
		
		BeanSchool u=(BeanSchool)session.get(BeanSchool.class, id);
		if(u!=null)
			throw new BaseException("学校ID重复!");
		
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		String hql="from BeanSchool s where s.school_name=:school_name";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("school_name",name);
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("学校名字重复");
		}
		
		BeanSchool result = new BeanSchool();
		result.setSchool_id(id);
		result.setSchool_name(name);
		result.setSchool_province(province);
		result.setSchool_city(city);
		result.setSchool_evaluate(evaluate);
		
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
	public List<BeanSchool> loadAllSchool() throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanSchool s";
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
	public void deleteSchool(BeanSchool school) throws BaseException { 
		Session session=HibernateUtil.getSession();
		int aSchoolId = school.getSchool_id();
		List<BeanMajor> aList = new ArrayList<BeanMajor>();
		
		String hql = "from BeanMajor s where s.aSchool.school_id=:school_id";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setInteger("school_id",school.getSchool_id());
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("学校中有专业存在!");
		}
		
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			hql="delete BeanSchool s where s.school_id=:school_id";
			query=session.createQuery(hql);
			query.setInteger("school_id",school.getSchool_id());
			query.executeUpdate();

			transaction.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			session.close();
		}
		
	}
	
	public BeanSchool modifySchool(BeanSchool school,String newName, String province, String city,String evaluate) throws BaseException { 
		if(newName==null || "".equals(newName) || newName.length()>20){
			throw new BusinessException("学校名字必须是1-20个字");
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		String hql="from BeanSchool s where s.school_name=:school_name";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("school_name",newName);
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("学校名字重复!");
		}
		
		school.setSchool_name(newName);
		school.setSchool_city(city);
		school.setSchool_evaluate(evaluate);
		school.setSchool_province(province);
		
		try {
			session.update(school);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return school;
	}
	
	//查询
	public List<BeanSchool> searchSchool(String name) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		try {
			String hql = "from BeanSchool s where s.school_name like '%" +name+ "%'";
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
	
}
