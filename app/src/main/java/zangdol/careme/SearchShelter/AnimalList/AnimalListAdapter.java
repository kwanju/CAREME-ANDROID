package zangdol.careme.SearchShelter.AnimalList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zangdol.careme.R;
import zangdol.careme.model.AnimalSummary;

// https://mailmail.tistory.com/6 참고

public class AnimalListAdapter extends BaseAdapter {

    private List<AnimalSummary> list;

    public AnimalListAdapter(List<AnimalSummary> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public AnimalSummary getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        /*
         * 여기서 기능이 많으면 View를 따로 만들어도 될듯 그리고 MVP 모델로
         *
         * ex) convertView = new ElementView();
         *
         * 이런식으로.
         *
         * */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_item_animal_list, parent, false);
        }

        ImageView iv_dogImage = (ImageView) convertView.findViewById(R.id.animal_list_item_dog_image);
        TextView tv_dogName = (TextView) convertView.findViewById(R.id.animal_list_item_name);
        TextView tv_species = (TextView) convertView.findViewById(R.id.animal_list_item_species);
        TextView tv_idx = (TextView) convertView.findViewById(R.id.animal_list_item_idx);

        AnimalSummary animalSummary = getItem(position);

        iv_dogImage.setImageResource(R.drawable.dog); // ImageURL로 바꿔주어야함 실제
        tv_dogName.setText(animalSummary.getName());
        tv_species.setText(animalSummary.getSpeciesCode());
        tv_idx.setText(animalSummary.getIdx());

        return convertView;
    }

}
