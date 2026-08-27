package com.addressbook.contact.service;

import com.addressbook.contact.dto.CreateContactRequest;
import com.addressbook.contact.entity.Contact;
import com.addressbook.contact.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    ContactRepository repository;

    @InjectMocks
    ContactService service;

    @Test
    void shouldReturnContacts() {

        Contact contact = Contact.builder()
                .id(1L)
                .ownerEmail("alex@test.com")
                .firstName("Ion")
                .lastName("Popescu")
                .build();

        when(repository.findAll())
                .thenReturn(List.of(contact));

        var result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Ion", result.getFirst().firstName());

        verify(repository).findAll();
    }

    @Test
    void shouldCreateContact() {

        CreateContactRequest request = new CreateContactRequest(
                "Ion",
                "Popescu",
                "0712",
                "ion@test.ro",
                "Netex"
        );

        Contact saved = Contact.builder()
                .id(1L)
                .ownerEmail("alex@test.com")
                .firstName("Ion")
                .lastName("Popescu")
                .phone("0712")
                .email("ion@test.ro")
                .company("Netex")
                .build();

        when(repository.save(any(Contact.class)))
                .thenReturn(saved);

        var result = service.create("alex@test.com", request);

        assertEquals("Ion", result.firstName());

        verify(repository).save(any(Contact.class));
    }

    @Test
    void shouldDeleteContact() {

        Contact contact = Contact.builder()
                .id(1L)
                .ownerEmail("alex@test.com")
                .build();

        when(repository.findByIdAndOwnerEmail(1L, "alex@test.com"))
                .thenReturn(Optional.of(contact));

        service.delete(1L, "alex@test.com");

        verify(repository).delete(contact);
    }
}