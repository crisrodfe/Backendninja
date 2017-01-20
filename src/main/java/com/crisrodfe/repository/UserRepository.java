package com.crisrodfe.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crisrodfe.entity.User;

/**
 * 
 * @author CrisRodFe
 * Repositorio para acceder y manipular la tabla 'user' de nuestra base de datos.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>
{
	/**
	 * Encuentra un registro de 'user' a partir de la columna-campo 'username'
	 * @param username
	 * @return
	 */
	public abstract User findByUsername(String username);
}
