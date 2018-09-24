package com.erik.learn.querydsl.persistent.repository;

import com.erik.learn.querydsl.persistent.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQueryDsl, QuerydslPredicateExecutor<Person> {
}
