package sousa.banco.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.enterprise.context.ApplicationScoped;
import sousa.banco.dto.MetricasDTO;

import java.util.List;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TelemetriaService {

    private final MeterRegistry meterRegistry;

    public TelemetriaService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void addMetricas(List<MetricasDTO> metricas, String nomeMetodo) {
        Timer timer = meterRegistry.find(nomeMetodo).timer();

        if (timer == null) {
            return;
        }

        MetricasDTO metricasDTO = new MetricasDTO(
                nomeMetodo,
                timer.count(),
                timer.totalTime(TimeUnit.SECONDS),
                timer.max(TimeUnit.SECONDS),
                timer.mean(TimeUnit.SECONDS)
        );

        metricas.add(metricasDTO);
    }

}
