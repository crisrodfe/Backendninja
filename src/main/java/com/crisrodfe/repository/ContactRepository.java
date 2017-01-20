package com.crisrodfe.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crisrodfe.entity.Contact;

/**
 * 
 * @author CrisRodFe
 * Repositorio para acceder y manipular la tabla 'contact' de nuestra base de datos.
 * Al heredar de JpaRepository ya contamos con mucha funcionalidad implementada y predefinida.
 */
@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Serializable>
{
	/*
	 * Encuentra un registro o contacto de la tabla 'contact' a partir de un id.
	 */
	public abstract Contact findById(int id);
}
