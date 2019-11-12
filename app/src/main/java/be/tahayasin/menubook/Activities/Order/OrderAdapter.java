package be.tahayasin.menubook.Activities.Order;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.tahayasin.menubook.Models.CartObject;
import be.tahayasin.menubook.R;

public class OrderAdapter extends ArrayAdapter<CartObject> implements View.OnClickListener{

    private ArrayList<CartObject> cartObjects;
    Context context;

    public OrderAdapter(ArrayList<CartObject> cartObjects,Context context){
        super(context,R.layout.order_list,cartObjects);
        this.cartObjects = cartObjects;
        this.context = context;
    }

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public View getView(int position,  View view,  ViewGroup parent) {
        CartObject menuItem=cartObjects.get(position);

        if(view==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.order_list, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView tittle=view.findViewById(R.id.name);
            tittle.setText(menuItem.getProduct().getName());
            Double totaal = Double.parseDouble(menuItem.getProduct().getPrice()) * menuItem.getAantal();
            TextView prijs = view.findViewById(R.id.price);
            TextView aantal = view.findViewById(R.id.aantal);
            prijs.setText("€ "+totaal.toString());
            //  aantal.setText(menuItem.getAantal());
        }
        return view;
    }
}
