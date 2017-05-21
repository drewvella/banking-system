package com.demo.bank.data.repository;

import com.demo.bank.data.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface AccountRepository extends CrudRepository<Account,Long> {
    //Locking the account row for updating to prevent conflicts
    @Query(value = "SELECT * FROM account a WHERE a.id=?1 FOR UPDATE", nativeQuery= true)
    Account findByIdAndLock(Long accountId);
}
