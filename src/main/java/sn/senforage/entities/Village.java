package sn.senforage.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Village implements Serializable{

	private static final long serialVersionUID = -8226925932824392681L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "nom", length = 150)
	private String nomVillage;
	
	@OneToMany(mappedBy="village", targetEntity=Client.class, fetch = FetchType.LAZY)
	private List<Client> clients;

}
