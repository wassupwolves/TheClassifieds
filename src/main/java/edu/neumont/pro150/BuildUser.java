package edu.neumont.pro150;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuildUser{

	@RequestMapping(value="/BuildUser", method=RequestMethod.POST)
	public String listNotes(Model model,
							@RequestParam("username") String username, 
							@RequestParam("password") String password,
							@RequestParam("confirmpassword") String confirmPassword,
							@RequestParam("email") String email) {
//		User user;
		if(password.equals(confirmPassword)){
//			user = new User(username, password, email);
//			Message message;
		}
		else{
			
		}
		
			
		return "thankyou";
	}
	
}
