package com.crisrodfe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author CrisRodFe
 * Entity que representa la tabla 'user_role' de nuestra base de datos donde guardaremos la informacion los roles de cada usuario registrado de la app. 
 * Anotamos como columnas cada una de las propiedades. Además el user_role_id lo marcamos como id y que genere el valor automáticamente.
 * Tambien usamos otras notaciones como @ManyToOne para indicar el tipo de relacion entre columnas.
 */
@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole 
{
	@Id
	@GeneratedValue
	@Column(name="user_role_id", unique=true, nullable=false)
	private Integer userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username", nullable = false)
	private User user;
	
	@Column(name="role", nullable = false, length = 45)
	private String role;

	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public UserRole() {
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
