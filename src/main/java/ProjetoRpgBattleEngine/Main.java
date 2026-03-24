import ProjetoRpgBattleEngine.*;

import java.sql.SQLOutput;
import java.util.*;

void main() {
    Personagem goblin = new Personagem("Goblin", 50, 50, 5, 0, 0);
    goblin.aprenderAcao(new AtaqueFisico(5));

    String nomeClasse;

    Personagem personagem;

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

    personagem = banco.buscarPorNome(nomeClasse);

    String continuar;

    if(personagem != null) {
        System.out.println("Já existe um progresso salvo, deseja carregar?  S/N");
        scan.nextLine();
        continuar = scan.nextLine();
        if (continuar.equals("S")) {
            if (nomeClasse.equals("Guerreiro")) {
                personagem.aprenderAcao(new AtaqueFisico(5));
            }
            if (nomeClasse.equals("Mago")) {
                personagem.aprenderAcao(new Cura(5));
            }
        } else {
            switch (classeEscolhida) {
                case 1 -> {
                    personagem = new Personagem("Guerreiro", 200, 200, 20, 0, 0);
                    personagem.aprenderAcao(new AtaqueFisico(5));
                }
                case 2 -> {
                    personagem = new Personagem("Mago", 70, 70, 5, 50, 50);
                    personagem.aprenderAcao(new Cura(5));
                }
                default -> personagem = new Personagem("Teste", 1, 1, 1, 1, 1);
            }
            banco.salvar(personagem);
        }
    }else if(personagem == null){
        switch (classeEscolhida) {
            case 1 -> {
                personagem = new Personagem("Guerreiro", 200, 200, 20, 0, 0);
                personagem.aprenderAcao(new AtaqueFisico(5));
            }
            case 2 -> {
                personagem = new Personagem("Mago", 70, 70, 5, 50, 50);
                personagem.aprenderAcao(new Cura(5));
            }
            default -> personagem = new Personagem("Teste", 1, 1, 1, 1, 1);
        }
        banco.salvar(personagem);
    }




    while (!personagem.morto() && !goblin.morto()) {
        System.out.println("\n-------------------- Seu Turno --------------------");
        personagem.listarAcoes();
        int escolha = scan.nextInt();
        System.out.println("");
        while(escolha < 0 || escolha >= personagem.acoes.size())
        {
            System.out.println("Número Inválido, digite um número que existe no índice: ");
            personagem.listarAcoes();
            escolha = scan.nextInt();
        }
        personagem.usarAcao(escolha, goblin);

        if (goblin.morto()) {
            System.out.println("O Goblin foi derrotado!");
            break;
        }

        pausar(scan);

        System.out.println("\n-------------------- Turno do Goblin --------------------");
        int goblinAcao = rand.nextInt(acoesGoblin);
        goblin.usarAcao(goblinAcao, personagem);
    }

    System.out.println("Fim da Batalha");
    banco.salvarProgresso(personagem);
}

public static void pausar(Scanner scan) {
    System.out.println("\n[ Pressione ENTER para continuar... ]");
    scan.nextLine();
    scan.nextLine();
}