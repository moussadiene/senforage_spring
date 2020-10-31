package sn.senforage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.senforage.dao.RolesRepository;
import sn.senforage.entities.Roles;

@Controller
@RequestMapping(value = "/roles")
public class RolesController {

	@Autowired
	private RolesRepository rolesRepository;
	
	
	@RequestMapping(value = {"/index",""}, method=RequestMethod.GET)
	public String index(Model model) {
		
		List<Roles> roles=  rolesRepository.findAll();
		
		//Page<Roles> roles = roleRepository.findAll(new Page)
		model.addAttribute("listeRoles",roles); 
		model.addAttribute("roles", new Roles());
		return "roles/listeRoles";
	
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addRoles(Model model,Roles role) {
		rolesRepository.save(role);
		model.addAttribute("success", "données enregistrer");

		return "redirect:/roles/index";
	}
	
	@RequestMapping(value = "/show", method=RequestMethod.GET)
	public String show(Model model,Long role) {
		model.addAttribute("roles", rolesRepository.findById(role).get());
		return "roles/show";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(Model model,Long role) {
		Roles r = new Roles();
		r = rolesRepository.findById(role).get();
		rolesRepository.delete(r);
		model.addAttribute("success", "donnée supprimée");

		return "redirect:/roles/index";
	}
}
