package zangdol.careme.animal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import zangdol.careme.R;
import zangdol.careme.model.Animal;

public class AnimalInfoActivity extends AppCompatActivity implements AnimalInfoContract.View {
    private AnimalInfoPresenter presenter;
    private AnimalInfoActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        me = this;
        presenter = new AnimalInfoPresenter(this);
        setElements();
        presenter.getAnimalInfo();
    }

    private void setElements() {

    }

    @Override
    public void setAnimalInfo(final Animal animal) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (animal.getDiscoveredSpot().equals("null"))
                    Toast.makeText(me, "null", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(me, "not null", Toast.LENGTH_LONG).show();

            }
        });
    }
}
