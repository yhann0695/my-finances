import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../security/auth.guard';
import { LancamentoCadastroComponent } from './lancamento-cadastro/lancamento-cadastro.component';
import { LancamentoConsultaComponent } from './lancamento-consulta/lancamento-consulta.component';

const routes: Routes = [
  { path: 'lancamentos', canActivate: [AuthGuard], component: LayoutComponent, children: [
    { path: 'cadastro', component: LancamentoCadastroComponent },
    { path: 'consulta', component: LancamentoConsultaComponent },
    { path: '', redirectTo: 'cadastro', pathMatch: 'full' }
  ] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LancamentoRoutingModule { }
