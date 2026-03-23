package ProjetoRpgBattleEngine;

public class Cura implements Acao{
    private int poderCura;

    public int getCusto(){
        return 10;
    }

    public Cura(int poderCura){
        this.poderCura = poderCura;
    }

    public String getNome(){
        return "Magia de Cura";
    }

    @Override
    public String getDescricao(Personagem executor) {
        return executor.getNome() + " curou " + this.poderCura + " de vida";
    }

    public void executar(Personagem alvo, Personagem executor){
            executor.receberCura(this.poderCura);
    }
}
