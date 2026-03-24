import ProjetoRpgBattleEngine.*;

import java.sql.SQLOutput;
import java.util.*;

void main() {
    Inimigo goblin = new Inimigo("Goblin", 50, 50, 5, 0, 0, 34);
    goblin.aprenderAcao(new AtaqueFisico(5));

    String nomeClasse;

    Jogador jogador;

    PersonagemDAO banco = new PersonagemDAO();

    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    int acoesGoblin = goblin.acoes.size();

    System.out.println("-------------------- Escolha sua Classe --------------------");
    System.out.println("1 - Guerreiro");
    System.out.println("2 - Mago");

    int classeEscolhida = scan.nextInt();
    if(classeEscolhida == 1){
        nomeClasse = "Guerreiro";
    }else{
        nomeClasse = "Mago";
    }

    jogador = banco.buscarPorNome(nomeClasse);
    boolean criarNovo = false;

    if (jogador != null) {
        System.out.println("Já existe um progresso salvo, deseja carregar? S/N");
        scan.nextLine();
        String continuar = scan.nextLine();

        if (continuar.equals("S")) {
            if (nomeClasse.equals("Guerreiro")){
                jogador.aprenderAcao(new AtaqueFisico(5));
            }
            if (nomeClasse.equals("Mago")){
                jogador.aprenderAcao(new Magia("Bola de Fogo", 20));
            }
        } else {
            criarNovo = true;
        }
    } else {
        criarNovo = true;
    }

    if (criarNovo) {
        switch (classeEscolhida) {
            case 1 -> {
                jogador = new Jogador("Guerreiro", 200, 200, 20, 0, 0, 1, 0);
                jogador.aprenderAcao(new AtaqueFisico(5));
            }
            case 2 -> {
                jogador = new Jogador("Mago", 70, 70, 5, 50, 50, 1, 0);
                jogador.aprenderAcao(new Magia("Bola de Fogo", 20));
            }
            default -> jogador = new Jogador("Teste", 1, 1, 1, 1, 1, 1, 0);
        }
        banco.deletarPersonagem(nomeClasse);
        banco.salvar(jogador);
    }




    while (!jogador.morto() && !goblin.morto()) {
        System.out.println("\n-------------------- Seu Turno --------------------");
        jogador.listarAcoes();
        int escolha = scan.nextInt();
        System.out.println("");
        while(escolha < 0 || escolha >= jogador.acoes.size())
        {
            System.out.println("Número Inválido, digite um número que existe no índice: ");
            jogador.listarAcoes();
            escolha = scan.nextInt();
        }
        jogador.usarAcao(escolha, goblin);

        if (goblin.morto()) {
            System.out.println("O Goblin foi derrotado!");
            break;
        }

        pausar(scan);

        System.out.println("\n-------------------- Turno do Goblin --------------------");
        int goblinAcao = rand.nextInt(acoesGoblin);
        goblin.usarAcao(goblinAcao, jogador);
    }

    System.out.println("Fim da Batalha");
    if (jogador.morto()) {
        System.out.println("Personagem morto, save apagado");
        banco.deletarPersonagem(jogador.getNome());
    }else{
        System.out.println("Progresso salvo");
        jogador.ganharXp(goblin.getXpEntregue());
        banco.salvarProgresso(jogador);
        exibirEstatisticas(jogador, goblin);
    }
}

public static void pausar(Scanner scan) {
    System.out.println("\n[ Pressione ENTER para continuar... ]");
    scan.nextLine();
    scan.nextLine();
}

public static void exibirEstatisticas(Jogador j, Inimigo inimigo) {
    System.out.println("\n==================================================");
    System.out.println("             RESUMO DA AVENTURA                     ");
    System.out.println("==================================================");
    System.out.println(" Herói: " + j.getNome());
    System.out.println(" Nível Atual: " + j.getLevel());
    System.out.println(" XP Atual: " + j.getXpAtual() + " / 100");

    System.out.print(" Vida Final: [" + j.getVidaAtual() + "/" + j.getVidaMaxima() + "] ");

    System.out.println("\n--------------------------------------------------");

    System.out.println(" Resultado: VITÓRIA sobre o " + inimigo.getNome());
    System.out.println(" Recompensa: +" + inimigo.getXpEntregue() + " XP obtidos.");

    System.out.println("==================================================\n");
}