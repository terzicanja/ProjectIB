package ib.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ib.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findOne(Long id);
	
	List<User> findByActiveFalse();
	
	List<User> findAllByEmail(String text);

}
