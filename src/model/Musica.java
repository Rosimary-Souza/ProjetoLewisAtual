/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rosim
 */
public class Musica {
    private String nomeArquivo;
    private String nome;
    private String pergunta;
    private String resposta;
    private String respostaErrada;
    private boolean aRepostaEstaCorreta;

    public Musica(String nomeArquivo, String nome, String pergunta, String resposta, String respostaErrada) {
        this.nomeArquivo = nomeArquivo;
        this.nome = nome;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.respostaErrada = respostaErrada;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNome() {
        return nome;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public String getRespostaErrada() {
        return respostaErrada;
    }

    public boolean seaRepostaEstaCorreta() {
        return aRepostaEstaCorreta;
    }

    public void setaRepostaEstaCorreta(boolean aRepostaEstaCorreta) {
        this.aRepostaEstaCorreta = aRepostaEstaCorreta;
    }
    
    
    
    
}
