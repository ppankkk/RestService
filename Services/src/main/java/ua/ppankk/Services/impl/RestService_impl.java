package ua.ppankk.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.DAO.repository.ContactDAO;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.RestService;
import ua.ppankk.Services.converters.ContactConverter;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestService_impl implements RestService {

    private final ContactDAO contactDAO;
    private final ContactConverter contactConverter;

    @Autowired
    public RestService_impl(ContactDAO contactDAO, ContactConverter contactConverter) {
        this.contactDAO = contactDAO;
        this.contactConverter = contactConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public ContactDTO getContact(Long id) {
        return contactConverter.toDto(contactDAO.getContact(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactDTO> getContactsFiltered(String regexp) {
        List<Contact> contacts = contactDAO.gelFilteredContacts(regexp);
        List<ContactDTO> contactsDTO = new ArrayList<>();

        contacts.forEach(
                contact -> contactsDTO.add(contactConverter.toDto(contact)));

        return contactsDTO;
    }
}
