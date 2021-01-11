package com.SEVO.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SEVO.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName (String username);
	public boolean existsByEmailAddress (String emailadress);
	public boolean existsByUserName(String username);
	
	
	@Override
	@Query("select e from #{#entityName} e where e.deleteFlag=false and e.id!=1")
	public List<User> findAll();

	@Query("select e from #{#entityName} e where e.deleteFlag=true")
	public List<User> recycleBin();

	@Modifying
	@Transactional
	@Query("update #{#entityName} e set e.deleteFlag=true where e.id=:userId")
	public void softDelete(Integer userId);
	
	@Modifying
	@Transactional
	@Query("update #{#entityName} e set e.deleteFlag=false where e.id=:userId")
	public void restoreUser(Integer userId);
	
}
