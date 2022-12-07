import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Usuario } from '../usuario/model/usuario';
import { map } from 'rxjs/operators';
import { Constants } from '../shared/constants';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<Usuario>;
  public currentUser: Observable<Usuario>;
  public jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Usuario>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public obterToken() {
    const tokenString = localStorage.getItem('currentUser');
    if(tokenString) {
      return JSON.parse(tokenString).token;
    }
    return null;
  }

  public isAuthenticated(): boolean {
    const token = this.obterToken();
    if(token) {
      return !this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }

  public getUsuarioLogado() {
    if(this.obterToken()) {
      return this.jwtHelper.decodeToken(this.obterToken()).sub;
    }
    return null;
  }

  public get currentUserValue(): Usuario {
    return this.currentUserSubject.getValue();
  }

  public setUserName(username: string) {
    localStorage.setItem('username', JSON.stringify(username));
  }

  public getUserName() {
    return JSON.parse(localStorage.getItem('username'));
  }

  public login(username: string, password: string) {
    return this.http.post<any>(`${Constants.API_URL}/usuarios/autenticar`, { username, password })
      .pipe(map(usuario => {
        localStorage.setItem('currentUser', JSON.stringify(usuario));
        this.currentUserSubject.next(usuario);
        return usuario;
      }));
  }

  public logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('username');
    this.currentUserSubject.next(null);
  }
}
