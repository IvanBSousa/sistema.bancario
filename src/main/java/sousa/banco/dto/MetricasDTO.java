package sousa.banco.dto;

public record MetricasDTO(
        String nomeMetodo,
        long count,
        double totalSeconds,
        double maxSeconds,
        double meanSeconds
) {
}
