/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

/**
 *
 * @author Mario
 */
public class ReponseQCMAuto extends ReponseQCM{
    private Boolean cochee;

    public ReponseQCMAuto() {
    }

    public ReponseQCMAuto(Boolean cochee, String ennonce, Integer id, Boolean correcte) {
        super(ennonce, id, correcte);
        this.cochee= cochee;
    }

     

    public Boolean getCochee() {
        return cochee;
    }

    public void setCochee(Boolean cochee) {
        this.cochee = cochee;
    }
    
    
    
    
}
