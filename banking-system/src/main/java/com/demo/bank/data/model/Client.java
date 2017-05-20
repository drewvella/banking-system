package com.demo.bank.data.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Entity
public class Client {
    private Long id;
    private String clientName;
    private String clientSurname;
    private Set<Account> accounts;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "client_surname")
    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (clientName != null ? !clientName.equals(client.clientName) : client.clientName != null) return false;
        if (clientSurname != null ? !clientSurname.equals(client.clientSurname) : client.clientSurname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientSurname != null ? clientSurname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
