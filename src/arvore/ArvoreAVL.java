package arvore;

import modelo.Livro;

public class ArvoreAVL {
    private NoAVL raiz;

    // --- Métodos Auxiliares ---

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private void atualizarAltura(NoAVL no) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }
    
    // --- Rotações ---

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x; 
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y; 
    }

    // --- Inserção (CRUD: Criar) ---

    public void inserir(Livro livro) {
        this.raiz = inserirRecursivo(raiz, livro);
        System.out.println("✅ Livro '" + livro.getTitulo() + "' inserido na Árvore AVL (ISBN: " + livro.getIsbn() + ")");
    }

    private NoAVL inserirRecursivo(NoAVL no, Livro livro) {
        if (no == null) {
            return new NoAVL(livro);
        }

        if (livro.compareTo(no.dado) < 0) {
            no.esquerda = inserirRecursivo(no.esquerda, livro);
        } else if (livro.compareTo(no.dado) > 0) {
            no.direita = inserirRecursivo(no.direita, livro);
        } else {
            System.err.println("❌ Erro: Livro com ISBN " + livro.getIsbn() + " já existe.");
            return no; // Chaves iguais não permitidas (ISBN único)
        }

        atualizarAltura(no);

        int balanceamento = fatorBalanceamento(no);

        // Casos de Rotação
        if (balanceamento > 1 && livro.compareTo(no.esquerda.dado) < 0) return rotacaoDireita(no); // EE
        if (balanceamento < -1 && livro.compareTo(no.direita.dado) > 0) return rotacaoEsquerda(no); // DD
        
        if (balanceamento > 1 && livro.compareTo(no.esquerda.dado) > 0) { // ED
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && livro.compareTo(no.direita.dado) < 0) { // DE
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    // --- Busca (CRUD: Ler) ---

    public Livro buscar(String isbn) {
        return buscarRecursivo(raiz, isbn);
    }

    private Livro buscarRecursivo(NoAVL no, String isbn) {
        if (no == null) {
            return null;
        }

        int comparacao = isbn.compareTo(no.dado.getIsbn());

        if (comparacao < 0) {
            return buscarRecursivo(no.esquerda, isbn);
        } else if (comparacao > 0) {
            return buscarRecursivo(no.direita, isbn);
        } else {
            return no.dado; 
        }
    }

    // --- Remoção (CRUD: Remover) ---

    public void remover(String isbn) {
        this.raiz = removerRecursivo(raiz, isbn);
    }

    private NoAVL noComValorMinimo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    private NoAVL removerRecursivo(NoAVL no, String isbn) {
        if (no == null) {
            System.out.println("❌ Erro: Livro com ISBN " + isbn + " não encontrado.");
            return no;
        }

        int comparacao = isbn.compareTo(no.dado.getIsbn());

        if (comparacao < 0) {
            no.esquerda = removerRecursivo(no.esquerda, isbn);
        } else if (comparacao > 0) {
            no.direita = removerRecursivo(no.direita, isbn);
        } else {
            // Nó a ser removido encontrado
            if ((no.esquerda == null) || (no.direita == null)) {
                NoAVL temp = (no.esquerda != null) ? no.esquerda : no.direita;

                if (temp == null) { // Nó folha
                    temp = null;
                    System.out.println("🗑️ Livro removido com sucesso (ISBN: " + isbn + ")");
                } else { // Um filho
                    no = temp; 
                    System.out.println("🗑️ Livro removido com sucesso (ISBN: " + isbn + ")");
                }
            } else {
                // Dois filhos: Encontra o sucessor in-order (menor da subárvore direita)
                NoAVL temp = noComValorMinimo(no.direita);
                no.dado = temp.dado; // Copia o dado do sucessor
                no.direita = removerRecursivo(no.direita, temp.dado.getIsbn()); // Remove o sucessor
            }
        }

        if (no == null) return no;

        // 2. Atualiza a altura do nó atual
        atualizarAltura(no);

        // 3. Rebalanceamento
        int balanceamento = fatorBalanceamento(no);
        int balanceamentoEsquerda = fatorBalanceamento(no.esquerda);
        int balanceamentoDireita = fatorBalanceamento(no.direita);

        // EE - Rotação Simples à Direita
        if (balanceamento > 1 && balanceamentoEsquerda >= 0) return rotacaoDireita(no);

        // ED - Rotação Dupla
        if (balanceamento > 1 && balanceamentoEsquerda < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // DD - Rotação Simples à Esquerda
        if (balanceamento < -1 && balanceamentoDireita <= 0) return rotacaoEsquerda(no);

        // DE - Rotação Dupla
        if (balanceamento < -1 && balanceamentoDireita > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    // --- Visualização da Estrutura (Requisito 2.e) ---

    // Exemplo de visualização hierárquica 
    public void visualizarEstrutura() {
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        visualizarEstruturaRecursivo(raiz, 0);
    }

    private void visualizarEstruturaRecursivo(NoAVL no, int nivel) {
        if (no != null) {
            visualizarEstruturaRecursivo(no.direita, nivel + 1);
            
            for (int i = 0; i < nivel; i++) {
                System.out.print("  | "); 
            }
            System.out.println("-> " + no.dado.getIsbn() + 
                               " (Alt: " + no.altura + 
                               ", FB: " + fatorBalanceamento(no) + ")" +
                               " - " + no.dado.getTitulo());

            visualizarEstruturaRecursivo(no.esquerda, nivel + 1);
        }
    }
}