package br.com.investimento.financas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.investimento.financas.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);

    List<Categoria> findAllByUsuarioId(Long usuario);

    Page<Categoria> findAllByUsuarioIdAndNomeContainingIgnoreCaseOrderByNomeAsc(Pageable pageable, Long usuario, String nome);

    Page<Categoria> findAllByUsuarioIdOrderByNomeAsc(Pageable paginacao, Long usuario);
}
