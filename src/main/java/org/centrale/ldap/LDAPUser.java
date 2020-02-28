/* --------------------------------------------------------------------------------
 * Astrolab General Tools
 * 
 * Ecole Centrale Nantes - Septembre 2015
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin, Q Roques
 * -------------------------------------------------------------------------------- */
package org.centrale.ldap;

/**
 * LDAPUser
 *
 * @author ECN
 */
public class LDAPUser {

    private String userNom;
    private String userPrenom;
    private String userUID;
    private String userEmail;
    private String userAffiliation;
    private String password;

    /**
     * Create
     */
    public LDAPUser() {
        this.userNom = null;
        this.userPrenom = null;
        this.userUID = null;
        this.userEmail = null;
        this.userAffiliation = null;
        this.password = null;
    }

    /**
     * Get LastName
     *
     * @return
     */
    public String getUserNom() {
        return userNom;
    }

    /**
     * Set lastname
     *
     * @param userNom
     */
    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    /**
     * Get firstname
     *
     * @return
     */
    public String getUserPrenom() {
        return userPrenom;
    }

    /**
     * Set firstname
     *
     * @param userPrenom
     */
    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    /**
     * Get UID
     *
     * @return
     */
    public String getUserUID() {
        return userUID;
    }

    /**
     * Set UID
     *
     * @param userUID
     */
    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    /**
     * Get Email
     *
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Set Email
     *
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * get Affiliation
     * @return 
     */
    public String getUserAffiliation() {
        return userAffiliation;
    }

    /**
     * Set Affiliation
     * @param userAffiliation 
     */
    public void setUserAffiliation(String userAffiliation) {
        this.userAffiliation = userAffiliation;
    }

    /**
     * Get password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    

}
