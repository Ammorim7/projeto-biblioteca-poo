package modelo;

public class Categoria {
    private int id;
    private String nome;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome;
    }

    // Setter
    public void setNome(String nome) { this.nome = nome; }
}