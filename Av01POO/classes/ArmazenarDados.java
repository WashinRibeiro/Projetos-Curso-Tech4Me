package classes;

public class ArmazenarDados {
    private Pessoa[] pilotos;

    public Pessoa[] getPilotos() {
        return pilotos;
    }

    public void AumentarArmazenamento(Pessoa[] pilotosBackup, int tamanhoNovoVetor, int qtdCadastrados) {
        pilotos = new Pessoa[tamanhoNovoVetor];
        for (int i = 0; i < qtdCadastrados; i++) {
            pilotos[i] = new Pessoa();
            pilotos[i] = pilotosBackup[i];
        }

    }

    public void iniciarVetor(int tamanhoVetor) {
        pilotos = new Pessoa[tamanhoVetor];
    }

    public void setPilotos(Pessoa pilotos, int qtdCadastrados) {
        this.pilotos[qtdCadastrados] = new Pessoa();
        this.pilotos[qtdCadastrados] = pilotos;
    }
    
}
