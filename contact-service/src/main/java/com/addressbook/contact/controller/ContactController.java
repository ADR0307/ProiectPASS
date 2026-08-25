package com.addressbook.contact.controller;

import com.addressbook.contact.dto.ContactResponse;
import com.addressbook.contact.dto.CreateContactRequest;
import com.addressbook.contact.dto.UpdateContactRequest;
import com.addressbook.contact.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContactResponse> getAll(Authentication authentication) {

        return service.getAll(authentication.getName());
    }

    @GetMapping("/{id}")
    public ContactResponse getById(@PathVariable Long id,
                                   Authentication authentication) {

        return service.getById(id, authentication.getName());
    }

    @PostMapping
    public ContactResponse create(@Valid @RequestBody CreateContactRequest request,
                                  Authentication authentication) {

        return service.create(authentication.getName(), request);
    }

    @PutMapping("/{id}")
    public ContactResponse update(@PathVariable Long id,
                                  @Valid @RequestBody UpdateContactRequest request,
                                  Authentication authentication) {

        return service.update(id, authentication.getName(), request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       Authentication authentication) {

        service.delete(id, authentication.getName());
    }
}