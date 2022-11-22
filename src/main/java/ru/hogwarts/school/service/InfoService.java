package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    private final Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer port;

    public Integer getPort(){
        logger.info("was invoking method getPort");
        return port;
    }

    public Integer getIntValue() {
        return Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}
