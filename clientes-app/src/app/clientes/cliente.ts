import { Region } from "./region";
import { Factura } from '../facturas/models/factura';
export class Cliente { id: number; nombre: string; apellido: string; email: string; foto: string; createAt: string; region: Region;  facturas: Array<Factura> = []; }
