package com.assess.demo;

import static org.assertj.core.api.Assertions.assertThat;
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

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assess.demo.model.Person;
import com.assess.demo.config.JwtAuthenticationEntryPoint;
import com.assess.demo.config.JwtUtil;
import com.assess.demo.controller.PersonController;
import com.assess.demo.service.MyUserDetailsService;
import com.assess.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest(PersonController.class)
//@AutoConfigureMockMvc
public class PersonControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PersonService service; 
	
	@Autowired
	private WebApplicationContext context;
	
	
	//private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2MTQzNzE2NTUsImlhdCI6MTYxNDMzNTY1NX0.aFHIc4jVxw3VFP485GJKFrW4gTAFhC-i17mC1PTfHq41";
	
	

	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		
	}
	
	@WithMockUser("foo")  //@WithMockUser(username="customUsername@example.io", roles={"USER_ADMIN"})
	@Test
	public void TestgetAllPerson() throws Exception {
		
		List<Person> list = new ArrayList<>();
		list.add(new Person("Amelia", "Jake", 23, "red"));
		list.add(new Person("Isla", "Harry", 54, "blue"));
		list.add(new Person("Lily", "George", 21, "green"));
		
		
		Mockito.when(service.getAllPersons()).thenReturn(list);
		
		MvcResult mvcResult = mockMvc.perform(get("/api/persons")).andExpect(status().isOk()).andDo(print()).andReturn();
		
		/*MvcResult mvcResult = mockMvc.perform(get("/api/persons").accept(MediaType.APPLICATION_JSON)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer "+token).with(csrf())).andExpect(status().isOk()).andDo(print()).andReturn();*/
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(list);
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
		
		
	}
	
	
	@WithMockUser("foo")
	@Test
	public void TestPersonById() throws Exception {
		
		Person getPerson =new Person(1l,"Amelia", "Jake", 23, "red");

		Mockito.when(service.getPersonById(1)).thenReturn(new Person(1l,"Amelia", "Jake", 23, "red"));
		
		MvcResult mvcResult = mockMvc.perform(get("/api/persons/{id}",1)).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(getPerson);
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
		
		
	}
	
	@WithMockUser("foo")
	@Test
    public void TestCreatePerson() throws Exception {
		

		Person newPerson =new Person("Amelia", "Jake", 23, "red");
		Person savePerson =new Person(1l,"Amelia", "Jake", 23, "red");

		Mockito.when(service.createPerson(any(Person.class))).thenReturn(savePerson);
		
		MvcResult mvcResult = mockMvc.perform(post("/api/persons").contentType("application/json")
				.content(objectMapper.writeValueAsString(newPerson))).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(savePerson);
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
    	
    }

	@WithMockUser("foo")
	@Test
    public void TestUpdatePerson() throws Exception {
		
		Person newPerson =new Person("Amelia", "Jake", 23, "red");
		Person updatePerson =new Person(2l,"Amelia", "Jake", 23, "red");

		Mockito.when(service.updatePerson(any(Person.class))).thenReturn(updatePerson);
		
		MvcResult mvcResult = mockMvc.perform(put("/api/persons/{id}",2).contentType("application/json")
				.content(objectMapper.writeValueAsString(newPerson))).andExpect(status().isOk()).andDo(print()).andReturn();
		
		String actualRes = mvcResult.getResponse().getContentAsString();
		String expectedRes = objectMapper.writeValueAsString(updatePerson);
		
		assertThat(actualRes).isEqualToIgnoringWhitespace(expectedRes);
    	
    }
	
	@WithMockUser("foo")
	@Test
    public void TestDeletePerson() throws Exception {
		Long personId =1L;
		
		Mockito.doNothing().when(service).deletePerson(personId);
		
		mockMvc.perform(delete("/api/persons/{id}",personId)).andExpect(status().isOk());
		
		Mockito.verify(service,times(1)).deletePerson(personId);
		
	}

}
