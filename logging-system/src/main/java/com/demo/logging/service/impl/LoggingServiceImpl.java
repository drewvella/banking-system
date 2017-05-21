package com.demo.logging.service.impl;

import com.demo.logging.api.ApiLogData;
import com.demo.logging.data.mapping.ModelMapper;
import com.demo.logging.data.model.LoggingData;
import com.demo.logging.data.repository.LoggingDataRepository;
import com.demo.logging.object.LogMessage;
import com.demo.logging.service.LoggingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    public static final ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        }
    };


    @Autowired
    private LoggingDataRepository loggingDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveMessage(LogMessage logMessage) throws Exception {
        LoggingData loggingData = new LoggingData();
        loggingData.setLogDateTime(sdf.get().parse(logMessage.getDateTime()));
        loggingData.setLogType(logMessage.getAction());
        loggingData.setUserId(Long.parseLong(logMessage.getUserId()));
        loggingDataRepository.save(loggingData);
    }

    @Override
    public List<ApiLogData> getLogsForUser(Long userId) {
        List<LoggingData> loggingData = loggingDataRepository.findByUserId(userId);
        return modelMapper.mapAsList(loggingData,ApiLogData.class);
    }
}
