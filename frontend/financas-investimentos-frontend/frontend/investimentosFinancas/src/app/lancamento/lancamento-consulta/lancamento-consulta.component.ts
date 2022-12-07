import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService } from 'primeng/api';
import { ArquivoUtils } from 'src/app/shared/arquivoUtils';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Paginacao } from 'src/app/shared/paginacao';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { Filtrar } from '../model/filtrar';
import { Lancamento } from '../model/lancamento';
import { LancamentoService } from '../service/lancamento.service';
import { LancamentoAlterarComponent } from './../lancamento-alterar/lancamento-alterar.component';
import { LancamentoModalComponent } from './../lancamento-modal/lancamento-modal.component';


@Component({
  selector: 'app-lancamento-consulta',
  templateUrl: './lancamento-consulta.component.html',
  styleUrls: ['./lancamento-consulta.component.css']
})
export class LancamentoConsultaComponent implements OnInit {

  public consulta: Lancamento[];
  public formularioPesquisa: FormGroup;
  public lancamento: Lancamento;
  public totalRegistros: number = 0;
  public paginacao: Paginacao;
  public filtro: Filtrar;
  public meses: any[] = [];
  public loading: boolean;

  @ViewChild('modalDetalhes') modalDetalhes: LancamentoModalComponent;
  @ViewChild('modalAlteracao') modalAlteracao: LancamentoAlterarComponent;


  constructor(
    private lancamentoService: LancamentoService,
    private formBuilder: FormBuilder, 
    private confirmationService: ConfirmationService, 
    private geralService: GeralService,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  public createForm(): void {
    this.formularioPesquisa = this.formBuilder.group(
      {
        usuario: [this.usuarioService.usuarioLogado],
        descricao: [''],
        dataInicio:[null, Validators.required],
        dataFim: [null, Validators.required]
      }
    )
  }

  public isInvalid(campo: string): boolean {
    return this.formularioPesquisa.get(campo).invalid && this.formularioPesquisa.get(campo).touched;
  }

  public isValid(campo: string): boolean {
    return this.formularioPesquisa.get(campo).valid && this.formularioPesquisa.get(campo).touched;
  }

  public abrirModal(lancamento: Lancamento): void {
    this.modalDetalhes.apresentarModal(lancamento);
  }

  public abrirModalAlteracao(lancamento: Lancamento): void {
    this.modalAlteracao.apresentarModal(lancamento);
  }

  public excluir(lancamento: Lancamento): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja fazer essa remoção?',
      accept: () => {
        this.lancamentoService.excluir(lancamento.id).subscribe(
          sucesso => {
            this.geralService.mensagemSucesso(Constants.OPERACAO_SUCESSO);
            this.consultar(null);
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

  

  public paginate(event: any): void {
    this.paginacao.page = event.page;
    this.paginacao.size = event.rows;

    this.recuperarListaPaginada();
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

  public downloadArquivo(nomeArquivo:string):void{
    this.lancamentoService.download(nomeArquivo).subscribe(
      resultado => {
        console.log(resultado);
        ArquivoUtils.download(resultado, nomeArquivo);
      },
      erro => {
        console.log(erro);
      }
    );

  }

  public recuperarListaPaginada(): any {
    this.filtro = this.formularioPesquisa.value;
    if (this.formularioPesquisa.valid) {
      
      this.lancamentoService.consultar(
        this.paginacao,
        this.filtro,
        this.usuarioService.usuarioLogado()
      ).subscribe(
        response => {
          this.consulta = response.content;
          this.totalRegistros = response.totalElements;
        },
        erro => {
          this.geralService.mensagemInfo(erro.error);
        }
      );
    } else {
      Object.keys(this.formularioPesquisa.controls).forEach(campo => {
        this.formularioPesquisa.get(campo).markAsTouched();
      });
    }
  }
}
