/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marek
 */
@Entity
@Table(name = "klient", catalog = "book_store", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k"),
    @NamedQuery(name = "Klient.findByIDKlient", query = "SELECT k FROM Klient k WHERE k.iDKlient = :iDKlient"),
    @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie"),
    @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko"),
    @NamedQuery(name = "Klient.findByLogin", query = "SELECT k FROM Klient k WHERE k.login = :login"),
    @NamedQuery(name = "Klient.findByHaslo", query = "SELECT k FROM Klient k WHERE k.haslo = :haslo"),
    @NamedQuery(name = "Klient.findByAdres", query = "SELECT k FROM Klient k WHERE k.adres = :adres")})
public class Klient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKlient")
    private Integer iDKlient;
    @Column(name = "Imie")
    private String imie;
    @Column(name = "Nazwisko")
    private String nazwisko;
    @Column(name = "Login")
    private String login;
    @Column(name = "Haslo")
    private String haslo;
    @Column(name = "Adres")
    private String adres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientIDKlient", fetch = FetchType.EAGER)
    private Collection<Zamowienia> zamowieniaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klient", fetch = FetchType.EAGER)
    private Collection<Komentarz> komentarzCollection;

    public Klient() {
    }

    public Klient(Integer iDKlient) {
        this.iDKlient = iDKlient;
    }

    public Integer getIDKlient() {
        return iDKlient;
    }

    public void setIDKlient(Integer iDKlient) {
        this.iDKlient = iDKlient;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @XmlTransient
    public Collection<Zamowienia> getZamowieniaCollection() {
        return zamowieniaCollection;
    }

    public void setZamowieniaCollection(Collection<Zamowienia> zamowieniaCollection) {
        this.zamowieniaCollection = zamowieniaCollection;
    }

    @XmlTransient
    public Collection<Komentarz> getKomentarzCollection() {
        return komentarzCollection;
    }

    public void setKomentarzCollection(Collection<Komentarz> komentarzCollection) {
        this.komentarzCollection = komentarzCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDKlient != null ? iDKlient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.iDKlient == null && other.iDKlient != null) || (this.iDKlient != null && !this.iDKlient.equals(other.iDKlient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Klient[ iDKlient=" + iDKlient + " ]";
    }
    
}
