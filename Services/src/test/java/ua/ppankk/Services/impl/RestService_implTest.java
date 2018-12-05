package ua.ppankk.Services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.RestService;
import ua.ppankk.Services.converters.ContactConverter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-servicesTest.xml")
@Transactional
public class RestService_implTest {

    @Autowired
    private RestService restService;

    @Autowired
    private ContactConverter contactConverter;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void getContact() {
        Contact actual = new Contact(null, "zzzzzzzz");

        entityManager.persist(actual);
        Contact contact1 = contactConverter.toEntity(restService.getContact(actual.getId()));

        assertEquals(actual, contact1);
    }

    @Test
    public void getAllContacts() {
        Contact actual1 = new Contact(null, "Azzzzzzz");
        Contact actual2 = new Contact(null, "yyeyyyyy");

        entityManager.persist(actual1);
        entityManager.persist(actual2);
        List<ContactDTO> contactList = restService.getAllContacts();
        assertTrue(contactList.contains(contactConverter.toDto(actual1))
                && contactList.contains(contactConverter.toDto(actual2)));
    }

    @Test
    public void getContactsFiltered() {
        Contact actual1 = new Contact(null, "Azzzzzzz");
        Contact actual2 = new Contact(null, "yyeyyyyy");

        entityManager.persist(actual1);
        entityManager.persist(actual2);
        List<ContactDTO> contactList = restService.getContactsFiltered("^A.*$");
        assertTrue(!contactList.contains(contactConverter.toDto(actual1))
                && contactList.contains(contactConverter.toDto(actual2)));
    }
}