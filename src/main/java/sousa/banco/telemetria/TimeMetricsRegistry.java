package sousa.banco.telemetria;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class TimeMetricsRegistry {

    private final MeterRegistry meterRegistry;
    private final Map<String, TimeMetricState> states = new ConcurrentHashMap<>();

    public TimeMetricsRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public TimeMetricState getOrCreate(String metricName) {
        return states.computeIfAbsent(metricName, name -> {
            TimeMetricState state = new TimeMetricState();

            meterRegistry.gauge(name + ".ultimaExecucao", state.ultimaExecucao());
            meterRegistry.gauge(name + ".minExecucao", state.minExecucao());

            return state;
        });
    }

    public TimeMetricState get(String metricName) {
        return states.get(metricName);
    }
}
