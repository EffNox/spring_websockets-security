import { Injectable } from "@angular/core";
import { Cliente } from "./cliente";
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { Router } from "@angular/router";
import { Region } from "./region";
import { environment } from '../../environments/environment.prod';

@Injectable({ providedIn: "root" })
export class ClienteService {

  private url = environment.url + '/cliente';

  constructor(private http: HttpClient, private router: Router) { }

  getAll() { return this.http.get<Cliente[]>(this.url); }

  getPage(p: number) { return this.http.get<Cliente[]>(`${this.url}/page?p=${p}`); }

  get(id: String) {
    return this.http.get<Cliente>(`${this.url}/${id}`).pipe(
      catchError((e) => {
        if (e.status != 401 && e.error.err) {
          this.router.navigateByUrl('/clientes');
          console.log(e.error.err);
        }
        return throwError(e);
      })
    );
  }

  create(cliente: Cliente): any {
    return this.http.post(this.url, cliente).pipe(
      catchError((e) => {
        if (e.status == 400) return throwError(e.error.err);
        return throwError(e);
      })
    );
  }

  update(cliente: Cliente): any {
    return this.http.put<Cliente>(`${this.url}/${cliente.id}`, cliente).pipe(
      catchError((e) => {
        if (e.status === 400) return throwError(e.error.err);
        return throwError(e);
      })
    );
  }

  delete(id: number) {
    return this.http.delete<Cliente>(`${this.url}/${id}`)/* .pipe(catchError((e) => throwError(e))) */;
  }

  uploadFile(file: File, id: string) {
    let fm = new FormData();
    fm.append("file", file);
    fm.append("id", id);

    const rq = new HttpRequest("POST", `${this.url}/upload`, fm, { reportProgress: true });
    return this.http.request<HttpEvent<{}>>(rq);
  }

  getRegiones() {
    return this.http.get<Region[]>(`${this.url}/regiones`);
  }
}
