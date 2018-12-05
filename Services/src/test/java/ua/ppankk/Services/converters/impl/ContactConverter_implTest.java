package ua.ppankk.Services.converters.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.Services.DTO.ContactDTO;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-servicesTest.xml")
public class ContactConverter_implTest {

    @Test
    public void toDto() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Vasya");
        ContactDTO contactDTO = new ContactDTO(1L, "Vasya");

        assertEquals(contactDTO, new ContactConverter_impl().toDto(contact));
    }

    @Test
    public void toEntity() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Vasya");
        ContactDTO contactDTO = new ContactDTO(1L, "Vasya");

        assertEquals(contact, new ContactConverter_impl().toEntity(contactDTO));
    }
}