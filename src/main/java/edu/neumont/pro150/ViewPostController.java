package edu.neumont.pro150;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Consumer;
import edu.neumont.pro150.datamodels.Post;
import edu.neumont.pro150.datamodels.PostImage;
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
		Consumer user = post.getConsumer();
		session.setAttribute("user", user);
		List<PostImage> images = consumerdb.namedQueryResult("postImage_all", "post_id", post.getPost_id(), PostImage.class);
		session.setAttribute("postImages", images);
		
		return new ModelAndView("ViewPost", "msg", "Showing single post!");
	}
	
	@RequestMapping(value="/UserPosts/{user_name}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView redirectUserPosts(
			ModelAndView model,
			HttpServletRequest request,
			@PathVariable(name="user_name") String user_name
			){
		Consumer user = consumerdb.namedQuerySingleResult("single_consumer", "username", user_name, Consumer.class);
		
		List<Post> userPosts = consumerdb.namedQueryResult("singleUser_all", "user_id", user.getUser_id(), Post.class);
		
		model.setViewName("UserPosts");
		model.addObject("userPosts", userPosts);
		model.addObject("msg", "Displaying posts from " + user.getUser_name());
		
		return model;
	}
}
