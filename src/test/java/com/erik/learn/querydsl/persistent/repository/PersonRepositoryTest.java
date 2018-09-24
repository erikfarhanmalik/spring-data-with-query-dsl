package com.erik.learn.querydsl.persistent.repository;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.erik.learn.querydsl.persistent.model.Address;
import com.erik.learn.querydsl.persistent.model.AddressType;
import com.erik.learn.querydsl.persistent.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Test
	public void save_shouldReturnSavedObject() {

		Person erik = personRepository.save(Person.builder()
				.name("Erik")
				.addresses(Arrays.asList(
						Address.builder()
								.type(AddressType.HOME)
								.location("Cibiru")
								.build(),
						Address.builder()
								.type(AddressType.OFFICE)
								.location("Pasteur")
								.build()
				))
				.build());
		assertThat(erik.getId()).isNotNull();
		assertThat(erik.getName()).isEqualToIgnoringCase("erik");
		assertThat(erik.getAddresses().size()).isEqualTo(2);
	}

	@Test
	public void findPersonWithOfficeAddress() {

		populateData();

		assertThat(personRepository.findAll().size()).isEqualTo(3);

		List<Person> personThatHasOfficeAddress = personRepository.findPersonWithOfficeAddress(AddressType.OFFICE);
		assertThat(personThatHasOfficeAddress.size()).isEqualTo(2);
		assertThat(personThatHasOfficeAddress.stream().map(Person::getName).collect(Collectors.joining(","))).isEqualToIgnoringCase("erik,kustian");
	}

	private void populateData() {

		Person erik = Person.builder()
				.name("Erik")
				.build();

		Person budi = Person.builder()
				.name("Budi")
				.build();

		Person kustian = Person.builder()
				.name("Kustian")
				.build();

		personRepository.saveAll(Arrays.asList(erik, budi, kustian));
		addressRepository.saveAll(Arrays.asList(
				Address.builder()
						.type(AddressType.HOME)
						.location("Cibiru")
						.person(erik)
						.build(),
				Address.builder()
						.type(AddressType.OFFICE)
						.location("Pasteur")
						.person(erik)
						.build(),
				Address.builder()
						.type(AddressType.HOME)
						.location("Cikajang")
						.person(budi)
						.build(),
				Address.builder()
						.type(AddressType.HOME)
						.location("Cimahi")
						.person(kustian)
						.build(),
				Address.builder()
						.type(AddressType.OFFICE)
						.location("Pasteur")
						.person(kustian)
						.build()));
	}
}