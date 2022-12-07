package br.com.investimento.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.investimento.financas.dto.CategoriaDto;
import br.com.investimento.financas.dto.estatistica.DadosEstatisticaMensalDto;
import br.com.investimento.financas.dto.estatistica.DadosEstatisticaMesAnteriorDto;
import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.service.CategoriaService;
import br.com.investimento.financas.service.LancamentoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/categorias")
@ApiOperation(value = "Controller utilizado para o contexto negocial de categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LancamentoService lancamentoService;

    @ApiOperation(value = "Endpoint para cadastro da categoria na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @PostMapping(path = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cadastrar(@RequestBody CategoriaDto dto) {
        this.categoriaService.cadastrar(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para listar categorias da base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/listar/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaDto>> listar(@PathVariable Long usuario) {
        return ResponseEntity.ok(categoriaService.listar(usuario));
    }

    @ApiOperation(value = "Endpoint para pesquisa dinâmica de categorias na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaDto>> pesquisar(@RequestParam(value = "usuario") Long usuario,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size,
                                                        @RequestParam(value = "nome", required = false) String nome) {

        return ResponseEntity.ok(categoriaService.consultarPaginado(page, size, nome, usuario));
    }

    @ApiOperation(value = "Endpoint para listar lançamentos de acordo categorias na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/listar-lancamentos-por-categorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<LancamentoDto>> listarLancamentosPorCategoria(@RequestParam(value = "usuario") Long usuario,
                                                                             @RequestParam(value = "page") int page,
                                                                             @RequestParam(value = "size") int size,
                                                                             @RequestParam(value = "categoria") Long categoria) {

        return ResponseEntity.ok(lancamentoService.listarLancamentosPorCategorias(page, size, categoria, usuario));
    }

    @ApiOperation(value = "Endpoint para listar lançamentos de acordo categorias na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @DeleteMapping(path = "/excluir/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluir(@PathVariable(value = "categoria") Long categoriaId) {
        categoriaService.remover(categoriaId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para consulta de estatísticas mensais de lançamentos por categoria na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/estatisticas/mensal/por-categorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DadosEstatisticaMensalDto>> listarDespesaMensalTotalPorCategoria(@RequestParam(value = "usuario") Long usuario) {

        return ResponseEntity.ok(categoriaService.listarDespesaMensalTotalPorCategoria(usuario));
    }

    @ApiOperation(value = "Endpoint para consulta de estatísticas mensais anteriores de lançamentos por categoria na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/estatisticas/mes-anterior/por-categorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DadosEstatisticaMesAnteriorDto>> listarDespesaMensalAnteriorTotalPorCategoria(@RequestParam(value = "usuario") Long usuario) {

        return ResponseEntity.ok(categoriaService.listarDespesaMensalAnteriorTotalPorCategoria(usuario));
    }
}
