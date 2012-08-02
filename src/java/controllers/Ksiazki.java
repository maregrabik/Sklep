/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DBManager;
import entity.Ksiazka;
import entity.Zamowienia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Marek
 */
@ManagedBean
@SessionScoped
public class Ksiazki {

    /**
     * Creates a new instance of Ksiazki
     */
    private Ksiazka aktywnaKsiazka;
    private ArrayList<Ksiazka> koszyk = new ArrayList<Ksiazka>();
    @ManagedProperty(value="#{aktualnyUzytkownik}")
    private AktualnyUzytkownik uzytkownikAktualny;

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

    public void doKoszyka() {
        this.getKoszyk().add(getAktywnaKsiazka());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Książka dodana do koszyka"));
        
        
    }

    public Ksiazka getAktywnaKsiazka() {
        return aktywnaKsiazka;
    }

    public void setAktywnaKsiazka(Ksiazka aktywnaKsiazka) {
        this.aktywnaKsiazka = aktywnaKsiazka;
    }

    public ArrayList<Ksiazka> getKoszyk() {
        return koszyk;
    }

    public void setKoszyk(ArrayList<Ksiazka> koszyk) {
        this.koszyk = koszyk;
    }
    public String usunZKoszyk(Ksiazka ksiazka) {

        this.getKoszyk().remove(ksiazka);
        return ("koszyk.xhtml");
    }
    public String realizujKoszyk(){
        
     EntityManager em = DBManager.getManager().createEntityManager();
     Zamowienia z = new Zamowienia();
     for(Ksiazka k:this.getKoszyk()){
         
     em.getTransaction().begin();
     
     z.setIDZamowienia(null);
     z.setKlientIDKlient(this.uzytkownikAktualny.getKlientAktualny());
     z.setKsiazkaIDKsiazka(k);  
     z.setStan("Przyjęte do zamówienia");
     z.setData(new Date());
     em.persist(z); 
     em.getTransaction().commit();
    
     
//     z.setKlientIDKlient(klientAktualny.getIDKlient());
//     z.setIDklient(klientAktualny.getIDKlient());
//     z.setKsiazkaIDKsiazka(k.getIDKsiazka().toString());
//     z.setIDksiazka(k.getIDksiazka());
//     z.setData(new Date());
//     z.setStan("przyjeto zamowienie");
     
     } 
     this.koszyk=null;
     return("sukces.xhtml");
    }

    public AktualnyUzytkownik getUzytkownikAktualny() {
        return uzytkownikAktualny;
    }

    public void setUzytkownikAktualny(AktualnyUzytkownik uzytkownikAktualny) {
        this.uzytkownikAktualny = uzytkownikAktualny;
    }

  
}
