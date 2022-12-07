import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ConfirmationService } from 'primeng/api';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Paginacao } from 'src/app/shared/paginacao';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { CategoriaModalLancamentosPorCategoriasComponent } from '../categoria-modal-lancamentos-por-categorias/categoria-modal-lancamentos-por-categorias.component';
import { Categoria } from '../model/categoria';
import { CategoriaService } from '../service/categoria.service';

@Component({
  selector: 'app-categoria-consulta',
  templateUrl: './categoria-consulta.component.html',
  styleUrls: ['./categoria-consulta.component.css']
})
export class CategoriaConsultaComponent implements OnInit {

  public consulta: Categoria[];
  public filtro: any;
  public paginacao: Paginacao;
  public totalRegistros: number = 0;
  public formularioPesquisa: FormGroup;
  public loading: boolean;

  @ViewChild('appModalConsultaLancamentos') appModalConsultaLancamentos: CategoriaModalLancamentosPorCategoriasComponent;

  constructor(
    private categoriaService: CategoriaService, 
    private geralService: GeralService, 
    private usuarioService: UsuarioService, 
    private formBuilder: FormBuilder, 
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  public createForm(): void {
    this.formularioPesquisa = this.formBuilder.group(
      {
        nome:['']
      }
    )
  }

  public consultar(event: any): void {
    this.loading = true;
    setTimeout(() => {
      this.filtro = this.formularioPesquisa.value;
      this.paginacao = new Paginacao();
      this.paginacao.filtro = this.filtro;
  
      this.recuperarListaPaginada();
      this.loading = false;
    }, 1000);
  }

  public paginate(event: any): void {
    this.paginacao.page = event.page;
    this.paginacao.size = event.rows;
    this.recuperarListaPaginada();
  }

  public abrirModalConsultaLancamentos(categoria: Categoria, event: any): void {
    this.appModalConsultaLancamentos.carregarModal(categoria, event);
  }

  public excluir(categoria: Categoria): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja fazer essa remoção?',
      accept: () => {
        this.categoriaService.excluir(categoria.id).subscribe(
          sucesso => {
            this.geralService.mensagemSucesso(Constants.OPERACAO_SUCESSO);
            this.recuperarListaPaginada();
          },
          erro => {
            this.geralService.mensagemErro(erro.error);
          }
        );
      },
      reject: () => {
        this.geralService.mensagemInfo(Constants.OPERACAO_CANCELADA);
      }
    })
  }

  public recuperarListaPaginada(): any {      
      this.categoriaService.consultar(this.paginacao, this.filtro, this.usuarioService.usuarioLogado()).subscribe(
        response => {
          this.consulta = response.content;
          this.totalRegistros = response.totalElements;
        },
        erro => {
          this.geralService.mensagemInfo(erro.error);
        }
      );  
  }

}
