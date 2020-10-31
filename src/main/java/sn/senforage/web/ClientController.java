package sn.senforage.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.senforage.dao.ClientRepository;
import sn.senforage.dao.VillageRepository;
import sn.senforage.entities.Client;

@Controller

@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private VillageRepository villageRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping(value = {"/index",""})
	private String index(Model model) {
		
		model.addAttribute("client", new Client());
		//model.addAttribute("village", new Village());
		model.addAttribute("listeVillages", villageRepository.findAll());
		model.addAttribute("listeClients", clientRepository.findAll());
		
		return "clients/listeClient";

	}
	@PostMapping(value = "/add")
	private String save(Model model,Client client) {
		
		clientRepository.save(client);
		model.addAttribute("success", "données enregistrer");

		return "redirect:/clients/index";
	}
	
	@GetMapping(value = "/ajoutClient")
	private String ajout(Model model) {
		
		model.addAttribute("client", new Client());
		model.addAttribute("listeVillages", villageRepository.findAll());

		return "clients/ajoutClient";
	}
	
	@RequestMapping(value = "/show", method=RequestMethod.GET)
	public String show(Model model,Long client) {
		Client c = new Client();
		c =  clientRepository.findById(client).get();
		model.addAttribute("listeVillages", villageRepository.findAll());
		model.addAttribute("client",c);
		return "clients/show";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(Model model,Long client) {
	
		clientRepository.delete(clientRepository.findById(client).get());
		model.addAttribute("success", "donnée supprimée");

		return "redirect:/clients/index";
	}
	
}
