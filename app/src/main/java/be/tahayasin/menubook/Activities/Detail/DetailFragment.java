package be.tahayasin.menubook.Activities.Detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.tahayasin.menubook.Models.Product;
import be.tahayasin.menubook.R;

public class DetailFragment extends Fragment {

    private Context context;
    private Product product;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void Setup(Context context, Product product){
        this.context = context;
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, container, false);
       ((TextView) v.findViewById(R.id.name)).setText(product.getName());
        ((TextView)v.findViewById(R.id.price)).setText(product.getPrice());
        return v;
    }
}