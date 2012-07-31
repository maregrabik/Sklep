/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DBManager;
import entity.Ksiazka;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Marek
 */
@ManagedBean
@RequestScoped
public class Ksiazki {

    /**
     * Creates a new instance of Ksiazki
     */
    private Ksiazka aktywnaKsiazka; 
            
    public Ksiazki() {
            
    }
     public List<Ksiazka> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List list = em.createNamedQuery("Ksiazka.findAll").getResultList();
        em.close();
        return list;

    }
      public String ksiazkaUstawAktywneId(Ksiazka ksiazka) {
        this.setAktywnaKsiazka(ksiazka);
        return "pokazKsiazke.xhtml";
}

    public Ksiazka getAktywnaKsiazka() {
        return aktywnaKsiazka;
    }

    public void setAktywnaKsiazka(Ksiazka aktywnaKsiazka) {
        this.aktywnaKsiazka = aktywnaKsiazka;
    }
}
