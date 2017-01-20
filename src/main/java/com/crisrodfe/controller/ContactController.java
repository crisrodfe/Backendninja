package com.crisrodfe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crisrodfe.constant.ViewConstant;
import com.crisrodfe.model.ContactModel;
import com.crisrodfe.service.ContactService;

/**
 * 
 * @author CrisRodFe
 * Clase Controller que gestiona las peticiones del formulario de añadir, eliminar, modificar contacto, etc.
 */
@Controller
@RequestMapping("/contacts")
public class ContactController
{
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return String  - template view del formulario de contactos
	 * 
	 * Manda los datos de un contacto a partir de un id recibido de la vista como parámetro.
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/contactform")
	private String redirectContactForm(@RequestParam(name="id", required=false) int id, Model model)
	{
		ContactModel contactModel = new ContactModel();
		if(id != 0)
		{
			contactModel = contactService.findContactByIdModel(id);
		}		
		model.addAttribute("contactModel",contactModel);
		return ViewConstant.CONTACT_FORM_VIEW;
	}
	
	/**
	 * 
	 * @return String - redireccion a template view de la lista de contactos de la base de datos
	 */
	@GetMapping("/cancel")
	public String cancel()
	{
		return "redirect:/contacts/showcontacts";
	}
	
	/**
	 * @param contactModel
	 * @param model
	 * @return String  - Redirección a /showcontacts que mostrará la lista de contactos de la base de datos
	 * 
	 * Lanza un resultado positivo o negativo según se haya podido realizar o no la acción de añadir contacto.
	 */
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model)
	{
		LOG.info("METHOD: addContact() -- PARAMS: "+contactModel.toString());
		
		if(null != contactService.addContact(contactModel))
		{	
			model.addAttribute("result",1);
			
		} else {
			model.addAttribute("result",0);
		}
			
		return "redirect:/contacts/showcontacts";
	}
	
	/**
	 * 
	 * @return ModelAndView - retorna la vista con los datos correspondientes (username y comntacts) añadidos
	 * Recibe desde un servicio la lista de contactos de la base de datos.
 	 */
	@GetMapping("/showcontacts")
	public ModelAndView showContacts()
	{
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_VIEW);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username",user.getUsername());
		mav.addObject("contacts",contactService.listAllContacts());
		
		return mav;
	}
	
	/**
	 * 
	 * @param id
	 * @return  ModelAndView - retorna una llamada al método que nos devuelve la vista con la lista de contactos
	 * 
	 * Elimina un usuario con el id recibido por parámetro usando para ello nuestro servicio correspondiente que a su vez hará uso del repositorio JPA
	 */
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id )
	{
		contactService.removeContact(id);
		
		return showContacts();
	}
	
}
