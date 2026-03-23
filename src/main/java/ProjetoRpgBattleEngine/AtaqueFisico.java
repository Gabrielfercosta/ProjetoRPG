package ProjetoRpgBattleEngine;

public class AtaqueFisico implements Acao {
    private int danoBase;

    public AtaqueFisico(int dano) {
        this.danoBase = dano;
    }

    @Override
    public String getNome() {
        return "Ataque de Espada";
    }

    public int getCusto(){
        return 0;
    }

    @Override
    public String getDescricao(Personagem executor) {
        int total = this.danoBase + executor.getPoder();
        return executor.getNome() + " causou " + total + " de dano";
    }

    @Override
    public void executar(Personagem alvo, Personagem executor) {
        int danoTotal = executor.getPoder() + this.danoBase;
        alvo.receberDano(danoTotal);
    }
}