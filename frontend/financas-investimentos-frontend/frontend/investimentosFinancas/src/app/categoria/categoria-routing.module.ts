import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../security/auth.guard';
import { CategoriaCadastrarComponent } from './categoria-cadastrar/categoria-cadastrar.component';
import { CategoriaConsultaComponent } from './categoria-consulta/categoria-consulta.component';

const routes: Routes = [
  { path: 'categoria', canActivate: [AuthGuard], component: LayoutComponent, children: [
    { path: 'cadastrar', component: CategoriaCadastrarComponent },
    { path: 'consulta', component: CategoriaConsultaComponent },
    { path: '', redirectTo: 'cadastrar', pathMatch: 'full' }
  ] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
