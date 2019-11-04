package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.ls.LSException;

import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.HibernateUtil;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DBUtil2;

public class ExampleUserManager implements IUserManager {

	@Override
	//注册
	public BeanUser reg(String userid, String username, String pwd,String pwd2,String grade) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null || "".equals(userid) || userid.length()>20){
			throw new BusinessException("用户ID必须是1-20个字");
		}
		if(username==null || "".equals(username) || username.length()>20){
			throw new BusinessException("用户名字必须是1-20个字");
		}
		if(!pwd.equals(pwd2)){
			throw new BusinessException("两次密码不同");
		}
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			BeanUser u=(BeanUser)session.get(BeanUser.class, userid);
			if(u!=null)
				throw new BaseException("登陆账号已经存在!");
		
			BeanUser result = new BeanUser();
			result.setUser_id(userid);
			result.setUser_pwd(pwd);
			result.setUser_name(username);
			result.setUser_grade(grade);
			
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
	//登录
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null || "".equals(userid) || userid.length()>20){
			throw new BusinessException("用户账号必须是1-20个字");
		}
		if(pwd==null || "".equals(pwd)){
			throw new BusinessException("密码不能为空");
		}
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		BeanUser result = (BeanUser)session.get(BeanUser.class, userid);
		try {
			if(result != null){
				if(!result.getUser_pwd().equals(pwd))
					throw new BusinessException("密码错误");
			}
			else {
				throw new BusinessException("登陆账户不存在!");
			}
				
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
	//修改密码
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null || "".equals(oldPwd)) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd)) throw new BusinessException("新密码不能为空");
		if(!newPwd.equals(newPwd2)){
			throw new BusinessException("两次密码不同");
		}
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql="select s.user_pwd from BeanUser s where s.user_id=:user_id";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setString("user_id", user.getUser_id());
			List<String> aList = query.list();
			if(aList.size() == 0) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(aList.get(0))) throw new BusinessException("原始密码错误");

			hql="update BeanUser s set s.user_pwd =:user_pwd1 where s.user_id=:user_id";
			query = session.createQuery(hql);
			query.setString("user_pwd1", newPwd);
			query.setString("user_id", user.getUser_id());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
		
	}
	
	
	public List<BeanUser> loadAll() throws BaseException {
		Session session=HibernateUtil.getSession();
		List<BeanUser> aList = new ArrayList<BeanUser>();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			
			String hql = "from BeanUser s";
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
	
	//重置密码
	public void resetPwd(BeanUser user) throws BaseException {
		if("管理员".equals(user.getUser_grade())) {
			throw new BusinessException("无法重置管理员用户");
		}
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			user.setUser_pwd("123456");
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
	}
	
	//删除
	public void delete(BeanUser user) throws BaseException {
		if("管理员".equals(user.getUser_grade()) && !BeanUser.currentLoginUser.getUser_id().equals(user.getUser_id())) {
			throw new BusinessException("无法删除其他管理员用户");
		}
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hql="delete from BeanUser s where s.user_id=:user_id";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setString("user_id", user.getUser_id());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			session.close();
		}
	}
	
}
