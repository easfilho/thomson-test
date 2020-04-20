package com.ilegra.resource;

import com.ilegra.factory.LogFactory;
import com.ilegra.resource.dto.LogInputDto;
import com.ilegra.resource.dto.LogOutputDto;
import com.ilegra.service.LogService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
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

@Path("/v1/laar")
@ApplicationScoped
@Traced
@Tags(value = @Tag(name = "Access"))
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
    @Timeout(300)
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.1, delay = 1000)
    @Transactional
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "400",
                            description = "Invalid log parameter",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @APIResponse(
                            responseCode = "201",
                            description = "Information about log saved with success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = LogOutputDto.class))) })
    @Operation(summary = "Receive a log and save his information in data base")
    public Response create(@Valid @NotNull(message = "Body can not be null") final LogInputDto logInputDto) {
        LOGGER.info("[ACCESS-LOG-SAVE] Starting the save of log {}", logInputDto);
        return Stream.of(logInputDto)
                .map(LogInputDto::getLog)
                .map(logService::save)
                .map(logFactory::createModel)
                .map(logOutputDto -> Response.status(Response.Status.CREATED).entity(logOutputDto).build())
                .findFirst()
                .orElse(Response.serverError().build());
    }
}
