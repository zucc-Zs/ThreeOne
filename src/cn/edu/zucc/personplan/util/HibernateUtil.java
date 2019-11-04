package cn.edu.zucc.personplan.util;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.edu.zucc.personplan.comtrol.example.ExampleMajorManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleStudentManager;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.model.BeanSubject;

public class HibernateUtil {
	private static SessionFactory sessionFactory 
= new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	public static void main(String[] args) throws BaseException{
//		Session session=getSession();
//		BeanStudent aStudent = ((BeanStudent)session.get(BeanStudent.class, "330682199902080025"));
//		new ExampleStudentManager().recommend1(aStudent);
	}
}


