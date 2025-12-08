# 📚 Sistema de Gerenciamento de Biblioteca (Árvore AVL)

Este projeto implementa um sistema básico de gerenciamento de biblioteca utilizando os principais conceitos de Programação Orientada a Objetos (POO) e **Árvores AVL** como estrutura principal de dados.

## Requisitos Atendidos

* **POO:** Utiliza 5 entidades (`Livro`, `Usuario`, `Autor`, `Categoria`, `Emprestimo`) com classes, atributos privados e métodos públicos.
* **CRUD:** Implementação completa de Criação, Leitura, Alteração e Remoção de registros.
* **Árvore AVL Genérica:** O projeto agora usa uma implementação genérica de Árvore AVL (`ArvoreAVLGeneric<T,K>`) que pode ser reutilizada para `Livro` (ISBN), `Autor` (id), `Usuario` (matrícula) e `Categoria` (id). A implementação inclui inserção, busca, remoção, listagem e visualização da estrutura.
* **Interação com o Usuário:** Interface de linha de comando (CLI) para interação e teste das funcionalidades.
* **Visualização da Árvore:** Implementa um método para exibir a estrutura hierárquica da AVL, mostrando o ISBN e o Fator de Balanceamento.
* **Linguagem:** Java.

## Mapeamento dos Requisitos para o Código

- **CRUD (5 entidades)** — todos os CRUDs estão implementados em `src/Main.Java` (menus e métodos):
    - `Livro`: `inserirLivro()`, `buscarLivro()`, `alterarLivro()`, `removerLivro()`
    - `Autor`: `inserirAutor()`, `listarAutores()`, `alterarAutor()`, `removerAutor()`
    - `Categoria`: `inserirCategoria()`, `listarCategorias()`, `alterarCategoria()`, `removerCategoria()`
    - `Usuario`: `inserirUsuario()`, `listarUsuarios()`, `alterarUsuario()`, `removerUsuario()`
    - `Emprestimo`: `realizarEmprestimo()`, `devolverLivro()`, `listarEmprestimos()`

- **Modelos/Entidades** — fontes em `src/modelo/`:
    - `src/modelo/Livro.java`, `src/modelo/Autor.java`, `src/modelo/Categoria.java`, `src/modelo/Usuario.java`, `src/modelo/Emprestimo.java`

- **Estrutura de Árvore (AVL)** — implementação genérica e canônica em `src/arvore/ArvoreAVLGeneric.java`.
    - Operações: `inserir(T)`, `buscar(K)`, `remover(K)`, `listarTodos()`, `visualizarEstrutura()`.
    - Instâncias em `Main`: declarações no topo de `src/Main.Java`:
        - `ArvoreAVLGeneric<Livro,String> arvoreLivros = new ArvoreAVLGeneric<>(Livro::getIsbn)`
        - `ArvoreAVLGeneric<Autor,Integer> arvoreAutores = new ArvoreAVLGeneric<>(Autor::getId)`
        - `ArvoreAVLGeneric<Usuario,Integer> arvoreUsuarios = new ArvoreAVLGeneric<>(Usuario::getMatricula)`
        - `ArvoreAVLGeneric<Categoria,Integer> arvoreCategorias = new ArvoreAVLGeneric<>(Categoria::getId)`

- **Visualização da árvore** — método `visualizarEstrutura()` em `src/arvore/ArvoreAVLGeneric.java` e chamada por `visualizarArvore()` em `src/Main.Java`.

- **Interação / Apresentação** — menus e entradas interativas em `src/Main.Java` (`exibirMenuPrincipal()` e sub-menus). A apresentação ao avaliador deve ser feita executando a aplicação e navegando pelos menus.

## Diagrama

- O arquivo `diagrama.md` na raiz foi atualizado para refletir a arquitetura atual (uso de `ArvoreAVLGeneric`, entidades e relacionamentos).

## Observações e recomendações

- Todos os requisitos obrigatórios da atividade estão implementados no código atual.
- Melhorias opcionais que podem aumentar nota/apresentação (não obrigatórias):
    - Persistência (salvar/ler do disco) — útil para demonstrar dados entre execuções.
    - Exportar a AVL para um grafo (Mermaid/Graphviz) automaticamente para a apresentação.
    - Testes automatizados (JUnit) para comprovar comportamento das operações AVL.
    - Melhor validação de entrada no CLI (ex.: tratamento de NumberFormatException, validação de e-mail).

## Como compilar (Windows PowerShell)

Abra o PowerShell a partir da pasta do projeto e execute:

```powershell
Set-Location 'C:\Users\leigh\OneDrive\Área de Trabalho\projeto-biblioteca-poo\src' ;
cmd /c "javac -d ..\bin modelo\*.java arvore\*.java Main.java"
```

Em seguida, execute a aplicação a partir do diretório raiz do projeto:

```powershell
Set-Location 'C:\Users\leigh\OneDrive\Área de Trabalho\projeto-biblioteca-poo' ;
java -cp bin Main
```

> Observação: usamos `cmd /c` para evitar problemas de expansão de glob em PowerShell ao compilar múltiplos arquivos Java.

## Execução da apresentação

1. Compile conforme instruções acima.
2. Execute `java -cp bin Main` e navegue pelos menus: mostre inserção de registros, busca, remoção e a opção `Visualizar Estrutura` para demonstrar a AVL.

---

## Componentes do Grupo

* Matheus de Amorim Santana
* Israel Hall Leighton
* Fernanda Dantas

## 🚀 Como Rodar a Aplicação

1.  **Pré-requisitos:** Certifique-se de ter o Java Development Kit (JDK) instalado.
2.  **Compilação:** Navegue até a pasta `src/` e compile os arquivos:
    ```bash
    javac -d ../bin modelo/*.java arvore/*.java Main.java
    ```
    *(Alternativamente, compile todos os arquivos .java no diretório src)*
3.  **Execução:** Execute a classe principal a partir do diretório raiz do projeto:
    ```bash
    java -cp bin Main 
    ```

**OBS:** O projeto utiliza pacotes (modelos, arvore) e, portanto, deve ser executado usando o `-cp` ou a partir da pasta de compilação.

## 👥 Componentes do Grupo

* Matheus de Amorim Santana
* Israel Hall Leighton
* Fernanda Dantas