/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosim
 */
public class UtilitarioMusica {

    private final List<Musica> musicas = new ArrayList<>();

    public UtilitarioMusica() {
        musicas.add(new Musica("01.mp3", "The Lazy Song", "1.According to or excerpt from Indian music what is the correct phrase?", "1-Cause in my castle I’m the freaking man", "2- Cause in my castle I'm he coment wall man"));
        musicas.add(new Musica("02.mp3", "You'll Never Know", "If there to some other way is prove that i love you choose the answer that corrects the excerpt above ", "resposta 2", "resposta errda"));
        musicas.add(new Musica("03.mp3", "The Lazy Song", "pergunta 3", "resposta 3", "resposta errda"));
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public Musica getMusica(int indice) {
        return this.getMusicas().get(indice);
    }
    
    public StringBuilder calculaPontuacao() {
        int totalAcertos = 0;
        StringBuilder pontuacao = new StringBuilder();
        StringBuilder resultados = new StringBuilder();
        
        for(Musica musica : this.getMusicas()) {
            if(musica.seaRepostaEstaCorreta()) {
                totalAcertos = totalAcertos + 1;
                resultados.append(musica.getNome() + " ----> acertou" );
            } else {
                resultados.append(musica.getNome() + " ----> errou" );
            }
            resultados.append("\n");
        }
        
        pontuacao.append("Total de acertos: " + (int)((float)totalAcertos/this.getMusicas().size()*100f) + "%");
        pontuacao.append("\n");
        pontuacao.append(resultados);        
        
        return pontuacao;
    }
}
