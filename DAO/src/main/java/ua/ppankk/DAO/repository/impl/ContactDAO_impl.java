package ua.ppankk.DAO.repository.impl;

import org.springframework.stereotype.Repository;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.DAO.repository.ContactDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

@Repository
public class ContactDAO_impl implements ContactDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contact getContact(Long id) {
        return entityManager.find(Contact.class, id);
    }

    @Override
    public List<Contact> gelFilteredContacts(String regex) {
        Query query = entityManager.createQuery("From Contact");
        int pageSize = 100;
        Query queryTotal = entityManager.createQuery("Select count(c.id) from Contact c");
        long countResult = (long)queryTotal.getSingleResult();
        int maxPageNumber =  (int)ceil(countResult / pageSize) + 1;
        query.setMaxResults(pageSize);
        List<Contact> result = new ArrayList<>();
        for (int pageNumber = 1; pageNumber <= maxPageNumber; pageNumber++)
        {
            query.setFirstResult((pageNumber-1)*pageSize);
            query.getResultList().forEach(o -> {
                Contact contact = (Contact)o;
                if(!contact.getName().matches(regex))
                    result.add(contact);
            });
        }
        System.out.println(maxPageNumber);
        return result;
    }
}
