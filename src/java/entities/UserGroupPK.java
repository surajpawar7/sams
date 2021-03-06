/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Ashish
 */
@Embeddable
public class UserGroupPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "role_name")
    private String roleName;

    /**
     * Creates UserGroupPK Entity
     */
    public UserGroupPK() {
    }

    /**
     * Creates UserGroupPK Entity with the specified user_name and role_name
     * @param userName
     * @param roleName
     */
    public UserGroupPK(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    /**
     * Get user_name from UserGroupPK Entity
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set user_name for UserGroupPK Entity
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get role_name from UserGroupPK Entity
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set role_name for UserGroupPK Entity
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        hash += (roleName != null ? roleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupPK)) {
            return false;
        }
        UserGroupPK other = (UserGroupPK) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        if ((this.roleName == null && other.roleName != null) || (this.roleName != null && !this.roleName.equals(other.roleName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserGroupPK[ userName=" + userName + ", roleName=" + roleName + " ]";
    }
    
}
