package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.sql.Update;

import cn.edu.zucc.personplan.itf.IMajorManager;
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


public class ExampleMajorManager implements IMajorManager {

	@Override
	
	//添加专业
	public BeanMajor addMajor(BeanSchool school, int id, String name, String type, String advantage) throws BaseException {
		// TODO Auto-generated method stub	
		if(name==null || "".equals(name) || name.length()>20){
			throw new BusinessException("专业名字必须是1-20个字");
		}
		
		Session session=HibernateUtil.getSession();
		BeanMajor u=(BeanMajor)session.get(BeanMajor.class, id);
		if(u!=null)
			throw new BaseException("专业id重复");
		

		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		String hql="from BeanMajor s where s.major_name=:major_name and s.school_id=:school_id";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("major_name",name);
		query.setInteger("school_id",school.getSchool_id());
		aList = query.list();
		if(aList.size() != 0) {
			throw new BusinessException("该学校中专业名字重复");
		}

		BeanMajor result = new BeanMajor();
		result.setMajor_id(id);
		result.setMajor_name(name);
		result.setMajor_type(type);
		result.setMajor_advantage(advantage);
		result.setaSchool(school);
		
		
		
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			if(advantage.equals("是")) {
				String advantageMajor=null;
				if("".equals(school.getSchool_advantage())||school.getSchool_advantage()==null)
					advantageMajor = name +"、";
				else 
					advantageMajor = school.getSchool_advantage()+name+"、";
				school.setSchool_advantage(advantageMajor);
				session.update(school);
			}
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
	public List<BeanMajor> loadAllMajor() throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanMajor> aList = new ArrayList<BeanMajor>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanMajor s";
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
	public void deleteMajor(BeanMajor major) throws BaseException { 
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanMajor p=(BeanMajor)session.get(BeanMajor.class, major.getMajor_id());
			session.delete(p);
			
			if(major.getMajor_advantage().equals("是")) {
				BeanSchool aSchool = session.get(BeanSchool.class, major.getSchool_id());
				String advantage = aSchool.getSchool_advantage();
				advantage = advantage.replace(major.getMajor_name()+"、", "");
				aSchool.setSchool_advantage(advantage);
				session.update(aSchool);
			}
			
			transaction .commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			session.close();
		}
		
	}
	
	public BeanMajor modifyMajor(BeanMajor major, int order, String New) throws BaseException { 

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		if(order == 1) {
			if(New==null || "".equals(New) || New.length()>20){
				throw new BusinessException("学校名字必须是1-20个字");
			}
			if(major.getMajor_name().equals(New) ){
				throw new BusinessException("与原学校名字相同");
			}
			List<BeanSchool> aList = new ArrayList<BeanSchool>();
			String hql="from BeanMajor s where s.major_name=:major_name and s.school_id=:school_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setString("major_name",New);
			query.setInteger("school_id",major.getSchool_id());
			aList = query.list();
			if(aList.size() != 0) {
				throw new BusinessException("该学校中专业名字重复");
			}
			major.setMajor_name(New);
		}
		if(order == 2)
			major.setMajor_type(New);
		if(order == 3)
			major.setMajor_advantage(New);
		
		try {
			session.update(major);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return major;
	}
	
	public List<BeanMajor> loadMajors(BeanSchool school) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanMajor> aList = new ArrayList<BeanMajor>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			int aShoolId= school.getSchool_id();
			String hql = "from BeanMajor s where s.aSchool.school_id=:school_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setInteger("school_id",aShoolId);
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
	
	//查询
	public List<BeanMajor> searchMajor(String name) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanMajor> aList = new ArrayList<BeanMajor>();
		try {
			String hql = "from BeanMajor s where s.major_name like '%" +name+ "%'";
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
	
	public List<BeanSchool> advantageSchool(String name) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanSchool> aList = new ArrayList<BeanSchool>();
		try {
			String hql = "FROM BeanSchool s where s.school_id in (SELECT m.school_id FROM BeanMajor m where m.major_advantage ='是' and m.major_name='"+name+"')";
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
