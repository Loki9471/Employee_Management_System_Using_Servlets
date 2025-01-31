package com.codegnan.factories;

import com.codegnan.service.EmployeeService;
import com.codegnan.service.EmployeeServiceImpl;

public class EmployeeServiceFactory {
private static EmployeeService employeeService=null;
static {
	employeeService=new EmployeeServiceImpl();
}
public static EmployeeService getEmployeeService() {
	return employeeService;
}
}
