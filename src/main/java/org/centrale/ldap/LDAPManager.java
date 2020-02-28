/* --------------------------------------------------------------------------------
 * Astrolab General Tools
 * 
 * Ecole Centrale Nantes - Septembre 2015
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin, Q Roques
 * -------------------------------------------------------------------------------- */
package org.centrale.ldap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Manage LDAP communication
 *
 * @author ECN
 */
public class LDAPManager {
    private static final String CONFIGFILENAME = "ldap.properties";
    
    private static String ldapBasedn;
    private static String ldapHost;
    private static String ldapsecurityprotocol;
    private static String defaultLDAPdn;
    private static String defaultLDAPpass;

    private static boolean ldapAvailable;

    /**
     * initialize
     */
    public static void init() {
        ldapAvailable = false;
        if (ldapBasedn == null) {
            try {
                String packageName = LDAPManager.class.getPackage().getName();
                String packagePath = LDAPManager.class.getClassLoader().getResource("").getPath();     
                String filePath = packagePath + packageName.replace('.', '/') + '/' + CONFIGFILENAME;
                //System.out.println(filePath);

                Properties parametre = new Properties();
                FileInputStream input = new FileInputStream(filePath);
                parametre.load(input);
                input.close();
                
                // USE config parameters
                ldapHost = parametre.getProperty("ldapHost").toLowerCase();
                ldapBasedn = parametre.getProperty("ldapBasedn").toLowerCase();
                ldapsecurityprotocol = parametre.getProperty("ldapsecurityprotocol").toLowerCase();
                defaultLDAPdn = parametre.getProperty("defaultLDAPdn");
                defaultLDAPpass = parametre.getProperty("defaultLDAPpass");

                ldapAvailable = true;
            } catch (IOException ex) {
                //unable to use LDAP authentication
                Logger.getLogger(LDAPManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Tells if LDAP is available
     * @return 
     */
    public static boolean isLdapAvailable() {
        return ldapAvailable;
    }

    
    /**
     * Build LDAP properties for a BIND
     *
     * @return
     */
    private static Properties getLDAPProperties() {
        Properties env = new Properties();
        // Add server
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapHost);
        if (ldapsecurityprotocol.equals("ssl")) {
            // Add SSL encription
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            // Use locally defined socked manager to avoid certificate validation
            env.put("java.naming.ldap.factory.socket", "org.centrale.ldap.MySSLSocketFactory");
        }

        return env;
    }

    /**
     * Build LDAP properties for a BIND
     *
     * @param field
     * @param fieldContent
     * @param password
     * @return
     */
    private static Properties getLDAPProperties(String field, String fieldContent, String password) {
        Properties env = getLDAPProperties();

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, field + "=" + fieldContent + "," + ldapBasedn);
        env.put(Context.SECURITY_CREDENTIALS, password);

        return env;
    }

    /**
     * Build LDAP properties for an admin BIND
     *
     * @return
     */
    private static Properties getLDAPDefaultProperties() {
        Properties env = getLDAPProperties();

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, defaultLDAPdn);
        env.put(Context.SECURITY_CREDENTIALS, defaultLDAPpass);

        return env;
    }

    /**
     * Build LDAP properties for a BIND
     *
     * @param login
     * @param password
     * @return
     */
    private static Properties getLDAPProperties(String login, String password) {
        return getLDAPProperties("uid", login, password);
    }

    /**
     * Identify with a LDAP BIND
     *
     * @param login
     * @param password
     * @return
     */
    public static boolean identifyLDAP(String login, String password) {
        boolean isIdentified = false;
        init();
        /**System.out.println(ldapBasedn);
        System.out.println(ldapAvailable);**/
        if (ldapAvailable) {
            try {
                Properties env = getLDAPProperties(login, password);
                //System.out.println(env);
                if (login != null && password != null) {
                    DirContext ctx = new InitialDirContext(env);
                    isIdentified = true;
                    ctx.close();
                }
            } catch (AuthenticationException ex) {
                // Non reconnu
            } catch (CommunicationException ex) {
                if (ldapAvailable) {
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.SEVERE, null, ex);
                } else {
                    ldapAvailable = false;
                }
            } catch (NamingException ex) {
                // Non reconnu
            }
        }
        //System.out.println(isIdentified);
        return isIdentified;
    }

