package sousa.banco.dto;

import java.time.LocalDateTime;

public record RespostaErrosDTO(
        String codigo,
        String detalhes,
        String path,
        LocalDateTime timestamp
) {
}
