import { ItemFactura } from './item-factura';
import { Cliente } from '../../clientes/cliente';
export class Factura {
    id: number;
    descripcion: string;
    observacion: string;  
    items: Array<ItemFactura> = [];
    cliente: Cliente;
    createAt: string;
    updateAt: string;
    total: number;

    calcularTotal(): number {
        this.total = 0;
        this.items.forEach(item => this.total += item.getImporte());
        return this.total;
    }

}
