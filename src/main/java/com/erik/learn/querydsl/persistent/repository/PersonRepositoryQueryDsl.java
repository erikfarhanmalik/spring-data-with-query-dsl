package com.erik.learn.querydsl.persistent.repository;

import java.util.List;

import com.erik.learn.querydsl.persistent.model.AddressType;
import com.erik.learn.querydsl.persistent.model.Person;

public interface PersonRepositoryQueryDsl {

	List<Person> findPersonWithOfficeAddress(AddressType addressType);
}
