/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DBManager;
import entity.Klient;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Marek
 */
@ManagedBean
@SessionScoped
public class AktualnyUzytkownik {

    private String login;
    private String haslo;
    private Klient klientAktualny;

    public AktualnyUzytkownik() {
    }

    public String loguj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        klientAktualny = new Klient();
        try {
            klientAktualny = (Klient) em.createNamedQuery("Klient.findByLogin").setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wystąpił błąd podczas logowania,sprawdz login ponownie"));
            return "index.xhtml";
        }

        if (klientAktualny.getHaslo().equals(haslo)) {
            return "start.xhtml";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wystąpił błąd podczas logowania,sprawdz hasło ponoweni"));
            return "index.xhtml";
        }
    }
    public void uaktualnij(){
        
      EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.merge(this.klientAktualny);
        em.getTransaction().commit();
        em.close();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Udało sie, Dane zostały zaktualizowane"));  
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Klient getKlientAktualny() {
        return klientAktualny;
    }

    public void setKlientAktualny(Klient klientAktualny) {
        this.klientAktualny = klientAktualny;
    }
}
