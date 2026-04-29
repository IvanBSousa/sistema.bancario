package sousa.banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sousa.banco.dto.*;
import sousa.banco.entity.ClientePF;
import sousa.banco.enums.*;
import sousa.banco.exception.ConflictException;
import sousa.banco.exception.NotFoundException;
import sousa.banco.messaging.ClientePFCadastradoPublisher;
import sousa.banco.repository.ClientePFRepository;
import sousa.banco.service.ClientePFService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de unidade para ClientePFService")
public class ClientePFServiceTest {

    @InjectMocks
    private ClientePFService clientePFService;

    @Mock
    private ClientePFRepository clientePFRepository;

    @Mock
    private ClientePFCadastradoPublisher clientePFCadastradoPublisher;

    private ClientePFDTO clientePFDTO;

    @BeforeEach
    void setUp() {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                EndercoEnum.RUA,
                "Rua Teste",
                "123",
                "Apto 101",
                "Bairro Teste",
                "Cidade Teste",
                EstadosEnum.AC,
                "12345-678",
                TipoEnderecoEnum.RESIDENCIAL
        );

        DocumentoDTO documentoDTO = new DocumentoDTO(
                TipoDocumentoEnum.RG,
                "123456789",
                "SSP/AC",
                EstadosEnum.AC,
                LocalDate.of(2010, 1, 1),
                LocalDate.of(2030, 1, 1)
        );

        ContatoDTO contatoDTO = new ContatoDTO(
                TipoContatoEnum.TELEFONE_RESIDENCIAL,
                "(11) 1234-5678"
        );

        RendaDTO rendaDTO = new RendaDTO(
                new BigDecimal("5000.00"),
                new BigDecimal("4950.00"),
                new BigDecimal("50.00"),
                "12345678000100",
                "Empresa Teste",
                "Cargo Teste",
                DocumentoRendaEnum.CONTRACHEQUE
        );

        clientePFDTO = new ClientePFDTO(
                "Nome Teste",
                "12345678900",
                List.of(documentoDTO),
                LocalDate.of(1990, 1, 1),
                "Nacionalidade Teste",
                EstadoCivilEnum.SOLTEIRO,
                null,
                null,
                List.of(rendaDTO),
                List.of(enderecoDTO),
                List.of(contatoDTO)
        );
    }

    @Test
    @DisplayName("Deve criar um ClientePF com sucesso")
    void testCriaClientePF_Sucesso() {
        when(clientePFRepository.buscaPorCpf("12345678900")).thenReturn(null);
        doNothing().when(clientePFRepository).persist(any(ClientePF.class));

        clientePFService.criaClientePF(clientePFDTO);

        verify(clientePFRepository, times(1)).buscaPorCpf("12345678900");
        verify(clientePFRepository, times(1)).persist(any(ClientePF.class));
        verify(clientePFCadastradoPublisher, times(1)).publicar(any(ClientePF.class));
    }

    @Test
    @DisplayName("Deve lançar ConflictException quando CPF já existe")
    void testCriaClientePF_CPFDuplicado() {
        // Arrange
        ClientePF clienteExistente = new ClientePF();
        clienteExistente.setCpf("12345678900");
        when(clientePFRepository.buscaPorCpf("12345678900")).thenReturn(clienteExistente);

        // Act & Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            clientePFService.criaClientePF(clientePFDTO);
        });

        assertEquals("ClientePF com CPF 12345678900 já existe.", exception.getMessage());
        assertEquals("CONFLICT", exception.getCodigo());
        verify(clientePFRepository, times(1)).buscaPorCpf("12345678900");
        verify(clientePFRepository, never()).persist(any(ClientePF.class));
    }

    @Test
    @DisplayName("Deve encontrar cliente por CPF com sucesso")
    void testBuscaClientePFPorCPF_Sucesso() {
        // Arrange
        ClientePF clienteEsperado = new ClientePF();
        clienteEsperado.setNomeCompleto("Nome Teste");
        clienteEsperado.setCpf("12345678900");

        when(clientePFRepository.buscaPorCpf("12345678900")).thenReturn(clienteEsperado);

        // Act
        var resultado = clientePFService.buscaClientePFPorCPF("12345678900");

        // Assert
        assertNotNull(resultado);
        assertEquals("Nome Teste", resultado.nomeCompleto());
        verify(clientePFRepository, times(1)).buscaPorCpf("12345678900");
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando cliente não encontrado por CPF")
    void testBuscaClientePFPorCPF_NaoEncontrado() {
        // Arrange
        when(clientePFRepository.buscaPorCpf("99999999999")).thenReturn(null);

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            clientePFService.buscaClientePFPorCPF("99999999999");
        });

        assertEquals("ClientePF com CPF 99999999999 não encontrado.", exception.getMessage());
        assertEquals("NOT_FOUND", exception.getCodigo());
        verify(clientePFRepository, times(1)).buscaPorCpf("99999999999");
    }

    @Test
    @DisplayName("Deve encontrar cliente por ID com sucesso")
    void testBuscaClientePFPorId_Sucesso() {
        // Arrange
        ClientePF clienteEsperado = new ClientePF();
        clienteEsperado.setNomeCompleto("Nome Teste");
        clienteEsperado.setCpf("12345678900");

        when(clientePFRepository.findById(1L)).thenReturn(clienteEsperado);

        // Act
        var resultado = clientePFService.buscaClientePFPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nome Teste", resultado.nomeCompleto());
        verify(clientePFRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando cliente não encontrado por ID")
    void testBuscaClientePFPorId_NaoEncontrado() {
        // Arrange
        when(clientePFRepository.findById(999L)).thenReturn(null);

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            clientePFService.buscaClientePFPorId(999L);
        });

        assertEquals("ClientePF com ID 999 não encontrado.", exception.getMessage());
        assertEquals("NOT_FOUND", exception.getCodigo());
        verify(clientePFRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Deve deletar cliente com sucesso")
    void testDeletaClientePF_Sucesso() {
        // Arrange
        when(clientePFRepository.deleteById(1L)).thenReturn(true);

        // Act
        boolean resultado = clientePFService.deletaClientePF(1L);

        // Assert
        assertTrue(resultado);
        verify(clientePFRepository, times(1)).deleteById(1L);
    }
}
