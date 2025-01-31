package com.codegnan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.codegnan.dto.Employee;
import com.codegnan.factories.EmployeeServiceFactory;
import com.codegnan.service.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int eno = 0;
        String ename = "";
        float esal = 0.0f;
        String eaddr = "";
        Employee employee = null;
        EmployeeService employeeService = EmployeeServiceFactory.getEmployeeService();
        String status = "";
        RequestDispatcher requestDispatcher = null;
        String requestURI = request.getRequestURI();

        if (requestURI.endsWith("add.do")) {
            try {
                eno = Integer.parseInt(request.getParameter("eno"));
                ename = request.getParameter("ename");
                esal = Float.parseFloat(request.getParameter("esal"));
                eaddr = request.getParameter("eaddr");

                employee = new Employee();
                employee.setEno(eno);
                employee.setEname(ename);
                employee.setEsal(esal);
                employee.setEaddr(eaddr);

                status = employeeService.addEmployee(employee);
                if (status.equalsIgnoreCase("existed")) {
                    requestDispatcher = request.getRequestDispatcher("/existed.html");
                } else if (status.equalsIgnoreCase("success")) {
                    requestDispatcher = request.getRequestDispatcher("/success.html");
                } else {
                    requestDispatcher = request.getRequestDispatcher("/failure.html");
                }
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestURI.endsWith("search.do")) {
            try {
                eno = Integer.parseInt(request.getParameter("eno"));
                employee = employeeService.searchEmployee(eno);
                if (employee == null) {
                    requestDispatcher = request.getRequestDispatcher("/noexisted.html");
                    requestDispatcher.forward(request, response);
                } else {
                    out.println("<html><body bgcolor='red' ><center><br><br><br>");
                    out.println("<table border='2' align='center' >");
                    out.println("<tr><td>Employee Number</td><td>" + employee.getEno() + "</td></tr>");
                    out.println("<tr><td>Employee Name</td><td>" + employee.getEname() + "</td></tr>");
                    out.println("<tr><td>Employee Salary</td><td>" + employee.getEsal() + "</td></tr>");
                    out.println("<tr><td>Employee Address</td><td>" + employee.getEaddr() + "</td></tr>");
                    out.println("</table></body></html>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestURI.endsWith("editform.do")) {
            try {
                eno = Integer.parseInt(request.getParameter("eno"));
                employee = employeeService.searchEmployee(eno);
                if (employee == null) {
                    requestDispatcher = request.getRequestDispatcher("/noexisted.html");
                    requestDispatcher.forward(request, response);
                } else {
                    out.println("<html><body bgcolor='red' ><center><br><br><br>");
                    out.println("<form method='post' action='update.do'>");
                    out.println("<table align='center' >");
                    out.println("<tr><td>Employee Number</td><td>" + employee.getEno() + "</td></tr>");
                    out.println("<input type='hidden' name='eno' value='" + employee.getEno() + "'/>");
                    out.println("<tr><td>Employee Name</td><td><input type='text' name='ename' value='" + employee.getEname() + "'/></td></tr>");
                    out.println("<tr><td>Employee Salary</td><td><input type='text' name='esal' value='" + employee.getEsal() + "'/></td></tr>");
                    out.println("<tr><td>Employee Address</td><td><input type='text' name='eaddr' value='" + employee.getEaddr() + "'/></td></tr>");
                    out.println("<tr><td><input type='submit' value='Update' /></td></tr>");
                    out.println("</table></form></center></body></html>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestURI.endsWith("update.do")) {
            try {
                eno = Integer.parseInt(request.getParameter("eno"));
                ename = request.getParameter("ename");
                esal = Float.parseFloat(request.getParameter("esal"));
                eaddr = request.getParameter("eaddr");

                employee = new Employee();
                employee.setEno(eno);
                employee.setEname(ename);
                employee.setEsal(esal);
                employee.setEaddr(eaddr);

                status = employeeService.updateEmployee(employee);

                if (status.equalsIgnoreCase("success")) {
                    requestDispatcher = request.getRequestDispatcher("/success.html");
                } else {
                    requestDispatcher = request.getRequestDispatcher("/failure.html");
                }
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestURI.endsWith("delete.do")) {
            try {
                eno = Integer.parseInt(request.getParameter("eno"));
                employee = employeeService.searchEmployee(eno);
                if (employee == null) {
                    requestDispatcher = request.getRequestDispatcher("/noexisted.html");
                } else {
                    status = employeeService.deleteEmployee(eno);
                    if (status.equalsIgnoreCase("success")) {
                        requestDispatcher = request.getRequestDispatcher("/success.html");
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/failure.html");
                    }
                }
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
