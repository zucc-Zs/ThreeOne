package cn.edu.zucc.personplan.comtrol.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.IMajorPlanManager;
import cn.edu.zucc.personplan.itf.ISchoolPlanManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;

public class ExampleMajorPlanManager implements IMajorPlanManager {

	@Override
	
	//Ìí¼Ó
	public BeanMajorPlan add(BeanMajor major, int year, String subject, String num, String tuition, String ask, String grade) throws BaseException {
		// TODO Auto-generated method stub	

		Session session=HibernateUtil.getSession();
		
		BeanMajorPlan result = new BeanMajorPlan();
		result.setMajor_id(major.getMajor_id());
		result.setMojorPlan_year(year);
		result.setSelectSubject_name(subject);
		result.setPlan_num(num);
		result.setMojorPlan_tuition(tuition);
		result.setMojorPlan_ask(ask);
		result.setMojorPlan_grade(grade);

		
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
	public List<BeanMajorPlan> loadAll(BeanMajor major) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanMajorPlan> aList = new ArrayList<BeanMajorPlan>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanMajorPlan s where s.major_id=:major_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			int major_id = major.getMajor_id();
			query.setInteger("major_id",major_id);
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
	public void delete(BeanMajorPlan majorPlan) throws BaseException { 
		Session session=HibernateUtil.getSession();

		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanMajorPlan p=(BeanMajorPlan)session.get(BeanMajorPlan.class, majorPlan.getMajorPlan_order());
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
	public BeanMajorPlan modify(BeanMajorPlan majorPlan, int year, String subject, String num, String tuition, String ask, String grade) throws BaseException { 
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		majorPlan.setMojorPlan_year(year);
		majorPlan.setSelectSubject_name(subject);
		majorPlan.setPlan_num(num);
		majorPlan.setMojorPlan_tuition(tuition);
		majorPlan.setMojorPlan_ask(ask);
		majorPlan.setMojorPlan_grade(grade);
		
		try {
			session.update(majorPlan);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return majorPlan;
	}
	
	
	
}

