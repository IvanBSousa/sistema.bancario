package sousa.banco.mapper;

import sousa.banco.dto.DocumentoDTO;
import sousa.banco.entity.Documento;

public class DocumentoMapper {

    public static DocumentoDTO toDTO(Documento documento) {
        if (documento == null) {
            return null;
        }
        return new DocumentoDTO(
                documento.getTipoDocumento(),
                documento.getNumeroDocumento(),
                documento.getOrgaoEmissor(),
                documento.getEstadoEmissor(),
                documento.getDataEmissao(),
                documento.getDataValidade(),
                documento.getDocClienteFK()
        );
    }

    public static Documento toEntity(DocumentoDTO documentoDTO) {
        if (documentoDTO == null) {
            return null;
        }
        Documento documento = new Documento();
        documento.setTipoDocumento(documentoDTO.tipoDocumento());
        documento.setNumeroDocumento(documentoDTO.numeroDocumento());
        documento.setOrgaoEmissor(documentoDTO.orgaoEmissor());
        documento.setEstadoEmissor(documentoDTO.estadoEmissor());
        documento.setDataEmissao(documentoDTO.dataEmissao());
        documento.setDataValidade(documentoDTO.dataValidade());
        documento.setDocClienteFK(documentoDTO.cliente());
        return documento;
    }
}
