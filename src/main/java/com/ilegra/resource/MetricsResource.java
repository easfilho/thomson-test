package com.ilegra.resource;

import com.ilegra.enums.TemporalParameter;
import com.ilegra.factory.MetricsAccessFactory;
import com.ilegra.resource.dto.MetricsAccessDto;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;

@Path("/v1/laa")
@ApplicationScoped
@Traced
@Tags(value = @Tag(name = "Metrics"))
public class MetricsResource {

    private final LogService logService;
    private final MetricsAccessFactory metricsAccessFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsResource.class);

    public MetricsResource(LogService logService, MetricsAccessFactory metricsAccessFactory) {
        this.logService = logService;
        this.metricsAccessFactory = metricsAccessFactory;
    }

    @GET
    @Path("/metrics")
    @Timeout(300)
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.1, delay = 1000)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "400",
                            description = "Invalid temporal grouper",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN)),
                    @APIResponse(
                            responseCode = "200",
                            description = "Metrics about URLs accessed",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = MetricsAccessDto.class))) })
    @Operation(summary = "Get Metrics about URLs accessed grouped by different visions")
    public Response getMetrics(@QueryParam("temporalGrouper") @Valid @NotNull(message = "Temporal grouper can not be null") TemporalParameter temporalParameter) {
        LOGGER.info("[METRICS] Starting collecting metrics about accessed URLs");
        return Stream.of(logService.getMetrics())
                .map(metricsAccessFactory::create)
                .map(metricsAccessDto -> Response.ok(metricsAccessDto).build())
                .findFirst()
                .orElse(Response.serverError().build());
    }
}
