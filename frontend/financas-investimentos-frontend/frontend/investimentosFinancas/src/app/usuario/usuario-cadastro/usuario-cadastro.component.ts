import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Usuario } from '../model/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-usuario-cadastro',
  templateUrl: './usuario-cadastro.component.html',
  styleUrls: ['./usuario-cadastro.component.css']
})
export class UsuarioCadastroComponent implements OnInit {

  public formularioCadastro: FormGroup;

  constructor(
    private router: Router, 
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService, 
    private geralService: GeralService
  ) {}

  ngOnInit(): void {
    this.createForm();
  }

  public createForm(): void {
    this.formularioCadastro = this.formBuilder.group(
      {
        nome: ['', Validators.required],
        sobrenome: ['', Validators.required],
        email: ['', Validators.required],
        username: ['', Validators.required],
        password: ['', Validators.required]
      }
    );
  }

  public isInvalid(campo: string): boolean {
    return this.formularioCadastro.get(campo).invalid && this.formularioCadastro.get(campo).touched;
  }

  public isValid(campo: string): boolean {
    return this.formularioCadastro.get(campo).valid && this.formularioCadastro.get(campo).touched;
  }

  public cadastrar() {
    if (this.formularioCadastro.valid) {

      let usuario = {
        ...new Usuario(),
        ...this.formularioCadastro.value
      }

      this.usuarioService.salvar(usuario).subscribe(response => {
        this.geralService.mensagemSucesso(Constants.OPERACAO_LOGIN)
        this.router.navigate(['/login']);
      }, error => {
        this.geralService.mensagemErro(error.error);
      });
    } else {
      Object.keys(this.formularioCadastro.controls).forEach(campo => {
        this.formularioCadastro.get(campo).markAsTouched();
      });
    }
  }

}
