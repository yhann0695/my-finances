import { HttpHeaders } from "@angular/common/http";

export class BaseService {

    public tokenString: string = localStorage.getItem('currentUser');
    public currentUser: any = JSON.parse(this.tokenString);

    httpOptions = {
      headers: new HttpHeaders(
          {
              'Authorization': `Bearer ${this.currentUser.token}`
          }
      )
  }
}