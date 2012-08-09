/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DBManager;
import entity.Klient;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

@ManagedBean
@RequestScoped
public class Rejestruj {

    private String login;
    private String haslo;
    private Klient klientDoRejestracji;

   
    public Rejestruj() {
   



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

    public Klient getKlientDoRejestracji() {
        return klientDoRejestracji;
    }

    public void setKlientDoRejestracji(Klient klientDoRejestracji) {
        this.klientDoRejestracji = klientDoRejestracji;
    }

    public String rejestruj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        FacesContext context = FacesContext.getCurrentInstance();
        setKlientDoRejestracji(new Klient());

        try {
            setKlientDoRejestracji((Klient) em.createNamedQuery("Klient.findByLogin").setParameter("login", login).getSingleResult());
        } catch (Exception e) {       
            getKlientDoRejestracji().setIDKlient(null);
            getKlientDoRejestracji().setLogin(login);
            getKlientDoRejestracji().setHaslo(haslo);
            getKlientDoRejestracji().setNazwisko("Prosze uzupełnić");
            getKlientDoRejestracji().setAdres("Prosze uzupełnić");
            getKlientDoRejestracji().setImie("Prosze uzupełnić");
            em.getTransaction().begin();
            em.persist(getKlientDoRejestracji());
            em.getTransaction().commit();
            em.close();           
            context.addMessage(null, new FacesMessage("Udało sie", "Zostałeś zarejestrowany do bazy " + this.getLogin()+" Po zalogowaniu prosimy o uzupełnieniu danych w panelu użytkownika"));
            setKlientDoRejestracji(new Klient());
            return "index.xhtml";
            
        }
            context.addMessage(null, new FacesMessage("Login " + this.getLogin()+" jest już zajęty"));
            return "index.xhtml";
               
            
            
           
        
 
    }

}
