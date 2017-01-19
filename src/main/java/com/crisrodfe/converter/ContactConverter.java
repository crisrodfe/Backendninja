package com.crisrodfe.converter;

import org.springframework.stereotype.Component;

import com.crisrodfe.entity.Contact;
import com.crisrodfe.model.ContactModel;

@Component("contactConverter")
public class ContactConverter
{
	public Contact modelToEntity(ContactModel contactModel)
	{
		Contact contact = new Contact();
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		contact.setId(contactModel.getId());
		
		return contact;
	}
	
	public ContactModel entityToModel(Contact contact)
	{
		ContactModel contactModel = new ContactModel();
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());
		contactModel.setId(contact.getId());
		return contactModel;
	}

}
