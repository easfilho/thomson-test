package com.ilegra.resource;

import com.ilegra.factory.LogFactory;
import com.ilegra.resource.dto.LogInputDto;
import com.ilegra.service.LogService;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;

@Path("/laar")
@ApplicationScoped
@Traced
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
    public Response create(@Valid @NotNull(message = "Body can not be null") final LogInputDto logInputDto) {
        LOGGER.info("[ACCESS-LOG] Starting the save of log {}", logInputDto);
        return Stream.of(logInputDto)
                .map(LogInputDto::getLog)
                .map(logService::save)
                .map(logFactory::createModel)
                .map(logOutputDto -> Response.status(Response.Status.CREATED).entity(logOutputDto).build())
                .findFirst()
                .orElse(Response.serverError().build());
    }
}
