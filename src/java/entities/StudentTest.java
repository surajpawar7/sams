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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Create POJO Entity for table 'student_test'
 * @author piit
 */
@Entity
@Table(name = "student_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTest.findAll", query = "SELECT s FROM StudentTest s"),
    @NamedQuery(name = "StudentTest.findByIdCurrentStudent", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.idCurrentStudent = :idCurrentStudent"),
    @NamedQuery(name = "StudentTest.findByIdCurrentStudentIdSubjectTest", query = "SELECT s FROM StudentTest s WHERE s.currentStudent = :idCurrentStudent AND s.subject = :idSubject AND s.studentTestPK.test = :test"),
    @NamedQuery(name = "StudentTest.findByIdSubject", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.idSubject = :idSubject"),
    @NamedQuery(name = "StudentTest.findByMarks", query = "SELECT s FROM StudentTest s WHERE s.marks = :marks"),
    @NamedQuery(name = "StudentTest.findByTest", query = "SELECT s FROM StudentTest s WHERE s.studentTestPK.test = :test")})
public class StudentTest implements Serializable {
     @Max(value=100)  @Min(value=-1)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "marks")
    private BigDecimal marks;
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected StudentTestPK studentTestPK;
    @JoinColumn(name = "id_subject", referencedColumnName = "id_subject", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;
    @JoinColumn(name = "id_current_student", referencedColumnName = "id_current_student", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CurrentStudent currentStudent;

    /**
     * Create StudentTest Entity
     */
    public StudentTest() {
    }

    /**
     * Create StudentTest Entity with the specified studentTestPK
     * @param studentTestPK
     */
    public StudentTest(StudentTestPK studentTestPK) {
        this.studentTestPK = studentTestPK;
    }

    /**
     * Create StudentTest Entity with the specific id_current_student, id_subject and test
     * @param idCurrentStudent
     * @param idSubject
     * @param test
     */
    public StudentTest(int idCurrentStudent, int idSubject, short test) {
        this.studentTestPK = new StudentTestPK(idCurrentStudent, idSubject, test);
    }

    /**
     * Get StudentTestPK from StudentTest Entity
     * @return
     */
    public StudentTestPK getStudentTestPK() {
        return studentTestPK;
    }

    /**
     * Set StudentTestPK for StudentTest Entity
     * @param studentTestPK
     */
    public void setStudentTestPK(StudentTestPK studentTestPK) {
        this.studentTestPK = studentTestPK;
    }

    /**
     * Get subject from StudentTest Entity
     * @return
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Set subject for StudentTest Entity
     * @param subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Get current_student from StudentTest Entity
     * @return
     */
    public CurrentStudent getCurrentStudent() {
        return currentStudent;
    }

    /**
     * Set current_student for StudentTest Entity
     * @param currentStudent
     */
    public void setCurrentStudent(CurrentStudent currentStudent) {
        this.currentStudent = currentStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentTestPK != null ? studentTestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentTest)) {
            return false;
        }
        StudentTest other = (StudentTest) object;
        if ((this.studentTestPK == null && other.studentTestPK != null) || (this.studentTestPK != null && !this.studentTestPK.equals(other.studentTestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentTest[ studentTestPK=" + studentTestPK + " ]";
    }

    /**
     *
     * @return
     */
    public BigDecimal getMarks() {
        return marks;
    }

    /**
     *
     * @param marks
     */
    public void setMarks(BigDecimal marks) {
        this.marks = marks;
    }
    
}
