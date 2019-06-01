package zangdol.careme.myPage.myPage;

import java.util.HashMap;

public class MyPageContract {
    public interface View{
        void setData(HashMap<String,String> myinfo);
    }
    public interface Presenter{
        void getData();
    }
}
