package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.AuthenticationDto;
import com.example.thymeleaf.dto.RegisterDto;
import com.example.thymeleaf.exception.AlreadyExistsException;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired 
	private AuthorizationService authorizationService;


	@PostMapping(value = "/login")
	public String login(AuthenticationDto userAuthentication, Model model) {
		try{
			var userNamePassword = new UsernamePasswordAuthenticationToken(userAuthentication.getEmail(), userAuthentication.getPassword());
			Authentication auth = authenticationManager.authenticate(userNamePassword);
			User user = (User) auth.getPrincipal();
		}catch (Exception e){
			model.addAttribute("error", "E-mail ou senha incorretos. Tente novamente.");
			return "login";
		}

		return "redirect:/list";
	}

	@PostMapping("/subscribe")
	public String registerUser(@Valid @ModelAttribute("user") RegisterDto registerUser, BindingResult result, Model model) {
		try{
			if (result.hasErrors()) {
				model.addAttribute("errorMessage", "Erro no cadastro: " + result.getAllErrors().get(0).getDefaultMessage());
				return "subscribe";
			}

			authorizationService.save(registerUser);
		}catch(AlreadyExistsException e){
			model.addAttribute("error", "E-mail já existe");
			return "subscribe";
		}

		return "redirect:/list";
	}

	@GetMapping(value = "/login")
	public String home(){
		return "login";
	}

	@GetMapping("/subscribe")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "subscribe";
	}

	@GetMapping(value = "/principal")
	public String principal(){
		return "principal";
	}
}
