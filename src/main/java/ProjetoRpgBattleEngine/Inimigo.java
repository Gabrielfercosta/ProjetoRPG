package ProjetoRpgBattleEngine;

public class Inimigo extends Personagem {
    private int xpEntregue;

    public Inimigo(String nome, int vidaAtual, int vidaMaxima, int poder, int manaAtual, int manaMaxima, int xpEntregue) {
        super(nome, vidaAtual, vidaMaxima, poder, manaAtual, manaMaxima);
        this.xpEntregue = xpEntregue;
    }

    public int getXpEntregue() { return xpEntregue; }
}