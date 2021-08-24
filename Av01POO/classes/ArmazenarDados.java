package classes;

public class ArmazenarDados {
    private Pessoa[] pilotos;
    private Aeronave[] aeronaves;

    public Pessoa[] getPilotos() {
        return pilotos;
    }

    public void AumentarArmazenamento(Pessoa[] pilotosBackup, int tamanhoNovoVetor, int qtdCadastrados, Aeronave[] aeronavesBackup) {
        pilotos = new Pessoa[tamanhoNovoVetor];
        for (int i = 0; i < qtdCadastrados; i++) {
            pilotos[i] = new Pessoa();
            pilotos[i] = pilotosBackup[i];
        }

        aeronaves = new Aeronave[tamanhoNovoVetor];
        for (int i = 0; i < qtdCadastrados; i++) {
            aeronaves[i] = new Aeronave();
            aeronaves[i] = aeronavesBackup[i];
        }

    }

    public void iniciarVetor(int tamanhoVetor) {
        pilotos = new Pessoa[tamanhoVetor];
        aeronaves = new Aeronave[tamanhoVetor];
    }

    public void setPilotos(Pessoa pilotos, int qtdCadastrados) {
        this.pilotos[qtdCadastrados] = new Pessoa();
        this.pilotos[qtdCadastrados] = pilotos;
    }

    public void setAeronaves(Aeronave aeronaveNova, int qtdCadastrados) {
        this.aeronaves[qtdCadastrados] = new Aeronave();
        this.aeronaves[qtdCadastrados] = aeronaveNova;
    }

    public Aeronave[] getAeronaves() {
        return aeronaves;
    }

    

    
    
}
