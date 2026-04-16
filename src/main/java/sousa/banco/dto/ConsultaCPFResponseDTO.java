package sousa.banco.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultaCPFResponseDTO(
        int code,
        ConsultaCPFDataDTO data
) {
    public record ConsultaCPFDataDTO(
            String cpf,
            String nome,
            String genero,
            @JsonProperty("data_nascimento")
            String dataNascimento
    ) {}
}


