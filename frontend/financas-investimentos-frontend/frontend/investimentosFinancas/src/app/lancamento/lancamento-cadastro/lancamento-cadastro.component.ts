import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/categoria/model/categoria';
import { CategoriaService } from 'src/app/categoria/service/categoria.service';
import { Constants } from 'src/app/shared/constants';
import { GeralService } from 'src/app/shared/geral.service';
import { Usuario } from 'src/app/usuario/model/usuario';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';
import { LancamentoCategoriaCadastrarComponent } from '../lancamento-categoria-cadastrar/lancamento-categoria-cadastrar.component';
import { Lancamento } from '../model/lancamento';
import { LancamentoService } from '../service/lancamento.service';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css']
})
export class LancamentoCadastroComponent implements OnInit {

  public formularioCadastro: FormGroup;
  public categorias: Categoria[];
  public formaPagamentos: any[] = [];
  public file: File;

  @ViewChild('modalCategoria') modalCategoria: LancamentoCategoriaCadastrarComponent;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private lancamentoService: LancamentoService,
    private categoriaService: CategoriaService, 
    private geralService: GeralService, 
    private usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.carregarCategorias();
    this.carregarFormaPagamento();
  }

  public createForm(): void {
    this.formularioCadastro = this.formBuilder.group(
      {
        usuario: [this.usuarioService.usuarioLogado(), Validators.required],
        descricao: ['', Validators.required],
        valor: ['', Validators.required],
        tipo: ['', Validators.required],
        categoria: [null, Validators.required],
        formaPagamento: [null, Validators.required],
        observacao: ['']
      }
    );
  }

  public onSelectFile(event:any): void {
    this.file = event.files[0];
  }

  public onRemoveFile(event:any): void {
    this.file = null;
  }

  public carregarCategorias() {
    this.categoriaService.listar(this.usuarioService.usuarioLogado()).subscribe(response => {
      this.categorias = response;
    });
  }

  public isInvalid(campo: string): boolean {
    return this.formularioCadastro.get(campo).invalid && this.formularioCadastro.get(campo).touched;
  }

  public isValid(campo: string): boolean {
    return this.formularioCadastro.get(campo).valid && this.formularioCadastro.get(campo).touched;
  }

  public voltar(): void {
    this.router.navigate(['.'], { relativeTo: this.route.parent });
  }

  public apresentarModalCategoria(): void {
    this.modalCategoria.apresentarModal();
  }

  public adicionaCategoria(categoria: Categoria) {
    if(this.categorias !== null) {
      this.categorias.push(categoria);
    }
  }

  public carregarFormaPagamento() {
    this.formaPagamentos = this.lancamentoService.comboFormaPagamento()
    return this.formaPagamentos;
  }

  public uploadFile(file: File) {
    this.lancamentoService.upload(this.file).subscribe(response => {
      console.log(response);
    });
  }

  public cadastrar() {
    if (this.formularioCadastro.valid) {
      this.uploadFile(this.file);
        let lancamento = {
           ...new Lancamento(), 
           ...this.formularioCadastro.value,
           nomeArquivo: this.file ? this.file.name : null,
           usuario: {
            ...new Usuario(),
            id: this.usuarioService.usuarioLogado()
           },
          categoria: {
            ...new Categoria(), 
            id: this.formularioCadastro.get('categoria').value,
            usuario: {
              ...new Usuario(),
              id: this.usuarioService.usuarioLogado()
             }
          }
        };
      this.lancamentoService.salvar(lancamento).subscribe(response => {
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
