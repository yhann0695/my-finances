package br.com.investimento.financas.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.utils.enums.TipoLancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    List<Lancamento> findTop5ByUsuarioOrderByIdDesc(Usuario usuario);

    @Query(value = "SELECT SUM(L.valor) FROM Lancamento L JOIN L.usuario U " 
                   + "WHERE U.id=:usuarioId AND L.tipo=:tipo "  
                   + "AND YEAR(L.dataCadastro) = YEAR(:data) "  
                   + "AND MONTH(L.dataCadastro) = MONTH(:data)")
    BigDecimal obterSaldoUsuario(Long usuarioId, TipoLancamento tipo, LocalDate data);

    @Query(value = "SELECT L FROM Lancamento L JOIN L.usuario U " 
                    + "WHERE U.id=:usuarioId " 
                    + "AND YEAR(L.dataCadastro) = YEAR(:data) "  
                    + "AND MONTH(L.dataCadastro) = MONTH(:data) ORDER BY L.dataCadastro ASC")
    List<Lancamento> dadosLancamentosMensal(Long usuarioId, LocalDate data);

    @Query(value = "SELECT L FROM Lancamento L JOIN L.usuario U " 
                    + "WHERE U in :usuarios "
                    + "AND YEAR(L.dataCadastro) = YEAR(:data) "  
                    + "AND MONTH(L.dataCadastro) = MONTH(:data) ORDER BY L.dataCadastro ASC")
    List<Lancamento> dadosLancamentosMesAnteriorEmail(List<Usuario> usuarios, LocalDate data);

    Page<Lancamento> findAllByCategoriaIdAndUsuarioId(Pageable pageable, Long categoriaId, Long usuarioId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END from Lancamento c WHERE c.categoria.id = :categoriaId")
    boolean isExisteRelacionamento(Long categoriaId);

    @Query(value = "SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId "  
                    + "AND (l.dataCadastro BETWEEN :dataInicio AND :dataFim) " 
                    + "AND (:descricao = null or UPPER(l.descricao) LIKE '%' || UPPER(:descricao) || '%')" )
    Page<Lancamento> findAllByUsuarioIdAndDataCadastroBetweenOrDescricaoContainingIgnoreCase(Pageable pageable,
     Long usuarioId, LocalDate dataInicio, LocalDate dataFim, String descricao);
}
