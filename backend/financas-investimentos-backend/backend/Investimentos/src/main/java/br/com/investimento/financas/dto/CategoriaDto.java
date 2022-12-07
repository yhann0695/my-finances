package br.com.investimento.financas.dto;

import br.com.investimento.financas.model.Categoria;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Long id;

    private String nome;

    private UsuarioDto usuario;

    private List<LancamentoDto> lancamentos;

    public CategoriaDto(Categoria entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.usuario = new UsuarioDto(entidade.getUsuario());
    }

    public static List<CategoriaDto> listToDto(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
