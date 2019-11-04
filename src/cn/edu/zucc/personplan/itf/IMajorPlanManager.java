package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanMajor;
import cn.edu.zucc.personplan.model.BeanMajorPlan;
import cn.edu.zucc.personplan.util.BaseException;

public interface IMajorPlanManager {
	public BeanMajorPlan add(BeanMajor major, int year, String subject, String num, String tuition, String ask, String grade) throws BaseException;
	
	public List<BeanMajorPlan> loadAll(BeanMajor major) throws BaseException;
	
	public void delete(BeanMajorPlan majorPlan) throws BaseException;
	
	public BeanMajorPlan modify(BeanMajorPlan majorPlan, int year, String subject, String num, String tuition, String ask, String grade) throws BaseException;
}
