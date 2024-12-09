package org.eduardomaravill.sdd_catalogo.configs.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Api SDD-Catalog",
                version = "1.0.35",
                description = """
                        ## Project: SDD-Catalog
                        A platform for managing vehicle configurations and fostering a gaming community.
                        Currently, video game enthusiasts, particularly players of [**Need for Speed Unbound**](https://www.ea.com/es-es/games/need-for-speed/need-for-speed-unbound), lack a centralized platform where they can create, save, and share personalized configurations for their vehicles.
                        
                        ---
                        
                        ## Proposed Solution
                        
                        [**SDD-Catalog**](https://sdd-catalog.netlify.app/home) allows users to:
                        
                        - Manage Vehicle Configurations.
                        - Explore Other Users' Configurations.
                        - Secure their accounts with robust authentication.
                        - Interact with other users and the platform administrator.
                        """,
                termsOfService = "https://github.com/EduardoMaravilla/sdd-catalog-backend/blob/master/Term.md",
                contact = @Contact(name = "Eduardo Maravilla", email = "eduardomaravilladev@gmail.com", url = "https://sdd-catalog.netlify.app/contact"),
                license = @License(name = "Apache License", url = "https://github.com/EduardoMaravilla/sdd-catalog-backend/blob/master/LICENSE")
        ),
        servers = {
                @Server(
                        description = "Development Server",
                        url = "http://localhost:9595/api/v1"
                ),
                @Server(
                        description = "Production Server",
                        url = "https://splendid-consideration-production.up.railway.app/api/v1")
        },
        security = {
                @SecurityRequirement(name = "Security Token")
        }

)
@SecurityScheme(
        name = "Security Token",
        description = "Access token for authentication",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}

