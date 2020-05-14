import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';
import { environment } from '../../environments/environment.prod';

declare var $;
@Component({ selector: 'app-clientes', templateUrl: './clientes.component.html' })
export class ClientesComponent implements OnInit {

  clientes: Cliente[] = [];
  paginator: any;
  clienteSeleccionado: Cliente;
  url = environment.url;
  constructor(private _svCliente: ClienteService, private actRoute: ActivatedRoute, public _auth: AuthService) { }

  ngOnInit(): void {
    this.actRoute.params.subscribe(rs => {
      this._svCliente.getPage(rs['page'] - 1 || 0).subscribe(rs => {
        this.clientes = rs['content'];
        this.paginator = rs;
      });
    })
  }

  delete(cliente: Cliente) {
    Swal.fire({
      title: 'Está seguro?',
      text: `Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {
        this._svCliente.delete(cliente.id).subscribe(rs => {
          this.clientes = this.clientes.filter(cli => cli !== cliente);
          Swal.fire('Cliente eliminado', `Cliente ${cliente.nombre} eliminado con éxito!`, 'success');
        }, er => (er.error != null) ? Swal.fire('Cliente', `${er.error.msj}`, 'warning') : null);
      }
    })
  }

  abrirModal(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
    $('#mdCliente').modal('show');
  }

  cerrarModal() {
    $('#mdCliente').modal('hide');
  }

}
