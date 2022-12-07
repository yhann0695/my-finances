import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Categoria } from 'src/app/categoria/model/categoria';
import { Lancamento } from '../model/lancamento';

@Component({
  selector: 'app-lancamento-modal',
  templateUrl: './lancamento-modal.component.html',
  styleUrls: ['./lancamento-modal.component.css']
})
export class LancamentoModalComponent implements OnInit {

  public display: boolean = false;
  public formModal: FormGroup;
  public lancamento: Lancamento;
  public categorias: Categoria;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  public createForm(): void {
    this.formModal = this.formBuilder.group(
      {
        descricao: [{ value: null, disabled: true }],
        valor: [{ value: null, disabled: true }],
        tipo: [{ value: null, disabled: true }],
        observacao: [{ value: null, disabled: true }],
        formaPagamento: [{ value: null, disabled: true }],
        dataCadastro: [{ value: null, disabled: true }],
        categorias: [{ value: null, disabled: true }]
      }
    )
  }

  public apresentarModal(lancamento: Lancamento): void {
    this.display = true;
    this.lancamento = lancamento;
    this.formModal.get('descricao').setValue(lancamento.descricao);
    this.formModal.get('valor').setValue(lancamento.valor);
    this.formModal.get('tipo').setValue(lancamento.tipo);
    this.formModal.get('observacao').setValue(lancamento.observacao);
    this.formModal.get('formaPagamento').setValue(lancamento.formaPagamento);
    this.formModal.get('dataCadastro').setValue(lancamento.dataCadastro);
    this.formModal.get('categorias').setValue(lancamento.categoria.nome);
  }

}
