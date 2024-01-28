package com.ichwan.api.service;

import com.ichwan.api.dto.PersonRequest;
import com.ichwan.api.entity.Person;
import com.ichwan.api.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @Transactional
    public void create(PersonRequest request) {
        Person person = new Person();

        person.setFirstname(request.firstname());
        person.setLastname(request.lastname());
        person.setAddress(request.address());
        personRepository.persist(person);
    }

    public Person getById(Long id) {
        return personRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Person with id " + id + " not found"));
    }

    public List<Person> getAll() {
        return personRepository.listAll();
    }

    @Transactional
    public void update(Long id, PersonRequest request) {
        personRepository.findByIdOptional(id)
                .ifPresent(person -> {
                    person.setFirstname(request.firstname());
                    person.setLastname(request.lastname());
                    person.setAddress(request.address());
                    personRepository.persist(person);
                });
    }

    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
