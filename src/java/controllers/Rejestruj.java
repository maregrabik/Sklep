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
        EntityManager em = DBManager.getManager().createEntityManager();



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

    public void rejestruj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        FacesContext context = FacesContext.getCurrentInstance();
        klientDoRejestracji = new Klient();

        try {
            klientDoRejestracji = (Klient) em.createNamedQuery("Klient.findByLogin").setParameter("login", login).getSingleResult();
        } catch (Exception e) {

            klientDoRejestracji.setIDKlient(null);
            klientDoRejestracji.setLogin(login);
            klientDoRejestracji.setHaslo(haslo);
            klientDoRejestracji.setNazwisko("Prosze uzupełnić");
            klientDoRejestracji.setAdres("Prosze uzupełnić");
            klientDoRejestracji.setImie("Prosze uzupełnić");
            em.getTransaction().begin();
            em.persist(klientDoRejestracji);
            em.getTransaction().commit();
            em.close();
            
            context.addMessage(null, new FacesMessage("Udało sie", "Zostałeś zarejestrowany do bazy " + this.getLogin()+" Po zalogowaniu prosimy o uzupełnieniu danych w panelu użytkownika"));
        }
 
    }

}
