package com.ichwan.books.api;

import com.ichwan.api.entity.Person;
import com.ichwan.api.service.PersonService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class PersonServiceTest {

    @InjectMock
    PersonService personService;

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1L,"Test","Test","Test");
        personService = Mockito.mock(PersonService.class);
    }

    @Test
    void testMockPersonServiceGetAll() {
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Mockito.when(personService.getAll()).thenReturn(personList);
    }

    @Test
    void testMockPersonServiceGetById() {
        Mockito.when(personService.getById(1L)).thenReturn(person);
    }

    @Test
    void testMockPersonServiceCreate() {

    }
}
