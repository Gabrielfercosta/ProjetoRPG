package ProjetoRpgBattleEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Personagem {
    private String nome;
    private int vidaAtual;
    private int vidaMaxima;
    private int poder;
    private int manaAtual;
    private int manaMaxima;
    public List<Acao> acoes = new ArrayList<>();

    public Personagem(String nome, int vidaAtual, int vidaMaxima, int poder, int manaAtual, int manaMaxima) {
        if (vidaAtual > vidaMaxima || vidaAtual < 0) {
            throw new IllegalArgumentException("Vida atual (" + vidaAtual + ") é inválida");
        }
        this.nome = nome;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.poder = poder;
        this.manaAtual = manaAtual;
        this.manaMaxima = manaMaxima;
    }

    public void aprenderAcao(Acao novaAcao) {
        this.acoes.add(novaAcao);
    }

    public void listarAcoes() {
        IntStream.range(0, acoes.size())
                .forEach(i -> System.out.println(i + " - " + acoes.get(i).getNome()));
    }
    //Lambda (IntStream cria uma sequência de números no range de 0 até a quantidade de ações, imprimindo-as no for-each

    public void receberDano(int dano) {
        this.vidaAtual = Math.max(0, this.vidaAtual - dano);
    }
    //usa o math max para pegar o valor que for maior, 0 ou vida atual - dano, para impedir valores menores que 0

    public void receberCura(int poderCura) {
        this.vidaAtual = Math.min(this.vidaMaxima, this.vidaAtual + poderCura);
    }

    public boolean morto() {
        return this.vidaAtual == 0;
    }

    public void usarAcao(int indice, Personagem alvo) {
        Acao acaoSelecionada = acoes.get(indice);
        if(acaoSelecionada.getCusto() > getManaAtual()){
            System.out.println("Você tenta usar sua magia mas não possui mana pra isso");
        }else{
            this.manaAtual -= acaoSelecionada.getCusto();
            acaoSelecionada.executar(alvo, this);
            // usa a interface executar para executar a acao selecionada indicando o alvo e indicando a si mesmo como executor utilizando o "this"
            System.out.println("Ação: " + acaoSelecionada.getNome());
            System.out.println(acaoSelecionada.getDescricao(this));
        }
        System.out.println("[ " + this.nome + ": " + this.vidaAtual + "/" + this.vidaMaxima + " HP  |  " + alvo.getNome() + ": " + alvo.getVidaAtual() + "/" + alvo.getVidaMaxima() + " HP ]"  );
        System.out.println("[ " + this.nome + ": " + this.manaAtual + "/" + this.manaMaxima + " Mana  |  " + alvo.getNome() + ": " + alvo.getManaAtual() + "/" + alvo.getManaMaxima() + " Mana ]"  );
        System.out.println("");
    }
    public String getNome() {
        return nome;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public int getVidaMaxima(){
        return vidaMaxima;
    }
    public int getPoder() {
        return poder;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = manaAtual;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
    }
}