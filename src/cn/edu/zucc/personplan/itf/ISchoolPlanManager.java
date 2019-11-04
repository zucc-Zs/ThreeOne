package cn.edu.zucc.personplan.itf;

import java.sql.Date;
import java.util.List;

import cn.edu.zucc.personplan.model.BeanSchool;
import cn.edu.zucc.personplan.model.BeanSchoolPlan;
import cn.edu.zucc.personplan.util.BaseException;

public interface ISchoolPlanManager {

	public BeanSchoolPlan add(BeanSchool school, String ask, String startTime, String endTime, String method) throws BaseException;
	
	public List<BeanSchoolPlan> loadAll(BeanSchool school) throws BaseException;
	
	public void delete(BeanSchoolPlan schoolPlan) throws BaseException;
	
	public BeanSchoolPlan modify(BeanSchoolPlan schoolPlan, String ask, String startTime, String endTime, String method) throws BaseException;
}
