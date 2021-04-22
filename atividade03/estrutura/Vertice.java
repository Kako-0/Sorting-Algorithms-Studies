package estrutura;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
    private String nome;
    private List<Aresta> adj;
    private String cor = "branco";
    private double distancia = 0;
    private boolean visitado = false;
    private Vertice pai;

    public Vertice(String nome) {
        this.setNome(nome);
        this.setAdj(new ArrayList<Aresta>());
    }

    public Vertice getPai() {
        return pai;
    }

    public double getDistancia() {
        return distancia;
    }

    public String getCor() {
        return cor;
    }

    public List<Aresta> getAdj() {
        return adj;
    }

    public void setAdj(List<Aresta> adj) {
        this.adj = adj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addAdj(Aresta e) {
        getAdj().add(e);
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setPai(Vertice pai){
        this.pai = pai;
    }

    public boolean isVisitado() {
        return visitado;
    }
    
    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }

    @Override
    public int compareTo(Vertice vertice) {
        if(this.getDistancia() < vertice.getDistancia()) 
            return -1;
        else if(this.getDistancia() == vertice.getDistancia()) 
            return 0;

        return 1;  
    }
}
