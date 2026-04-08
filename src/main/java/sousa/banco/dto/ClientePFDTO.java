package sousa.banco.dto;

import sousa.banco.enums.EstadoCivilEnum;
import sousa.banco.validation.ValidaEnderecoResidencial;

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
        @ValidaEnderecoResidencial
        List<EnderecoDTO> endereco,
        List<ContatoDTO> contato
) {
}
