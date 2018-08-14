package com.cisco.clmsbackend.services;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LdapContextSource contextSource;
	
	@Autowired
	Environment env;

	@Override
	public boolean authenticate(String username, String password) {
		String userDn = "CN=" + username + "," + env.getRequiredProperty("ldap.base");
		DirContext ctx = null;
		try {
			ctx = contextSource.getContext(userDn, password);
			return true;
		} catch (Exception e) {
			System.out.println("Login failed");
			return false;
		} finally {
			// It is imperative that the created DirContext instance is always closed
			LdapUtils.closeContext(ctx);
		}
	}

}
