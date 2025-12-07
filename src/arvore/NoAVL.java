package arvore;

import modelo.Livro;

public class NoAVL {
    public Livro dado;
    public NoAVL esquerda;
    public NoAVL direita;
    public int altura; 

    public NoAVL(Livro dado) {
        this.dado = dado;
        this.altura = 1; // Nova folha tem altura 1
    }
}