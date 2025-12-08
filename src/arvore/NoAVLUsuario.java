package arvore;

import modelo.Usuario;

public class NoAVLUsuario {
    public Usuario dado;
    public NoAVLUsuario esquerda;
    public NoAVLUsuario direita;
    public int altura;

    public NoAVLUsuario(Usuario dado) {
        this.dado = dado;
        this.altura = 1;
    }
}
