package sousa.banco.mapper;

import sousa.banco.dto.RendaDTO;
import sousa.banco.entity.Renda;

public class RendaMapper {

    public static RendaDTO toDTO(Renda renda) {
        if (renda == null) {
            return null;
        }
        return new RendaDTO(
                renda.getRendaMensalBruta(),
                renda.getRendaMensalLiquida(),
                renda.getDescontos(),
                renda.getDocFontePagadora(),
                renda.getNomeFontePagadora(),
                renda.getProfissao(),
                renda.getTipoDocumentoRenda()
        );
    }

    public static Renda toEntity(RendaDTO rendaDTO) {
        if (rendaDTO == null) {
            return null;
        }

        Renda renda = new Renda();
        renda.setRendaMensalBruta(rendaDTO.rendaMensalBruta());
        renda.setRendaMensalLiquida(rendaDTO.rendaMensalLiquida());
        renda.setDescontos(rendaDTO.descontos());
        renda.setDocFontePagadora(rendaDTO.docFontePagadora());
        renda.setNomeFontePagadora(rendaDTO.nomeFontePagadora());
        renda.setProfissao(rendaDTO.profissao());
        renda.setTipoDocumentoRenda(rendaDTO.tipoDocumentoRenda());
        return renda;
    }
}
