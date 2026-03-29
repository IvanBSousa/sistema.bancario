package sousa.banco.mapper;

import sousa.banco.dto.ParticipacaoSocietariaDTO;
import sousa.banco.entity.ParticipacaoSocietaria;

public class ParticipacaoSocietariaMapper {

    public static ParticipacaoSocietariaDTO toDTO(ParticipacaoSocietaria participacaoSocietaria) {
        if (participacaoSocietaria == null) {
            return null;
        }
        return new ParticipacaoSocietariaDTO(
                participacaoSocietaria.getEmpresa(),
                participacaoSocietaria.getSocio(),
                participacaoSocietaria.getEmpresaSocia(),
                participacaoSocietaria.getPercentualParticipacao(),
                participacaoSocietaria.getDataEntrada(),
                participacaoSocietaria.getTipoParticipacao()
        );
    }

    public static ParticipacaoSocietaria toEntity(ParticipacaoSocietariaDTO participacaoSocietariaDTO) {
        if (participacaoSocietariaDTO == null) {
            return null;
        }
        ParticipacaoSocietaria participacaoSocietaria = new ParticipacaoSocietaria();
        participacaoSocietaria.setEmpresa(participacaoSocietariaDTO.empresa());
        participacaoSocietaria.setSocio(participacaoSocietariaDTO.socio());
        participacaoSocietaria.setEmpresaSocia(participacaoSocietariaDTO.empresaSocia());
        participacaoSocietaria.setPercentualParticipacao(participacaoSocietariaDTO.percentualParticipacao());
        participacaoSocietaria.setDataEntrada(participacaoSocietariaDTO.dataEntrada());
        participacaoSocietaria.setTipoParticipacao(participacaoSocietariaDTO.tipoParticipacao());
        return participacaoSocietaria;
    }
}
