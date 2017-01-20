package com.crisrodfe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author CrisRodFe
 * Entity que representa la tabla 'user' de nuestra base de datos donde guardaremos la informacion los usuarios registrados que pueden usar la app.
 * Anotamos como columnas cada una de las propiedades. Además el username lo marcamos como id.
 * Tambien usamos otras notaciones como @OneToMany para indicar el tipo de relacion entre columnas y configuramos algunas constraints
 */
@Entity
@Table(name = "users")
public class User
{
	@Id
	@Column(name="username", unique=true, nullable=false, length = 45)
	private String username;
	
	@Column(name="password", unique=true, nullable=false, length = 60)
	private String password;
	
	@Column(name="enable", nullable=false)
	private boolean enabled;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Column()
	private Set<UserRole> userRole = new HashSet<UserRole>();

	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
