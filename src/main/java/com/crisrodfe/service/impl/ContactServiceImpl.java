package com.crisrodfe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crisrodfe.converter.ContactConverter;
import com.crisrodfe.entity.Contact;
import com.crisrodfe.model.ContactModel;
import com.crisrodfe.repository.ContactRepository;
import com.crisrodfe.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService
{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.modelToEntity(contactModel));
		return contactConverter.entityToModel(contact);
	}


	@Override
	public List<ContactModel> listAllContacts() 
	{
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<>();
		for(Contact contact : contacts)
		{
			contactsModel.add(contactConverter.entityToModel(contact));
		}
		return contactsModel;
	}


	@Override
	public Contact findContactById(int id) 
	{
		return contactRepository.findById(id);
	}
	
	public ContactModel findContactByIdModel(int id)
	{
		return contactConverter.entityToModel(findContactById(id));
	}


	@Override
	public void removeContact(int id) 
	{
		Contact contact = findContactById(id);
		if(null != contact)
		{
			contactRepository.delete(findContactById(id));	
		}
	}
	

}
