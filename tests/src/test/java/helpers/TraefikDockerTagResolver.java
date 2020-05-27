package helpers;

import io.homecentr.testcontainers.images.EnvironmentImageTagResolver;

public class TraefikDockerTagResolver extends EnvironmentImageTagResolver {
    public TraefikDockerTagResolver() {
        super("homecentr/traefik:local");
    }
}
