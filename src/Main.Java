import arvore.ArvoreAVLGeneric;
import modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    private static ArvoreAVLGeneric<modelo.Livro, String> arvoreLivros = new ArvoreAVLGeneric<>(modelo.Livro::getIsbn);
    private static ArvoreAVLGeneric<modelo.Autor, Integer> arvoreAutores = new ArvoreAVLGeneric<>(modelo.Autor::getId);
    private static ArvoreAVLGeneric<modelo.Usuario, Integer> arvoreUsuarios = new ArvoreAVLGeneric<>(modelo.Usuario::getMatricula);
    private static ArvoreAVLGeneric<modelo.Categoria, Integer> arvoreCategorias = new ArvoreAVLGeneric<>(modelo.Categoria::getId);
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    private static int proximoIdAutor = 1;
    private static int proximoIdCategoria = 1;

    public static void main(String[] args) {
        Autor a1 = new Autor(proximoIdAutor++, "Machado de Assis", "Brasileiro"); arvoreAutores.inserir(a1);
        Autor a2 = new Autor(proximoIdAutor++, "Clarice Lispector", "Brasileira"); arvoreAutores.inserir(a2);
        Categoria c1 = new Categoria(proximoIdCategoria++, "Clássico"); arvoreCategorias.inserir(c1);
        Categoria c2 = new Categoria(proximoIdCategoria++, "Ficção Científica"); arvoreCategorias.inserir(c2);
        arvoreUsuarios.inserir(new Usuario(101, "Alice Silva", "alice@lib.com"));
        arvoreUsuarios.inserir(new Usuario(102, "Bruno Costa", "bruno@lib.com"));

        arvoreLivros.inserir(new Livro("978-857", "Dom Casmurro", 1899, a1, c1));
        arvoreLivros.inserir(new Livro("978-019", "O Alienista", 1882, a1, c1));
        arvoreLivros.inserir(new Livro("978-052", "A Peste", 1947, new Autor(proximoIdAutor++, "Albert Camus", "Francês"), c1));
        arvoreLivros.inserir(new Livro("978-000", "Cem Anos de Solidão", 1967, new Autor(proximoIdAutor++, "Gabriel García Márquez", "Colombiano"), c1));
        
        System.out.println("Sistema de Gerenciamento de Biblioteca (AVL)");
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
                        case 2: menuUsuarios(); break; 
                        case 3: menuAutores(); break; 
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
        while (opcao != 4) {
            System.out.println("\n--- Gerenciar Empréstimos ---");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Devolver Livro");
            System.out.println("3. Listar Empréstimos");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: realizarEmprestimo(); break;
                    case 2: devolverLivro(); break;
                    case 3: listarEmprestimos(); break;
                    case 4: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
    
    
    private static void inserirLivro() {
        System.out.println("\n--- Inserir Livro ---");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        if (arvoreLivros.buscar(isbn) != null) {
            System.out.println("Erro: Livro com este ISBN já cadastrado.");
            return;
        }
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(scanner.nextLine());

          if (arvoreAutores.listarTodos().isEmpty() || arvoreCategorias.listarTodos().isEmpty()) {
               System.out.println("Cadastre Autores e Categorias primeiro.");
               return;
           }

              Autor autorSelecionado = arvoreAutores.listarTodos().get(0);
              Categoria categoriaSelecionada = arvoreCategorias.listarTodos().get(0);

           arvoreLivros.inserir(new Livro(isbn, titulo, ano, autorSelecionado, categoriaSelecionada));
    }
    
    private static void buscarLivro() {
        System.out.println("\n--- Buscar Livro ---");
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        
        Livro encontrado = arvoreLivros.buscar(isbn);
        if (encontrado != null) {
            System.out.println("Livro Encontrado:\n" + encontrado);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
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
                    System.out.println("Ano inválido. Manterá o anterior.");
                }
            }
            System.out.println("Livro alterado com sucesso!\n" + livro);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
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


    private static void menuAutores() {
        int opcao = -1;
        while (opcao != 5) {
            System.out.println("\n--- Gerenciar Autores (AVL) ---");
            System.out.println("1. Inserir Autor (C)");
            System.out.println("2. Listar Autores (R)");
            System.out.println("3. Alterar Autor (U)");
            System.out.println("4. Remover Autor (D)");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: inserirAutor(); break;
                    case 2: listarAutores(); break;
                    case 3: alterarAutor(); break;
                    case 4: removerAutor(); break;
                    case 5: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    private static void inserirAutor() {
        System.out.println("\n--- Inserir Autor ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        Autor novo = new Autor(proximoIdAutor++, nome, nacionalidade);
        arvoreAutores.inserir(novo);
    }

    private static void listarAutores() {
        System.out.println("\n--- Lista de Autores ---");
        for (Autor a : arvoreAutores.listarTodos()) {
            System.out.println(a);
        }
    }

    private static void alterarAutor() {
        System.out.println("\n--- Alterar Autor ---");
        System.out.print("ID do Autor: ");
        int id = Integer.parseInt(scanner.nextLine());
        Autor autor = arvoreAutores.buscar(id);
        if (autor == null) {
            System.out.println("Autor não encontrado.");
            return;
        }
        System.out.print("Novo Nome (vazio para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            arvoreAutores.remover(id);
            Autor novo = new Autor(id, nome, autor.getNacionalidade());
            arvoreAutores.inserir(novo);
        }
        System.out.print("Nova Nacionalidade (vazio para manter): ");
        String nac = scanner.nextLine();
        if (!nac.isEmpty()) {
            arvoreAutores.remover(id);
            Autor novo = new Autor(id, autor.getNome(), nac);
            arvoreAutores.inserir(novo);
        }
        System.out.println("Autor alterado (recriado). Consulte a lista.");
    }

    private static void removerAutor() {
        System.out.println("\n--- Remover Autor ---");
        System.out.print("ID do Autor a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        arvoreAutores.remover(id);
    }


    private static void inserirCategoria() {
        System.out.println("\n--- Inserir Categoria ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        Categoria c = new Categoria(proximoIdCategoria++, nome);
        arvoreCategorias.inserir(c);
        System.out.println("Categoria inserida: " + c.getNome());
    }

    private static void listarCategorias() {
        System.out.println("\n--- Lista de Categorias ---");
        for (Categoria c : arvoreCategorias.listarTodos()) System.out.println(c);
    }

    private static void alterarCategoria() {
        System.out.println("\n--- Alterar Categoria ---");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Categoria cat = arvoreCategorias.buscar(id);
        if (cat == null) { System.out.println("Categoria não encontrada."); return; }
        System.out.print("Novo Nome (vazio para manter): ");
        String novo = scanner.nextLine();
        if (!novo.isEmpty()) {
            cat.setNome(novo);
            System.out.println("Categoria alterada.");
        }
    }

    private static void removerCategoria() {
        System.out.println("\n--- Remover Categoria ---");
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        arvoreCategorias.remover(id);
        System.out.println("Se existia, categoria removida.");
    }


    private static void menuUsuarios() {
        int opcao = -1;
        while (opcao != 5) {
            System.out.println("\n--- Gerenciar Usuários ---");
            System.out.println("1. Inserir Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Alterar Usuário");
            System.out.println("4. Remover Usuário");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: inserirUsuario(); break;
                    case 2: listarUsuarios(); break;
                    case 3: alterarUsuario(); break;
                    case 4: removerUsuario(); break;
                    case 5: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) { System.out.println("Entrada inválida."); }
        }
    }

    private static void inserirUsuario() {
        System.out.println("\n--- Inserir Usuário ---");
        System.out.print("Matrícula (número): ");
        int mat = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        arvoreUsuarios.inserir(new Usuario(mat, nome, email));
        System.out.println("Usuário cadastrado.");
    }

    private static void listarUsuarios() {
        System.out.println("\n--- Lista de Usuários ---");
        for (Usuario u : arvoreUsuarios.listarTodos()) System.out.println(u);
    }

    private static void alterarUsuario() {
        System.out.println("\n--- Alterar Usuário ---");
        System.out.print("Matrícula: ");
        int mat = Integer.parseInt(scanner.nextLine());
        Usuario u = arvoreUsuarios.buscar(mat);
        if (u == null) { System.out.println("Usuário não encontrado."); return; }
        System.out.print("Novo Nome (vazio para manter): ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email (vazio para manter): ");
        String email = scanner.nextLine();
        if (!nome.isEmpty()) u.setNome(nome);
        if (!email.isEmpty()) u.setEmail(email);
        System.out.println("Usuário alterado.");
    }

    private static void removerUsuario() {
        System.out.println("\n--- Remover Usuário ---");
        System.out.print("Matrícula: ");
        int mat = Integer.parseInt(scanner.nextLine());
        arvoreUsuarios.remover(mat);
        System.out.println("Se existia, usuário removido.");
    }


    private static void listarEmprestimos() {
        System.out.println("\n--- Empréstimos Registrados ---");
        for (Emprestimo e : emprestimos) System.out.println(e);
    }


    private static void realizarEmprestimo() {
        System.out.println("\n--- Realizar Empréstimo ---");
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();
        System.out.print("Matrícula do Usuário (101/102): ");
        int matricula = Integer.parseInt(scanner.nextLine());
        
        Livro livro = arvoreLivros.buscar(isbn);
        Usuario usuario = arvoreUsuarios.buscar(matricula);

        if (livro == null || usuario == null) {
            System.out.println("Livro ou Usuário não encontrado.");
            return;
        }
        
        if (livro.isEmprestado()) {
            System.out.println("Livro já emprestado.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(7);
        Emprestimo novoEmprestimo = new Emprestimo(livro, usuario, hoje, devolucaoPrevista);
        
        livro.setEmprestado(true); 
        emprestimos.add(novoEmprestimo);
        
        System.out.println("Empréstimo registrado:\n" + novoEmprestimo);
    }
    
    private static void devolverLivro() {
        System.out.println("\n--- Devolver Livro ---");
        System.out.print("ISBN do Livro a ser devolvido: ");
        String isbn = scanner.nextLine();
        
        Livro livro = arvoreLivros.buscar(isbn);
        
        if (livro == null || !livro.isEmprestado()) {
            System.out.println("Livro não encontrado ou não está emprestado.");
            return;
        }
        
        livro.setEmprestado(false); 
        
        System.out.println("Livro '" + livro.getTitulo() + "' devolvido com sucesso.");
    }
}