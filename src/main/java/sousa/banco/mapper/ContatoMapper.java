package sousa.banco.mapper;

import sousa.banco.dto.ContatoDTO;
import sousa.banco.entity.Contato;

public class ContatoMapper {
        public static ContatoDTO toDTO(Contato contato) {
            if (contato == null) {
                return null;
            }
            return new ContatoDTO(
                    contato.getTipoContato(),
                    contato.getContato(),
                    contato.getCliente()
            );
        }

        public static Contato toEntity(ContatoDTO contatoDTO) {
            if (contatoDTO == null) {
                return null;
            }
            Contato contato = new Contato();
            contato.setTipoContato(contatoDTO.tipoContato());
            contato.setContato(contatoDTO.contato());
            contato.setCliente(contatoDTO.cliente());
            return contato;
        }
}
