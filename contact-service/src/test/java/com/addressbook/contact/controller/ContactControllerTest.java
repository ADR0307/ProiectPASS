package com.addressbook.contact.controller;

import com.addressbook.contact.client.UserServiceClient;
import com.addressbook.contact.dto.ContactResponse;
import com.addressbook.contact.dto.CreateContactRequest;
import com.addressbook.contact.dto.UpdateContactRequest;
import com.addressbook.contact.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    ContactService service;

    @MockitoBean
    UserServiceClient userServiceClient;

    private Authentication auth() {
        Authentication auth = Mockito.mock(Authentication.class);

        Mockito.when(auth.getName())
                .thenReturn("alex@test.com");

        return auth;
    }

    @Test
    void shouldReturnContacts() throws Exception {

        Mockito.when(service.getAll())
                .thenReturn(List.of(
                        new ContactResponse(
                                1L,
                                "Ion",
                                "Popescu",
                                "0712",
                                "ion@test.ro",
                                "Netex"
                        )
                ));

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Ion"))
                .andExpect(jsonPath("$[0].lastName").value("Popescu"));
    }

    @Test
    void shouldCreateContact() throws Exception {

        CreateContactRequest request = new CreateContactRequest(
                "Ion",
                "Popescu",
                "0712",
                "ion@test.ro",
                "Netex"
        );

        ContactResponse response = new ContactResponse(
                1L,
                "Ion",
                "Popescu",
                "0712",
                "ion@test.ro",
                "Netex"
        );

        Mockito.when(service.create(eq("alex@test.com"), any()))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/contacts")
                                .principal(auth())
                                .contentType("application/json")
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ion"));
    }

    @Test
    void shouldSearchContacts() throws Exception {

        Mockito.when(service.search(eq("alex@test.com"), eq("Ion")))
                .thenReturn(List.of(
                        new ContactResponse(
                                1L,
                                "Ion",
                                "Popescu",
                                "0712",
                                "ion@test.ro",
                                "Netex"
                        )
                ));

        mockMvc.perform(
                        get("/api/contacts/search")
                                .param("name", "Ion")
                                .principal(auth())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Ion"))
                .andExpect(jsonPath("$[0].lastName").value("Popescu"))
                .andExpect(jsonPath("$[0].email").value("ion@test.ro"));
    }

    @Test
    void shouldExportContactsAsCsv() throws Exception {

        String csv =
                "First Name,Last Name,Phone,Email,Company\n" +
                        "Ion,Popescu,0712,ion@test.ro,Netex\n";

        Mockito.when(service.exportCsv("alex@test.com"))
                .thenReturn(csv);

        mockMvc.perform(
                        get("/api/contacts/export")
                                .principal(auth())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/csv"))
                .andExpect(header().string(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=contacts.csv"
                ))
                .andExpect(content().string(csv));
    }

    @Test
    void shouldUpdateContact() throws Exception {

        Long contactId = 1L;

        UpdateContactRequest request = new UpdateContactRequest(
                "Ion",
                "Ionescu",
                "0722",
                "ion.ionescu@test.ro",
                "Google"
        );

        ContactResponse response = new ContactResponse(
                contactId,
                "Ion",
                "Ionescu",
                "0722",
                "ion.ionescu@test.ro",
                "Google"
        );

        Mockito.when(service.update(
                eq(contactId),
                eq("alex@test.com"),
                any(UpdateContactRequest.class)
        )).thenReturn(response);

        mockMvc.perform(
                        put("/api/contacts/{id}", contactId)
                                .principal(auth())
                                .contentType("application/json")
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ion"))
                .andExpect(jsonPath("$.lastName").value("Ionescu"))
                .andExpect(jsonPath("$.phone").value("0722"))
                .andExpect(jsonPath("$.email").value("ion.ionescu@test.ro"))
                .andExpect(jsonPath("$.company").value("Google"));
    }

    @Test
    void shouldDeleteContact() throws Exception {

        Long contactId = 1L;

        Mockito.doNothing().when(service)
                .delete(contactId, "alex@test.com");

        mockMvc.perform(
                        delete("/api/contacts/{id}", contactId)
                                .principal(auth())
                )
                .andExpect(status().isOk());

        Mockito.verify(service)
                .delete(contactId, "alex@test.com");
    }
}