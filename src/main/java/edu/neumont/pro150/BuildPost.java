package edu.neumont.pro150;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import edu.neumont.pro150.datamodels.PostImage;
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
	public ModelAndView buildPost(ModelAndView model,
								HttpServletRequest request,
								@RequestParam("postTitle") String postTitle, 
								@RequestParam("postDescription") String postDescription,
								@RequestParam("postPrice") double postPrice,
								@RequestParam("postPictures") MultipartFile[] postPictures,
								@RequestParam(value="appliances", required=false) boolean appliances,
								@RequestParam(value="auto", required=false) boolean auto,
								@RequestParam(value="electronics", required=false) boolean electronics,
								@RequestParam(value="entertainment", required=false) boolean entertainment,
								@RequestParam(value="furniture", required=false) boolean furniture,
								@RequestParam(value="other", required=false) boolean other) throws IOException
	{
		HttpSession session = request.getSession(true);
		Consumer consumer = (Consumer) session.getAttribute("currentConsumer");
		Post post = new Post();
		post.setConsumer(consumer);
		post.setPost_title(postTitle);
		post.setPost_description(postDescription);
		post.setPost_price(postPrice);
		
		//post.setPost_pictures(getPicturePaths(postPictures));
		Set<PostImage> images = new HashSet<>();
		for(MultipartFile f : postPictures)
		{
			images.add(getPostImage(f, post));
		}
		post.setImages(images);
		
//		MUST ADD MORE .PUTS TO ADD MORE TAGS
		tags.put("appliances", appliances);
		tags.put("auto", auto);
		tags.put("electronics", electronics);
		tags.put("entertainment", entertainment);
		tags.put("furniture", furniture);
		tags.put("other", other);
		
		post.setPost_tags(addTagValues());
		post.setPost_date(getDate());
		
		consumerdb.insertORupdate(post);
		
		List<Post> posts = consumerdb.namedQueryResult("post_all", Post.class);
		model.addObject("posts", posts);
		model.addObject("username", consumer.getUser_name());
		model.setViewName("home");
		model.addObject("msg", postTitle + " post created!");
		
		return model;
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
	
	
	
	private PostImage getPostImage(MultipartFile f, Post post) throws IllegalStateException, IOException {
		String filePath = "C:\\web\\images\\" + f.getOriginalFilename();
		String urlPath = "http://10.10.14.30/images/" + f.getOriginalFilename();
		
		PostImage postImage = new PostImage();
		postImage.setPost(post);
		postImage.setUrl(urlPath);
		postImage.setAbsolutepath(filePath);
		
		File convFile = new File(filePath);
        f.transferTo(convFile);
		
		postImage.setUpload_datetime(getDate());
		return postImage;
	}
	/*
	 * No good
	private String getPicturePaths(MultipartFile[] files) throws IOException{
		String picturePaths = "";
		for(MultipartFile file : files){
			File convFile = new File("C:\\web\\images\\" + file.getOriginalFilename());
	        file.transferTo(convFile);
	        String seperator = "$$$" + "10.10.14.30/images/" + file.getOriginalFilename();
	        picturePaths += seperator;
		}		
		picturePaths.replaceFirst("$$$", "");
		return picturePaths;
	}
	*/

}
