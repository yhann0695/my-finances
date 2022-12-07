import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../security/auth.service';
import { GeralService } from '../shared/geral.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  public loginForm: FormGroup;
  public username: string;
  public password: string;
  public error = '';

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router, 
    private authService: AuthService,
    private geralService: GeralService
  ) {
      if (this.authService.currentUserValue) this.router.navigate(['/home']);
    }

  ngOnInit(): void {
    this.formLogin();
  }

  public formLogin() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  public isInvalid(campo: string): boolean {
    return this.loginForm.get(campo).invalid && this.loginForm.get(campo).touched;
  }

  public isValid(campo: string): boolean {
    return this.loginForm.get(campo).valid && this.loginForm.get(campo).touched;
  }

  public onSubmit() {

    if (this.loginForm.invalid) {
      Object.keys(this.loginForm.controls).forEach(campo => {
        this.loginForm.get(campo).markAsTouched();
      });
    }

    this.username = this.loginForm.get('username').value;
    this.password = this.loginForm.get('password').value;

    this.authService.login(this.username, this.password)
      .pipe(first())
      .subscribe({
        next: () => {
          this.router.navigate(['/home']);
        },
        error: error => {
          this.geralService.mensagemErro(error.error);
        }
      });
  }

  

}
