package com.awesome.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.awesome.account.vo.Account;
import com.awesome.profile.service.ProfileService;
import com.awesome.profile.vo.Profile;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Value("${api.bff.path}")
	private String serviceUrl;

	private ProfileService profileService;
	
	@Autowired
	public HomeController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("username") != null) {
			session.removeAttribute("username");
			session.removeAttribute("profile");			
		}
		
		model.addAttribute("apiconnect", serviceUrl);
		return "login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public Model login(Account account, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String username = account.getUsername();
		session.setAttribute("username", username);
		List<Profile> profiles = profileService.getProfiles(username);
		
		String defaultProfile = profiles.get(0).getId();
		session.setAttribute("profile", defaultProfile);
		logger.info("===== login profile: " + defaultProfile);
		
		return model;
	}
	
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public String home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return "redirect:/index";			
		} 
		return "index";
	}
	
	@RequestMapping(value = "/categories/{category}", method=RequestMethod.GET)
	public String category(HttpServletRequest request, @PathVariable(name="category") String category) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return "redirect:/index";			
		}
		
		logger.info("===== move category: " + category);
		return "category";
	}
	
	@RequestMapping(value = "/favorites", method=RequestMethod.GET)
	public String favorites(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return "redirect:/index";			
		}
		return "category";
	}
	
}
