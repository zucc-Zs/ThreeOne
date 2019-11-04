package cn.edu.zucc.personplan.util;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super(ex.getMessage());
	}
}
