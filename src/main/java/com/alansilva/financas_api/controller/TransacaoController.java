package com.alansilva.financas_api.controller;

import com.alansilva.financas_api.dto.request.TransacaoRequestDTO;
import com.alansilva.financas_api.dto.response.DashboardFinanceiroResponseDTO;
import com.alansilva.financas_api.dto.response.ResumoFinanceiroResponseDTO;
import com.alansilva.financas_api.dto.response.TransacaoResponseDTO;
import com.alansilva.financas_api.entity.enums.TipoTransacao;
import com.alansilva.financas_api.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Operation(summary = "Criar uma nova transação")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO criar(
            @RequestBody @Valid TransacaoRequestDTO request) {

        return transacaoService.criar(request);
    }

    @Operation(summary = "Obter resumo financeiro")
    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroResponseDTO> obterResumo() {
        return ResponseEntity.ok(transacaoService.obterResumo());
    }

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "0")
            int pagina,

            @RequestParam(defaultValue = "10")
            int tamanho,

            @RequestParam(required = false)
            LocalDate dataInicial,

            @RequestParam(required = false)
            LocalDate dataFinal

    ) {

        if (dataInicial != null && dataFinal != null) {

            return ResponseEntity.ok(
                    transacaoService.buscarPorPeriodo(
                            dataInicial,
                            dataFinal
                    )
            );
        }

        return ResponseEntity.ok(
                transacaoService.listarPaginado(
                        pagina,
                        tamanho
                )
        );
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardFinanceiroResponseDTO> dashboard(){

        return ResponseEntity.ok(
                transacaoService.obterDashboard()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable UUID id) {

        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @Operation(summary = "Buscar transações por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TransacaoResponseDTO>> buscarPorTipo(
            @PathVariable TipoTransacao tipo
    ) {

        return ResponseEntity.ok(
                transacaoService.buscarPorTipo(tipo)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid TransacaoRequestDTO request
    ) {

        return ResponseEntity.ok(transacaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        transacaoService.deletar(id);
    }
}
