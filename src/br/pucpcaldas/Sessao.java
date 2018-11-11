/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

/**
 *
 * @author Elton
 */
public class Sessao {
    
    private static Sessao sessao;
    private String login;
    private Double nota;
    
    public static Sessao getSessao() {
        if(sessao == null)
            sessao = new Sessao();
        return sessao;
    }
    
    public static void finalizar() {
        sessao = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
    
    
}
