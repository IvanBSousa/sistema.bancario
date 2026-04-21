package sousa.banco.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Liveness
public class KeycloakHealth implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/realms/banco-realm/.well-known/openid-configuration"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return HealthCheckResponse.up("Keycloak está acessível!");
            } else {
                return HealthCheckResponse.down("Keycloak retornou status " + response.statusCode());
            }
        } catch (Exception e) {
            return HealthCheckResponse.down("Keycloak inacessível! Erro: " + e.getMessage());
        }
    }
}