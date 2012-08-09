/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.DBManager;
import entity.Klient;
import entity.Komentarz;
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
    @ManagedProperty(value = "#{aktualnyUzytkownik}")
    private AktualnyUzytkownik uzytkownikAktualny;
    private String trescKomentarza;
    private Komentarz komentarz;

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

    public String getTrescKomentarza() {
        return trescKomentarza;
    }

    public void setTrescKomentarza(String trescKomentarza) {
        this.trescKomentarza = trescKomentarza;
    }

    public String usunZKoszyk(Ksiazka ksiazka) {

        this.getKoszyk().remove(ksiazka);
        return ("koszyk.xhtml");
    }

    public String realizujKoszyk() {

        EntityManager em = DBManager.getManager().createEntityManager();

        for (Ksiazka k : this.getKoszyk()) {
            Zamowienia z = new Zamowienia();
            em.getTransaction().begin();

            z.setIDZamowienia(null);
            z.setKlientIDKlient(this.uzytkownikAktualny.getKlientAktualny());
            z.setKsiazkaIDKsiazka(k);
            z.setStan("Przyjęte do zamówienia");
            z.setData(new Date());
            em.persist(z);
            em.getTransaction().commit();



        }
        setKoszyk(new ArrayList<Ksiazka>());
        return "sukces.xhtml";
    }

    public AktualnyUzytkownik getUzytkownikAktualny() {
        return uzytkownikAktualny;
    }

    public void setUzytkownikAktualny(AktualnyUzytkownik uzytkownikAktualny) {
        this.uzytkownikAktualny = uzytkownikAktualny;
    }

    public List<Ksiazka> zwrocKomentarze() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List list = em.createNamedQuery("Komentarz.znajdzPoIdKsiazki").setParameter("idKsiazki", aktywnaKsiazka).getResultList();
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }

    }

    public String zwrocUzytkownikaPoId(Integer id) {
        EntityManager em = DBManager.getManager().createEntityManager();
        Klient k = (Klient) em.createNamedQuery("Klient.findByIDKlient").setParameter("iDKlient", id).getSingleResult();
        if (!k.getImie().isEmpty()) {
            em.close();
            return k.getImie();
        } else {
            em.close();
            return "nieznany użytkownik";
        }


    }

    public String dodajKomentarz() {

        komentarz = new Komentarz();
        EntityManager em = DBManager.getManager().createEntityManager();
        getKomentarz().setIdKomentarz(null);
        getKomentarz().setKlient(uzytkownikAktualny.getKlientAktualny());
        getKomentarz().setKsiazka(aktywnaKsiazka);
        getKomentarz().setTresc(trescKomentarza);
        em.getTransaction().begin();
        em.persist(getKomentarz());
        em.getTransaction().commit();
        em.close();


        return "pokazKsiazke";

    }

    public Komentarz getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(Komentarz komentarz) {
        this.komentarz = komentarz;
    }
}
