package sousa.banco.messaging;

public record ClientePFCadastradoEventDTO(
        Long clienteId,
        String nome,
        String email,
        String cpf
) {
}
