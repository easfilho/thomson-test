package com.ilegra.service;

import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MigrationService {

    private final Flyway flyway;

    public MigrationService(Flyway flyway) {
        this.flyway = flyway;
    }

    public String getCurrentMigration() {
        return flyway.info().current().getVersion().getVersion();
    }
}
