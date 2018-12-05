package ua.ppankk.Services.converters.impl;

import org.springframework.stereotype.Component;
import ua.ppankk.DAO.model.Contact;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.converters.ContactConverter;

@Component
public class ContactConverter_impl implements ContactConverter {

    @Override
    public ContactDTO toDto(Contact entity) {
        return new ContactDTO(entity.getId(), entity.getName());
    }

    @Override
    public Contact toEntity(ContactDTO contactDTO) {
        return new Contact(contactDTO.getId(), contactDTO.getName());
    }
}
