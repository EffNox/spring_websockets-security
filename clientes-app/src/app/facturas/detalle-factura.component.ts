import { Component, OnInit } from '@angular/core';
import { FacturaService } from './services/factura.service';
import { Factura } from './models/factura';
import { ActivatedRoute } from '@angular/router';

@Component({ selector: 'app-detalle-factura', templateUrl: './detalle-factura.component.html', })
export class DetalleFacturaComponent implements OnInit {

  factura: Factura;
  titulo = 'Detalle de la Factura';
  constructor(private _svFac: FacturaService, private actRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.actRoute.paramMap.subscribe(rs => {
      let id = +rs.get('id');
      this._svFac.get(id).subscribe(rs => this.factura = rs);
    })
  }

}
