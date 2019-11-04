package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanSpeciality;
import cn.edu.zucc.personplan.model.BeanStudent;
import cn.edu.zucc.personplan.util.BaseException;

public interface ISpecialityManager {
	
	public BeanSpeciality add(BeanStudent student, String type, String grade, String evidence) throws BaseException;
	
	public List<BeanSpeciality> loadAll(BeanStudent student) throws BaseException;
	
	public void delete(BeanSpeciality speciality) throws BaseException;
	
	public BeanSpeciality modify(BeanSpeciality speciality, String newType, String newGrade, String newEvidence) throws BaseException;
	
}
