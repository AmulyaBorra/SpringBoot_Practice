package com.Employee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpRestController{
	 private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping("/login")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate ";
	}
	
	@RequestMapping(value = "/employees", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE, 
                    MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Employee> getEmployees(){
		 log.debug("debug level log");
		List<Employee> list=employeeDAO.getAllEmployees();
		return list;
		
	}
	@RequestMapping(value="/employees/{empNo}",method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeDAO.getEmployee(empNo);
	}
	
	
	  @RequestMapping(value = "/employee1", 
	            method = RequestMethod.POST, 
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public Employee addEmployee(@RequestBody Employee emp) {
	 
	        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
	 
	        return employeeDAO.addEmployee(emp);
	    }
	  @RequestMapping(value = "/employee", 
	            method = RequestMethod.PUT, 
	            produces = { MediaType.APPLICATION_JSON_VALUE, 
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public Employee updateEmployee(@RequestBody Employee emp) {
	 
	        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
	 
	        return employeeDAO.updateEmployee(emp);
	    }
	 
	    @RequestMapping(value = "/employee2/{empNo}", 
	            method = RequestMethod.DELETE, 
	            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public void deleteEmployee(@PathVariable("empNo") String empNo) {
	 
	        System.out.println("(Service Side) Deleting employee: " + empNo);
	 
	        employeeDAO.deleteEmployee(empNo);
	
	    }
	
}