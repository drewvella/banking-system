package com.demo.logging.controller;

import com.demo.logging.api.ApiLogData;
import com.demo.logging.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@RestController
@RequestMapping("/api/logging-system")
public class LoggingDataController {

    @Autowired
    private LoggingService loggingService;

    /**
     * Retrieves logs for a specified user id
     * @param userId - User for which to receive logs
     * @return List of api log information
     */
    @RequestMapping(value = "/users/{userId}/logs", method = RequestMethod.GET)
    public List<ApiLogData> getUserLogs(@PathVariable Long userId) {
        return loggingService.getLogsForUser(userId);
    }
}
