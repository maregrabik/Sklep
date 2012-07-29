/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marek
 */
@Entity
@Table(name = "komentarz", catalog = "book_store", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Komentarz.findAll", query = "SELECT k FROM Komentarz k"),
    @NamedQuery(name = "Komentarz.findByIdKomentarz", query = "SELECT k FROM Komentarz k WHERE k.idKomentarz = :idKomentarz"),
    @NamedQuery(name = "Komentarz.findByTresc", query = "SELECT k FROM Komentarz k WHERE k.tresc = :tresc")})
public class Komentarz implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdKomentarz")
    private Integer idKomentarz;
    @Column(name = "Tresc")
    private String tresc;
    @JoinColumn(name = "Ksiazka", referencedColumnName = "IDKsiazka")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ksiazka ksiazka;
    @JoinColumn(name = "Klient", referencedColumnName = "IDKlient")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Klient klient;

    public Komentarz() {
    }

    public Komentarz(Integer idKomentarz) {
        this.idKomentarz = idKomentarz;
    }

    public Integer getIdKomentarz() {
        return idKomentarz;
    }

    public void setIdKomentarz(Integer idKomentarz) {
        this.idKomentarz = idKomentarz;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKomentarz != null ? idKomentarz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Komentarz)) {
            return false;
        }
        Komentarz other = (Komentarz) object;
        if ((this.idKomentarz == null && other.idKomentarz != null) || (this.idKomentarz != null && !this.idKomentarz.equals(other.idKomentarz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Komentarz[ idKomentarz=" + idKomentarz + " ]";
    }
    
}
