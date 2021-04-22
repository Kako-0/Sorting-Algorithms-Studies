package estrutura;

public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private double peso;
    private boolean visitado = false;

    public Aresta(Vertice origem, Vertice destino, double peso) {
        this.setOrigem(origem);
        this.setDestino(destino);
        this.setPeso(peso);
    }

    public estrutura.Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public estrutura.Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
