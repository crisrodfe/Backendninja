package com.crisrodfe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crisrodfe.model.UserCredential;

@Controller
public class LoginController 
{
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@GetMapping("/")
	public String redirectToLogin(){
		LOG.info("METHOD: redirectToLogin()");
		return("redirect:/login");
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
								@RequestParam(name="error", required=false) String error,
								@RequestParam(name="logout", required=false) String logout)
	{

		LOG.info("METHOD: showLoginForm() -- PARAMS: erro = " + error +"--  logout:"+ logout);	
		
		model.addAttribute("error",error);
		model.addAttribute("userCredentials",new UserCredential());
		model.addAttribute("logout",logout);
		
		LOG.info("Returning to login view");
		return "login";
	}
	
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential)
	{
		LOG.info("METHOD loginCheck() -- PARAMS: "+ userCredential.toString());
		
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")){
			LOG.info("Returning to contacts view");
			return "contacts";
		}else{
			LOG.info("Returning to login?erro");
			return "redirect:/login?error";
		}

	}
}
