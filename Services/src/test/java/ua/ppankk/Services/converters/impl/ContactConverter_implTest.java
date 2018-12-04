package ua.ppankk.Services.converters.impl;

import org.junit.Test;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.Services.DTO.ContactDTO;

import static org.junit.Assert.*;

public class ContactConverter_implTest {

    @Test
    public void toDto() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Vasya");
        ContactDTO contactDTO = new ContactDTO(1L, "Vasya");

        assertEquals(contactDTO, new ContactConverter_impl().toDto(contact));
    }
}