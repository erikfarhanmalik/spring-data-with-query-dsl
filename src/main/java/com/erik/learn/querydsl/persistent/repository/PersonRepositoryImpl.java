package com.erik.learn.querydsl.persistent.repository;

import java.util.ArrayList;
import java.util.List;

import com.erik.learn.querydsl.persistent.model.AddressType;
import com.erik.learn.querydsl.persistent.model.Person;
import com.erik.learn.querydsl.persistent.model.QPerson;

import org.springframework.beans.factory.annotation.Autowired;

public class PersonRepositoryImpl implements PersonRepositoryQueryDsl {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findPersonWithOfficeAddress(AddressType addressType) {

		List<Person> people = new ArrayList<>();
		personRepository.findAll(QPerson.person.addresses.any().type.eq(addressType)).forEach(people::add);
		return people;
	}
}
