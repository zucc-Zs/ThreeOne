package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.w3c.dom.ls.LSException;

import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IStudentManager;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.model.BeanRecommend;
import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSelectTest;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanStudyTest;
import cn.edu.zucc.personplan.model.BeanSubject;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DBUtil2;

public class ExampleStudentManager implements IStudentManager {

	@Override
	//注册
	public BeanStudent addStudent(String id, int testid, String name,String sex,String phoneNumber,String middleSchool,String province) throws BaseException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			BeanStudent u=(BeanStudent)session.get(BeanStudent.class, id);
			if(u!=null)
				throw new BusinessException("该考生已经存在!");
		
			String rege = "\\d{15}(\\d{2}[0-9xX])?";
			if(!id.matches(rege)) {
				throw new BusinessException("身份证号输入有误");
			}
			
			List<BeanStudent> aList = new ArrayList<BeanStudent>();
			String hql="from BeanStudent s where s.student_testid=:student_testid";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setInteger("student_testid",testid);
			aList = query.list();
			if(aList.size() != 0) {
				throw new BusinessException("准考证号重复");
			}
			
			String regex = "(\\86|0086)?\\s*1[0-9] {10}";
			if(!phoneNumber.matches(regex)) {
				throw new BusinessException("手机号输入有误");
			}
			
		
			
			BeanStudent result = new BeanStudent();
			result.setStudent_id(id);
			result.setStudent_testid(testid);
			result.setStudent_name(name);
			result.setStudent_sex(sex);
			result.setStudent_phoneNumber(phoneNumber);
			result.setStudent_middleSchool(middleSchool);
			result.setStudent_province(province);
			
