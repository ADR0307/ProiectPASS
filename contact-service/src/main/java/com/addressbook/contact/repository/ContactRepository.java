package com.addressbook.contact.repository;

import com.addressbook.contact.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByOwnerEmail(String ownerEmail);

    Optional<Contact> findByIdAndOwnerEmail(Long id, String ownerEmail);
}