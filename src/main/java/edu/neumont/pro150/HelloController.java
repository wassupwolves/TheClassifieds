package edu.neumont.pro150;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Consumer;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Controller
public class HelloController {

	@Autowired
	private EMQueryUtil consumerdb;
	
	@GetMapping("/hello")
	public ModelAndView hello(ModelAndView model) {
		model.setViewName("welcome");
		model.addObject("name", "John Doe");
		return model;
	}
	
	@GetMapping("/")
	public ModelAndView homePage(ModelAndView model){
		
		List<Consumer> cons = consumerdb.namedQueryResult("consumer_all", Consumer.class);
		
		
		model.setViewName("home");
//		model.addObject();
		return model;
	}

}
