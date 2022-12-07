import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptorService implements HttpInterceptor {

  constructor() { }

  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const tokenString = localStorage.getItem('currentUser');
    
    if (tokenString) {
      const currentUser = JSON.parse(tokenString);
      const jwt = currentUser.token;
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${jwt}`
          
        }
      });
    }
    return next.handle(request);
  }
}
