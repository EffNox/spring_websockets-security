import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class PerfilGuard implements CanActivate {
  constructor(private _auth: AuthService, private router: Router) { }
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this._auth.isAuthenticated()) {
      this.router.navigateByUrl('/login');
      return false
    };
    let perfil = next.data['perfil'] as string;
    if (this._auth.hasPerfil(perfil)) return true;

    Swal.fire('Acceso denegado', `${this._auth.usuario.nombre} usted no tiene acceso a este recurso.`, 'warning');
    this.router.navigateByUrl('/clientes');
    return false;
  }

}
