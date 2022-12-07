import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class GeralService {

  constructor(private messageService: MessageService) { }

  public mensagemSucesso(msg: string): void {
    this.messageService.add({
      severity: 'success',
      summary: 'Sucesso!',
      detail: msg
    });
  }

  public mensagemInfo(msg: string): void {
    this.messageService.add({
      severity: 'warn',
      summary: 'Aviso!',
      detail: msg
    });
  }

  public mensagemErro(msg: string): void {
    this.messageService.add({
      severity: 'error',
      summary: 'Erro!',
      detail: msg
    });
  }
}
