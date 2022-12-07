
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Usuario } from 'src/app/usuario/model/usuario';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { Categoria } from '../model/categoria';
import { CategoriaService } from '../service/categoria.service';

@Component({
  selector: 'app-categoria-cadastrar',
  templateUrl: './categoria-cadastrar.component.html',
  styleUrls: ['./categoria-cadastrar.component.css']
})
export class CategoriaCadastrarComponent implements OnInit {

  public formularioCadastro: FormGroup;
  public mensagemSucesso: string;

  constructor(
    private formBuilder: FormBuilder,
    private categoriaService: CategoriaService,
    private geralService: GeralService, 
    private usuarioService: UsuarioService
  ) { }

    ngOnInit(): void {
      this.createForm();
    }
  
    public createForm(): void {
      this.formularioCadastro = this.formBuilder.group(
        {
          usuario: [this.usuarioService.usuarioLogado(), Validators.required],
          nome: ['', Validators.required]
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
        let categoria = {
          ...new Categoria(),
          nome: this.formularioCadastro.get('nome').value,
          usuario: {
            ...new Usuario(),
            id: this.usuarioService.usuarioLogado()
          }
        }
        
        this.categoriaService.salvar(categoria).subscribe(response => {
          this.geralService.mensagemSucesso(Constants.OPERACAO_SUCESSO);
          this.formularioCadastro.reset();
          this.formularioCadastro.markAsUntouched();
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
