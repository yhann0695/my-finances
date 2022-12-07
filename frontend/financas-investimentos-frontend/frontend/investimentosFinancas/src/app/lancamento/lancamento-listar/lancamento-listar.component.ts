import { Component, OnInit } from '@angular/core';
import { GeralService } from 'src/app/shared/geral.service';
import { Lancamento } from '../model/lancamento';
import { LancamentoService } from '../service/lancamento.service';

@Component({
  selector: 'app-lancamento-listar',
  templateUrl: './lancamento-listar.component.html',
  styleUrls: ['./lancamento-listar.component.css']
})
export class LancamentoListarComponent implements OnInit {

  public lancamentos: Lancamento[];

  constructor(
    private lancamentoService: LancamentoService,
    private geralService: GeralService
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  public listar(): void {
    this.lancamentoService.listar().subscribe(
      sucesso => {
        this.lancamentos = sucesso;
      },
      erro => {
        this.geralService.mensagemInfo(erro.error);
      }
    );
  }

}
