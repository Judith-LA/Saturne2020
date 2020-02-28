/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.items.Connexion;
import org.centrale.pgrou.saturne.repositories.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author louis-alexandre
 */
public class DisconnectController {
    
    @Autowired
    ConnexionRepository connexionRepository;
    
    @RequestMapping(value = "Disconnect.do", method = RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request) {
        Security.check(connexionRepository);
        String code = request.getParameter("code");
        
        if ((code != null) && (!code.isEmpty())) {
            Optional<Connexion> result = connexionRepository.findById(code);
            if (result.isPresent()) {
                Connexion connexion = result.get();
                connexionRepository.delete(connexion);
            }
        }
        return new ModelAndView("index");
    }
    
}
