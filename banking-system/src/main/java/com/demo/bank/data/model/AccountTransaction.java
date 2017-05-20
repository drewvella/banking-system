package com.demo.bank.data.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Entity
@Table(name = "account_transaction", schema = "public", catalog = "banking")
public class AccountTransaction {
    private Long id;
    private String reference;
    private BigDecimal debitedAmount;
    private BigDecimal creditedAmount;
    private Account sourceAccount;
    private Account destinationAccount;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reference")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Basic
    @Column(name = "debited_amount")
    public BigDecimal getDebitedAmount() {
        return debitedAmount;
    }

    public void setDebitedAmount(BigDecimal debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    @Basic
    @Column(name = "credited_amount")
    public BigDecimal getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(BigDecimal creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountTransaction that = (AccountTransaction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reference != null ? !reference.equals(that.reference) : that.reference != null) return false;
        if (debitedAmount != null ? !debitedAmount.equals(that.debitedAmount) : that.debitedAmount != null)
            return false;
        if (creditedAmount != null ? !creditedAmount.equals(that.creditedAmount) : that.creditedAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (debitedAmount != null ? debitedAmount.hashCode() : 0);
        result = 31 * result + (creditedAmount != null ? creditedAmount.hashCode() : 0);
        return result;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_account_id", referencedColumnName = "id", nullable = false)
    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id", nullable = false)
    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
