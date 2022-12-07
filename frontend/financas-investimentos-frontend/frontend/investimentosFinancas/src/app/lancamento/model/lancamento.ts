import { Categoria } from "src/app/categoria/model/categoria";
import { Usuario } from "src/app/usuario/model/usuario";

export class Lancamento {
    id: number;
    descricao: string;
    valor: number;
    ano: number;
    mes: number;
    formaPagamento: string;
    tipo: string;
    observacao: string;
    usuario: Usuario;
    dataCadastro: Date;
    categoria: Categoria;
    nomeArquivo: string;
}