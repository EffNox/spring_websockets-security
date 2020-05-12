import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './usuarios/login.component';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header/header.component';
import { FooterComponent } from './footer/footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormComponent } from './clientes/form.component';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

import lc from '@angular/common/locales/es';
import lcEx from '@angular/common/locales/extra/es-PE';
import { registerLocaleData } from '@angular/common';
import { PaginatorComponent } from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DetalleComponent } from './clientes/detalle/detalle.component';
import { TokenInterceptor } from './usuarios/interceptors/token.interceptor';
import { AuthInterceptor } from './usuarios/interceptors/auth.interceptor';
registerLocaleData(lc, 'es-PE', lcEx);

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      FooterComponent,
      DirectivaComponent,
      ClientesComponent,
      FormComponent,
      PaginatorComponent,
      DetalleComponent,
      LoginComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      AppRoutingModule,
      FormsModule,
      BrowserAnimationsModule,
      MatDatepickerModule,
      MatMomentDateModule,
      MatFormFieldModule,
      MatInputModule,
      MatSelectModule
   ],
   providers: [
      { provide: LOCALE_ID, useValue: 'es-PE' },
      { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
   bootstrap: [AppComponent]
})
export class AppModule { }
