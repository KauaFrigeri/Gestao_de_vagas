package br.com.kauafrigeri.gestao_de_vagas.modules;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("O usuário ja existe");
    }
}
