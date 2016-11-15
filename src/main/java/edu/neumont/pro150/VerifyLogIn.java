package edu.neumont.pro150;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.neumont.pro150.datamodels.Consumer;
import edu.neumont.pro150.hibernate5.emqueryutil.EMQueryUtil;

@Controller
public class VerifyLogIn {
	
	@Autowired
	private EMQueryUtil consumerdb;
	
	@RequestMapping(value="/VerifyLogIn", method=RequestMethod.POST)
	public ModelAndView verifyUser(Model model,
							@RequestParam("username") String username, 
							@RequestParam("password") String password){
		
		String hashedPassword = VerifyLogIn.handlePassword(password);
		Consumer checkConsumer = consumerdb.namedQuerySingleResult("single_consumer", "username", username, Consumer.class);	
		
		if(checkConsumer == null){			
			return new ModelAndView("SignIn", "msg", "That username doesn't exist");
		}		
		else if(!checkConsumer.getUser_password().equals(hashedPassword)){
			return new ModelAndView("SignIn", "msg", "That password doesn't match the username");
		}
		else{			
			return new ModelAndView("home", "msg", "User Signed In");
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
