package com.alansilva.financas_api.controller;


import com.alansilva.financas_api.dto.request.CategoriaRequestDTO;
import com.alansilva.financas_api.dto.response.CategoriaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import com.alansilva.financas_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Criar uma nova categoria")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO criar (
            @RequestBody @Valid CategoriaRequestDTO request){

        return categoriaService.criar(request);
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(
            @PathVariable UUID id){
        return categoriaService.buscarPorId(id);
    }

    @Operation(summary = "Listar todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid CategoriaRequestDTO request
    ){
        return categoriaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id){
        categoriaService.deletar(id);
    }
}
