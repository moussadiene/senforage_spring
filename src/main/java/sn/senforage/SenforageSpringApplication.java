package sn.senforage;

//import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sn.senforage.dao.VillageRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class SenforageSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(SenforageSpringApplication.class, args);
		VillageRepository villageRepository = ctx.getBean(VillageRepository.class);
//		Village v = new Village();
//		v.setNomVillage("foundiougne");
//		villageRepository.save(v);
		villageRepository.findAll().forEach(v->System.out.println(v.getId() + " - " + v.getNomVillage()));
		int i = 0;
	    while (i < 2) {
	        String password = "papis";
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(password);

	        System.out.println(hashedPassword);
	        i++;
	    }
	}

//	@Override
//	public void run(String... args) throws Exception {
//		int i = 0;
//		 
////	    while (i < 2) {
////	        String password = "mosila21";
////	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////	        String hashedPassword = passwordEncoder.encode(password);
////
////	        System.out.println(hashedPassword);
////	        i++;
////	    }
//		
//	}
	

}
