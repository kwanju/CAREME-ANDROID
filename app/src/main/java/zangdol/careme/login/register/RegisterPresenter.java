package zangdol.careme.login.register;

        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.widget.Toast;

        import java.util.HashMap;

        import zangdol.careme.main.MainActivity;
        import zangdol.careme.model.User;
        import zangdol.careme.newMain.NewMainActivity;
        import zangdol.careme.restapi.CheckDuplicatedID;
        import zangdol.careme.restapi.Register;
        import zangdol.careme.util.AlarmManager;
        import zangdol.careme.util.SaveSharedPreference;


public class RegisterPresenter implements RegisterContract.Presenter, CheckDuplicatedID.OnCheckDupIDListener, Register.OnRegisterListener {
    private RegisterActivity activity;
    private boolean isCheckDupID = false; // 아이디가 중복체크 되어있는지 확인하기 위한 변수.

    public RegisterPresenter(RegisterActivity activity) {
        this.activity = activity;
    }

    @Override
    public void checkDupId(String id) {
        if (id.equals("")) { // 아이디가 공백인지 확인
            alarm("ID를 입력해주세요.");
            return;
        }
        new CheckDuplicatedID(id, this);
    }

    @Override
    public void register(String id, String pw, String nickname, String pnum, String email) {
        if (!isCheckDupID) { // 아이디가 중복체크 되어있는지 확인.
            alarm("아이디 중복체크를 해주세요");
            return;
        }
        if (!checkForm(pw, nickname, pnum, email))
            return;
        String token = SaveSharedPreference.getToken();
        new Register(id, pw, nickname, pnum, email,token, this);
    }

    @Override
    public void setCheckDupIDForFalse() {
        isCheckDupID = false;
    }

    @Override
    public void OnCheckDupID(boolean result) {
        final String res;

        if (result) {
            res = "아이디 사용 가능합니다.";
            isCheckDupID = true;
        } else
            res = "아이디가 중복되었습니다. 다른 아이디를 사용해주시기 바랍니다.";

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alarm(res);
            }
        });

    }

    // 알람을 부르는 함수.
    private void alarm(String message) {
        AlarmManager.alarm(message,activity);
    }

    // 폼의 데이터들이 정상인지 체크( 빈칸인지 주로 체크)
    private boolean checkForm(String pw, String nickname, String pnum, String email) {

        if (pw.equals("")) {
            alarm("패스워드를 입력해주세요");
            return false;
        }
        if (nickname.equals("")) {
            alarm("닉네임을 입력해주세요");
            return false;
        }
        if (pnum.equals("")) {
            alarm("핸드폰 번호를 입력해주세요");
            return false;
        }
        if (email.equals("")) {
            alarm("이메일을 입력해주세요");
            return false;
        }
        return true;
    }

    @Override
    public void onResgiter(final HashMap<String, String> results) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (results.get("result").equals("1")) {// 성공일 때
                    Toast.makeText(activity, "회원가입 성공", Toast.LENGTH_SHORT).show();

                    User.setSession(results); // Session 설정.

                    // 메인엑티비티로 이동.
                    Intent intent = new Intent(activity, NewMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                } else
                    Toast.makeText(activity, "회원가입 실패", Toast.LENGTH_LONG).show();
            }
        });
    }
}
