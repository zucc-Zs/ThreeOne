package cn.edu.zucc.personplan.comtrol.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.personplan.itf.ISpecialityManager;
import cn.edu.zucc.personplan.itf.ISubjectManager;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;

public class ExampleSpecialityManager implements ISpecialityManager {

	@Override
	
	//Ìí¼Ó
	public BeanSpeciality add(BeanStudent student, String type, String grade, String evidence) throws BaseException {
		// TODO Auto-generated method stub	

		Session session=HibernateUtil.getSession();
		
		BeanSpeciality result = new BeanSpeciality();
		result.setStudent_id(student.getStudent_id());
		result.setSpeciality_type(type);
		result.setSpeciality_grade(grade);
		result.setSpeciality_evidence(evidence);

		
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
	public List<BeanSpeciality> loadAll(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanSpeciality> aList = new ArrayList<BeanSpeciality>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanSpeciality s where s.student_id=:student_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			String student_id = student.getStudent_id();
			query.setString("student_id",student_id);
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
	public void delete(BeanSpeciality speciality) throws BaseException { 
		Session session=HibernateUtil.getSession();

		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanSpeciality p=(BeanSpeciality)session.get(BeanSpeciality.class, speciality.getSpeciality_order());
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
	public BeanSpeciality modify(BeanSpeciality speciality, String newType, String newGrade, String newEvidence) throws BaseException { 
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		speciality.setSpeciality_type(newType);
		speciality.setSpeciality_grade(newGrade);
		speciality.setSpeciality_evidence(newEvidence);
		
		try {
			session.update(speciality);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return speciality;
	}
	
	
	
}

