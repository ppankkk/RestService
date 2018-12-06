package ua.ppankk.Web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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

    private Logger logger = Logger.getLogger(RestServiceController.class);

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
    public @ResponseBody String getContactsFiltered(@RequestParam(name = "nameFilter") String regex){
        logger.info("Regex: " + regex);
        String ret = "";
        List<ContactDTO> contactsFiltered = restService.getContactsFiltered(regex);
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        ArrayNode rootNode = factory.arrayNode();
        contactsFiltered.forEach(contactDTO -> rootNode.addPOJO(contactDTO));

        try {
            ret = mapper.writer()
                    .withRootName("contacts")
                    .writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
