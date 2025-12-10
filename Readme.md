# 📚 Sistema de Gerenciamento de Biblioteca (Árvore AVL)

Este projeto implementa um sistema básico de gerenciamento de biblioteca usando Programação Orientada a Objetos (POO) e uma implementação genérica de Árvore AVL.

Resumo rápido

- Linguagem: Java
- Entidades: `Livro`, `Usuario`, `Autor`, `Categoria`, `Emprestimo`
- Estrutura: `ArvoreAVLGeneric<T,K>` (inserir, buscar, remover, listar, visualizar)
- Interface: CLI em `src/Main.Java`

O repositório contém o código-fonte em `src/` e documentação mínima para executar o sistema localmente.

## Mapeamento dos Requisitos para o Código

- CRUD (5 entidades): implementado em `src/Main.Java` (métodos como `inserirLivro()`, `buscarLivro()`, `alterarLivro()`, `removerLivro()`, `inserirAutor()`, `inserirUsuario()`, etc.).
- Modelos: código em `src/modelo/` (`Livro.java`, `Autor.java`, `Categoria.java`, `Usuario.java`, `Emprestimo.java`).
- Árvore AVL genérica: `src/arvore/ArvoreAVLGeneric.java` — usada por `Main` para armazenar e buscar entidades.
- Visualização: `visualizarEstrutura()` em `ArvoreAVLGeneric` e opção no menu (`visualizarArvore()` em `Main`).
- Diagrama: `diagrama.md` (Mermaid) mostra as principais classes e dependências.

## Como compilar e executar (relativo ao diretório do projeto)

Pré-requisito: JDK instalado e `javac`/`java` no `PATH`.

Unix / macOS / WSL:

```bash
cd src
javac -d ../bin modelo/*.java arvore/*.java Main.java
cd ..
java -cp bin Main
```

Windows PowerShell:

```powershell
cd src
# Use cmd /c para evitar expansão de glob do PowerShell
cmd /c "javac -d ..\bin modelo\*.java arvore\*.java Main.java"
cd ..
java -cp bin Main
```

Alternativa: abra o projeto em uma IDE (IntelliJ, Eclipse) e execute a classe `Main`.

## Como apresentar (roteiro rápido)

1. Abra `diagrama.md` para contextualizar as classes.
2. Mostre `src/arvore/ArvoreAVLGeneric.java` (explique `keyExtractor`, rotações, balanceamento).
3. Execute a aplicação e navegue pelos menus (`Main`) demonstrando: inserir, buscar, alterar, remover e `Visualizar Estrutura` para provar o balanceamento.
4. Demonstre empréstimos (realizar e devolver) para mostrar relacionamento entre `Emprestimo`, `Livro` e `Usuario`.

## Melhorias opcionais

- Persistência (salvar/ler em JSON ou CSV).
- Exportar árvore para Mermaid/Graphviz para slides.
- Testes automatizados (JUnit).
- Validação de entrada mais robusta no CLI.

## Componentes do Grupo

- Matheus de Amorim Santana
- Israel Hall Leighton
- Fernanda Dantas de Carvalho Fernandes
