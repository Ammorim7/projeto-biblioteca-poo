package modelo;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;

    public Autor(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Nacionalidade: " + nacionalidade;
    }
}