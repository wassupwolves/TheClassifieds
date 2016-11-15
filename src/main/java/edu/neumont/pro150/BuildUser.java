package edu.neumont.pro150;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import edu.neumont.pro150.datamodels.*;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;
       
@Controller
public class BuildUser{

	@Autowired
	private EMQueryUtil consumerdb;

	@RequestMapping(value="/BuildUser", method=RequestMethod.POST)
	public ModelAndView listNotes(Model model,
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			@RequestParam("confirmpassword") String confirmPassword,
			@RequestParam("email") String email) {
		
		if(password.equals(confirmPassword)){
			Consumer consumer = new Consumer();
			consumer.setUser_email(email);
			consumer.setUser_name(username);
			String secret = handlePassword(password);
			consumer.setUser_password(secret);
			consumerdb.insertORupdate(consumer);
			return new ModelAndView("VerifyLogIn");
		}
		else{
			return new ModelAndView("SignUp", "msg", "THE PASSWORDS DO NOT MATCH");
		}
	}

	public static String handlePassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Unable to find the algorithm requested.");
		}
		md.update(password.getBytes());
		byte[] result = md.digest();
		return Base64.getEncoder().encodeToString(result);

	}

}
