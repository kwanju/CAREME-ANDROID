package zangdol.careme.restapi;

/*
 * Facade Controller, Singleton으로 구현
 *
 *
 * */
public class RestFacade {

    private static RestFacade instance = null;

    private RestFacade() {
    }

    public static RestFacade getInstance() {
        if (instance == null)
            instance = new RestFacade();
        return instance;
    }

    // 로그인 관련  RESTAPI 기능을 수행.
    public void login(String id, String pw, String token, Login.OnLoginListener listener) {
        new Login().login(id, pw,token, listener);
    }


}
