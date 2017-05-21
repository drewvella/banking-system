package com.demo.logging.data.mapping;

import com.demo.logging.api.ApiLogData;
import com.demo.logging.data.model.LoggingData;
import com.demo.logging.service.impl.LoggingServiceImpl;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
public class ModelMapper extends ConfigurableMapper {

    public void configure(MapperFactory factory) {
        super.configure(factory);

        factory.registerClassMap(factory.classMap(LoggingData.class, ApiLogData.class)
                .customize(new CustomMapper<LoggingData, ApiLogData>() {
                    @Override
                    public void mapAtoB(LoggingData loggingData, ApiLogData apiLog, MappingContext context) {
                        apiLog.setAction(loggingData.getLogType());
                        apiLog.setLogDate(LoggingServiceImpl.sdf.get().format(loggingData.getLogDateTime()));
                    }
                }).byDefault().toClassMap());

    }
}