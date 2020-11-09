package escola;

import dao.AlunoDAO;
import java.util.Scanner;
import modelo.Aluno;

public class Escola {

    public static void main(String[] args) {

        // Leitor para tipo String
        Scanner leitor = new Scanner(System.in);
        // Leitor para tipo Int
        Scanner input = new Scanner(System.in);

        Aluno aluno = new Aluno();
        AlunoDAO dao = new AlunoDAO();
        dao.iniciar();

        String opcao;

        do {
            opcao = Menu();

            switch (opcao) {
                case "A":
                    dao.iniciar();
                    System.out.print("\nInforme o Nome do Aluno: ");
                    aluno.setNome(leitor.nextLine());
                    System.out.print("Informe o Curso: ");
                    aluno.setCurso(leitor.nextLine());
                    System.out.println("");
                    dao.inserir(aluno);
                    dao.encerrar();
                    break;
                case "B":
                    dao.iniciar();
                    System.out.print("\nInforme o ID do Aluno a ser Removido: ");
                    dao.remover(input.nextLong());
                    dao.encerrar();
                    break;
                case "C":
                    dao.iniciar();
                    System.out.print("\nInforme o ID do Aluno a ser Pesquisado: ");
                    Aluno alunoID = dao.consultarId(input.nextLong());
                    if (alunoID == null) {
                        dao.encerrar();
                        break;
                    } else {
                        System.out.println("\nNome do Aluno: " + alunoID.getNome()
                                + "\nNome do Curso: " + alunoID.getCurso());
                        dao.encerrar();
                    }
                    break;
                case "D":
                    dao.iniciar();
                    System.out.print("\nInforme o ID (Caso não possua, digite zero): ");
                    Long id = input.nextLong();
                    System.out.print("Informe o Nome do Aluno: ");
                    aluno.setNome(leitor.nextLine());
                    System.out.print("Informe o Curso: ");
                    aluno.setCurso(leitor.nextLine());
                    System.out.println("");
                    dao.atualizarPorID(id, aluno);
                    //dao.atualizar(aluno);
                    dao.encerrar();
                    break;
                case "E":
                    dao.iniciar();
                    System.out.print("\nInforme o ID do Aluno a ser Pesquisado: ");
                    Aluno alunoIDJPQL = dao.consultarPorJPQL(input.nextLong());
                    if (alunoIDJPQL == null) {
                        dao.encerrar();
                        break;
                    } else {
                        System.out.println("\nNome do Aluno: " + alunoIDJPQL.getNome()
                                + "\nNome do Curso: " + alunoIDJPQL.getCurso());
                        dao.encerrar();
                    }
                    break;
            }
        } while (!opcao.equals("F"));
    }

    public static String Menu() {
        Scanner input = new Scanner(System.in);
        String escolha;

        System.out.println("\n\t\tMenu Escola"
                + "\n\nA) Inserir um novo Aluno"
                + "\nB) Remover um Aluno"
                + "\nC) Pesquisar por ID"
                + "\nD) Atualizar Aluno"
                + "\nE) Pesquisar por ID via JPQL"
                + "\nF) Sair");

        System.out.print("\nEscolha: ");
        escolha = input.next().toUpperCase().trim();

        while (!(escolha.equals("A") || escolha.equals("B") || escolha.equals("C") || escolha.equals("D") || escolha.equals("E") || escolha.equals("F"))) {
            System.out.print("\nEscolha um opção válida!: ");
            escolha = input.next().toUpperCase().trim();
        }
        return escolha;
    }
}
