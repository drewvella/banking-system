package com.demo.bank.data.model;

import javax.persistence.*;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Entity
@Table(name = "banking_user_role")
public class BankingUserRole {
    private Long bankingUserRoleId;
    private String role;
    private BankingUser bankingUser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="banking_user_id")
    public BankingUser getBankingUser() {
        return bankingUser;
    }

    public void setBankingUser(BankingUser bankingUser) {
        this.bankingUser = bankingUser;
    }





    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banking_user_role_id")
    public Long getBankingUserRoleId() {
        return bankingUserRoleId;
    }

    public void setBankingUserRoleId(Long bankingUserRoleId) {
        this.bankingUserRoleId = bankingUserRoleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankingUserRole that = (BankingUserRole) o;

        if (bankingUserRoleId != null ? !bankingUserRoleId.equals(that.bankingUserRoleId) : that.bankingUserRoleId != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankingUserRoleId != null ? bankingUserRoleId.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
