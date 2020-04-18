package com.ilegra.resource;

import com.ilegra.enums.TemporalParameter;
import com.ilegra.factory.MetricsAccessFactory;
import com.ilegra.service.LogService;
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

@Path("/laa")
@ApplicationScoped
@Traced
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMetrics(@QueryParam("temporalGrouper") @Valid @NotNull(message = "Temporal grouper can not be null") TemporalParameter temporalParameter) {
        LOGGER.info("[METRICS] Starting collecting metrics about accessed URLs");
        return Stream.of(logService.getMetrics())
                .map(metricsAccessFactory::create)
                .map(metricsAccessDto -> Response.ok(metricsAccessDto).build())
                .findFirst()
                .orElse(Response.serverError().build());
    }
}
