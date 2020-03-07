/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.ldap;

/**
 *
 * @author louis-alexandre
 */
public class TestLDAP {
    public static void main(String[] argv) {
        /**LDAPManager.init();
        System.out.println("Ca marche ?");
        System.out.println(LDAPManager.isLdapAvailable());**/
        String login = "jlouisalex2018";
        String mdp = "Chocolat36";
        Boolean inch = LDAPManager.identifyLDAP(login, mdp);
        System.out.println("inch");
        LDAPUser ldapUser = LDAPManager.searchLDAP(login, mdp);
        System.out.println(ldapUser.getUserAffiliation());
        System.out.println(ldapUser.getUserAffiliation().equals("student"));
    }
}
