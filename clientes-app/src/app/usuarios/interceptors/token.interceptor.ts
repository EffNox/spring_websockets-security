import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

  constructor(private _auth: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = this._auth.token;
    if (token != null) {
      const authRq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) })
      return next.handle(authRq)/* .pipe(catchError(this.manejarError)) */;
    }
    return next.handle(req);
  }

  // manejarError(error: HttpErrorResponse) {
  //   console.log('Sucedió un error');
  //   console.log('Regiustro en el log file');
  //   console.warn(error);
  //   return throwError('Error personalizado');
  // }

}
