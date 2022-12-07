import { AfterViewInit, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/categoria/model/categoria';
import { CategoriaService } from 'src/app/categoria/service/categoria.service';
import { Constants } from 'src/app/shared/constants';

import { GeralService } from 'src/app/shared/geral.service';
import { Usuario } from 'src/app/usuario/model/usuario';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { Lancamento } from '../model/lancamento';
import { LancamentoService } from '../service/lancamento.service';

@Component({
  selector: 'app-lancamento-alterar',
  templateUrl: './lancamento-alterar.component.html',
  styleUrls: ['./lancamento-alterar.component.css']
})
export class LancamentoAlterarComponent implements OnInit, AfterViewInit {

  public display: boolean = false;
  public lancamento: Lancamento;
  public formAlteracao: FormGroup;
  public categorias: Categoria[];
  public formaPagamentos: any[] = [];

  @Output('eventoAlteracao') eventoAlteracao = new EventEmitter();
  
  constructor(
    private formBuilder: FormBuilder, 
    private lancamentoService: LancamentoService,
    private geralService: GeralService, 
    private categoriaService: CategoriaService, 
    private usuarioService: UsuarioService
  ) { }
    
  ngAfterViewInit(): void {
    this.carregarCategorias();
  }
  
  ngOnInit(): void {
    this.createForm();
    this.carregarFormaPagamento();
  }

  public createForm(): void {
    this.formAlteracao = this.formBuilder.group(
      {
        usuario: [this.usuarioService.usuarioLogado],
        descricao: [{ value: null, Validators: [Validators.required, Validators.minLength(5)] }],
        valor: [{ value: null, Validators: [Validators.required] }],
        tipo: [{ value: null, Validators: [Validators.required] }],
        categorias: [{ value: null, Validators: [Validators.required, Validators.minLength(4), Validators.maxLength(30)] }],
        formaPagamento: [{ value: null, Validators: [Validators.required] }],
        observacao: [{ value: null, Validators: [Validators.maxLength(50)]}]
      }
    )
  }

  public carregarCategorias() {
    this.categoriaService.listar(this.usuarioService.usuarioLogado()).subscribe(response => {
      this.categorias = response;
    });
  }

  public carregarFormaPagamento() {
    this.formaPagamentos = this.lancamentoService.comboFormaPagamento()
    return this.formaPagamentos;
  }

  public apresentarModal(lancamento: Lancamento): void {
    this.display = true;
    this.lancamento = lancamento;
    this.formAlteracao.get('descricao').setValue(lancamento.descricao);
    this.formAlteracao.get('valor').setValue(lancamento.valor);
    this.formAlteracao.get('tipo').setValue(lancamento.tipo);
    this.formAlteracao.get('categorias').setValue(lancamento.categoria.id);
    this.formAlteracao.get('formaPagamento').setValue(lancamento.formaPagamento);
    this.formAlteracao.get('observacao').setValue(lancamento.observacao);
  }

  public isInvalid(campo: string): boolean {
    return this.formAlteracao.get(campo).invalid && this.formAlteracao.get(campo).touched;
  }

  public isValid(campo: string): boolean {
    return this.formAlteracao.get(campo).valid && this.formAlteracao.get(campo).touched;
  }

   public alterar(): void {
     if (this.formAlteracao.valid) {
      let usuario = new Usuario();
      usuario.id = this.usuarioService.usuarioLogado();

      this.lancamento.usuario = usuario;
      this.lancamento.descricao = this.formAlteracao.get('descricao').value;
      this.lancamento.valor = this.formAlteracao.get('valor').value;
      this.lancamento.tipo = this.formAlteracao.get('tipo').value;
      this.lancamento.formaPagamento = this.formAlteracao.get('formaPagamento').value;
      this.lancamento.observacao = this.formAlteracao.get('observacao').value;

      let categoria = new Categoria();
      categoria.id = this.formAlteracao.get('categorias').value;
      categoria.usuario = usuario;
      this.lancamento.categoria = categoria;
      
       this.lancamentoService.alterar(this.lancamento).subscribe(
         sucesso => {
           this.display = false;
           this.eventoAlteracao.emit();
           this.geralService.mensagemSucesso(Constants.OPERACAO_SUCESSO);
         },
         erro => {
          this.geralService.mensagemErro(erro.error);
         }
       )
     } else {
      Object.keys(this.formAlteracao.controls).forEach(campo => {
        this.formAlteracao.get(campo).markAsTouched();
      });
    }
   }

}
