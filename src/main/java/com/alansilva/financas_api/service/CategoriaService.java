package com.alansilva.financas_api.service;

import com.alansilva.financas_api.dto.request.CategoriaRequestDTO;
import com.alansilva.financas_api.dto.response.CategoriaResponseDTO;
import com.alansilva.financas_api.entity.Categoria;
import com.alansilva.financas_api.exception.BusinessException;
import com.alansilva.financas_api.exception.ResourceNotFoundException;
import com.alansilva.financas_api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO criar(CategoriaRequestDTO request) {

        if(categoriaRepository.existsByNomeIgnoreCase(request.nome())){
            throw new BusinessException(
                    "Já existe uma categoria com esse nome."
            );
        }

        Categoria categoria = Categoria.builder()
                .nome(request.nome())
                .build();

        categoria =  categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }

    public CategoriaResponseDTO buscarPorId(UUID id){

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria não encontrada."
                ));

        return new  CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }

    public List<CategoriaResponseDTO> listar(){

        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> new CategoriaResponseDTO(
                        categoria.getIdCategoria(),
                        categoria.getNome()
                ))
                .toList();
    }

    public CategoriaResponseDTO atualizar(UUID id, CategoriaRequestDTO request){

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria não encontrada."
                ));

        categoria.setNome(request.nome());

        categoria = categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }

    public void deletar(UUID id){

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoria não encontrada"
                ));

        categoriaRepository.delete(categoria);
    }
}
