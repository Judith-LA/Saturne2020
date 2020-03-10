/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Optional;
import org.centrale.pgrou.saturne.items.Menu;
import org.centrale.pgrou.saturne.items.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louis-alexandre
 */
@Repository
public class MenuCustomRepositoryImpl implements MenuCustomRepository {
    
    @Autowired
    @Lazy
    MenuRepository menuRepository;

    @Override
    public Menu create(String menuTitle) {
        if ((menuTitle != null) && (!menuTitle.isEmpty())) {
            Menu menu = new Menu();
            menu.setNom(menuTitle);
            menuRepository.save(menu);

            Optional<Menu> resultMenu = menuRepository.findById(menu.getMenuid());
            if (resultMenu.isPresent()) {
                return resultMenu.get();
            }
        }

        return null;
    }

    @Override
    public Menu checkAccess(Personne person, String action) {
        Menu returnedValue = null;

        if (person != null) {
            Collection<Menu> menuList = menuRepository.checkMenuLink(person.getPersonneid(), action);
            if (menuList.size() == 1) {
                returnedValue = menuList.iterator().next();
            }
        }
        return returnedValue;
    }
    
}
