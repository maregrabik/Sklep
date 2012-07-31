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
@Table(name = "ksiazka", catalog = "book_store", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ksiazka.findAll", query = "SELECT k FROM Ksiazka k"),
    @NamedQuery(name = "Ksiazka.findByIDKsiazka", query = "SELECT k FROM Ksiazka k WHERE k.iDKsiazka = :iDKsiazka"),
    @NamedQuery(name = "Ksiazka.findByNazwa", query = "SELECT k FROM Ksiazka k WHERE k.nazwa = :nazwa"),
    @NamedQuery(name = "Ksiazka.findByCena", query = "SELECT k FROM Ksiazka k WHERE k.cena = :cena")})
public class Ksiazka implements Serializable {
    @Column(name = "Autor")
    private String autor;
    @Column(name = "Opis")
    private String opis;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKsiazka")
    private Integer iDKsiazka;
    @Column(name = "Nazwa")
    private String nazwa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Cena")
    private Double cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ksiazkaIDKsiazka", fetch = FetchType.EAGER)
    private Collection<Zamowienia> zamowieniaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ksiazka", fetch = FetchType.EAGER)
    private Collection<Komentarz> komentarzCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ksiazkaIDKsiazka", fetch = FetchType.EAGER)
    private Collection<Kategoria> kategoriaCollection;

    public Ksiazka() {
    }

    public Ksiazka(Integer iDKsiazka) {
        this.iDKsiazka = iDKsiazka;
    }

    public Integer getIDKsiazka() {
        return iDKsiazka;
    }

    public void setIDKsiazka(Integer iDKsiazka) {
        this.iDKsiazka = iDKsiazka;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
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

    @XmlTransient
    public Collection<Kategoria> getKategoriaCollection() {
        return kategoriaCollection;
    }

    public void setKategoriaCollection(Collection<Kategoria> kategoriaCollection) {
        this.kategoriaCollection = kategoriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDKsiazka != null ? iDKsiazka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazka)) {
            return false;
        }
        Ksiazka other = (Ksiazka) object;
        if ((this.iDKsiazka == null && other.iDKsiazka != null) || (this.iDKsiazka != null && !this.iDKsiazka.equals(other.iDKsiazka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ksiazka[ iDKsiazka=" + iDKsiazka + " ]";
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
}
