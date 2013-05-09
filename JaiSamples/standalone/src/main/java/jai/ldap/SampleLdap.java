package jai.ldap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.LdapContext;

public class SampleLdap {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws NamingException, IOException {
		//build a hashtable containing all the necessary configuration parameters
		SampleLdap.class.getResourceAsStream("ldap.properties");
		File f = new File("standalone/src/main/resources/ldap.properties");
		String canonicalPath = f.getCanonicalPath();
		Properties conf = new Properties();
		conf.load(new FileReader(f));
		Hashtable<String, String> environment = new Hashtable<String, String>();
		

		environment.put(LdapContext.CONTROL_FACTORIES, conf.getProperty("ldap.factories.control"));
		environment.put(Context.INITIAL_CONTEXT_FACTORY, conf.getProperty("ldap.factories.initctx"));
		environment.put(Context.PROVIDER_URL, conf.getProperty("ldap.host"));
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, conf.getProperty("ldap.user"));
		environment.put(Context.SECURITY_CREDENTIALS, conf.getProperty("ldap.password"));
		environment.put(Context.STATE_FACTORIES, "PersonStateFactory");
		environment.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");

		// connect to LDAP
		DirContext ctx = new InitialDirContext(environment);

		// Specify the search filter
		//String FILTER = "(&(objectClass=Person) ((sAMAccountName=" + user.getUsername() + ")))";

		// limit returned attributes to those we care about
		String[] attrIDs = { "sn", "givenName" };

		SearchControls ctls = new SearchControls();
		ctls.setReturningAttributes(attrIDs);
		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		// Search for objects using filter and controls
		/*
		NamingEnumeration answer = ctx.search(searchBase, FILTER, ctls);


		SearchResult sr = (SearchResult) answer.next();
		Attributes attrs = sr.getAttributes();
		surName = attrs.get("sn").toString();
		givenName = attrs.get("givenName").toString();*/

	}
}
