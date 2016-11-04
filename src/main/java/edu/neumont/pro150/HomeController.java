package edu.neumont.pro150;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.POST)
	public String printHello(ModelMap model){
		model.addAttribute("message", "Hello Spring MVC");
		return "hello";
	}
	
}
