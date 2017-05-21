package com.demo.bank.data.repository;

import com.demo.bank.data.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface ClientRepository extends CrudRepository<Client,Long> {
}
