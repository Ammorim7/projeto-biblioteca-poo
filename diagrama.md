# Diagrama UML de Classes (Mermaid)

```mermaid
classDiagram
    %% Layout mais legível: de cima para baixo
    direction TB

    class Livro {
        +String isbn
        +String titulo
        +int anoPublicacao
        +boolean emprestado
        +toString()
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
        +Livro livro
        +Usuario usuario
        +LocalDate dataEmprestimo
        +LocalDate dataDevolucaoPrevista
        +toString()
    }

    class ArvoreAVLGeneric~T,K~ {
        -No raiz
        +inserir(T item)
        +buscar(K chave) T
        +remover(K chave)
        +listarTodos() List~T~
        +visualizarEstrutura()
    }

    %% Relações principais
    Livro --> Autor : autor
    Livro --> Categoria : categoria
    Emprestimo --> Livro : referencia
    Emprestimo --> Usuario : realizado_por

    %% Uso das árvores pelo Main (dependência)
    Main ..> ArvoreAVLGeneric~Livro,String~ : usa
    Main ..> ArvoreAVLGeneric~Autor,Integer~ : usa
    Main ..> ArvoreAVLGeneric~Usuario,Integer~ : usa
    Main ..> ArvoreAVLGeneric~Categoria,Integer~ : usa

    %% Nota: a estrutura interna (nós) é representada dentro da implementação genérica, não detalhada aqui.