package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.comtrol.example.ExampleMajorManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleMajorPlanManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSchoolManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSchoolPlanManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSelectTestManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSpecialityManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleStudentManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleStudyTestManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleSubjectManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleUserManager;
import cn.edu.zucc.personplan.itf.IMajorManager;
import cn.edu.zucc.personplan.itf.IMajorPlanManager;
import cn.edu.zucc.personplan.itf.ISchoolManager;
import cn.edu.zucc.personplan.itf.ISchoolPlanManager;
import cn.edu.zucc.personplan.itf.ISelectTestManager;
import cn.edu.zucc.personplan.itf.ISpecialityManager;
import cn.edu.zucc.personplan.itf.IStudentManager;
import cn.edu.zucc.personplan.itf.IStudyTestManager;
import cn.edu.zucc.personplan.itf.ISubjectManager;
import cn.edu.zucc.personplan.itf.IUserManager;

public class PersonPlanUtil {
	public static ISchoolManager schoolManager=new ExampleSchoolManager();//需要换成自行设计的实现类
	
	public static IMajorManager majorManager=new ExampleMajorManager();//需要换成自行设计的实现类
	
	public static IUserManager userManager=new ExampleUserManager();//需要换成自行设计的实现类
	
	public static IStudentManager studentManager=new ExampleStudentManager();//需要换成自行设计的实现类
	
	public static ISubjectManager subjectManager=new ExampleSubjectManager();//需要换成自行设计的实现类
	
	public static IStudyTestManager studyTestManager=new ExampleStudyTestManager();//需要换成自行设计的实现类
	
	public static ISelectTestManager selectTestManager=new ExampleSelectTestManager();//需要换成自行设计的实现类
	
	public static ISpecialityManager specialityManager=new ExampleSpecialityManager();//需要换成自行设计的实现类
	
	public static ISchoolPlanManager schoolPlanManager=new ExampleSchoolPlanManager();//需要换成自行设计的实现类
	
	public static IMajorPlanManager majorPlanManager=new ExampleMajorPlanManager();//需要换成自行设计的实现类
}
