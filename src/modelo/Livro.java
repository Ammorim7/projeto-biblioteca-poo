package modelo;

// Implementa Comparable para permitir a comparação na Árvore AVL pelo ISBN
public class Livro implements Comparable<Livro> {
    private String isbn; // Chave de busca principal
    private String titulo;
    private int anoPublicacao;
    private Autor autor;
    private Categoria categoria;
    private boolean emprestado;

    // Construtor
    public Livro(String isbn, String titulo, int anoPublicacao, Autor autor, Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.categoria = categoria;
        this.emprestado = false;
    }

    // --- Getters e Setters ---
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public Autor getAutor() { return autor; }
    public Categoria getCategoria() { return categoria; }
    public boolean isEmprestado() { return emprestado; }
    public void setEmprestado(boolean emprestado) { this.emprestado = emprestado; }

    // Implementação da comparação baseada no ISBN
    @Override
    public int compareTo(Livro outroLivro) {
        return this.isbn.compareTo(outroLivro.isbn);
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Categoria: %s | Emprestado: %s",
                             isbn, titulo, autor.getNome(), categoria.getNome(), emprestado ? "Sim" : "Não");
    }
}