package com.crisrodfe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crisrodfe.model.ContactModel;

/**
 * 
 * @author CrisRodFe
 * Clase sencilla de ejemplo de una petición REST. Devuelve los datos de un Contacto creado manualmente, formato string json. Desde el front end podremos usarlo.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController
{
	@GetMapping("checkrest")
	public ResponseEntity<ContactModel> checkRest()
	{
		ContactModel contactModel = new ContactModel(2,"Prueba","Prueba2","999","Gijon");
		return new ResponseEntity<>(contactModel,HttpStatus.OK);
	}
}
