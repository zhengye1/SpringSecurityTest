package com.vincent.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vincent.model.Role;
import com.vincent.model.User;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByUsername(username);
		System.out.println(user);
		if (user == null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), user.getStatus().equals("ACTIVE"), false, false, false, 
				getGrantedAuthorities(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for(Role userRole : user.getRoles()){
			System.out.println("UserProfile : "+userRole);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
		}
		System.out.print("authorities :"+authorities);
		return authorities;
	}
}
