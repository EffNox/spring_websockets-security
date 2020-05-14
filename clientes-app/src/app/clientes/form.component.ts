import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from "sweetalert2";
import { Region } from './region';

@Component({ selector: 'app-form', templateUrl: './form.component.html', })
export class FormComponent implements OnInit {

  public cliente: Cliente = new Cliente();
  regiones: Region[];
  errores: string[];
  constructor(private _svCliente: ClienteService, private route: Router, private actRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this._svCliente.getRegiones().subscribe(rs => this.regiones = rs);
    this.cargarCliente();
  }

  cargarCliente() {
    this.actRoute.params.subscribe(rs => {
      if (rs.id != 'nuevo') this._svCliente.get(rs.id).subscribe(rs => this.cliente = rs, er => Swal.fire('Cliente', `${er.error.msj}`, 'warning'));
    });
  }

  create() {
    this._svCliente.create(this.cliente).subscribe(rs => {
      this.route.navigateByUrl("/clientes");
      Swal.fire('Cliente', `${rs.msj}: ${rs.dt.nombre}`, 'success');
    }, er => this.errores = er);
  }

  update() {
    this.cliente.facturas = null;
    this._svCliente.update(this.cliente).subscribe(rs => {
      this.route.navigateByUrl("/clientes");
      Swal.fire('Cliente', `${rs.msj}: ${rs.dt.nombre}`, 'success');
    }, er => this.errores = er);
  }

  compararRegion(o1: Region, o2: Region) {
    // return o1 === null || o1 === null ? false : o1?.id === o2?.id;
    // return o1 === null || o1 === null || o1 === undefined || o1 === undefined ? false : o1?.id === o2?.id;
    return o1?.id === o2?.id;
  }

}
