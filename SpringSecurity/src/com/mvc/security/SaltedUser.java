package com.mvc.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SaltedUser extends User{

	private String salt;
	
	public SaltedUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, List<GrantedAuthority> combinedAuthorities,String salt) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, combinedAuthorities);
		this.salt=salt;
		
	}
	
	

	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
