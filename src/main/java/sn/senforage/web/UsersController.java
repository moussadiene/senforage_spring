package sn.senforage.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.senforage.dao.UsersRepository;
import sn.senforage.entities.Users;
import sn.senforage.dao.RolesRepository;

@Controller

@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	@GetMapping(value = {"/index",""})
	private String index(Model model) {
		
		model.addAttribute("users", new Users());
		//model.addAttribute("roles", new Roles());
		
		model.addAttribute("listeRoles", rolesRepository.findAll());
		model.addAttribute("listeUsers", usersRepository.findAll());
		
		return "users/listeUsers";

	}
	@PostMapping(value = "/add")
	private String ajout(Model model,Users users) {
		String password = users.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
		users.setPassword( passwordEncoder.encode(password));
		users.setEtat(1);
		usersRepository.save(users);
		model.addAttribute("success", "données enregistrer");

		return "redirect:/users/index";
	}	
	
	@RequestMapping(value = "/show", method=RequestMethod.GET)
	public String show(Model model,Long users) {
		Users u = new Users();
		u =  usersRepository.findById(users).get();
		model.addAttribute("listeRoles", rolesRepository.findAll());
		model.addAttribute("users",u);
		return "users/show";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(Model model,Long users) {
	
		usersRepository.delete(usersRepository.findById(users).get());
		
		model.addAttribute("success", "donnée supprimée");

		return "redirect:/users/index";
	}
	
}
