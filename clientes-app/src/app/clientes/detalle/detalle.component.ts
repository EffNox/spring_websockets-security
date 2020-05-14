import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { AuthService } from '../../usuarios/auth.service';
import { FacturaService } from '../../facturas/services/factura.service';
import { Factura } from '../../facturas/models/factura';
declare var $;
@Component({ selector: 'app-detalle', templateUrl: './detalle.component.html' })
export class DetalleComponent implements OnInit {

  @Input() cliente: Cliente;
  titulo: string = 'Detalle del Cliente';
  selectFile: File;
  progreso: number = 0;
  constructor(private _svCliente: ClienteService, public _auth: AuthService,public _svFac:FacturaService) { }

  ngOnInit(): void {
    // this.actRoute.snapshot.paramMap.get('id');
    // this.actRoute.queryParams.subscribe(rs => {
    /*  this.actRoute.paramMap.subscribe(rs => {
       if (rs.get('id')) {
         this._svCliente.get(rs.get('id')).subscribe(rs => this.cliente = rs);
       }
     }) */
  }

  seleccionarArchivo(ev) {
    this.selectFile = ev.target.files[0];
    this.progreso = 0;
    // console.log(this.selectFile);
    // if (this.selectFile.type.indexOf('image') < 0) {
    //   Swal.fire(`Error al subir`, `Error al selecionar imagen, solo puede subir archivos de tipo imagen ` + this.selectFile.type.split("/")[1], 'warning');
    //   this.selectFile = null;
    // }
  }
  uploadFile() {
    if (!this.selectFile) return Swal.fire(`Error al subir`, `Debe seleccionar una Foto`, 'warning');

    this._svCliente.uploadFile(this.selectFile, this.cliente.id.toString()).subscribe(ev => {
      if (ev.type === HttpEventType.UploadProgress) {
        this.progreso = Math.round((ev.loaded / ev.total) * 100);
        // console.log(this.progreso);
      } else if (ev.type === HttpEventType.Response) {
        let rs: any = ev.body;
        this.cliente = rs.dt;
        Swal.fire(`Se subio con éxito la foto`, `${rs.msj}: ${rs.dt.nombre}`, 'success');
      }
    })
  }
  cerrarModal() {
    $('#mdCliente').modal('hide');
  }

  delete(factura: Factura) {
    Swal.fire({
      title: 'Está seguro?',
      text: `Seguro que desea eliminar la factura [${factura.descripcion}] del cliente ${this.cliente.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {
        this._svFac.delete(factura.id).subscribe(rs => {
          this.cliente.facturas = this.cliente.facturas.filter(f => f !== factura);
          Swal.fire('Eliminado', `Factura ${factura.descripcion} eliminada con éxito!`, 'success');
        },er=> Swal.fire('Error', `${er.error.msj}`, 'warning'));
      }
    })
  }
}
