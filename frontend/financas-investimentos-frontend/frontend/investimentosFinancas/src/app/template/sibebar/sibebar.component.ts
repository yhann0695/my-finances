import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-sibebar',
  templateUrl: './sibebar.component.html',
  styleUrls: ['./sibebar.component.css']
})
export class SibebarComponent implements OnInit {

  public usuarioLogado: string;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioLogado();
  }

}
