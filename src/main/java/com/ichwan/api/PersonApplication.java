package com.ichwan.api;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Person API",
                description = "Person API with REST and SQL Server",
                version = "1.0.0"
        ),
        tags = @Tag(name = "persons")
)
public class PersonApplication extends Application {
}
