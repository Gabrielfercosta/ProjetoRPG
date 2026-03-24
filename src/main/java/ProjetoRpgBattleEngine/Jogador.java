package ProjetoRpgBattleEngine;

public class Jogador extends Personagem{
    private int level = 1;
    private int xpAtual = 0;

    public Jogador(String nome, int vidaAtual, int vidaMaxima, int poder, int manaAtual, int manaMaxima, int level, int xpAtual) {
        super(nome, vidaAtual, vidaMaxima, poder, manaAtual, manaMaxima);
        this.level = level;
        this.xpAtual = xpAtual;
    }

    public void ganharXp(int quantidade){
        this.xpAtual += quantidade;
        if (this.xpAtual >= 100) {
            this.level++;
            this.xpAtual = 0;
            System.out.println("Você subiu de nível, você agora é nível " + level);
        }
    }

    public int getLevel() {
        return level;
    }

    public int getXpAtual() {
        return xpAtual;
    }
}
