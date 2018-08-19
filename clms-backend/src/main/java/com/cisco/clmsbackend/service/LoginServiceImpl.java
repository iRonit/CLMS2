package com.cisco.clmsbackend.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cisco.clmsbackend.repository.AdminRepository;
import com.cisco.clmsbackend.security.JwtTokenProvider;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LdapContextSource contextSource;
	
	@Autowired
	Environment env;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
    private AdminRepository adminRepository;
	
	@Override
	public List<String> authenticate(String username, String password) {
		System.out.println("-----------------> authenticateLDAP");
		if(authenticateLDAP(username, password)) {
			System.out.println("--------------------> authenticateLDAP -> true");
			return getCompoundedJWT(username);
		}
		return null;
	}
	
	@Override
	public boolean authenticateLDAP(String username, String password) {
		String userDn = "CN=" + username + "," + env.getRequiredProperty("ldap.base");
		DirContext ctx = null;
		try {
			ctx = contextSource.getContext(userDn, password);
			System.out.println("-------------> ctx === "+ ctx);
			return true;
		} catch (Exception e) {
			System.out.println("Login failed");
			return false;
		} finally {
			// It is imperative that the created DirContext instance is always closed
			LdapUtils.closeContext(ctx);
		}
	}

	@Override
	public List<String> getCompoundedJWT(String username) {
		System.out.println("Encrypted password: " + (new BCryptPasswordEncoder()).encode(""));
		Set<GrantedAuthority> authorities;
		if(adminRepository.findByUsername(username) == null) {
	        	authorities = new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
	        } else {
	        	authorities = new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
	        }
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, "", authorities));
		System.out.println("---------------->>> After authentication: " + authentication.isAuthenticated()
		+ " ---> " + authentication.getName());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("SecurityContrextHolder.getContext()");
		return tokenProvider.generateCompoundedToken(authentication);
	}

}
