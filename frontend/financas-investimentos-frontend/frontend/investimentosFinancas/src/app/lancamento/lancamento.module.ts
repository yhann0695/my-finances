import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from "ngx-currency-mask/src/currency-mask.config";
import { CommonModule } from '@angular/common';

import { LancamentoRoutingModule } from './lancamento-routing.module';
import { LancamentoCadastroComponent } from './lancamento-cadastro/lancamento-cadastro.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LancamentoService } from './service/lancamento.service';
import { HttpClientModule } from '@angular/common/http';
import { LancamentoListarComponent } from './lancamento-listar/lancamento-listar.component';
import { TableModule } from 'primeng/table';
import {TooltipModule} from 'primeng/tooltip';
import { ScrollingModule } from '@angular/cdk/scrolling';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputTextModule} from 'primeng/inputtext';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {CardModule} from 'primeng/card';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { LancamentoConsultaComponent } from './lancamento-consulta/lancamento-consulta.component';
import { LancamentoModalComponent } from './lancamento-modal/lancamento-modal.component';
import { ButtonModule } from 'primeng/button';
import {FileUploadModule} from 'primeng/fileupload';
import { PaginatorModule } from 'primeng/paginator';
import { CalendarModule } from 'primeng/calendar';
import { LancamentoAlterarComponent } from './lancamento-alterar/lancamento-alterar.component';
import { LancamentoCategoriaCadastrarComponent } from './lancamento-categoria-cadastrar/lancamento-categoria-cadastrar.component';


export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "left",
  allowNegative: true,
  allowZero: true,
  decimal: ",",
  precision: 2,
  prefix: "R$ ",
  suffix: "",
  thousands: "."
};

@NgModule({
  declarations: [
    LancamentoCadastroComponent,
    LancamentoListarComponent,
    LancamentoConsultaComponent,
    LancamentoModalComponent,
    LancamentoAlterarComponent,
    LancamentoCategoriaCadastrarComponent
  ],
  imports: [
    CommonModule,
    LancamentoRoutingModule,
    HttpClientModule,
    CurrencyMaskModule,
    PaginatorModule,
    FileUploadModule,
    CalendarModule,
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
  exports: [
    LancamentoCadastroComponent,
    LancamentoListarComponent,
    LancamentoConsultaComponent,
    LancamentoModalComponent,
    LancamentoAlterarComponent
  ],
  providers: [
    LancamentoService, ConfirmationService,
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ]
})
export class LancamentoModule { }
