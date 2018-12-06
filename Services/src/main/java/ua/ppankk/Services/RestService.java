package ua.ppankk.Services;

import ua.ppankk.Services.DTO.ContactDTO;

import java.util.List;

public interface RestService {

    ContactDTO getContact(Long id);
    List<ContactDTO> getContactsFiltered(String regexp);
}
