package modelo;

public class Usuario {
    private int matricula;
    private String nome;
    private String email;

    public Usuario(int matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }

    public String getEmail() { return email; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Email: " + email;
    }
}