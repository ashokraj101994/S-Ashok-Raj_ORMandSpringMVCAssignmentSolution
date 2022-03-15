package com.greatlearning.CRM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Customer")
public class ControllerFile {
    
	@Autowired
	Services service;


	@RequestMapping("/showAll")
	public String listBooks(Model theModel) {
       List<Customer> cust = service.showAll();
       theModel.addAttribute("Customer",cust);
       return "Customer-list";
	}
	
	@RequestMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {

		System.out.println(id);
		Customer cust;
		if(id!=0)
		{
			cust=service.findById(id);
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			cust.setEmail(email);
		}
		else {
			cust=new Customer(firstName, lastName, email);
		}
        
		
		service.save(cust);
		return "redirect:/Customer/showAll";
		
		
	}	
	
	@RequestMapping("/addCustomer")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer customer = new Customer();

		theModel.addAttribute("Customer", customer);

		return "ModificationForm";
	}
	@RequestMapping("/updateCustomer")
	public String showFormForUpdate(@RequestParam("custId") int theId,
			Model theModel) {

		// get the Book from the service
		Customer customer = service.findById(theId);


		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", customer);

		// send over to our form
		return "ModificationForm";		
}  
	@RequestMapping("/delete")
	public String delete(@RequestParam("custId") int theId) {

		// delete the Book
		service.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/Customer/showAll";
	
}

}














