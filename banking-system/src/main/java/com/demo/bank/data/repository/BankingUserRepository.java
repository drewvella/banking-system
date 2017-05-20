package com.demo.bank.data.repository;

import com.demo.bank.data.model.BankingUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author andrewvella
 * @since  21/05/2017.
 */
public interface BankingUserRepository extends CrudRepository<BankingUser,Long> {

    BankingUser findByUsername(String username);
}
