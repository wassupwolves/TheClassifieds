package edu.neumont.pro150;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public ModelAndView hello(ModelAndView model) {
		model.setViewName("welcome");
		model.addObject("name", "John Doe");
		return model;
	}
	
	@GetMapping("/")
	public ModelAndView homePage(ModelAndView model){
		model.setViewName("home");
//		model.addObject();
		return model;
	}

}
