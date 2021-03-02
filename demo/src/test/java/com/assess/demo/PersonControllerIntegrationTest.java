package com.assess.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assess.demo.exception.RecordNotFoundException;
import com.assess.demo.model.AuthenticationRequest;
import com.assess.demo.model.AuthenticationResponse;
import com.assess.demo.model.Person;
import com.assess.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private PersonService service; 
	
	@Autowired
	private WebApplicationContext context;
	

	
	@Test
	public void TestAuthenticationCheck() throws Exception {

		
	 mockMvc.perform(get("/api/persons")).andExpect(status().isUnauthorized());

		
	}
	

	private String ObtainAccessToken() throws Exception {
		AuthenticationRequest user = new AuthenticationRequest();
		user.setUsername("foo");
		user.setPassword("foo");

	    MvcResult mvcResult = mockMvc.perform(post("/authenticate").contentType("application/json")
					.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print()).andReturn();

	    String res = mvcResult.getResponse().getContentAsString(); 
	    
	    AuthenticationResponse auth = new ObjectMapper().readValue(res, AuthenticationResponse.class);  

	    System.out.println(auth.getJwt());
	    
	    return auth.getJwt();
	}
	

	@Test
	public void TestgetAllPerson() throws Exception {

		
		MvcResult mvcResult = mockMvc.perform(get("/api/persons").accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+ObtainAccessToken()).with(csrf())).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		Person [] resPersons = objectMapper.readValue(actualRes, Person[].class);
		
		assertEquals(resPersons.length, service.getAllPersons().size());
		
	}
	

	@Test
	public void TestPersonById() throws Exception {
		//Person person;
		Long personId =1L;
		
		MvcResult mvcResult = mockMvc.perform(get("/api/persons/{id}",personId).accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+ObtainAccessToken()).with(csrf())).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(service.getPersonById(personId));
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
	}
	

	@Test
    public void TestCreatePerson() throws Exception {
		

		Person newPerson =new Person("Amelia", "Jake", 23, "red");
		Person savePerson;

		
		MvcResult mvcResult = mockMvc.perform(post("/api/persons").contentType("application/json")
				.content(objectMapper.writeValueAsString(newPerson)).accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+ObtainAccessToken()).with(csrf())).andExpect(status().isOk()).andDo(print()).andReturn();
		
		
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		savePerson =  objectMapper.readValue(actualRes,Person.class);
		String expectedRes = objectMapper.writeValueAsString(service.getPersonById(savePerson.getId()));
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
    	
    }

	

	@Test
    public void TestUpdatePerson() throws Exception {
		
		Long id =2L;
		Person newPerson =new Person("Amelia", "Jake", 46, "red");
		
		MvcResult mvcResult = mockMvc.perform(put("/api/persons/{id}",id).contentType("application/json")
				.content(objectMapper.writeValueAsString(newPerson)).accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+ObtainAccessToken()).with(csrf())).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(service.getPersonById(id));
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
    	
    }
	

	@Test
    public void TestDeletePerson() throws Exception {
		Long personId =1L;
		
		mockMvc.perform(delete("/api/persons/{id}",personId).accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+ObtainAccessToken()).with(csrf())).andExpect(status().isOk());
		
		Assertions.assertThrows(RecordNotFoundException.class,() -> {service.getPersonById(personId);});
		
	}

}
