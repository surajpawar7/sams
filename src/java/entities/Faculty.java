/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Ashish
 */
@Entity
@Table(name = "faculty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f"),
    @NamedQuery(name = "Faculty.findByIdFaculty", query = "SELECT f FROM Faculty f WHERE f.idFaculty = :idFaculty"),
    @NamedQuery(name = "Faculty.findByFacultyFname", query = "SELECT f FROM Faculty f WHERE f.facultyFname = :facultyFname"),
    @NamedQuery(name = "Faculty.findByFacultyLname", query = "SELECT f FROM Faculty f WHERE f.facultyLname = :facultyLname"),
    @NamedQuery(name = "Faculty.findByFacultyMobile", query = "SELECT f FROM Faculty f WHERE f.facultyMobile = :facultyMobile"),
    @NamedQuery(name = "Faculty.findByFacultyEmail", query = "SELECT f FROM Faculty f WHERE f.facultyEmail = :facultyEmail"),
    @NamedQuery(name = "Faculty.findByFacultyPassword", query = "SELECT f FROM Faculty f WHERE f.facultyPassword = :facultyPassword"),
    @NamedQuery(name = "Faculty.findByFacultyDepartment", query = "SELECT f FROM Faculty f WHERE f.facultyDepartment = :facultyDepartment"),
    @NamedQuery(name = "Faculty.findByFacultyLastLogin", query = "SELECT f FROM Faculty f WHERE f.facultyLastLogin = :facultyLastLogin"),
    @NamedQuery(name = "Faculty.findByFacultyTitle", query = "SELECT f FROM Faculty f WHERE f.facultyTitle = :facultyTitle"),
    @NamedQuery(name = "Faculty.findByFacultyShowFeedback", query = "SELECT f FROM Faculty f WHERE f.facultyShowFeedback = :facultyShowFeedback")})
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_faculty")
    private String idFaculty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "faculty_fname")
    private String facultyFname;
    @Size(max = 45)
    @Column(name = "faculty_lname")
    private String facultyLname;
    @Size(max = 10)
    @Column(name = "faculty_mobile")
    private String facultyMobile;
    @Size(max = 45)
    @Column(name = "faculty_email")
    private String facultyEmail;
    @Size(max = 45)
    @Column(name = "faculty_password")
    private String facultyPassword;
    @Size(max = 45)
    @Column(name = "faculty_department")
    private String facultyDepartment;
    @Column(name = "faculty_last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date facultyLastLogin;
    @Size(max = 45)
    @Column(name = "faculty_title")
    private String facultyTitle;
    @Column(name = "faculty_show_feedback")
    private Boolean facultyShowFeedback;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFaculty")
    private Collection<FacultySubject> facultySubjectCollection;

    public Faculty() {
    }

    public Faculty(String idFaculty) {
        this.idFaculty = idFaculty;
    }

    public Faculty(String idFaculty, String facultyFname) {
        this.idFaculty = idFaculty;
        this.facultyFname = facultyFname;
    }

    public String getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(String idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getFacultyFname() {
        return facultyFname;
    }

    public void setFacultyFname(String facultyFname) {
        this.facultyFname = facultyFname;
    }

    public String getFacultyLname() {
        return facultyLname;
    }

    public void setFacultyLname(String facultyLname) {
        this.facultyLname = facultyLname;
    }

    public String getFacultyMobile() {
        return facultyMobile;
    }

    public void setFacultyMobile(String facultyMobile) {
        this.facultyMobile = facultyMobile;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getFacultyPassword() {
        return facultyPassword;
    }

    public void setFacultyPassword(String facultyPassword) {
        final String hash = DigestUtils.sha256Hex(facultyPassword);
        System.out.println(hash);
        this.facultyPassword = hash;
       // this.facultyPassword = facultyPassword;
    }

    public String getFacultyDepartment() {
        return facultyDepartment;
    }

    public void setFacultyDepartment(String facultyDepartment) {
        this.facultyDepartment = facultyDepartment;
    }

    public Date getFacultyLastLogin() {
        return facultyLastLogin;
    }

    public void setFacultyLastLogin(Date facultyLastLogin) {
        this.facultyLastLogin = facultyLastLogin;
    }

    public String getFacultyTitle() {
        return facultyTitle;
    }

    public void setFacultyTitle(String facultyTitle) {
        this.facultyTitle = facultyTitle;
    }

    public Boolean getFacultyShowFeedback() {
        return facultyShowFeedback;
    }

    public void setFacultyShowFeedback(Boolean facultyShowFeedback) {
        this.facultyShowFeedback = facultyShowFeedback;
    }

    @XmlTransient
    public Collection<FacultySubject> getFacultySubjectCollection() {
        return facultySubjectCollection;
    }

    public void setFacultySubjectCollection(Collection<FacultySubject> facultySubjectCollection) {
        this.facultySubjectCollection = facultySubjectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaculty != null ? idFaculty.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculty)) {
            return false;
        }
        Faculty other = (Faculty) object;
        if ((this.idFaculty == null && other.idFaculty != null) || (this.idFaculty != null && !this.idFaculty.equals(other.idFaculty))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Faculty[ idFaculty=" + idFaculty + " ]";
    }
    
}