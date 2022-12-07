import { CUSTOM_ELEMENTS_SCHEMA, DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { CommonModule, DecimalPipe } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { LancamentoModule } from './lancamento/lancamento.module';
import { ToastModule } from 'primeng/toast';
import { UsuarioModule } from './usuario/usuario.module';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { UsuarioService } from './usuario/service/usuario.service';
import { AuthService } from './security/auth.service';
import { LancamentoService } from './lancamento/service/lancamento.service';
import { JwtInterceptorService } from './security/jwt-interceptor.service';
import { MessageService } from 'primeng/api';
import { registerLocaleData } from '@angular/common';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import localePt from '@angular/common/locales/pt';
import { PanelModule } from 'primeng/panel';
import { ChartModule } from 'primeng/chart';
import { CategoriaModule } from './categoria/categoria.module';
import { CategoriaService } from './categoria/service/categoria.service';

registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ChartModule,
    NgxChartsModule,
    PanelModule,
    ToastModule,
    FormsModule,
    ReactiveFormsModule,
    TemplateModule,
    LancamentoModule,
    UsuarioModule,
    CategoriaModule
  ],
  providers: [
    UsuarioService, 
    LancamentoService, 
    AuthService, 
    MessageService, 
    CategoriaService,
    DecimalPipe,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true },
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' }
],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule { }
