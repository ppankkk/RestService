package ua.ppankk.Web.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.ppankk.Services.DTO.ContactDTO;
import ua.ppankk.Services.RestService;

import java.util.List;

@Controller
public class RestServiceController {

    private final RestService restService;

    Logger logger = Logger.getLogger(RestServiceController.class);

    @Autowired
    public RestServiceController(RestService restService) {
        this.restService = restService;
    }

    @RequestMapping(
            value = "/hello/contact",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody ContactDTO getContact(@RequestParam(name = "id") Long id){
        logger.info("Id: " + id);
        return restService.getContact(id);
    }

    @RequestMapping(
            value = "/hello/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody List<ContactDTO> getContactsFiltered(@RequestParam(name = "nameFilter") String regex){
        logger.info("Regex: " + regex);
        return restService.getContactsFiltered(regex);
    }

    @RequestMapping(
            value = "/hello/allcontacts",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public @ResponseBody List<ContactDTO> getAllContacts(){
        logger.info("Getting all contacts");
        return restService.getAllContacts();
    }
}