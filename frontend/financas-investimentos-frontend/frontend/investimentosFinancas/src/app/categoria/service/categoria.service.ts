import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from 'src/app/shared/constants';
import { Page } from 'src/app/shared/page';
import { Paginacao } from 'src/app/shared/paginacao';
import { Categoria } from '../model/categoria';
import { DadosEstatisticas } from '../model/dadosEstatisticas';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(protected http: HttpClient) { }

  public salvar(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(`${Constants.API_URL}/categorias/cadastrar`, categoria);
  }

  public excluir(id: number): Observable<any> {
    return this.http.delete(`${Constants.API_URL}/categorias/excluir/${id}`);
  }

  public listar(usuario: number): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${Constants.API_URL}/categorias/listar/${usuario}`);
  }

  public consultar(paginacao: Paginacao, filtro: Categoria, usuario: number): Observable<Page> {
    let params = `?usuario=${usuario}&page=${paginacao.page}&size=${paginacao.size}&nome=${filtro.nome}`;
    return this.http.get<Page>(`${Constants.API_URL}/categorias/pesquisar${params}`);
  }

  public listarLancamentosPorCategorias(paginacao: Paginacao, categoria: Categoria, usuario: number): Observable<Page> {
    let params = `?usuario=${usuario}&page=${paginacao.page}&size=${paginacao.size}&categoria=${categoria.id}`;
    return this.http.get<Page>(`${Constants.API_URL}/categorias/listar-lancamentos-por-categorias${params}`);
  }

  public listarDespesaMensalTotalPorCategoria(usuario: number): Observable<DadosEstatisticas[]> {
    return this.http.get<DadosEstatisticas[]>(`${Constants.API_URL}/categorias/estatisticas/mensal/por-categorias?usuario=${usuario}`);
  }

  public listarDespesaMesAnteriorTotalPorCategoria(usuario: number): Observable<DadosEstatisticas[]> {
    return this.http.get<DadosEstatisticas[]>(`${Constants.API_URL}/categorias/estatisticas/mes-anterior/por-categorias?usuario=${usuario}`);
  }
}
