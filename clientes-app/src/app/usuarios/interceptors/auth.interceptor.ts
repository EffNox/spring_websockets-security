import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { catchError } from 'rxjs/operators';
import Swal from "sweetalert2";
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthInterceptor implements HttpInterceptor {

  constructor(private _auth: AuthService,private router:Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(this.manejarError));
  }

  manejarError(e: HttpErrorResponse) {

    if (e.status == 401) {
      // Swal.fire('Debe iniciar sesión: ' + e.status, `Usted debe iniciar sesión para poder acceder a este recurso`, 'warning');
      if (this._auth.isAuthenticated()) {
        this._auth.logOut();
      }
    }
    if (e.status === 403) {
      Swal.fire('Acceso denegado: ' + e.status, `Usted no tiene acceso a este recurso.`, 'warning');
      this.router.navigateByUrl('/clientes');
    }

    return throwError(e);
  }

}
