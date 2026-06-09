package com.alansilva.financas_api.service;

import com.alansilva.financas_api.dto.request.TransacaoRequestDTO;
import com.alansilva.financas_api.dto.response.TransacaoResponseDTO;
import com.alansilva.financas_api.entity.Categoria;
import com.alansilva.financas_api.entity.Transacao;
import com.alansilva.financas_api.entity.enums.TipoTransacao;
import com.alansilva.financas_api.exception.ResourceNotFoundException;
import com.alansilva.financas_api.repository.CategoriaRepository;
import com.alansilva.financas_api.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void deveCriarTransacaoComSucesso(){

        UUID categoriaId = UUID.randomUUID();

        Categoria categoria = Categoria.builder()
                .idCategoria(categoriaId)
                .nome("Salário")
                .build();

        TransacaoRequestDTO request =
                new TransacaoRequestDTO(
                        TipoTransacao.ENTRADA,
                        new BigDecimal("5000.00"),
                        LocalDate.now(),
                        "Salário Mensal",
                        categoriaId
                );

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.of(categoria));

        when(transacaoRepository.save(any(Transacao.class)))
                .thenAnswer(invocation -> {

                    Transacao transacao = invocation.getArgument(0);

                    transacao.setIdTransacao(UUID.randomUUID());

                    return transacao;
                });

        TransacaoResponseDTO response =
                transacaoService.criar(request);

        assertNotNull(response);

        assertEquals(
                TipoTransacao.ENTRADA,
                response.tipo()
        );

        assertEquals(
                "Salário",
                response.categoria()
        );

        verify(transacaoRepository, times(1))
                .save(any(Transacao.class));

    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir(){

        UUID categoriaId = UUID.randomUUID();

        TransacaoRequestDTO request =
                new TransacaoRequestDTO(
                        TipoTransacao.ENTRADA,
                        new BigDecimal("5000.00"),
                        LocalDate.now(),
                        "Salario",
                        categoriaId
                );

        when(categoriaRepository.findById(categoriaId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> transacaoService.criar(request)
                );

        assertEquals(
                "Categoria não encontrada!",
                exception.getMessage()
        );

        verify(transacaoRepository, never()).save(any());
    }

    @Test
    void deveBuscarTransacaoPorId() {

        UUID id = UUID.randomUUID();

        Categoria categoria = Categoria.builder()
                .idCategoria(UUID.randomUUID())
                .nome("Salário")
                .build();

        Transacao transacao = Transacao.builder()
                .idTransacao(id)
                .tipo(TipoTransacao.ENTRADA)
                .valor(new BigDecimal("5000.00"))
                .data(LocalDate.now())
                .descricao("Salário Mensal")
                .categoria(categoria)
                .build();

        when(transacaoRepository.findById(id))
                .thenReturn(Optional.of(transacao));

        TransacaoResponseDTO response =
                transacaoService.buscarPorId(id);

        assertNotNull(response);

        assertEquals(id, response.idTransacao());

        assertEquals(
                TipoTransacao.ENTRADA,
                response.tipo()
        );

        assertEquals(
                "Salário",
                response.categoria()
        );

        verify(transacaoRepository)
                .findById(id);
    }

    @Test
    void deveDeletarTransacaoComSucesso(){

        UUID id = UUID.randomUUID();

        when(transacaoRepository.existsById(id))
                .thenReturn(true);

        transacaoService.deletar(id);

        verify(transacaoRepository, times(1))
                .deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoDeletarTransacaoInexistente() {

        UUID id = UUID.randomUUID();

        when(transacaoRepository.existsById(id))
                .thenReturn(false);

        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> transacaoService.deletar(id)
                );

        assertEquals(
                "Transação não encontrada!",
                exception.getMessage()
        );

        verify(transacaoRepository, never())
                .deleteById(any());
    }
}
