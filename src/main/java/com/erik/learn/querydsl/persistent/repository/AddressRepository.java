package com.erik.learn.querydsl.persistent.repository;

import com.erik.learn.querydsl.persistent.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, QuerydslPredicateExecutor {
}
