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

import be.tahayasin.menubook.Handlers.ImageFactory;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.R;

public class LanguageArrayAdapter extends ArrayAdapter<HoofdModel> implements View.OnClickListener {
    private final Context context;
    private final HoofdModel[] hoofdModels;

    public LanguageArrayAdapter(Context context, HoofdModel[] values) {
        super(context,R.layout.custom,values);

        this.context = context;
        this.hoofdModels = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        imageView.setImageResource(ImageFactory.getLanguageId(hoofdModels[position].getCode()));
        textView.setText(hoofdModels[position].getName());


        return rowView;
    }

    @Override
    public void onClick(View view) {

    }
}
