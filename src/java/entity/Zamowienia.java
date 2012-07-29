/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marek
 */
@Entity
@Table(name = "zamowienia", catalog = "book_store", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamowienia.findAll", query = "SELECT z FROM Zamowienia z"),
    @NamedQuery(name = "Zamowienia.findByIDZamowienia", query = "SELECT z FROM Zamowienia z WHERE z.iDZamowienia = :iDZamowienia"),
    @NamedQuery(name = "Zamowienia.findByData", query = "SELECT z FROM Zamowienia z WHERE z.data = :data"),
    @NamedQuery(name = "Zamowienia.findByStan", query = "SELECT z FROM Zamowienia z WHERE z.stan = :stan")})
public class Zamowienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDZamowienia")
    private Integer iDZamowienia;
    @Column(name = "Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "stan")
    private String stan;
    @JoinColumn(name = "Ksiazka_IDKsiazka", referencedColumnName = "IDKsiazka")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ksiazka ksiazkaIDKsiazka;
    @JoinColumn(name = "Klient_IDKlient", referencedColumnName = "IDKlient")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Klient klientIDKlient;

    public Zamowienia() {
    }

    public Zamowienia(Integer iDZamowienia) {
        this.iDZamowienia = iDZamowienia;
    }

    public Integer getIDZamowienia() {
        return iDZamowienia;
    }

    public void setIDZamowienia(Integer iDZamowienia) {
        this.iDZamowienia = iDZamowienia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public Ksiazka getKsiazkaIDKsiazka() {
        return ksiazkaIDKsiazka;
    }

    public void setKsiazkaIDKsiazka(Ksiazka ksiazkaIDKsiazka) {
        this.ksiazkaIDKsiazka = ksiazkaIDKsiazka;
    }

    public Klient getKlientIDKlient() {
        return klientIDKlient;
    }

    public void setKlientIDKlient(Klient klientIDKlient) {
        this.klientIDKlient = klientIDKlient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDZamowienia != null ? iDZamowienia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienia)) {
            return false;
        }
        Zamowienia other = (Zamowienia) object;
        if ((this.iDZamowienia == null && other.iDZamowienia != null) || (this.iDZamowienia != null && !this.iDZamowienia.equals(other.iDZamowienia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Zamowienia[ iDZamowienia=" + iDZamowienia + " ]";
    }
    
}
