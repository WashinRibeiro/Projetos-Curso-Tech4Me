package classes;

public class ArmazenarDados {
    private Piloto[] pilotos;
    private Aeronave[] aeronaves;

    public Piloto[] getPilotos() {
        return pilotos;
    }

    public void AumentarArmazenamento(Piloto[] pilotosBackup, int tamanhoNovoVetor, int qtdCadastrados, Aeronave[] aeronavesBackup) {
        pilotos = new Piloto[tamanhoNovoVetor];
        for (int i = 0; i < qtdCadastrados; i++) {
            pilotos[i] = new Piloto();
            pilotos[i] = pilotosBackup[i];
        }

        aeronaves = new Aeronave[tamanhoNovoVetor];
        for (int i = 0; i < qtdCadastrados; i++) {
            aeronaves[i] = new Aeronave();
            aeronaves[i] = aeronavesBackup[i];
        }

    }

    public void iniciarVetor(int tamanhoVetor) {
        pilotos = new Piloto[tamanhoVetor];
        aeronaves = new Aeronave[tamanhoVetor];
    }

    public void setPilotos(Piloto pilotos, int qtdCadastrados) {
        this.pilotos[qtdCadastrados] = new Piloto();
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
