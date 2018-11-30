package ua.ppankk.DAO.repository.impl;

import org.springframework.stereotype.Repository;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.DAO.repository.ContactDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<Contact> contactEntityType = metamodel.entity(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(contactEntityType);
        criteriaQuery.where(
                criteriaBuilder.like(contactRoot.get("name"),regex)
        );
        criteriaQuery.select(contactRoot);

        TypedQuery<Contact> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Contact> getAllContacts() {
        TypedQuery<Contact> findAllContacts =
                entityManager.createQuery("SELECT c FROM Contact c", Contact.class);

        return findAllContacts.getResultList();
    }
}
