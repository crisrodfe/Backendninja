package com.crisrodfe.service;

import java.util.List;

import com.crisrodfe.entity.Contact;
import com.crisrodfe.model.ContactModel;

/**
 * 
 * @author CrisRodFe
*/
public interface ContactService
{
	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> listAllContacts();
	public abstract Contact findContactById(int id);
	public abstract void removeContact(int id);
	public abstract ContactModel findContactByIdModel(int id);
	
}
