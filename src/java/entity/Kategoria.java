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
@Table(name = "kategoria", catalog = "book_store", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategoria.findAll", query = "SELECT k FROM Kategoria k"),
    @NamedQuery(name = "Kategoria.findByIdKategoria", query = "SELECT k FROM Kategoria k WHERE k.idKategoria = :idKategoria"),
    @NamedQuery(name = "Kategoria.findByNazwa", query = "SELECT k FROM Kategoria k WHERE k.nazwa = :nazwa")})
public class Kategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idKategoria")
    private Integer idKategoria;
    @Column(name = "Nazwa")
    private String nazwa;
    @JoinColumn(name = "Ksiazka_IDKsiazka", referencedColumnName = "IDKsiazka")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ksiazka ksiazkaIDKsiazka;

    public Kategoria() {
    }

    public Kategoria(Integer idKategoria) {
        this.idKategoria = idKategoria;
    }

    public Integer getIdKategoria() {
        return idKategoria;
    }

    public void setIdKategoria(Integer idKategoria) {
        this.idKategoria = idKategoria;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Ksiazka getKsiazkaIDKsiazka() {
        return ksiazkaIDKsiazka;
    }

    public void setKsiazkaIDKsiazka(Ksiazka ksiazkaIDKsiazka) {
        this.ksiazkaIDKsiazka = ksiazkaIDKsiazka;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategoria != null ? idKategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategoria)) {
            return false;
        }
        Kategoria other = (Kategoria) object;
        if ((this.idKategoria == null && other.idKategoria != null) || (this.idKategoria != null && !this.idKategoria.equals(other.idKategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kategoria[ idKategoria=" + idKategoria + " ]";
    }
    
}
