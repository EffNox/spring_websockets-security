import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Client } from "@stomp/stompjs";
import * as Sock from 'sockjs-client';
import { Mensaje } from './models/mensaje';

@Component({ selector: 'app-chat', templateUrl: './chat.component.html' })
export class ChatComponent implements OnInit {

  private client: Client;
  conectado = false;
  msj: Mensaje = new Mensaje();
  msjs: Mensaje[] = [];
  escribiendos: string;
  clienteId: string;

  @ViewChild('scrollChat') comment: ElementRef;
  scrolltop: number = null
  constructor() {
    this.clienteId = 'id-' + new Date().getUTCMilliseconds() + '-' + Math.random().toString(36).substr(2);
  }

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = () => new Sock('http://localhost:8000/chat-websocket');

    this.client.onConnect = (frame) => {
      console.log('Conectado: ' + this.client.connected + ' : ' + frame);
      this.conectado = true;

      this.client.subscribe('/chat/msj', e => { //Escuchar Los eventos que manda desde el Broker(BackEnd)
        let msj = JSON.parse(e.body) as Mensaje;
        (!this.msj.color && msj.tipo == 'NEW_USER' && this.msj.username == msj.username) ? this.msj.color = msj.color : null;
        this.msjs.push(msj);
        setTimeout(() => this.scrolltop = this.comment.nativeElement.scrollHeight, 100);
        console.log(msj);
      });

      this.msj.tipo = 'NEW_USER';
      this.client.publish({ destination: '/app/msj', body: JSON.stringify(this.msj) });


      this.client.subscribe('/chat/escribiendo', e => {
        this.escribiendos = '@' + e.body;
        setTimeout(() => this.escribiendos = '', 3000);
      });

      console.log(this.clienteId);
      
      
      this.client.subscribe('/chat/history/' + this.clienteId, e => {
        const history = JSON.parse(e.body) as Mensaje[];
        this.msjs = history.reverse();
        // this.msjs = history.map(m=>{
        //     m.fecha=new Date(m.fecha);
        //     return m;
        // }).reverse();
      });

      this.client.publish({ destination: '/app/history', body: this.clienteId });

    }

    this.client.onDisconnect = (frame) => {
      console.log('Desconectado: ' + !this.client.connected + ' : ' + frame);
      this.conectado = false;
      this.msj = new Mensaje();
      this.msjs = [];
    }

    this.client.onWebSocketClose = () => this.conectado = false;

  }

  connect() {
    this.client.activate();
  }

  disconnect() {
    this.client.deactivate();
  }

  sendMessage() {
    this.msj.tipo = 'NEW_MSJ';
    this.client.publish({ destination: '/app/msj', body: JSON.stringify(this.msj) });
    this.msj.texto = '';
  }

  escribiendo() {
    this.client.publish({ destination: '/app/escribiendo', body: this.msj.username });
  }

}
