package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class InfoService {
    private final Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer port;

    public Integer getPort() {
        logger.debug("was invoking method getPort");
        return port;
    }

    public Long getIntValue() {
        logger.debug("was invoking method getIntValue");
        return LongStream.rangeClosed(1, 1000000)
                .reduce(0, Long::sum);
    }
}
