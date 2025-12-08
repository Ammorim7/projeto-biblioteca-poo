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

    class NoAVLAutor {
        +Autor dado
        +NoAVLAutor esquerda
        +NoAVLAutor direita
        +int altura
    }

    class ArvoreAVLGeneric<T, K> {
        -No raiz
        +inserir(T)
        +buscar(K) T
        +remover(K)
        +listarTodos()
        +visualizarEstrutura()
    }

    Livro "1" -- "1" NoAVL : contem
    ArvoreAVL "1" -- "0..*" NoAVL : possui
    Livro "1" -- "1" Autor : tem
    Livro "1" -- "1" Categoria : pertence a
    Emprestimo "0..*" -- "1" Livro : referencia
    Emprestimo "0..*" -- "1" Usuario : realizado por
    Main "1" --> "1" ArvoreAVLGeneric : gerencia estruturas
    Main "1" --> "*" Usuario : gerencia
    Main "1" --> "*" Emprestimo : gerencia
