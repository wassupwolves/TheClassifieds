package edu.neumont.pro150;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Consumer;
import edu.neumont.pro150.datamodels.Post;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Controller
public class HelloController {

	@Autowired
	private EMQueryUtil consumerdb;
	
	@RequestMapping(value = {"/"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView homePage(ModelAndView model){
		
		List<Consumer> cons = consumerdb.namedQueryResult("consumer_all", Consumer.class);		
		List<Post> posts = consumerdb.namedQueryResult("post_all", Post.class);
		model.addObject("posts", posts);
		model.setViewName("home");
//		model.addObject();
		return model;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView returnHome(ModelAndView model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Consumer user = (Consumer) session.getAttribute("currentConsumer");
		if(user != null){
			model.addObject("username", user.getUser_name());								
			model.addObject("msg", "Home Page");
		}		
		
		List<Post> posts = consumerdb.namedQueryResult("post_all", Post.class);
		model.addObject("posts", posts);
		model.setViewName("home");
		
		return model;
	}

}
