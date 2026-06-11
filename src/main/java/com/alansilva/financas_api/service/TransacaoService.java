package com.alansilva.financas_api.service;

import com.alansilva.financas_api.dto.request.TransacaoRequestDTO;
import com.alansilva.financas_api.dto.response.DashboardFinanceiroResponseDTO;
import com.alansilva.financas_api.dto.response.PageResponseDTO;
import com.alansilva.financas_api.dto.response.ResumoFinanceiroResponseDTO;
import com.alansilva.financas_api.dto.response.TransacaoResponseDTO;
import com.alansilva.financas_api.entity.Categoria;
import com.alansilva.financas_api.entity.Transacao;
import com.alansilva.financas_api.entity.enums.TipoTransacao;
import com.alansilva.financas_api.exception.ResourceNotFoundException;
import com.alansilva.financas_api.repository.CategoriaRepository;
import com.alansilva.financas_api.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoResponseDTO criar(TransacaoRequestDTO request) {

        log.info(
                "Iniciando criação de transação. Tipo={}, Valor={}",
                request.tipo(),
                request.valor()
        );

            Categoria categoria = categoriaRepository.findById(
                            request.idCategoria())
                    .orElseThrow(() -> {
                        log.warn(
                                "Categoria não encontrada. Id={}",
                                request.idCategoria()
                        );
                        return new ResourceNotFoundException(
                                "Categoria não encontrada!"
                        );
                    });

        Transacao transacao = Transacao.builder()
                .tipo(request.tipo())
                .valor(request.valor())
                .data(request.data())
                .descricao(request.descricao())
                .categoria(categoria)
                .build();

        transacao = transacaoRepository.save(transacao);

        log.info(
                "Transação criada com sucesso. Id={}",
                transacao.getIdTransacao()
        );

        return new TransacaoResponseDTO(
                transacao.getIdTransacao(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getData(),
                transacao.getDescricao(),
                categoria.getNome(),
                transacao.getDataCriacao()
        );
    }

    public ResumoFinanceiroResponseDTO obterResumo() {

        log.info("Gerando resumo financeiro");

        BigDecimal totalEntradas =
                transacaoRepository.somarPorTipo(TipoTransacao.ENTRADA);

        BigDecimal totalSaidas =
                transacaoRepository.somarPorTipo(TipoTransacao.SAIDA);

        BigDecimal saldo =
                totalEntradas.subtract(totalSaidas);

        log.info(
                "Resumo financeiro gerado. Saldo={}",
                saldo
        );

        return new ResumoFinanceiroResponseDTO(
                totalEntradas,
                totalSaidas,
                saldo
        );
    }

    public List<TransacaoResponseDTO> listarTodas() {

        return transacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<TransacaoResponseDTO> buscarPorPeriodo(
            LocalDate dataInicial,
            LocalDate dataFinal
    ) {

        return transacaoRepository
                .findByDataBetween(dataInicial, dataFinal)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private TransacaoResponseDTO toResponseDTO(Transacao transacao) {

        return new TransacaoResponseDTO(
                transacao.getIdTransacao(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getData(),
                transacao.getDescricao(),
                transacao.getCategoria().getNome(),
                transacao.getDataCriacao()
        );
    }

    public PageResponseDTO<TransacaoResponseDTO> listarPaginado(
            int pagina,
            int tamanho
    ) {

        log.info("Listando transações paginadas. Página={}, Tamanho={}",
                pagina, tamanho
        );

        Page<Transacao> page = transacaoRepository.findAll(
                PageRequest.of(pagina, tamanho)
        );

        return new PageResponseDTO<>(
                page.getContent()
                        .stream()
                        .map(this::toResponseDTO)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public TransacaoResponseDTO buscarPorId(UUID id) {

        log.info("Buscando transacao por id {}",
                id
        );

        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> {

                    log.warn(
                            "Transação não encontrada. Id={}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Transação não encontrada!"
                    );
                });

        return toResponseDTO(transacao);
    }

    public List<TransacaoResponseDTO> buscarPorTipo(TipoTransacao tipo) {

        return transacaoRepository.findByTipo(tipo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TransacaoResponseDTO atualizar(
            UUID id,
            TransacaoRequestDTO request
    ) {

        log.info(
                "Atualizando transação. Id={}",
                id
        );

        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> {

                    log.warn(
                            "Tentativa de atualização de transação inexistente. Id={}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Transação não encontrada!"
                    );
                });

        Categoria categoria = categoriaRepository.findById(
                        request.idCategoria())
                .orElseThrow(() -> {

                    log.warn(
                            "Categoria não encontrada durante atualização. Id={}",
                            request.idCategoria()
                    );


                    return new ResourceNotFoundException(
                            "Categoria não encontrada!"
                    );
                });

        transacao.setTipo(request.tipo());
        transacao.setValor(request.valor());
        transacao.setData(request.data());
        transacao.setDescricao(request.descricao());
        transacao.setCategoria(categoria);

        Transacao transacaoAtualizada =
                transacaoRepository.save(transacao);

        log.info(
                "Transação atualizada com sucesso. Id={}",
                id
        );

        return toResponseDTO(transacaoAtualizada);
    }

    public DashboardFinanceiroResponseDTO obterDashboard() {

        log.info("Gerando dashboard financeiro");

        BigDecimal totalEntradas =
                transacaoRepository.somarPorTipo(TipoTransacao.ENTRADA);

        BigDecimal totalSaidas =
                transacaoRepository.somarPorTipo(TipoTransacao.SAIDA);

        Long quantidadeEntradas =
                transacaoRepository.contarPorTipo(TipoTransacao.ENTRADA);

        Long quantidadeSaidas =
                transacaoRepository.contarPorTipo(TipoTransacao.SAIDA);

        BigDecimal saldo =
                totalEntradas.subtract(totalSaidas);

        log.info(
                "Dashboard gerado. Entradas={}, Saídas={}, Saldo={}",
                totalEntradas,
                totalSaidas,
                saldo
        );

        return new DashboardFinanceiroResponseDTO(
                totalEntradas,
                totalSaidas,
                saldo,
                quantidadeEntradas,
                quantidadeSaidas
        );
    }

    public void deletar(UUID id) {

        log.info("Solicitada exclusão da transação {}", id);

        if (!transacaoRepository.existsById(id)) {

            log.warn(
                    "Tentativa de exclusão de transação inexistente. Id={}",
                    id
            );

            throw new ResourceNotFoundException(
                    "Transação não encontrada!"
            );
        }
        transacaoRepository.deleteById(id);

        log.info(
                "Transação removida com sucesso. Id={}",
                id
        );
    }
}
