/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Creates POJO Entity for table 'old_fb_pi'
 * @author piit
 */
@Entity
@Table(name = "old_fb_pi", catalog = "piit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OldFbPi.findAll", query = "SELECT o FROM OldFbPi o"),
    @NamedQuery(name = "OldFbPi.findAllGroupByUser", query = "SELECT o FROM OldFbPi o GROUP BY o.oldFbPiPK.facId"),
    @NamedQuery(name = "OldFbPi.findByFacId", query = "SELECT o FROM OldFbPi o WHERE o.oldFbPiPK.facId = :facId ORDER BY o.oldFbPiPK.subId,o.oldFbPiPK.division,o.oldFbPiPK.batch"),
    @NamedQuery(name = "OldFbPi.findByFname", query = "SELECT o FROM OldFbPi o WHERE o.fname = :fname"),
    @NamedQuery(name = "OldFbPi.findByLname", query = "SELECT o FROM OldFbPi o WHERE o.lname = :lname"),
    @NamedQuery(name = "OldFbPi.findBySubId", query = "SELECT o FROM OldFbPi o WHERE o.oldFbPiPK.subId = :subId"),
    @NamedQuery(name = "OldFbPi.findByFtype", query = "SELECT o FROM OldFbPi o WHERE o.ftype = :ftype"),
    @NamedQuery(name = "OldFbPi.findByDivision", query = "SELECT o FROM OldFbPi o WHERE o.oldFbPiPK.division = :division"),
    @NamedQuery(name = "OldFbPi.findByBatch", query = "SELECT o FROM OldFbPi o WHERE o.oldFbPiPK.batch = :batch"),
    @NamedQuery(name = "OldFbPi.findByPi", query = "SELECT o FROM OldFbPi o WHERE o.pi = :pi")})
public class OldFbPi implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OldFbPiPK oldFbPiPK;
    @Size(max = 100)
    @Column(name = "fname")
    private String fname;
    @Size(max = 100)
    @Column(name = "lname")
    private String lname;
    @Column(name = "ftype")
    private Short ftype;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pi")
    private BigDecimal pi;

    /**
     * Creates OldFbPi Entity
     */
    public OldFbPi() {
    }

    /**
     * Creates OldFbPi Entity with the specified oldFbPiPk  
     * @param oldFbPiPK
     */
    public OldFbPi(OldFbPiPK oldFbPiPK) {
        this.oldFbPiPK = oldFbPiPK;
    }

    /**
     * Creates OldFbPi Entity with the specified fac_id, sub_id, division and batch
     * @param facId
     * @param subId
     * @param division
     * @param batch
     */
    public OldFbPi(String facId, String subId, String division, short batch) {
        this.oldFbPiPK = new OldFbPiPK(facId, subId, division, batch);
    }

    /**
     * Get OldFbPiPk from OldFbPi Entity
     * @return
     */
    public OldFbPiPK getOldFbPiPK() {
        return oldFbPiPK;
    }

    /**
     * Set OldFbPiPk for OldFbPi Entity
     * @param oldFbPiPK
     */
    public void setOldFbPiPK(OldFbPiPK oldFbPiPK) {
        this.oldFbPiPK = oldFbPiPK;
    }

    /**
     * Get fname from OldFbPi Entity
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     * Set fname for OldFbPi Entity
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Get lname from OldFbPi Entity
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     * Set lname for OldFbPi Entity
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Get ftype from OldFbPi Entity
     * @return
     */
    public short getFtype() {
        return ftype;
    }
    
    /**
     * TODO:
     * @return
     */
    public String getFtypeD() {
        if (ftype == 0)
            return "Theory";
        else
            return "Prac/Tut";
    }

    /**
     * Set ftype from OldFbPi Entity
     * @param ftype
     */
    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    /**
     * TODO:
     * @return
     */
    public BigDecimal getPi() {
        return pi;
    }

    /**
     * TODO:
     * @param pi
     */
    public void setPi(BigDecimal pi) {
        this.pi = pi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oldFbPiPK != null ? oldFbPiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OldFbPi)) {
            return false;
        }
        OldFbPi other = (OldFbPi) object;
        if ((this.oldFbPiPK == null && other.oldFbPiPK != null) || (this.oldFbPiPK != null && !this.oldFbPiPK.equals(other.oldFbPiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OldFbPi[ oldFbPiPK=" + oldFbPiPK + " ]";
    }
    
}
