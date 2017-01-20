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

/**
 * 
 * @author CrisRodFe
 * Servicio que usaremos para añadir contacto, listar todos los contactos, y eliminar contacto. 
 * Será utilizado por nuestro ContactController para dotar de funcionalidad a las diferentes vistas.
 */
@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService
{
	//Repositorio de acceso a la base de datos (tabla 'contact'), al 
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	//Nuestro conversor Entity<->Model
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	/**
	 * @return ContactModel 
	 * 
	 * Guardará el contacto recibido por parámetro. 
	 * Si el id no está crea un registro nuevo, si ya existe en la base de datos lo actualiza, por lo que lo utilizamos para la función de modificar contacto.
	 */
	@Override
	public ContactModel addContact(ContactModel contactModel)
	{
		Contact contact = contactRepository.save(contactConverter.modelToEntity(contactModel));
		return contactConverter.entityToModel(contact);
	}

	/**
	 * return List<ContactModel>, lista de todos los contactos de la base de datos.
	 */
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

	/**
	 * Encuentra un Contact a partir de un id recibido como parámetro
	 */
	@Override
	public Contact findContactById(int id) 
	{
		return contactRepository.findById(id);
	}
	
	/**
	 * Encuentra un ContactModel a partir de un id recibido como parámetro.
	 */
	public ContactModel findContactByIdModel(int id)
	{
		return contactConverter.entityToModel(findContactById(id));
	}


	/**
	 * Elimina un contacto de la base de datos en caso de encontrar el registro con el id recibido como parámetro.
	 */
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
