package com.ilegra.resource;

import com.ilegra.service.MigrationService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/flyway")
@ApplicationScoped
@Tags(value = @Tag(name = "Migration"))
public class MigrationResource {

    private final MigrationService migrationService;

    public MigrationResource(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @GET
    @Path("/current-migration")
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Current migration applied in database",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN))})
    @Operation(summary = "Get the current migration applied in database")
    public Response getCurrentMigration() {
        return Response.ok(migrationService.getCurrentMigration()).build();
    }
}
