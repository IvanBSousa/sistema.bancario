package sousa.banco.dto;

import sousa.banco.enums.RegimeTributarioEnum;

import java.time.LocalDate;
import java.util.List;

public record ClientePJDTO(
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        LocalDate dataConstituicao,
        RegimeTributarioEnum regimeTributario,
        List<ParticipacaoSocietariaDTO> socios,
        List<FaturamentoDTO> faturamento,
        List<EnderecoDTO> endereco,
        List<ContatoDTO> contato
) {
}
