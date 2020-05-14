import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Usuario } from './usuario';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment.prod';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private _usuario: Usuario;
  private _token: string;
  private url = environment.url;

  constructor(private http: HttpClient,private router:Router) { }

  public get usuario(): Usuario {
    if (this._usuario != null) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get token(): string {
    if (this._token != null) {
      return this._token;
    } else if (this._token == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token');
      return this._token;
    }
    return null;
  }


  login(u: Usuario) {
    const creadenciales = btoa('angularapp:123456');
    const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Basic ' + creadenciales });

    // // // let ps = new URLSearchParams();ps.set('username', u.username);ps.set('password', u.password);ps.set('grant_type', 'password');
    const ps = new HttpParams().append('username', u.username).append('password', u.password).append('grant_type', 'password');
    return this.http.post(`${this.url}/login`, ps, { headers });
  }

  saveData(token: string) {
    let pl = this.getPayload(token);
    this._usuario = new Usuario;
    this._usuario.nombre = pl.extra_info.split("/")[0];
    this._usuario.apeliido = pl.extra_info.split("/")[1];
    this._usuario.email = pl.extra_info.split("/")[2];
    this._usuario.perfil = pl.authorities;
    this._usuario.username = pl.user_name;
    sessionStorage.setItem("usuario", JSON.stringify(this._usuario));
    this._token = token;
    sessionStorage.setItem("token", this._token);
  }

  getPayload(token: string) {
    return (token != null) ? JSON.parse(atob(token.split(".")[1])) : null;
  }

   isAuthenticated():boolean {
    let pl = this.getPayload(this.token);
    // return (pl != null && pl.user_name && pl.user_name.length > 0) ? true : false;
    if (pl != null) {
      return true;
    }
    return false;
  }

  logOut() {
    this._usuario = null;
    this._token = null;
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
    this.router.navigateByUrl('/clientes');
  }

  hasPerfil(perfil: string) {
    if (this.usuario.perfil.includes(perfil)) return true;
    return false;
  }

}
