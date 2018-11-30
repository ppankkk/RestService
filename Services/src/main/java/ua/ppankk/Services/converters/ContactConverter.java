package ua.ppankk.Services.converters;

import ua.ppankk.DAO.model.Contact;
import ua.ppankk.Services.DTO.ContactDTO;

public interface ContactConverter {

    ContactDTO toDto(Contact entity);
}
