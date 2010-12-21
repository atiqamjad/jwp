package com.samsonych.model.wp;

// Generated 21.12.2010 7:24:07 by Hibernate Tools 3.4.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WpUsers generated by hbm2java
 */
@Entity
@Table(name = "wp_users", catalog = "gads_wp")
public class WpUsers implements java.io.Serializable {

    private Long id;
    private String userLogin;
    private String userPass;
    private String userNicename;
    private String userEmail;
    private String userUrl;
    private Date userRegistered;
    private String userActivationKey;
    private int userStatus;
    private String displayName;

    public WpUsers() {
    }

    public WpUsers(String userLogin, String userPass, String userNicename, String userEmail,
            String userUrl, Date userRegistered, String userActivationKey, int userStatus,
            String displayName) {
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userNicename = userNicename;
        this.userEmail = userEmail;
        this.userUrl = userUrl;
        this.userRegistered = userRegistered;
        this.userActivationKey = userActivationKey;
        this.userStatus = userStatus;
        this.displayName = displayName;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_login", nullable = false, length = 60)
    public String getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Column(name = "user_pass", nullable = false, length = 64)
    public String getUserPass() {
        return this.userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Column(name = "user_nicename", nullable = false, length = 50)
    public String getUserNicename() {
        return this.userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    @Column(name = "user_email", nullable = false, length = 100)
    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "user_url", nullable = false, length = 100)
    public String getUserUrl() {
        return this.userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_registered", nullable = false, length = 19)
    public Date getUserRegistered() {
        return this.userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    @Column(name = "user_activation_key", nullable = false, length = 60)
    public String getUserActivationKey() {
        return this.userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
    }

    @Column(name = "user_status", nullable = false)
    public int getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Column(name = "display_name", nullable = false, length = 250)
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
