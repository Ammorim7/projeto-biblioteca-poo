# 📚 Sistema de Gerenciamento de Biblioteca (Árvore AVL)

Este projeto implementa um sistema básico de gerenciamento de biblioteca utilizando os principais conceitos de Programação Orientada a Objetos (POO) e **Árvores AVL** como estrutura principal de dados.

## Requisitos Atendidos

* **POO:** Utiliza 5 entidades (`Livro`, `Usuario`, `Autor`, `Categoria`, `Emprestimo`) com classes, atributos privados e métodos públicos.
* **CRUD:** Implementação completa de Criação, Leitura, Alteração e Remoção de registros.
* **Árvore AVL:** A entidade `Livro` é armazenada na estrutura AVL, otimizando as operações de CRUD pelo seu ISBN (chave). A implementação da árvore (inserção, busca, remoção e rotações) é própria.
* **Interação com o Usuário:** Interface de linha de comando (CLI) para interação e teste das funcionalidades.
* **Visualização da Árvore:** Implementa um método para exibir a estrutura hierárquica da AVL, mostrando o ISBN e o Fator de Balanceamento.
* **Linguagem:** Java.

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