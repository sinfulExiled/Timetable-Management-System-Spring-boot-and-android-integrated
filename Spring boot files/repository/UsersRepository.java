package com.cb007727.EEATimeTableManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cb007727.EEATimeTableManager.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	 Optional<Users> findByUserName(String userName);
	
}
