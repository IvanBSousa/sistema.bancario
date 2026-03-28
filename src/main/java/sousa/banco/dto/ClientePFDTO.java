package sousa.banco.dto;

import sousa.banco.entity.ClientePF;
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
        ClientePF conjuge,
        List<RendaDTO> renda,
        List<EnderecoDTO> endereco,
        List<ContatoDTO> contato
) {
}
