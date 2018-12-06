package ua.ppankk.Web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.RestService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-webTest.xml")
public class RestServiceControllerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private RestServiceController restServiceController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(restServiceController).build();
    }

    @Test
    public void getContact() throws Exception {
        Long contactId = 1L;
        ContactDTO contactDTO = new ContactDTO(contactId, "name");
        given(restService.getContact(contactId)).willReturn(contactDTO);

        String expected = "{\"Contact\":{\"id\":1,\"name\":\"name\"}}";

        String result = this.mockMvc
                .perform(get("/hello/contact?id=" + contactId))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);
    }

    /*
    * TODO: Need to fix that test. But that method works fine.
    *
    * I don't know how to test that method correctly
    * REGEX filtering takes place in ContactDAO,
    * because I should minimize the quantity of objects
    * for transfer from DAO to Service layers
    * If you change any ContactDTO.name to "Aname"
    * that test doesn't break, but according to regex it should break.
    *
    * */
    @Test
    public void getContactsFiltered() throws Exception {
        Long contactId1 = 1L;
        Long contactId2 = 2L;
        String regex = "^A.*$";
        List<ContactDTO> contactDTOS = new ArrayList<>();
        contactDTOS.add(new ContactDTO(contactId1, "name1"));
        contactDTOS.add(new ContactDTO(contactId2, "name2"));
        given(restService.getContactsFiltered(regex)).willReturn(contactDTOS);

        String expected = "{\"contacts\":[{\"Contact\":{\"id\":1,\"name\":\"name1\"}},{\"Contact\":{\"id\":2,\"name\":\"name2\"}}]}";

        String result = this.mockMvc
                .perform(get("/hello/contacts?nameFilter="+ regex))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);
    }
}