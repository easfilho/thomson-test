package com.ilegra.resource;

import com.ilegra.factory.LogFactory;
import com.ilegra.resource.dto.LogInputDto;
import com.ilegra.service.LogService;
import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;


@Path("/laar")
public class AccessResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessResource.class);
    private final LogService logService;
    private final LogFactory logFactory;

    public AccessResource(LogService logService, LogFactory logFactory) {
        this.logService = logService;
        this.logFactory = logFactory;
    }

    @POST
    @Path("/ingest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(final LogInputDto logInputDto) {
        LOGGER.info("[ACCESS-LOG] Starting the save of log {}", logInputDto);
        return Stream.of(logInputDto)
                .map(LogInputDto::getLog)
                .map(logService::save)
                .map(logFactory::create)
                .map(logOutputDto -> Response.status(Response.Status.CREATED).entity(logOutputDto).build())
                .findFirst()
                .orElse(Response.serverError().build());
    }
}
