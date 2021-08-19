package classes;

public class Aeronave {
    private String modelo;
    private String categoria; // Se é uma aeronave de asa fixa ou móvel
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
    
}
