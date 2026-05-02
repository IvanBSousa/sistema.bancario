package sousa.banco.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import sousa.banco.telemetria.TimeMetricsRegistry;

import java.util.concurrent.TimeUnit;

@TimeMetrics()
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TimeMetricsInterceptor {

    private final MeterRegistry meterRegistry;
    private final TimeMetricsRegistry timeMetricsRegistry;

    public TimeMetricsInterceptor(MeterRegistry meterRegistry, TimeMetricsRegistry timeMetricsRegistry) {
        this.meterRegistry = meterRegistry;
        this.timeMetricsRegistry = timeMetricsRegistry;
    }

    @AroundInvoke
    public Object medicao(InvocationContext context) throws Exception {
        TimeMetrics annotation = context.getMethod().getAnnotation(TimeMetrics.class);
        if (annotation == null) {
            annotation = context.getTarget().getClass().getAnnotation(TimeMetrics.class);
        }

        String metricaNome = annotation.value();
        Timer.Sample sample = Timer.start(meterRegistry);

        try {
            return context.proceed();
        } finally {
            long duracaoNanos = sample.stop(meterRegistry.timer(metricaNome));
            timeMetricsRegistry.getOrCreate(metricaNome).atualiza(duracaoNanos);
        }
    }
}
