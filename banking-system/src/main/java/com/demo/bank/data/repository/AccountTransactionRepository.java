package com.demo.bank.data.repository;

import com.demo.bank.data.model.AccountTransaction;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;


/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction,Long>,QueryDslPredicateExecutor<AccountTransaction>{


}
