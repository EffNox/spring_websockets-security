import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { FormComponent } from './clientes/form.component';
import { LoginComponent } from './usuarios/login.component';
import { AuthGuard } from './usuarios/guards/auth.guard';
import { PerfilGuard } from './usuarios/guards/perfil.guard';
import { DetalleFacturaComponent } from './facturas/detalle-factura.component';
import { FacturasComponent } from './facturas/facturas.component';

const routes: Routes = [
  { path: 'directivas', component: DirectivaComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'clientes/page/:page', component: ClientesComponent },
  { path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard, PerfilGuard], data: { perfil: 'ROLE_ADMIN' } },
  { path: 'facturas/:id', component: DetalleFacturaComponent, canActivate: [AuthGuard, PerfilGuard], data: { perfil: 'ROLE_USER' } },
  { path: 'facturas/form/:clienteId', component: FacturasComponent, canActivate: [AuthGuard, PerfilGuard], data: { perfil: 'ROLE_ADMIN' } },
  // { path: 'clientes/ver/:id', component: DetalleComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'clientes' },
];

@NgModule({ imports: [RouterModule.forRoot(routes, { useHash: true })], exports: [RouterModule] })
export class AppRoutingModule { }
