package sousa.banco.mapper;

import sousa.banco.dto.ClientePJDTO;
import sousa.banco.entity.*;

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
        if (clientePJDTO.socios() != null) {
            clientePJDTO.socios().forEach(sDto -> {
                ParticipacaoSocietaria socio = ParticipacaoSocietariaMapper.toEntity(sDto);
                clientePJ.addSocio(socio); // ✅ mantém consistência
            });
        }

        if (clientePJDTO.faturamento() != null) {
            clientePJDTO.faturamento().forEach(fDto -> {
                Faturamento faturamento = FaturamentoMapper.toEntity(fDto);
                clientePJ.addFaturamento(faturamento); // ✅ importante
            });
        }
        if (clientePJDTO.endereco() != null) {
            clientePJDTO.endereco().forEach(eDto -> {
                Endereco endereco = EnderecoMapper.toEntity(eDto);
                clientePJ.addEndereco(endereco); // ✅ garante FK
            });
        }
        if (clientePJDTO.contato() != null) {
            clientePJDTO.contato().forEach(cDto -> {
                Contato contato = ContatoMapper.toEntity(cDto);
                clientePJ.addContato(contato); // ✅ mesmo padrão
            });
        }
        return clientePJ;
    }
}
