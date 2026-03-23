import ProjetoRpgBattleEngine.*;
import java.util.*;

void main() {
    Personagem joao = new Personagem("Joao", 100, 100, 5, 20, 20);
    Personagem goblin = new Personagem("Goblin", 50, 50, 5, 20, 20);

    joao.aprenderAcao(new AtaqueFisico(10));
    joao.aprenderAcao(new Cura(10));
    goblin.aprenderAcao(new Cura(20));
    goblin.aprenderAcao(new AtaqueFisico(5));

    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    int acoesGoblin = goblin.acoes.size();

    while (!joao.morto() && !goblin.morto()) {System.out.print("\033[H\033[2J");
        System.out.println("\n-------------------- Turno do Joao --------------------");
        joao.listarAcoes();
        int escolha = scan.nextInt();
        System.out.println("");
        while(escolha < 0 || escolha >= joao.acoes.size())
        {
            System.out.println("Número Inválido, digite um número que existe no índice: ");
            joao.listarAcoes();
            escolha = scan.nextInt();
        }
        joao.usarAcao(escolha, goblin);

        if (goblin.morto()) {
            System.out.println("O Goblin foi derrotado!");
            break;
        }

        pausar(scan);

        System.out.println("\n-------------------- Turno do Goblin --------------------");
        int goblinAcao = rand.nextInt(acoesGoblin);
        goblin.usarAcao(goblinAcao, joao);
    }

    System.out.println("Fim da Batalha");
}

public static void pausar(Scanner scan) {
    System.out.println("\n[ Pressione ENTER para continuar... ]");
    scan.nextLine();
    scan.nextLine();
}