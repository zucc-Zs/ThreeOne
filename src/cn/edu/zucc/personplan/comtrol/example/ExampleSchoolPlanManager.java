package cn.edu.zucc.personplan.comtrol.example;

import java.beans.Beans;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.ISchoolPlanManager;
import cn.edu.zucc.personplan.itf.ISubjectManager;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;

public class ExampleSchoolPlanManager implements ISchoolPlanManager {

	@Override
	
	//Ìí¼Ó
	public BeanSchoolPlan add(BeanSchool school, String ask, String startTime, String endTime, String method) throws BaseException {
		// TODO Auto-generated method stub	

		Session session=HibernateUtil.getSession();
		
		BeanSchoolPlan result = new BeanSchoolPlan();
		result.setSchool_id(school.getSchool_id());
		result.setSchoolPlan_ask(ask);
		result.setSign_startTime(java.sql.Date.valueOf(startTime));
		result.setSign_endTime(java.sql.Date.valueOf(endTime));
		result.setSign_method(method);

		
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
	public List<BeanSchoolPlan> loadAll(BeanSchool school) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanSchoolPlan> aList = new ArrayList<BeanSchoolPlan>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanSchoolPlan s where s.school_id=:school_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			int school_id = school.getSchool_id();
			query.setInteger("school_id",school_id);
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
	public void delete(BeanSchoolPlan schoolPlan) throws BaseException { 
		Session session=HibernateUtil.getSession();

		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanSchoolPlan p=(BeanSchoolPlan)session.get(BeanSchoolPlan.class, schoolPlan.getSchool_id());
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
	
	@Override
	public BeanSchoolPlan modify(BeanSchoolPlan schoolPlan, String ask, String startTime, String endTime, String method) throws BaseException { 
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		schoolPlan.setSchoolPlan_ask(ask);
		schoolPlan.setSign_startTime(java.sql.Date.valueOf(startTime));
		schoolPlan.setSign_endTime(java.sql.Date.valueOf(endTime));
		schoolPlan.setSign_method(method);
		
		try {
			session.update(schoolPlan);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return schoolPlan;
	}
	
	
	
}

