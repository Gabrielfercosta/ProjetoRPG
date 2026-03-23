package ProjetoRpgBattleEngine;

public interface Acao {
    String getNome();
    String getDescricao(Personagem executor);
    void executar(Personagem alvo, Personagem executor);
    int getCusto();
}