package ua.ppankk.DAO.repository;

import ua.ppankk.DAO.model.Contact;

import java.util.List;

public interface ContactDAO {

    Contact getContact(Long id);
    List<Contact> gelFilteredContacts(String regex);
}
