import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import { ClienteService } from '../cliente.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { AuthService } from '../../usuarios/auth.service';

@Component({ selector: 'app-detalle', templateUrl: './detalle.component.html', styleUrls: ['./detalle.component.css'] })
export class DetalleComponent implements OnInit {

  @Input() cliente: Cliente;
  titulo: string = 'Detalle del Cliente';
  selectFile: File;
  progreso: number = 0;
  constructor(private _svCliente: ClienteService, private actRoute: ActivatedRoute,public _auth:AuthService) { }

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


}
