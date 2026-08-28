package com.addressbook.contact.service;

import com.addressbook.contact.dto.ContactResponse;
import com.addressbook.contact.dto.CreateContactRequest;
import com.addressbook.contact.dto.UpdateContactRequest;
import com.addressbook.contact.entity.Contact;
import com.addressbook.contact.exception.ContactNotFoundException;
import com.addressbook.contact.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<ContactResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ContactResponse getById(Long id) {

        Contact contact = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));

        return toResponse(contact);
    }

    public ContactResponse create(
            String ownerEmail,
            CreateContactRequest request) {

        Contact contact = Contact.builder()
                .ownerEmail(ownerEmail)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phone(request.phone())
                .email(request.email())
                .company(request.company())
                .address(request.address())
                .picture(request.picture())
                .build();

        return toResponse(repository.save(contact));
    }

    public ContactResponse update(
            Long id,
            String ownerEmail,
            UpdateContactRequest request) {

        Contact contact = repository.findByIdAndOwnerEmail(id, ownerEmail)
                .orElseThrow(() -> new ContactNotFoundException(id));

        contact.setFirstName(request.firstName());
        contact.setLastName(request.lastName());
        contact.setPhone(request.phone());
        contact.setEmail(request.email());
        contact.setCompany(request.company());
        contact.setAddress(request.address());
        contact.setPicture(request.picture());

        return toResponse(repository.save(contact));
    }

    public void delete(
            Long id,
            String ownerEmail) {

        Contact contact = repository.findByIdAndOwnerEmail(id, ownerEmail)
                .orElseThrow(() -> new ContactNotFoundException(id));

        repository.delete(contact);
    }

    public List<ContactResponse> search(String ownerEmail, String name) {

        return repository
                .findByOwnerEmailAndFirstNameContainingIgnoreCaseOrOwnerEmailAndLastNameContainingIgnoreCase(
                        ownerEmail,
                        name,
                        ownerEmail,
                        name
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public String exportCsv(String ownerEmail) {

        StringBuilder csv = new StringBuilder();

        csv.append("First Name,Last Name,Phone,Email,Company,Address,Picture\n");

        repository.findByOwnerEmail(ownerEmail)
                .forEach(contact -> csv.append(
                        String.format(
                                "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                                safe(contact.getFirstName()),
                                safe(contact.getLastName()),
                                safe(contact.getPhone()),
                                safe(contact.getEmail()),
                                safe(contact.getCompany()),
                                safe(contact.getAddress()),
                                safe(contact.getPicture())
                        )
                ));

        return csv.toString();
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private ContactResponse toResponse(Contact contact) {

        return new ContactResponse(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getCompany(),
                contact.getAddress(),
                contact.getPicture()
        );
    }
}