package com.testyodi.testYodi.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testyodi.testYodi.models.Member;

public interface MembersRepository extends JpaRepository<Member, Integer> {
	
}
