package sousa.banco.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.enterprise.context.ApplicationScoped;
import sousa.banco.dto.MetricasDTO;
import sousa.banco.telemetria.TimeMetricState;
import sousa.banco.telemetria.TimeMetricsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TelemetriaService {

    private final MeterRegistry meterRegistry;
    private final TimeMetricsRegistry timeMetricsRegistry;

    public TelemetriaService(MeterRegistry meterRegistry, TimeMetricsRegistry timeMetricsRegistry) {
        this.meterRegistry = meterRegistry;
        this.timeMetricsRegistry = timeMetricsRegistry;
    }

    public List<MetricasDTO> buscarMetricas(String... nomesMetodos) {
        List<MetricasDTO> metricas = new ArrayList<>();

        for (String nomeMetodo : nomesMetodos) {
            MetricasDTO metricasDTO = buscarMetrica(nomeMetodo);
            if (metricasDTO != null) {
                metricas.add(metricasDTO);
            }
        }

        return metricas;
    }

    public MetricasDTO buscarMetrica(String nomeMetodo) {
        Timer timer = meterRegistry.find(nomeMetodo).timer();
        if (timer == null) {
            return null;
        }

        TimeMetricState state = timeMetricsRegistry.get(nomeMetodo);

        double ultimaExecucao = state != null ? state.ultimaExecucaoSec() : 0.0;
        double minSeconds = state != null ? state.minExecucaoSec() : 0.0;

        return new MetricasDTO(
                nomeMetodo,
                timer.count(),
                ultimaExecucao,
                timer.totalTime(TimeUnit.SECONDS),
                minSeconds,
                timer.max(TimeUnit.SECONDS),
                timer.mean(TimeUnit.SECONDS)
        );
    }

}
