import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from 'src/app/shared/constants';
import { Usuario } from '../model/usuario';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(protected http: HttpClient) { }

  public usuarioLogado() {
    var userLogado = JSON.parse(localStorage.getItem('currentUser'));
    return userLogado.id;
  }

  public salvar(usuario: Usuario) : Observable<any> {
    return this.http.post<any>(`${Constants.API_URL}/usuarios/cadastrar`, usuario);
  }

  public obterSaldoUsuario(id: number) : Observable<any> {
    return this.http.get<any>(`${Constants.API_URL}/usuarios/saldo/${id}`);
  }

  public obterSaldoDespesas(id: number) : Observable<any> {
    return this.http.get<any>(`${Constants.API_URL}/usuarios/saldo/despesas/${id}`);
  }

  public obterSaldoDespesasGrafico(id: number) : Observable<Array<any>> {
    return this.http.get<Array<any>>(`${Constants.API_URL}/usuarios/saldo/despesas/${id}`);
  }

  public obterSaldoReceitas(id: number) : Observable<any> {
    return this.http.get<any>(`${Constants.API_URL}/usuarios/saldo/receitas/${id}`);
  }
  
}
