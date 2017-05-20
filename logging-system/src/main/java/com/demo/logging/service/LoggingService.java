package com.demo.logging.service;

import com.demo.logging.api.ApiLogData;
import com.demo.logging.object.LogMessage;

import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface LoggingService {

    void saveMessage(LogMessage logMessage) throws Exception;

    List<ApiLogData> getLogsForUser(Long userId);
}
