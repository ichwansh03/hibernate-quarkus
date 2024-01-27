package com.ichwan.api.service;

import com.ichwan.api.dto.PersonRequest;
import com.ichwan.api.entity.Person;
import com.ichwan.api.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public void create(PersonRequest request) {
        Person person = new Person();

        person.setFirstname(request.firstname());
        person.setLastname(request.lastname());
        person.setAddress(request.address());
        personRepository.persist(person);
    }

    public Person getById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> getAll() {
        return personRepository.listAll();
    }

    public void update(Long id, PersonRequest request) {
        Person person = personRepository.findById(id);
        person.setFirstname(request.firstname());
        person.setLastname(request.lastname());
        person.setAddress(request.address());
        personRepository.persist(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
