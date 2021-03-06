package classes;

public class Aeronave {
    private String modelo;
    private String categoria; // Se é uma aeronave de asa fixa ou móvel
    private Piloto piloto;
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Piloto getPiloto() {
        return piloto;
    }
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Aeronave(Aeronave aeronaveNova) {
        this.categoria = aeronaveNova.getCategoria();
        this.modelo = aeronaveNova.getModelo();
        this.piloto = aeronaveNova.getPiloto();
    }

    public Aeronave() {
        
    }
    
}
