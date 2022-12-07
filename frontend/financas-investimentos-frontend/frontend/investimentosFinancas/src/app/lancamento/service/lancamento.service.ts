import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from 'src/app/shared/constants';
import { Page } from 'src/app/shared/page';
import { Paginacao } from 'src/app/shared/paginacao';
import { Filtrar } from '../model/filtrar';
import { Lancamento } from '../model/lancamento';

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  constructor(protected http: HttpClient) { }

  public comboFormaPagamento = () => {
    return [
        { label: 'Dinheiro', value: 'DINHEIRO' },
        { label: 'Cartão de Crédito', value: 'CREDITO' },
        { label: 'Cartão de Débito', value: 'DEBITO' },
        { label: 'Pagamento com cheque', value: 'CHEQUE' },
        { label: 'Boleto', value: 'BOLETO' },
        { label: 'Pagamento salário', value: 'PAGAMENTO' },
        { label: 'Transferência pix', value: 'PIX' }
    ];
  }

  public comboTipoLancamento = () => {
    return [
        { label: 'Receita', value: 'RECEITA' },
        { label: 'Despesa', value: 'DESPESA' }
    ];
  }

  public salvar(lancamento: Lancamento): Observable<Lancamento> {
    return this.http.post<Lancamento>(`${Constants.API_URL}/lancamentos/cadastrar`, lancamento);
  }

  public excluir(id: number): Observable<any> {
    return this.http.delete(`${Constants.API_URL}/lancamentos/excluir/${id}`);
  }

  public alterar(lancamento: Lancamento): Observable<Lancamento> {
    return this.http.put<Lancamento>(`${Constants.API_URL}/lancamentos/alterar/${lancamento.id}`, lancamento);
  }

  public listar(): Observable<Lancamento[]> {
    return this.http.get<Lancamento[]>(`${Constants.API_URL}/lancamentos/listar`);
  }

  public consultar(paginacao: Paginacao, filtro: Filtrar, usuario: number): Observable<Page> {
    let params = `?usuario=${usuario}&page=${paginacao.page}&size=${paginacao.size}&dataInicio=${filtro.dataInicio}&dataFim=${filtro.dataFim}`;

    if (filtro.descricao) params = `${params}&descricao=${filtro.descricao}`;

    return this.http.get<Page>(`${Constants.API_URL}/lancamentos/pesquisar${params}`);
  }

  public gerarRelatorioLancamentosMensais(usuario: number): Observable<any> {
    return this.http.get(`${Constants.API_URL}/lancamentos/relatorio-lancamentos-mensal?usuarioId=${usuario}`, { responseType: 'blob' });
  }

  public upload(file: File): Observable<any> {
    let formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${Constants.API_URL}/lancamentos/upload`, formData);
  }

  public download(nomeArquivo: string): Observable<Object> {
    return this.http.get(`${Constants.API_URL}/lancamentos/download/${nomeArquivo}`, { observe: 'response', responseType: 'blob' });
  }
  

  
}