    /**
     * Search in LDAP
     *
     * @param login
     * @param password
     * @return
     */
    public static LDAPUser searchLDAP(String login, String password) {
        LDAPUser aUser = null;
        if (ldapAvailable) {
            if ((login != null && password != null) && (!login.isEmpty()) && (!password.isEmpty())) {
                Properties env = getLDAPProperties(login, password);
                try {
                    DirContext ctx = new InitialDirContext(env);

                    StringBuilder searchConstraint = new StringBuilder();
                    searchConstraint.append("(uid=");
                    searchConstraint.append(login);
                    searchConstraint.append(")");

                    SearchControls constraints = new SearchControls();
                    constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
                    NamingEnumeration results = ctx.search(ldapBasedn, searchConstraint.toString(), constraints);
                    if (results != null) {
                        if (results.hasMore()) {
                            // Get all attributes
                            SearchResult si = (SearchResult) (results.next());
                            Attributes attrs = si.getAttributes();

                            aUser = new LDAPUser();
                            aUser.setUserUID(getAttribute(attrs, "uid"));
                            aUser.setUserNom(getAttribute(attrs, "sn"));
                            aUser.setUserPrenom(getAttribute(attrs, "givenName"));
                            aUser.setUserEmail(getAttribute(attrs, "mail"));
                            aUser.setUserUID(getAttribute(attrs, "uid"));
                            aUser.setUserAffiliation(getAttribute(attrs, "eduPersonAffiliation"));
                        }
                    }
                    ctx.close();
                } catch (AuthenticationException ex) {
                    // Non reconnu
                } catch (CommunicationException ex) {
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    // Non reconnu
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.INFO, null, ex);
                } catch (Exception ex) {
                    // Non reconnu
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.INFO, null, ex);
                }
            }
        }
        return aUser;
    }

    /**
     * Search in LDAP
     *
     * @param parameters
     * @return
     */
    public static List<LDAPUser> searchLDAPAll(HashMap<String, String> parameters) {
        List<LDAPUser> users = new ArrayList<LDAPUser>();
        if (ldapAvailable) {
            if ((parameters != null) && (!parameters.isEmpty())) {
                // Use default properties. We don't know user password
                Properties env = getLDAPDefaultProperties();
                try {
                    DirContext ctx = new InitialDirContext(env);

                    StringBuilder searchConstraint = new StringBuilder();
                    if (parameters.keySet().size() > 1) {
                        // If there is more than 1 parameter we add an &
                        searchConstraint.append("(&");
                    }
                    for (String key : parameters.keySet()) {
                        // Add new search element
                        String value = parameters.get(key);
                        searchConstraint.append("(");
                        searchConstraint.append(key);
                        searchConstraint.append("=");
                        searchConstraint.append(value);
                        searchConstraint.append(")");
                    }
                    if (parameters.keySet().size() > 1) {
                        // close parenthesis
                        searchConstraint.append(")");
                    }

                    // search
                    SearchControls constraints = new SearchControls();
                    constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
                    NamingEnumeration results = ctx.search(ldapBasedn, searchConstraint.toString(), constraints);
                    if (results != null) {
                        while (results.hasMore()) {
                            // Get all attributes
                            SearchResult si = (SearchResult) (results.next());
                            Attributes attrs = si.getAttributes();

                            LDAPUser aUser = new LDAPUser();
                            aUser.setUserUID(getAttribute(attrs, "uid"));
                            aUser.setUserNom(getAttribute(attrs, "sn"));
                            aUser.setUserPrenom(getAttribute(attrs, "givenName"));
                            aUser.setUserEmail(getAttribute(attrs, "mail"));
                            aUser.setUserUID(getAttribute(attrs, "uid"));
                            aUser.setUserAffiliation(getAttribute(attrs, "eduPersonAffiliation"));
                            users.add(aUser);
                        }
                    }
                    ctx.close();
                } catch (AuthenticationException ex) {
                    // Non reconnu
                } catch (CommunicationException ex) {
                    // Can't connect to LDAP
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    // Non reconnu
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.INFO, null, ex);
                } catch (Exception ex) {
                    // Non reconnu
                    Logger.getLogger(LDAPManager.class.getName()).log(Level.INFO, null, ex);
                }
            }
        }
        return users;
    }

    /**
     * Get LDAP attribute
     *
     * @param attrs
     * @param attribute
     * @return
     */
    private static String getAttribute(Attributes attrs, String attribute) {
        Attribute attr = attrs.get(attribute);
        if (attr != null) {
            try {
                return (String) (attr.get());
            } catch (NamingException ex) {
                return "";
            }
        } else {
            return "";
        }
    }
}

