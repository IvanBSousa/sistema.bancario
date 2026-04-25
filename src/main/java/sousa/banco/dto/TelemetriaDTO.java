package sousa.banco.dto;

import java.util.List;

public record TelemetriaDTO(
    List<MetricasDTO> telemetria
) {
}
