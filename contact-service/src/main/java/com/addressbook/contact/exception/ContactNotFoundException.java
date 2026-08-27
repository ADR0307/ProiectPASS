package com.addressbook.contact.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Long id) {
        super("Contact with id " + id + " was not found");
    }
}