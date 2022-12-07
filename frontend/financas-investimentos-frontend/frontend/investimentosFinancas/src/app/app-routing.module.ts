import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './security/auth.guard';
import { UsuarioCadastroComponent } from './usuario/usuario-cadastro/usuario-cadastro.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'usuario-cadastro', component: UsuarioCadastroComponent },
  { path: '', component: LayoutComponent, children: [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    {path: '', redirectTo: 'home', pathMatch: 'full'},
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
