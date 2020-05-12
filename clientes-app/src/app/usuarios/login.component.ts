import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({ selector: 'app-login', templateUrl: './login.component.html' })
export class LoginComponent implements OnInit {

  titulo = 'Por favor Inicie Sesión';
  u: Usuario = new Usuario();
  constructor(private router: Router, private _auth: AuthService) { }

  ngOnInit(): void { 
    if (this._auth.isAuthenticated()) {
      this.router.navigateByUrl("/clientes");
      Swal.fire('Login', 'Hola, ' + this._auth.usuario.username + " ya estas autenticado!", 'info');
    }
  }

  login() {
    if (this.u.username == null || this.u.password == null) {
      Swal.fire('Error login', 'Usuario o contraseña vacios', 'warning');
    }
    this._auth.login(this.u).subscribe((rs: any) => {
      this._auth.saveData(rs.access_token);
      // let info = rs['extra_info'].split("/")[0] + " " + rs['extra_info'].split("/")[1];
      let usuario = this._auth.usuario;
      this.router.navigateByUrl("/clientes");
      Swal.fire('Login', 'Hola, ' + usuario.username + " bienvenido!", 'success');
    }, er => {
      if (er.status == 400) return Swal.fire('Login', 'Usuario y/o contraseña incorectos', 'warning');
    })
  }

}
