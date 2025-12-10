package arvore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ArvoreAVLGeneric<T, K extends Comparable<K>> {
    private class No {
        T dado;
        No esquerda, direita;
        int altura;
        No(T dado) { this.dado = dado; this.altura = 1; }
        K chave() { return keyExtractor.apply(dado); }
    }

    private No raiz;
    private final Function<T,K> keyExtractor;

    public ArvoreAVLGeneric(Function<T,K> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    private int altura(No n) { return (n == null) ? 0 : n.altura; }

    private void atualizarAltura(No n) { n.altura = 1 + Math.max(altura(n.esquerda), altura(n.direita)); }

    private int fator(No n) { return (n == null) ? 0 : altura(n.esquerda) - altura(n.direita); }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;
        x.direita = y; y.esquerda = T2;
        atualizarAltura(y); atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
        y.esquerda = x; x.direita = T2;
        atualizarAltura(x); atualizarAltura(y);
        return y;
    }

    public void inserir(T item) {
        raiz = inserirRec(raiz, item);
        try { System.out.println("Inserido: " + keyExtractor.apply(item)); } catch (Exception e) {}
    }

    private No inserirRec(No no, T item) {
        if (no == null) return new No(item);
        K chaveItem = keyExtractor.apply(item);
        int cmp = chaveItem.compareTo(no.chave());
        if (cmp < 0) no.esquerda = inserirRec(no.esquerda, item);
        else if (cmp > 0) no.direita = inserirRec(no.direita, item);
        else { System.err.println("Chave já existe: " + chaveItem); return no; }

        atualizarAltura(no);
        int balance = fator(no);

        if (balance > 1 && chaveItem.compareTo(no.esquerda.chave()) < 0) return rotacaoDireita(no);
        if (balance < -1 && chaveItem.compareTo(no.direita.chave()) > 0) return rotacaoEsquerda(no);
        if (balance > 1 && chaveItem.compareTo(no.esquerda.chave()) > 0) { no.esquerda = rotacaoEsquerda(no.esquerda); return rotacaoDireita(no); }
        if (balance < -1 && chaveItem.compareTo(no.direita.chave()) < 0) { no.direita = rotacaoDireita(no.direita); return rotacaoEsquerda(no); }

        return no;
    }

    public T buscar(K chave) { return buscarRec(raiz, chave); }
    private T buscarRec(No no, K chave) {
        if (no == null) return null;
        int cmp = chave.compareTo(no.chave());
        if (cmp < 0) return buscarRec(no.esquerda, chave);
        else if (cmp > 0) return buscarRec(no.direita, chave);
        else return no.dado;
    }

    public void remover(K chave) { raiz = removerRec(raiz, chave); }

    private No minValor(No no) { No atual = no; while (atual.esquerda != null) atual = atual.esquerda; return atual; }

    private No removerRec(No no, K chave) {
        if (no == null) { System.out.println("Chave não encontrada: " + chave); return no; }
        int cmp = chave.compareTo(no.chave());
        if (cmp < 0) no.esquerda = removerRec(no.esquerda, chave);
        else if (cmp > 0) no.direita = removerRec(no.direita, chave);
        else {
            if (no.esquerda == null || no.direita == null) {
                No temp = (no.esquerda != null) ? no.esquerda : no.direita;
                if (temp == null) { temp = null; System.out.println("Removido: " + chave); }
                else no = temp;
            } else {
                No temp = minValor(no.direita);
                no.dado = temp.dado;
                no.direita = removerRec(no.direita, temp.chave());
            }
        }

        if (no == null) return no;
        atualizarAltura(no);
        int balance = fator(no);
        int bfLeft = fator(no.esquerda);
        int bfRight = fator(no.direita);

        if (balance > 1 && bfLeft >= 0) return rotacaoDireita(no);
        if (balance > 1 && bfLeft < 0) { no.esquerda = rotacaoEsquerda(no.esquerda); return rotacaoDireita(no); }
        if (balance < -1 && bfRight <= 0) return rotacaoEsquerda(no);
        if (balance < -1 && bfRight > 0) { no.direita = rotacaoDireita(no.direita); return rotacaoEsquerda(no); }

        return no;
    }

    public List<T> listarTodos() {
        List<T> lista = new ArrayList<>(); inorder(raiz, lista); return lista;
    }

    private void inorder(No no, List<T> lista) { if (no != null) { inorder(no.esquerda, lista); lista.add(no.dado); inorder(no.direita, lista); } }

    public void visualizarEstrutura() { if (raiz == null) { System.out.println("A árvore está vazia."); return; } visualizarRec(raiz, 0); }
    private void visualizarRec(No no, int nivel) {
        if (no != null) {
            visualizarRec(no.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) System.out.print("  | ");
            System.out.println("-> " + no.chave() + " (Alt: " + no.altura + ") - " + no.dado.toString());
            visualizarRec(no.esquerda, nivel + 1);
        }
    }
}
