package ib.project.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User implements Serializable,UserDetails {
	
	public User() {
		
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="username")
//	private String username;
//	
//	public void setUsername(String username) {
//		this.username = username;
//	}

	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="certificate",nullable=true)
	private String certificate;
	
	@Column(name="active")
	private boolean active;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_authority",
			joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="authority_id",referencedColumnName="id"))
	private Set<Authority> user_authorities = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	@Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
		 return this.user_authorities;
	 }

//	public void setAuthorities(Set<Authority> authorities) {
//		this.authorities = authorities;
//	}
	
	public Set<Authority> getUser_authorities() {
		return user_authorities;
	}

	public void setUser_authorities(Set<Authority> user_authorities) {
		this.user_authorities = user_authorities;
	}
	
//	public Set<Authority> getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(Set<Authority> authorities) {
//		this.authorities = authorities;
//	}
	
	
	@Override
	public String getUsername() {
		return email;
//		return this.email;
	}
	
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	

}
