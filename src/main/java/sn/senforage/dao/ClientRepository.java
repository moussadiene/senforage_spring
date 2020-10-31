package sn.senforage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.senforage.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
