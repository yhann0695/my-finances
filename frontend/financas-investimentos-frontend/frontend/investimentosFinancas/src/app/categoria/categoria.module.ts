import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoriaRoutingModule } from './categoria-routing.module';
import { CategoriaCadastrarComponent } from './categoria-cadastrar/categoria-cadastrar.component';
import { CategoriaService } from './service/categoria.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DialogModule } from 'primeng/dialog';
import { CategoriaConsultaComponent } from './categoria-consulta/categoria-consulta.component';
import { PaginatorModule } from 'primeng/paginator';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { CardModule } from 'primeng/card';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TableModule } from 'primeng/table';
import { ScrollingModule } from '@angular/cdk/scrolling';
import {DataViewModule} from 'primeng/dataview';
import { CategoriaModalLancamentosPorCategoriasComponent } from './categoria-modal-lancamentos-por-categorias/categoria-modal-lancamentos-por-categorias.component';
import { ConfirmationService } from 'primeng/api';


@NgModule({
  declarations: [
    CategoriaCadastrarComponent,
    CategoriaConsultaComponent,
    CategoriaModalLancamentosPorCategoriasComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    CategoriaRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    DialogModule,
    DataViewModule,
    PaginatorModule,
    FormsModule,
    TooltipModule,
    ButtonModule,
    ConfirmDialogModule,
    DialogModule,
    CardModule,
    InputNumberModule,
    InputTextModule,
    InputTextareaModule,
    ReactiveFormsModule,
    TableModule,
    ScrollingModule
  ],
  providers: [
    CategoriaService, ConfirmationService
  ],
  exports: [
    CategoriaCadastrarComponent,
    CategoriaConsultaComponent
  ]
})
export class CategoriaModule { }
