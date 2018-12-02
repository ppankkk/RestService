package ua.ppankk.DAO.repository.impl;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.RegexpQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.stereotype.Repository;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.DAO.repository.ContactDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        entityManager.getTransaction().begin();

        RegexpQuery luceneQuery = new RegexpQuery(new Term("name", regex));
        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Contact.class);

        List result = jpaQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public List<Contact> getAllContacts() {
        TypedQuery<Contact> findAllContacts =
                entityManager.createQuery("SELECT c FROM Contact c", Contact.class);

        return findAllContacts.getResultList();
    }
}
