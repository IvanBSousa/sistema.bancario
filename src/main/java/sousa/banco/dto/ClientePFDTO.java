package sousa.banco.dto;

import sousa.banco.enums.EstadoCivilEnum;

import java.time.LocalDate;
import java.util.List;

public record ClientePFDTO(
        String nomeCompleto,
        String cpf,
        List<DocumentoDTO> documentos,
        LocalDate dataNascimento,
        String nacionalidade,
        EstadoCivilEnum estadoCivil,
        String cpfConjuge,
        String nomeConjuge,
        List<RendaDTO> renda,
        List<EnderecoDTO> endereco,
        List<ContatoDTO> contato
) {
}
