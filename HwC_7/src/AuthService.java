/**
 * Created by Админ on 15.09.2017.
 */
public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();
    //void setNick(String login, String pass,String nick);

}

