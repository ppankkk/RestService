package ua.ppankk.Web.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ppankk.DAO.repository.ContactDAO;
import ua.ppankk.DAO.repository.impl.ContactDAO_impl;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.RestService;
import ua.ppankk.Services.converters.ContactConverter;
import ua.ppankk.Services.converters.impl.ContactConverter_impl;
import ua.ppankk.Services.impl.RestService_impl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-webTest.xml")
public class RestServiceControllerTest {

    private ContactDAO contactDAO = new ContactDAO_impl();
    private ContactConverter contactConverter = new ContactConverter_impl();
    private RestService restService =
            new RestService_impl(contactDAO, contactConverter);

    public RestServiceControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContact() {
        Long id = 1L;
        ContactDTO contactDTO = new ContactDTO(id, "Vasya");

        ContactDTO returnedDTO = restService.getContact(id);
        assertEquals(contactDTO, returnedDTO);
    }

    @Test
    public void getContactsFiltered() {
    }

    @Test
    public void getAllContacts() {
    }
}