package ProjetoRpgBattleEngine;

public class Magia implements Acao {
    private String nome;
    private int dano;
    public Magia(String nome, int dano) {
        this.nome = nome;
        this.dano = dano;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getDescricao(Personagem executor) {
        int total = this.dano + executor.getPoder();
        return executor.getNome() + " causou " + total + " de dano";
    }

    @Override
    public void executar(Personagem alvo, Personagem executor) {
        if (executor.getManaAtual() >= getCusto()) {
            executor.setManaAtual(executor.getManaAtual() - getCusto());
            int danoTotal = executor.getPoder() + this.dano;
            alvo.receberDano(danoTotal);
            System.out.println(executor.getNome() + " lançou " + nome + "!");
        } else {
            System.out.println("Mana insuficiente para lançar " + nome + "!");
        }
    }

    @Override
    public int getCusto() {
        return 25;
    }
}