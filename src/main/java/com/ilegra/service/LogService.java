package com.ilegra.service;

import com.ilegra.factory.LogFactory;
import com.ilegra.model.LogModel;
import com.ilegra.repository.LogRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LogService {

    private final LogFactory logFactory;
    private final LogRepository logRepository;

    public LogService(LogFactory logFactory, LogRepository logRepository) {
        this.logFactory = logFactory;
        this.logRepository = logRepository;
    }

    public LogModel save(String log) {
        LogModel logModel = logFactory.create(log);
        logRepository.persist(logModel);
        return logModel;
    }
}
