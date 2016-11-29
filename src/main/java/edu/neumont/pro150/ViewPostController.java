package edu.neumont.pro150;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Post;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Controller
public class ViewPostController {
	
	@Autowired
	private EMQueryUtil consumerdb;
	
	@RequestMapping(value="/ViewPost/{post_id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView redirectSignUp(
			Model model,
			HttpServletRequest request,
			@PathVariable(name="post_id") int post_id
		){
		HttpSession session = request.getSession(true);
		
		Post post = consumerdb.namedQuerySingleResult("single_post", "post_id", post_id, Post.class);
		session.setAttribute("current_post", post);		
		
		return new ModelAndView("ViewPost", "msg", "Showing single post!");
	}
	
	

}
