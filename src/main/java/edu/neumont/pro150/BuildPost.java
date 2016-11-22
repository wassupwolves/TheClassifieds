package edu.neumont.pro150;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Consumer;
import edu.neumont.pro150.datamodels.Post;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Controller
public class BuildPost {
	
	private HashMap<String, Boolean> tags = new HashMap<String, Boolean>();
	
	@Autowired
	private EMQueryUtil consumerdb;
	
	@RequestMapping(value="/MakePost", method=RequestMethod.GET)
	public ModelAndView redirectSignUp(){
		return new ModelAndView("MakePost", "msg", "Please fill in the form to create a post");
	}
	
	@RequestMapping(value="/BuildPost", method=RequestMethod.POST)
	public ModelAndView buildPost(Model model,
								HttpServletRequest request,
								@RequestParam("postTitle") String postTitle, 
								@RequestParam("postDescription") String postDescription,
								@RequestParam("postPrice") double postPrice,
								@RequestParam("postPictures") MultipartFile[] postPictures,
								@RequestParam(value="appliances", required=false) boolean appliances,
								@RequestParam(value="auto", required=false) boolean auto,
								@RequestParam(value="electronics", required=false) boolean electronics,
								@RequestParam(value="entertainment", required=false) boolean entertainment,
								@RequestParam(value="furniture", required=false) boolean furniture) throws IOException
	{
		HttpSession session = request.getSession(true);
		Consumer consumer = (Consumer) session.getAttribute("currentConsumer");
		Post post = new Post();
		post.setConsumer(consumer);
		post.setPost_title(postTitle);
		post.setPost_description(postDescription);
		post.setPost_price(postPrice);
		
		post.setPost_pictures(getPicturePaths(postPictures));
		
//		MUST ADD MORE .PUTS TO ADD MORE TAGS
		tags.put("appliances", appliances);
		tags.put("auto", auto);
		tags.put("electronics", electronics);
		tags.put("entertainment", entertainment);
		tags.put("furniture", furniture);
		
		post.setPost_tags(addTagValues());
		post.setPost_date(getDate());
		
		consumerdb.insertORupdate(post);
		
		return new ModelAndView("home", "msg", postTitle + " post created!");
	}
	
	private String addTagValues(){
		String finalTagString = "";
		for(String key : tags.keySet()){
			if(tags.get(key)){
				String tagToAdd = "$$$" + key;
				finalTagString += tagToAdd;
			}
		}
		finalTagString.replaceFirst("$$$", "");
		return finalTagString;
	}
	
	private Date getDate(){
		Date date = new Date(Calendar.getInstance().getTime().getTime());	
		return date;
	}
	
	private String getPicturePaths(MultipartFile[] files) throws IOException{
		String picturePaths = "";
		for(MultipartFile file : files){
			File convFile = new File("C:\\web\\images\\" + file.getOriginalFilename());
	        file.transferTo(convFile);
	        String seperator = "$$$" + file.getOriginalFilename();
	        picturePaths += seperator;
		}		
		picturePaths.replaceFirst("$$$", "");
		return picturePaths;
	}

}
