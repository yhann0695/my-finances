export class Usuario {

    constructor(id?: number, nome?: string, sobrenome?: string,  email?: string, username?: string, 
                password?: string) { 

        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.username = username;
        this.password = password;
    }
        

    id: number;
    nome: string;
    sobrenome: string;
    email: string;
    username: string;
    password: string;
}