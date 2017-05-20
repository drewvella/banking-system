package com.demo.logging.data.repository;

import com.demo.logging.data.model.LoggingData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface LoggingDataRepository extends CrudRepository<LoggingData,Long> {

    List<LoggingData> findByUserId(Long userId);

}
