# Diagrama UML de Classes (Mermaid)

```mermaid
classDiagram
    direction LR

    class Livro {
        +String isbn
        +String titulo
        +int anoPublicacao
        -boolean emprestado
        +compareTo(Livro) int
        +toString() String
    }
    
    class Autor {
        +int id
        +String nome
        +String nacionalidade
    }
    
    class Categoria {
        +int id
        +String nome
    }
    
    class Usuario {
        +int matricula
        +String nome
        +String email
    }

    class Emprestimo {
        +LocalDate dataEmprestimo
        +LocalDate dataDevolucaoPrevista
    }

    class NoAVL {
        +Livro dado
        +NoAVL esquerda
        +NoAVL direita
        +int altura
    }

    class ArvoreAVL {
        -NoAVL raiz
        +inserir(Livro)
        +buscar(String isbn) Livro
        +remover(String isbn)
        +visualizarEstrutura()
        -rotacaoDireita(NoAVL) NoAVL
        -rotacaoEsquerda(NoAVL) NoAVL
    }

    Livro "1" -- "1" NoAVL : contem
    ArvoreAVL "1" -- "0..*" NoAVL : possui
    Livro "1" -- "1" Autor : tem
    Livro "1" -- "1" Categoria : pertence a
    Emprestimo "0..*" -- "1" Livro : referencia
    Emprestimo "0..*" -- "1" Usuario : realizado por
    Main "1" --> "1" ArvoreAVL : gerencia
    Main "1" --> "*" Usuario : gerencia
    Main "1" --> "*" Emprestimo : gerencia