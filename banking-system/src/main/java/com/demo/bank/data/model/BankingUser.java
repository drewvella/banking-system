package com.demo.bank.data.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Entity
@Table(name = "banking_user")
public class BankingUser {
    private Long bankingUserId;
    private String username;
    private String password;
    private Boolean enabled;
    private Set<BankingUserRole> roles;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banking_user_id")
    public Long getBankingUserId() {
        return bankingUserId;
    }

    public void setBankingUserId(Long bankingUserId) {
        this.bankingUserId = bankingUserId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankingUser that = (BankingUser) o;

        if (bankingUserId != null ? !bankingUserId.equals(that.bankingUserId) : that.bankingUserId != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankingUserId != null ? bankingUserId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
    @OneToMany(mappedBy="bankingUser", fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.LAZY)
    public Set<BankingUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<BankingUserRole> roles) {
        this.roles = roles;
    }
}
