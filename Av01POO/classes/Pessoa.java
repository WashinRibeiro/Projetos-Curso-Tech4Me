package classes;

import java.util.InputMismatchException;

public abstract class Pessoa {
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;

        if(verificarCpf(cpf)){
            this.cpf = cpf;
        }
    }

    public Pessoa() {

    }

    public Pessoa(Pessoa pessoa){
        this.nome = pessoa.nome;
        if(verificarCpf(pessoa.getCpf())){
            this.cpf = pessoa.getCpf();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(verificarCpf(cpf)){
            this.cpf = cpf;
        }
    }

    // Método para evitar repetição da verificação do CPF
    boolean verificarCpf(String cpf){
        if(cpf.length() != 14){
            throw new InputMismatchException("\n O tamanho do CPF deve ser = 14, como o padrão: 000.000.000-00");

        } else if(!cpf.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")) {
            throw new InputMismatchException("\n O padrão digitado está errado!! o Correto é: 000.000.000-00");
        } else {
            return true; 
        }
    }


}
