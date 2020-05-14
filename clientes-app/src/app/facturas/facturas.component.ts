import { Component, OnInit } from '@angular/core';
import { Factura } from './models/factura';
import { ClienteService } from '../clientes/cliente.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { flatMap, map } from 'rxjs/operators';
import { FacturaService } from './services/factura.service';
import { Producto } from './models/producto';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ItemFactura } from './models/item-factura';
import Swal from 'sweetalert2';

@Component({ selector: 'app-facturas', templateUrl: './facturas.component.html', })
export class FacturasComponent implements OnInit {
  titulo = 'Nueva Factura';
  factura: Factura = new Factura();

  autocomplete = new FormControl();
  options: string[] = ['Mesa', 'Silla', 'Zapatilla'];
  filterProds: Observable<Producto[]>;


  constructor(private _svCli: ClienteService, public _svFac: FacturaService, private actRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.actRoute.paramMap.subscribe(rs => this._svCli.get(rs.get('clienteId')).subscribe(rs => {
      this.factura.cliente = rs;
      this.factura.cliente.facturas = null;
    }));
    this.filterProds = this.autocomplete.valueChanges
      .pipe(
        map(v => typeof v === 'string' ? v : v.nombre),
        flatMap(v => v ? this._filter(v) : [])
      );
  }

  private _filter(val: string): Observable<Producto[]> {
    return this._svFac.fillterProductos(val);
  }

  mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }

  selectProducto(ev: MatAutocompleteSelectedEvent) {
    let producto = ev.option.value as Producto;

    if (this.existeItem(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      let nuevoItem = new ItemFactura();
      nuevoItem.producto = producto;
      this.factura.items.unshift(nuevoItem);
    }

    this.autocomplete.setValue('');
    ev.option.focus();
    ev.option.deselect();
  }

  actualizarCant(id: number, ev) {
    let cantidad = ev.target.value as number;
    if (cantidad == 0) return this.eliminarItemFactura(id);

    this.factura.items = this.factura.items.map(item => {
      if (id === item.producto.id) item.cantidad = cantidad as number;
      return item;
    });
  }

  existeItem(id: number) {
    let exists = false;
    this.factura.items.forEach(item => {
      if (id === item.producto.id) exists = true;
    });
    return exists;
  }

  incrementaCantidad(id: number) {
    this.factura.items = this.factura.items.map(item => {
      if (id === item.producto.id) ++item.cantidad;
      return item;
    });
  }

  eliminarItemFactura(id: number) {
    this.factura.items = this.factura.items.filter(item => id !== item.producto.id);
  }

  create() {
    this.autocomplete.setErrors({ 'invalid': true });
    this._svFac.create(this.factura).subscribe(rs => {
      Swal.fire(this.titulo, `Factura ${rs.descripcion} creada con éxito!`, 'success');
      this.router.navigate(['/facturas', rs.id]);
    })
  }

}
