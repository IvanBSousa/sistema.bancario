package sousa.banco.dto;

import java.util.concurrent.atomic.AtomicLong;

public record MetricasDTO(
        String nomeMetodo,
        long count,
        double ultimaExecucao,
        double totalSeconds,
        double minSeconds,
        double maxSeconds,
        double meanSeconds


) {
}
