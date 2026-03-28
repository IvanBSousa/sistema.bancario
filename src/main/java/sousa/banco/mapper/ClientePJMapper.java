package sousa.banco.mapper;

import sousa.banco.dto.ClientePJDTO;
import sousa.banco.entity.ClientePJ;

public class ClientePJMapper {

    public static ClientePJDTO toDTO(ClientePJ clientePJ) {
        if (clientePJ == null) {
            return null;
        }
        return new ClientePJDTO(
                clientePJ.getRazaoSocial(),
                clientePJ.getNomeFantasia(),
                clientePJ.getCnpj(),
                clientePJ.getDataConstituicao(),
                clientePJ.getRegimeTributario(),
                clientePJ.getSocios()
                        .stream()
                        .map(ParticipacaoSocietariaMapper::toDTO)
                        .toList(),
                clientePJ.getFaturamento()
                        .stream()
                        .map(FaturamentoMapper::toDTO)
                        .toList(),
                clientePJ.getEndereco()
                        .stream()
                        .map(EnderecoMapper::toDTO)
                        .toList(),
                clientePJ.getContato()
                        .stream()
                        .map(ContatoMapper::toDTO)
                        .toList()
        );
    }

    public static ClientePJ toEntity(ClientePJDTO clientePJDTO) {
        if (clientePJDTO == null) {
            return null;
        }
        ClientePJ clientePJ = new ClientePJ();
        clientePJ.setRazaoSocial(clientePJDTO.razaoSocial());
        clientePJ.setNomeFantasia(clientePJDTO.nomeFantasia());
        clientePJ.setCnpj(clientePJDTO.cnpj());
        clientePJ.setDataConstituicao(clientePJDTO.dataConstituicao());
        clientePJ.setRegimeTributario(clientePJDTO.regimeTributario());
        clientePJ.setSocios(
                clientePJDTO.socios()
                        .stream()
                        .map(ParticipacaoSocietariaMapper::toEntity)
                        .toList()
        );
        clientePJ.setFaturamento(
                clientePJDTO.faturamento()
                        .stream()
                        .map(FaturamentoMapper::toEntity)
                        .toList()
        );
        clientePJ.setEndereco(
                clientePJDTO.endereco()
                        .stream()
                        .map(EnderecoMapper::toEntity)
                        .toList()
        );
        clientePJ.setContato(
                clientePJDTO.contato()
                        .stream()
                        .map(ContatoMapper::toEntity)
                        .toList()
        );
        return clientePJ;
    }
}
