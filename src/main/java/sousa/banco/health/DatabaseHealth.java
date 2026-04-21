package sousa.banco.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.sql.Connection;
import java.sql.DriverManager;

@Liveness
public class DatabaseHealth implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clientesdb",
                "postgres", "postgres")) {
            return HealthCheckResponse.up("Banco de dados conectado!");
        } catch (Exception e) {
            return HealthCheckResponse.down("Banco de dados inacessível! Erro: " + e);
        }
    }
}