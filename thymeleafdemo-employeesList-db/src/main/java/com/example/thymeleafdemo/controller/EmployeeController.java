package com.example.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thymeleafdemo.entity.Employee;
import com.example.thymeleafdemo.service.EmployeeService;
//import com.example.thymeleafdemo.model.Employee; used for initial employee object from memory not from db

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	//used for inmemory employee test
	/*private List<Employee> theEmployees;
	
	@PostConstruct
	private void loadData() {
		
		Employee empl1 = new Employee(1, "Leslie", "Andrews", "lislie@gmail.com");
		Employee empl2 = new Employee(1, "Emma", "Baumgarten", "emma@gmail.com");
		Employee empl3 = new Employee(1, "Avanie", "Gupta", "avanie@gmail.com");
		
		theEmployees = new ArrayList<>();
		
		theEmployees.add(empl1);
		theEmployees.add(empl2);
		theEmployees.add(empl3);
	}
	*/
	
	private EmployeeService employeeService;
	
	//one constructor the autowired inject is optionnal
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel){
		//theModel.addAttribute("employees", theEmployees); used for inmemory test
		List<Employee> theEmployees = employeeService.findAll();
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
	
		Employee theEmployee = employeeService.findById(theId);
		
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
	}

}
