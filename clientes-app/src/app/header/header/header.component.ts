import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../usuarios/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({ selector: 'app-header', templateUrl: './header.component.html', })
export class HeaderComponent implements OnInit {

  titulo = 'Angular APP';
  constructor(public _auth: AuthService, private router: Router) { }

  ngOnInit(): void { }

  logout() {
    Swal.fire('LogOut', `Hola ${this._auth.usuario.nombre} has cerrado sesión con éxito`, 'success');
    this._auth.logOut();
    this.router.navigateByUrl('/clientes');
  }

}
