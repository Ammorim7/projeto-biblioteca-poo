package modelo;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }

    @Override
    public String toString() {
        return String.format("Livro: %s | Usuário: %s | Empréstimo: %s | Devolução Prevista: %s",
                             livro.getTitulo(), usuario.getNome(), dataEmprestimo, dataDevolucaoPrevista);
    }
}