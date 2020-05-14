import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Factura } from '../models/factura';
import { Producto } from '../models/producto';
import { environment } from '../../../environments/environment.prod';

@Injectable({ providedIn: 'root' })
export class FacturaService {

  private url = environment.url + '/factura';
  constructor(private http: HttpClient) { }

  get(id: number) { return this.http.get<Factura>(`${this.url}/${id}`); }

  delete(id: number) { return this.http.delete(`${this.url}/${id}`); }

  fillterProductos(nombre: string) { return this.http.get<Producto[]>(`${this.url}/producto?n=${nombre}`); }

  create(factura: Factura) { return this.http.post<Factura>(`${this.url}`, factura); }

}
