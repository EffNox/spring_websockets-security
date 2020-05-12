import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private _auth: AuthService, private router: Router) { }
  canActivate() {
    if (this._auth.isAuthenticated()) {
      if (this.isTokenExpired()) {
        this._auth.logOut();
        this.router.navigateByUrl('/login');
        return false;
      }
      return true;
    }
    this.router.navigateByUrl('/login');
    return false;
  }

  isTokenExpired() {
    let token = this._auth.token;
    let pl = this._auth.getPayload(token);
    let now = new Date().getTime() / 1000;
    if (pl.exp < now) return true;
    return false;
  }

}
