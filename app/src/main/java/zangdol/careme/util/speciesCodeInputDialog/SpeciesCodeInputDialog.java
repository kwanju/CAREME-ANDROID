package zangdol.careme.util.speciesCodeInputDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zangdol.careme.R;
import zangdol.careme.util.ConvertManager;

public class SpeciesCodeInputDialog implements View.OnClickListener {
    private Context context;
    private View dialogView;
    private EditText et_keyword;
    private Button bt_search;
    private ListView lv_result;
    private AlertDialog.Builder alertDialogBuilder;

    private OnSpeciesCodeSelectListener listener;
    private String[] selected;

    public interface OnSpeciesCodeSelectListener {
        void onSpeciesCode(String[] result);
    }

    public SpeciesCodeInputDialog(Context context, OnSpeciesCodeSelectListener listener) {
        this.context = context;
        this.listener = listener;
        selected = new String[2];
    }

    public void showDialog() {
        setElements();
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onSpeciesCode(selected);
                    }
                })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void setElements() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        dialogView = layoutInflater.inflate(R.layout.input_dialog_species_code, null);
        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        et_keyword = (EditText) dialogView.findViewById(R.id.idsc_keyword);
        bt_search = (Button) dialogView.findViewById(R.id.idsc_search);
        lv_result = (ListView) dialogView.findViewById(R.id.idsc_list);

        bt_search.setOnClickListener(this);
        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = (String) parent.getItemAtPosition(position);

                selected[0] = ConvertManager.getCode(selected_item);
                selected[1] = selected_item;

                for (int i = 0; i < lv_result.getChildCount(); i++) {
                    if (position == i) {
                        lv_result.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                    } else {
                        lv_result.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idsc_search:
                HashMap<String, String> result = ConvertManager.search(et_keyword.getText().toString());
                List<String> list = new ArrayList<>();

                for (String key : result.keySet())
                    list.add(result.get(key));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1, list);

                lv_result.setAdapter(adapter);
                break;
        }
    }
}
