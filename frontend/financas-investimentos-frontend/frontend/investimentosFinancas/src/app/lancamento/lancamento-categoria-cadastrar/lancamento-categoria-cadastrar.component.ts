import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/categoria/model/categoria';
import { CategoriaService } from 'src/app/categoria/service/categoria.service';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Usuario } from 'src/app/usuario/model/usuario';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';

@Component({
  selector: 'app-lancamento-categoria-cadastrar',
  templateUrl: './lancamento-categoria-cadastrar.component.html',
  styleUrls: ['./lancamento-categoria-cadastrar.component.css']
})
export class LancamentoCategoriaCadastrarComponent implements OnInit {

  public formularioCadastro: FormGroup;
  public display: boolean = false;

  @Output() emitirCategoria = new EventEmitter<Categoria>();

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
          nome: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(30)]]
        }
      );
    }

    public isInvalid(campo: string): boolean {
      return this.formularioCadastro.get(campo).invalid && this.formularioCadastro.get(campo).touched;
    }
  
    public isValid(campo: string): boolean {
      return this.formularioCadastro.get(campo).valid && this.formularioCadastro.get(campo).touched;
    }

    public apresentarModal(): void {
      this.display = true;
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
          this.display = false;
          this.emitirCategoria.emit(categoria);
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
