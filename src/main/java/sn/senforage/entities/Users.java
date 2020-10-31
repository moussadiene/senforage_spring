package sn.senforage.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Users implements Serializable{

	private static final long serialVersionUID = 7910321553880971720L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom",nullable = false,length = 100)
	private String nom;
	@Column(name = "prenom",nullable = false,length = 100)
	private String prenom;
	@Column(name = "email",nullable = false, unique = true, length = 100)
	private String username;
	@Column(name = "password",nullable = false,length = 255)
	private String password;
	private int etat;
	 
//	@ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//            })
//    @JoinTable(name = "users_roles",
//            joinColumns = { @JoinColumn(name = "idUser") },
//            inverseJoinColumns = { @JoinColumn(name = "idRole") })
//	 private List<Roles> roles = new ArrayList<>();
	
	@ManyToMany(
			cascade=CascadeType.ALL,fetch=FetchType.EAGER
			)
    @JoinTable(name="users_roles",
        joinColumns = {
        		@JoinColumn(name="user", referencedColumnName="email")},
        inverseJoinColumns = {
        		@JoinColumn(name="role", referencedColumnName="role")}
    )
    private List<Roles> roles;
}
