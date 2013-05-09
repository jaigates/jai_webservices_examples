package jai.ldap.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPConstants {

/*	*//** latest from kevin *//*
	public static final String UserName="CN=LDSAdmin,CN=Users,O=ibo,DC=adld,DC=ibo,DC=org";
	public static final String Password="m1grat1on!";
	public static final String LDAP_URL="ldaps://10.0.19.72:636";*/
	
	
	public static final String UserName="CN=APPuser,CN=Users,O=ibo,DC=adld,DC=ibo,DC=org";
	public static final String Password="APPl1m1ted!";
	public static final String LDAP_URL="ldaps://172.16.1.49:636";
	
	
	public static DirContext getLdapContext() {
		DirContext ctx = null;

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAP_URL);
		try {

			env.put(Context.SECURITY_PRINCIPAL, UserName);
			env.put(Context.SECURITY_CREDENTIALS, Password);

			// Specify SSL
			env.put(Context.SECURITY_PROTOCOL, "ssl");

			// Authenticate as S. User and password "mysecret"
			env.put(Context.SECURITY_AUTHENTICATION, "simple");

			 ctx = new InitialDirContext(env);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ctx;
	}

}
