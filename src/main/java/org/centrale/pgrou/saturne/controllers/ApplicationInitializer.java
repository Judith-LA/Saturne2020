/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.Optional;
import org.centrale.ldap.LDAPManager;
import org.centrale.pgrou.saturne.items.Personne;
import org.centrale.pgrou.saturne.items.Role;
import org.centrale.pgrou.saturne.items.Menu;
import org.centrale.pgrou.saturne.repositories.PersonneRepository;
import org.centrale.pgrou.saturne.repositories.RoleRepository;
import org.centrale.pgrou.saturne.repositories.MenuRepository;

/**
 *
 * @author louis-alexandre
 */
public class ApplicationInitializer {
    // Must be accessible from package
    static final String TRAPLOGIN = "admin";
    static final String TRAPPASS = "arkadia";

    private static boolean inited = false;
    
    public static void init(PersonneRepository personRepository, RoleRepository roleRepository, MenuRepository menuRepository) {
        if (!inited) {
            inited = true;

            //LDAPManager.init();

            createDefaultRole(roleRepository);
            createDefaultMenu(menuRepository, roleRepository);
            createDefaultLogin(personRepository, roleRepository);
        }
    }
    
    private static void createDefaultRole(RoleRepository roleRepository) {
        long cpt = roleRepository.findAll().size();
        if (cpt == 0L) {
            // Default roles
            roleRepository.create("Admin",1);
            roleRepository.create("Teacher",2);
            roleRepository.create("Student",3);
        }
    }
    
    static void createDefaultLogin(PersonneRepository personRepository, RoleRepository roleRepository) {
        Optional<Personne> ResultAdmin = personRepository.findById(1);
        if (!ResultAdmin.isPresent()) {
            // Default user
            Personne admin = personRepository.create("Administrateur", "", ApplicationInitializer.TRAPLOGIN, ApplicationInitializer.TRAPPASS);

            Optional<Role> roleAdminResult = roleRepository.findById(1);
            if (roleAdminResult.isPresent()) {
                Role adminRole = roleAdminResult.get();
                admin.getRoleCollection().add(adminRole);
                adminRole.getPersonneCollection().add(admin);
                personRepository.save(admin);
                roleRepository.save(adminRole);
            }
            personRepository.flush();
        }
    }
    
    static void createDefaultMenu(MenuRepository menuRepository, RoleRepository roleRepository){
        long cpt = menuRepository.findAll().size();
        if (cpt == 0L) {
            // Default menus
            Menu menu;
            Role admin = roleRepository.findOneById(1);
            Role teacher = roleRepository.findOneById(2);
            Role student = roleRepository.findOneById(3);
            
            menu = menuRepository.create("menuEtudiantPrincipal");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(student, menu);
            
            menu = menuRepository.create("menuProfesseurPrincipal");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuEtudiantTest");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(student, menu);
            
            menu = menuRepository.create("menuEtudiantResultats");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(student, menu);
            
            menu = menuRepository.create("menuProfesseurCreationGroupe");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuProfesseurCreationQuestion");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuProfesseurCreationQuestionnaire");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuProfesseurCreationEvaluation");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuProfesseurResultats");
            roleRepository.linkRoleAndMenu(admin, menu);
            roleRepository.linkRoleAndMenu(teacher, menu);
            
            menu = menuRepository.create("menuAdminPrincipal");
            roleRepository.linkRoleAndMenu(admin, menu);
        }
    }
    
}
