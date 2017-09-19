package com.company.server;

public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();
    //void setNick(String login, String pass,String nick);

}

