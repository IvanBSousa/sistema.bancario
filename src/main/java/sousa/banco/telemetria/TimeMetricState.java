package sousa.banco.telemetria;

import java.util.concurrent.atomic.AtomicLong;

public final class TimeMetricState {

    private final AtomicLong ultimaExecucao = new AtomicLong(0);
    private final AtomicLong minExecucao = new AtomicLong(Long.MAX_VALUE);

    public double ultimaExecucaoSec() {
        return ultimaExecucao.get() / 1000.0;
    }

    public double minExecucaoSec() {
        long min = minExecucao.get();
        return  min == Long.MAX_VALUE ? 0.0 : min / 1000.0;
    }

    public void atualiza(long duracao) {
        ultimaExecucao.set(duracao);
        minExecucao.accumulateAndGet(duracao, Math::min);
    }

    public AtomicLong ultimaExecucao() {
        return ultimaExecucao;
    }

    public AtomicLong minExecucao() {
        return minExecucao;
    }
}
