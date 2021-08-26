package classes;

public class Piloto extends Pessoa {
    private String breve;
    private String matricula;

    public String getBreve() {
        return breve;
    }
    public void setBreve(String breve) {
        this.breve = breve;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Piloto (Piloto piloto) {
        this.breve = piloto.getBreve();
        this.matricula = piloto.getMatricula();
        setNome(piloto.getNome());

        if(verificarCpf(piloto.getCpf())) {
            setCpf(piloto.getCpf());
        }
    }

    public Piloto () {
        
    }
}
