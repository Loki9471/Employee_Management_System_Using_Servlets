package com.codegnan.factories;

import com.codegnan.dao.EmployeeDao;
import com.codegnan.dao.EmployeeDaoImp1;

public class EmployeeDaoFactory {
public static EmployeeDao employeeDao=null;
static {
	employeeDao = new EmployeeDaoImp1();
	
}
public static EmployeeDao getEmployeeDao() {
	return employeeDao;
}
}



