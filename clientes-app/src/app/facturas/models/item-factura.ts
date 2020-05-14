import { Producto } from './producto';
export class ItemFactura {
    cantidad: number = 1; producto: Producto; importe: number;
    public getImporte(): number {
        return this.cantidad * this.producto.precio;
    }
}
