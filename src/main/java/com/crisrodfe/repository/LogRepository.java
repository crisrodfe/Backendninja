package com.crisrodfe.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crisrodfe.entity.Log;

/**
 * 
 * @author CrisRodFe
 * Repositorio para acceder y manipular la tabla 'log' de nuestra base de datos.
 */
@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable>
{

}
