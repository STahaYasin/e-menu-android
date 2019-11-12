package be.tahayasin.menubook.Activities.Catagory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.tahayasin.menubook.Handlers.ImageFactory;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.R;


public class CountryAdapter extends ArrayAdapter<HoofdModel> {

    public CountryAdapter(Context context, HoofdModel[] countryList) {
        super(context, 0, countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.image);
        TextView textViewName = convertView.findViewById(R.id.text);

        HoofdModel currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(ImageFactory.getLanguageId(currentItem.getCode()));
            textViewName.setText(currentItem.getName());
        }

        return convertView;
    }
}