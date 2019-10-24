package be.tahayasin.menubook.Activities.Product;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Interfaces.OnProductClickListener;
import be.tahayasin.menubook.R;

public class ProductFragment extends Fragment {

    private Context context;
    private Category category;
    private OnProductClickListener clickListener;

    public ProductFragment() {
        // Required empty public constructor
    }

    public void Setup(Context context, OnProductClickListener clickListener, Category category){
        this.context = context;
        this.category = category;
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment, container, false);

        RecyclerView rv = v.findViewById(R.id.product_rc);

        ProductsAdapterManager.Setup(context, clickListener, rv, category);

        return v;
    }
}