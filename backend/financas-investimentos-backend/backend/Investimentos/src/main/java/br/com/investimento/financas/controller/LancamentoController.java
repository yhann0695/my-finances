package br.com.investimento.financas.controller;

import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.dto.filter.FilterDateAndDescription;
import br.com.investimento.financas.service.LancamentoService;
import br.com.investimento.financas.service.relatorio.RelatorioLancamento;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/lancamentos")
@ApiOperation(value = "Controller utilizado para o contexto negocial do lançamento")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private RelatorioLancamento relatorioLancamento;


    @ApiOperation(value = "Endpoint para inserção de um lançamento na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @PostMapping(path = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cadastrar(@RequestBody LancamentoDto dto) {
        lancamentoService.cadastrar(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para listar lançamentos da base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LancamentoDto>> listar() {
        return ResponseEntity.ok(lancamentoService.listar());
    }

    @ApiOperation(value = "Endpoint para pesquisa dinâmica de lançamentos na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<LancamentoDto>> pesquisar(FilterDateAndDescription filter) {
        return ResponseEntity.ok(lancamentoService.consultarPaginado(filter));
    }

    @ApiOperation(value = "Endpoint para excluir lançamentos da base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @DeleteMapping(path = "/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        lancamentoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para alteração de um lançamento na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @PutMapping(path = "/alterar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> alterar(@RequestBody LancamentoDto dto, @PathVariable Long id) {
        lancamentoService.alterar(id, dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint para gerar relatório de lançamentos da base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "Authorization",
            value = "Token de acesso",
            required = true,
            paramType = "header",
            dataTypeClass = String.class)})
    @GetMapping(path = "/relatorio-lancamentos-mensal")
    public ResponseEntity<byte[]> gerarRelatorioLancamentosMensais(@RequestParam("usuarioId") Long usuarioId) throws JRException {
        return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                        .body(relatorioLancamento.gerarRelatorioLancamentosMensais(usuarioId));
    }

    @ApiOperation(value = "Endpoint para upload de arquivos na base de dados")
    @ApiImplicitParams({@ApiImplicitParam(
        name = "Authorization",
        value = "Token de acesso",
        required = true,
        paramType = "header",
        dataTypeClass = String.class)})
	@PostMapping(path = "/upload")
	public ResponseEntity<Void> upload(@RequestParam(value = "file") MultipartFile file) {
		lancamentoService.uploadFile(file);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Endpoint para download de arquivos na base de dados")
	@ApiImplicitParams({@ApiImplicitParam(
                name = "Authorization",
                value = "Token de acesso",
                required = true,
                paramType = "header",
                dataTypeClass = String.class)})
	@GetMapping(path = "/download/{nomeArquivo}")
	public ResponseEntity<Resource> download(@PathVariable(value = "nomeArquivo") String nomeArquivo) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + nomeArquivo);
		headers.add("Access-Control-Expose-Headers", "Content-Disposition");
		headers.add("Cache-Control", "no-cache");

		ByteArrayResource resource = lancamentoService.download(nomeArquivo);
		
		 return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(resource.contentLength())
		            .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
		            .body(resource);
	}
}
