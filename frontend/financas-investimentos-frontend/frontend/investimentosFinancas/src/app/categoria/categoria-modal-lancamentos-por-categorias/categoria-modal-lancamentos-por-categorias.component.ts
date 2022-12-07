import { Component, OnInit } from '@angular/core';
import { Lancamento } from 'src/app/lancamento/model/lancamento';
import { GeralService } from 'src/app/shared/geral.service';
import { Paginacao } from 'src/app/shared/paginacao';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { Categoria } from '../model/categoria';
import { CategoriaService } from '../service/categoria.service';

@Component({
  selector: 'app-categoria-modal-lancamentos-por-categorias',
  templateUrl: './categoria-modal-lancamentos-por-categorias.component.html',
  styleUrls: ['./categoria-modal-lancamentos-por-categorias.component.css']
})
export class CategoriaModalLancamentosPorCategoriasComponent implements OnInit {

  public display: boolean = false;
  public lancamento: Lancamento;
  public lancamentos: Lancamento[];
  public totalRegistros: number = 0;
  public paginacao: Paginacao;
  public categoria: Categoria;
  public loading: boolean;

  constructor( 
    private usuarioService: UsuarioService,
    private categoriaService: CategoriaService, 
    private geralService: GeralService
  ) { }

  ngOnInit(): void {}

  public carregarModal(categoria: Categoria, event: any): void {
    this.loading = true;
    this.display = true;
    setTimeout(() => {
      this.categoria = categoria;
      this.paginacao = new Paginacao();
  
      this.listarLancamentosPorCategoria();
      this.loading = false;
    }, 1000);
  }

  public paginate(event: any): void {
    this.paginacao.page = event.page;
    this.listarLancamentosPorCategoria();
  }

  public listarLancamentosPorCategoria() {
    this.categoriaService.listarLancamentosPorCategorias(
      this.paginacao, 
      this.categoria,
      this.usuarioService.usuarioLogado()
    ).subscribe(response => {
        this.lancamentos = response.content;
        this.totalRegistros = response.totalElements;
    }, erro => {
      this.geralService.mensagemInfo(erro.error)
    });
  }

}
