package ua.ppankk.DAO.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.DAO.repository.ContactDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-daoTest.xml")
@Transactional
public class ContactDAO_implTest {

    @Autowired
    private ContactDAO contactDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void getContact() {
        Contact actual = new Contact(null, "zzzzzzzz");

        entityManager.persist(actual);
        Contact contact1 = contactDAO.getContact(actual.getId());

        assertEquals(actual, contact1);
    }

    @Test
    public void gelFilteredContacts() {
        assertTrue(false);
    }

    @Test
    public void getAllContacts() {
        Contact actual1 = new Contact(null, "zzzzzzzz");
        Contact actual2 = new Contact(null, "yyyyyyyy");

        entityManager.persist(actual1);
        entityManager.persist(actual2);
        List<Contact> contactList = contactDAO.getAllContacts();

        assertTrue(contactList.contains(actual1) && contactList.contains(actual2));
    }
}