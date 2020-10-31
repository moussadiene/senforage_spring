package sn.senforage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sn.senforage.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	@Query("SELECT u FROM Users u WHERE u.username=:email ")
    Users getUsersByEmail(@Param("email")String username);
}
