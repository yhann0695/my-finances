import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { UsuarioService } from './service/usuario.service';

import {InputMaskModule} from 'primeng/inputmask';



@NgModule({
  declarations: [
    UsuarioCadastroComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    InputMaskModule,
    HttpClientModule,
    UsuarioRoutingModule
  ],
  exports: [
    UsuarioCadastroComponent
  ],
  providers: [
    UsuarioService
  ]
})
export class UsuarioModule { }
