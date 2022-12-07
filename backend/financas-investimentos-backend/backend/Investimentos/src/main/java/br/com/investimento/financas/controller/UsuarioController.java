package br.com.investimento.financas.controller;

import br.com.investimento.financas.dto.TokenDto;
import br.com.investimento.financas.dto.UsuarioDto;
import br.com.investimento.financas.service.LancamentoService;
import br.com.investimento.financas.service.UsuarioService;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequestMapping(path = "/usuarios")
@ApiOperation(value = "Controller utilizado para o contexto negocial do usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;

    @ApiOperation(value = "Endpoint para inserção de um usuário na base de dados")
    @PostMapping(path = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioDto dto) {
        usuarioService.cadastrar(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para autenticação de um usuário")
    @PostMapping(path = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> autenticar(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(usuarioService.autenticarToken(usuario));
    }

    @ApiOperation(value = "Endpoint para consultar o saldo do usuário na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping("/saldo/{usuario}")
    public ResponseEntity<BigDecimal> obterSaldo(@PathVariable Long usuario) {
        return ResponseEntity.ok(lancamentoService.obterSaldoMensalTotalPorUsuario(usuario));
    }

    @ApiOperation(value = "Endpoint para consultar o saldo de despesas do usuário na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping("/saldo/despesas/{usuario}")
    public ResponseEntity<BigDecimal> obterSaldoDespesas(@PathVariable Long usuario) {
        return ResponseEntity.ok(lancamentoService.obterSaldoMensalPorTipo(TipoLancamento.DESPESA, usuario, LocalDate.now()));
    }

    @ApiOperation(value = "Endpoint para consultar o saldo de receitas do usuário na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping("/saldo/receitas/{usuario}")
    public ResponseEntity<BigDecimal> obterSaldoReceitas(@PathVariable Long usuario) {
        return ResponseEntity.ok(lancamentoService.obterSaldoMensalPorTipo(TipoLancamento.RECEITA, usuario, LocalDate.now()));
    }
}