			session.save(result);
			transaction .commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
	}

	
	@Override
	//修改姓名
	public BeanStudent modifyStudent(BeanStudent student,String newName) throws BaseException { 
		if(newName==null || "".equals(newName) || newName.length()>20){
			throw new BusinessException("考生名字必须是1-20个字!");
		}
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		student.setStudent_name(newName);
		
		try {
			session.update(student);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		return student;
	}
	
	
	public List<BeanStudent> loadAllStudent() throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanStudent> aList = new ArrayList<BeanStudent>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			
			String hql = "from BeanStudent s";
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

	//删除
	public void deleteStudent(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			BeanStudent p=(BeanStudent)session.get(BeanStudent.class, student.getStudent_id());
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
	
	//查询
	public List<BeanStudent> searchStudent(String name) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanStudent> aList = new ArrayList<BeanStudent>();
		try {
			String hql = "from BeanStudent s where s.student_name like '%" +name+ "%'";
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
	public void recommend1(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanMajorPlan> aList = new ArrayList<BeanMajorPlan>();
		List<BeanSelectTest> bList = new ArrayList<BeanSelectTest>();
		List<BeanStudyTest> cList = new ArrayList<BeanStudyTest>();
		String hql = "from BeanSelectTest s where s.student_id =:student_id";
		org.hibernate.query.Query query =session.createQuery(hql);
		query.setString("student_id",student.getStudent_id());
		bList = query.list();
		if(bList.size()<3) {
			throw new BusinessException("选考成绩输入不完整");
		}
		int gradeSum = 0;
		for(int i=0;i<3;i++) {
			gradeSum=gradeSum+Integer.parseInt(bList.get(i).getSelectTest_grade());
		}
		
		hql = "from BeanStudyTest s where s.student_id =:student_id";
		query =session.createQuery(hql);
		query.setString("student_id",student.getStudent_id());
		cList = query.list();
		if(cList.size()<10) {
			throw new BusinessException("学考成绩输入不完整");
		}
		for(int i=0;i<10;i++) {
			if(cList.get(i).getStudyTest_grade().equals("A")) {
				gradeSum=gradeSum+10;
			}
			if(cList.get(i).getStudyTest_grade().equals("B")) {
				gradeSum=gradeSum+5;
			}
			if(cList.get(i).getStudyTest_grade().equals("C")) {
				gradeSum=gradeSum+3;
			}
		}
		
		int highMajorId=0,lowMajorId=0,provinceMajorId=0;
		int highGrade=400,lowGrade=0;
		for(int i=0;i<3;i++) {
			hql = "from BeanMajorPlan s where s.selectSubject_name like '%" +((BeanSubject)session.get(BeanSubject.class, bList.get(i).getSubject_id())).getSubject_name()+ "%'";
			query =session.createQuery(hql);
			aList = query.list();
			for(int j=0;j<aList.size();j++) {
				if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > gradeSum) {
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < highGrade) {
						highGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
						highMajorId = aList.get(j).getMajor_id();
					}
				}
				if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < gradeSum) {
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > lowGrade) {
						lowGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
						lowMajorId = aList.get(j).getMajor_id();
					}
				}
				int schoolID = ((BeanMajor)session.get(BeanMajor.class, aList.get(j).getMajor_id())).getSchool_id();
				if(((BeanSchool)session.get(BeanSchool.class, schoolID)).getSchool_province().equals(student.getStudent_province())) {
					provinceMajorId = aList.get(j).getMajor_id();
				}
					
					
			}
		}
		try {

			BeanRecommend result = new BeanRecommend();
			result.setMajor_id(highMajorId);
			result.setStudent_id(student.getStudent_id());
			result.setRecommend_reason("该专业分数略高于学生成绩，建议挑战选择");
			session.save(result);
			
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
	public void recommend2(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanMajorPlan> aList = new ArrayList<BeanMajorPlan>();
		List<BeanSelectTest> bList = new ArrayList<BeanSelectTest>();
		List<BeanStudyTest> cList = new ArrayList<BeanStudyTest>();
		try {
			String hql = "from BeanSelectTest s where s.student_id =:student_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setString("student_id",student.getStudent_id());
			bList = query.list();
			if(bList.size()<3) {
				throw new BusinessException("选考成绩输入不完整");
			}
			int gradeSum = 0;
			for(int i=0;i<3;i++) {
				gradeSum=gradeSum+Integer.parseInt(bList.get(i).getSelectTest_grade());
			}
			
			hql = "from BeanStudyTest s where s.student_id =:student_id";
			query =session.createQuery(hql);
			query.setString("student_id",student.getStudent_id());
			cList = query.list();
			if(cList.size()<10) {
				throw new BusinessException("学考成绩输入不完整");
			}
			for(int i=0;i<10;i++) {
				if(cList.get(i).getStudyTest_grade().equals("A")) {
					gradeSum=gradeSum+10;
				}
				if(cList.get(i).getStudyTest_grade().equals("B")) {
					gradeSum=gradeSum+5;
				}
				if(cList.get(i).getStudyTest_grade().equals("C")) {
					gradeSum=gradeSum+3;
				}
			}
			
			int highMajorId=0,lowMajorId=0,provinceMajorId=0;
			int highGrade=400,lowGrade=0;
			for(int i=0;i<3;i++) {
				hql = "from BeanMajorPlan s where s.selectSubject_name like '%" +((BeanSubject)session.get(BeanSubject.class, bList.get(i).getSubject_id())).getSubject_name()+ "%'";
				query =session.createQuery(hql);
				aList = query.list();
				for(int j=0;j<aList.size();j++) {
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > gradeSum) {
						if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < highGrade) {
							highGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
							highMajorId = aList.get(j).getMajor_id();
						}
					}
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < gradeSum) {
						if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > lowGrade) {
							lowGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
							lowMajorId = aList.get(j).getMajor_id();
						}
					}
					int schoolID = ((BeanMajor)session.get(BeanMajor.class, aList.get(j).getMajor_id())).getSchool_id();
					if(((BeanSchool)session.get(BeanSchool.class, schoolID)).getSchool_province().equals(student.getStudent_province())) {
						provinceMajorId = aList.get(j).getMajor_id();
					}
						
						
				}
			}
			BeanRecommend result = new BeanRecommend();
			
			result.setMajor_id(lowMajorId);
			result.setStudent_id(student.getStudent_id());
			result.setRecommend_reason("该专业分数略低于学生成绩，建议保守选择");
			session.save(result);
			
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
	public void recommend3(BeanStudent student) throws BaseException {
		Session session=HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		List<BeanMajorPlan> aList = new ArrayList<BeanMajorPlan>();
		List<BeanSelectTest> bList = new ArrayList<BeanSelectTest>();
		List<BeanStudyTest> cList = new ArrayList<BeanStudyTest>();
		try {
			String hql = "from BeanSelectTest s where s.student_id =:student_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setString("student_id",student.getStudent_id());
			bList = query.list();
			if(bList.size()<3) {
				throw new BusinessException("选考成绩输入不完整");
			}
			int gradeSum = 0;
			for(int i=0;i<3;i++) {
				gradeSum=gradeSum+Integer.parseInt(bList.get(i).getSelectTest_grade());
			}
			
			hql = "from BeanStudyTest s where s.student_id =:student_id";
			query =session.createQuery(hql);
			query.setString("student_id",student.getStudent_id());
			cList = query.list();
			if(cList.size()<10) {
				throw new BusinessException("学考成绩输入不完整");
			}
			for(int i=0;i<10;i++) {
				if(cList.get(i).getStudyTest_grade().equals("A")) {
					gradeSum=gradeSum+10;
				}
				if(cList.get(i).getStudyTest_grade().equals("B")) {
					gradeSum=gradeSum+5;
				}
				if(cList.get(i).getStudyTest_grade().equals("C")) {
					gradeSum=gradeSum+3;
				}
			}
			
			int highMajorId=0,lowMajorId=0,provinceMajorId=0;
			int highGrade=400,lowGrade=0;
			for(int i=0;i<3;i++) {
				hql = "from BeanMajorPlan s where s.selectSubject_name like '%" +((BeanSubject)session.get(BeanSubject.class, bList.get(i).getSubject_id())).getSubject_name()+ "%'";
				query =session.createQuery(hql);
				aList = query.list();
				for(int j=0;j<aList.size();j++) {
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > gradeSum) {
						if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < highGrade) {
							highGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
							highMajorId = aList.get(j).getMajor_id();
						}
					}
					if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) < gradeSum) {
						if(Integer.parseInt(aList.get(j).getMojorPlan_grade()) > lowGrade) {
							lowGrade = Integer.parseInt(aList.get(j).getMojorPlan_grade());
							lowMajorId = aList.get(j).getMajor_id();
						}
					}
					int schoolID = ((BeanMajor)session.get(BeanMajor.class, aList.get(j).getMajor_id())).getSchool_id();
					if(((BeanSchool)session.get(BeanSchool.class, schoolID)).getSchool_province().equals(student.getStudent_province())) {
						provinceMajorId = aList.get(j).getMajor_id();
					}
						
						
				}
			}
			
			BeanRecommend result = new BeanRecommend();
			result.setMajor_id(provinceMajorId);
			result.setStudent_id(student.getStudent_id());
			result.setRecommend_reason("该专业所属省份与学生相同，建议就近选择");
			session.save(result);
			
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
	public List<BeanRecommend> loadRecommend(BeanStudent astudent) throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanRecommend> aList = new ArrayList<BeanRecommend>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanRecommend s where s.student_id=:student_id";
			org.hibernate.query.Query query =session.createQuery(hql);
			query.setString("student_id",astudent.getStudent_id());
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
