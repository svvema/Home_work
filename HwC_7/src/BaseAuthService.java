import java.util.ArrayList;

/**
 * Created by Админ on 15.09.2017.
 */
public class BaseAuthService implements AuthService {
    private class Entry {
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private ArrayList<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("login1", "pass1", "nick1"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }

    @Override
    public void start() {

    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    @Override
    public void stop() {

    }
//    @Override
//
//    public void setNick(String login, String pass, String nick) {
//        for (Entry o : entries){
//            if (o.login.equals(login) && o.pass.equals(pass)){
//
//            entries.set(entries.indexOf(o),new Entry(o.login,o.pass,nick));
//            }
//        }
//    }
}
