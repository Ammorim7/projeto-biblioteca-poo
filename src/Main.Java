import arvore.ArvoreAVL;
import modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    private static ArvoreAVL arvoreLivros = new ArvoreAVL();
    // Armazenamento simples para as outras entidades (para fins de exemplo)
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Autor> autores = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoIdAutor = 1;
    private static int proximoIdCategoria = 1;

    public static void main(String[] args) {
        // --- População Inicial de Dados (para teste) ---
        Autor a1 = new Autor(proximoIdAutor++, "Machado de Assis", "Brasileiro"); autores.add(a1);
        Autor a2 = new Autor(proximoIdAutor++, "Clarice Lispector", "Brasileira"); autores.add(a2);
        Categoria c1 = new Categoria(proximoIdCategoria++, "Clássico"); categorias.add(c1);
        Categoria c2 = new Categoria(proximoIdCategoria++, "Ficção Científica"); categorias.add(c2);
        usuarios.add(new Usuario(101, "Alice Silva", "alice@lib.com"));
        usuarios.add(new Usuario(102, "Bruno Costa", "bruno@lib.com"));

        arvoreLivros.inserir(new Livro("978-8578882746", "Dom Casmurro", 1899, a1, c1));
        arvoreLivros.inserir(new Livro("978-0199535729", "O Alienista", 1882, a1, c1));
        arvoreLivros.inserir(new Livro("978-0520288285", "A Peste", 1947, new Autor(proximoIdAutor++, "Albert Camus", "Francês"), c1));
        arvoreLivros.inserir(new Livro("978-0007447990", "Cem Anos de Solidão", 1967, new Autor(proximoIdAutor++, "Gabriel García Márquez", "Colombiano"), c1));
        
        System.out.println("📚 Sistema de Gerenciamento de Biblioteca (AVL)");
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        int opcao = -1;
        while (opcao != 6) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Livros (CRUD na Árvore AVL)");
            System.out.println("2. Gerenciar Usuários (CRUD)");
            System.out.println("3. Gerenciar Autores (CRUD)");
            System.out.println("4. Gerenciar Empréstimos");
            System.out.println("5. Visualizar Estrutura da Árvore AVL");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: menuLivros(); break;
                    case 2: System.out.println("Implementar CRUD de Usuários aqui..."); break; 
                    case 3: System.out.println("Implementar CRUD de Autores aqui..."); break; 
                    case 4: menuEmprestimos(); break;
                    case 5: visualizarArvore(); break;
                    case 6: System.out.println("Saindo do sistema. Até logo!"); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
    
    // --- Menus de Entidades ---

    private static void menuLivros() {
        int opcao = -1;
        while (opcao != 5) {
            System.out.println("\n--- Gerenciar Livros (AVL CRUD) ---");
            System.out.println("1. Inserir Livro (C)");
            System.out.println("2. Buscar Livro (R)");
            System.out.println("3. Alterar Livro (U)");
            System.out.println("4. Remover Livro (D)");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: inserirLivro(); break;
                    case 2: buscarLivro(); break;
                    case 3: alterarLivro(); break;
                    case 4: removerLivro(); break;
                    case 5: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    private static void menuEmprestimos() {
        int opcao = -1;
        while (opcao != 3) {
            System.out.println("\n--- Gerenciar Empréstimos ---");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Devolver Livro");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: realizarEmprestimo(); break;
                    case 2: devolverLivro(); break;
                    case 3: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
    
    // --- Funções CRUD Livros (AVL) ---
    
    private static void inserirLivro() {
        System.out.println("\n--- Inserir Livro ---");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        if (arvoreLivros.buscar(isbn) != null) {
            System.out.println("❌ Erro: Livro com este ISBN já cadastrado.");
            return;
        }
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(scanner.nextLine());

        // Para simplificar, usamos o primeiro autor/categoria. O ideal seria uma busca/seleção.
        if (autores.isEmpty() || categorias.isEmpty()) {
             System.out.println("⚠️ Cadastre Autores e Categorias primeiro.");
             return;
        }

        arvoreLivros.inserir(new Livro(isbn, titulo, ano, autores.get(0), categorias.get(0)));
    }
    
    private static void buscarLivro() {
        System.out.println("\n--- Buscar Livro ---");
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        
        Livro encontrado = arvoreLivros.buscar(isbn);
        if (encontrado != null) {
            System.out.println("✅ Livro Encontrado:\n" + encontrado);
        } else {
            System.out.println("❌ Livro com ISBN " + isbn + " não encontrado.");
        }
    }
    
    private static void alterarLivro() {
        System.out.println("\n--- Alterar Livro (Update) ---");
        System.out.print("Digite o ISBN do livro a ser alterado: ");
        String isbn = scanner.nextLine();
        
        Livro livro = arvoreLivros.buscar(isbn);
        if (livro != null) {
            System.out.println("Livro atual: " + livro.getTitulo() + " (" + livro.getAnoPublicacao() + ")");
            System.out.print("Novo Título (deixe vazio para manter): ");
            String novoTitulo = scanner.nextLine();
            
            if (!novoTitulo.isEmpty()) {
                livro.setTitulo(novoTitulo);
            }
            
            System.out.print("Novo Ano de Publicação (deixe vazio para manter): ");
            String novoAnoStr = scanner.nextLine();
            if (!novoAnoStr.isEmpty()) {
                try {
                    livro.setAnoPublicacao(Integer.parseInt(novoAnoStr));
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Ano inválido. Manterá o anterior.");
                }
            }
            System.out.println("✅ Livro alterado com sucesso!\n" + livro);
        } else {
            System.out.println("❌ Livro com ISBN " + isbn + " não encontrado.");
        }
    }
    
    private static void removerLivro() {
        System.out.println("\n--- Remover Livro ---");
        System.out.print("Digite o ISBN do livro a ser removido: ");
        String isbn = scanner.nextLine();
        
        arvoreLivros.remover(isbn);
    }
    
    private static void visualizarArvore() {
        System.out.println("\n--- Visualização Hierárquica da Árvore AVL (ISBN) ---");
        arvoreLivros.visualizarEstrutura();
    }

    // --- Funções Gerenciamento de Empréstimos ---

    private static void realizarEmprestimo() {
        System.out.println("\n--- Realizar Empréstimo ---");
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();
        System.out.print("Matrícula do Usuário (101/102): ");
        int matricula = Integer.parseInt(scanner.nextLine());
        
        Livro livro = arvoreLivros.buscar(isbn);
        Usuario usuario = usuarios.stream().filter(u -> u.getMatricula() == matricula).findFirst().orElse(null);

        if (livro == null || usuario == null) {
            System.out.println("❌ Livro ou Usuário não encontrado.");
            return;
        }
        
        if (livro.isEmprestado()) {
            System.out.println("❌ Livro já emprestado.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(7);
        Emprestimo novoEmprestimo = new Emprestimo(livro, usuario, hoje, devolucaoPrevista);
        
        livro.setEmprestado(true); // Marca o livro como emprestado
        emprestimos.add(novoEmprestimo);
        
        System.out.println("✅ Empréstimo registrado:\n" + novoEmprestimo);
    }
    
    private static void devolverLivro() {
        System.out.println("\n--- Devolver Livro ---");
        System.out.print("ISBN do Livro a ser devolvido: ");
        String isbn = scanner.nextLine();
        
        Livro livro = arvoreLivros.buscar(isbn);
        
        if (livro == null || !livro.isEmprestado()) {
            System.out.println("❌ Livro não encontrado ou não está emprestado.");
            return;
        }
        
        livro.setEmprestado(false); // Marca o livro como devolvido
        // Na prática, o empréstimo seria removido ou marcado como 'concluído'
        
        System.out.println("✅ Livro '" + livro.getTitulo() + "' devolvido com sucesso.");
    }
}