package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityProductFragment extends Fragment {

    private Context context;
    private Category category;
    private OnProductClickListener clickListener;

    public MainActivityProductFragment() {
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
        View v = inflater.inflate(R.layout.activity_main_category_fragment, container, false);

        ((TextView) v.findViewById(R.id.category_name)).setText(category.getName());
        RecyclerView rv = v.findViewById(R.id.recycler_products);

        ProductsAdapterManager.Setup(context, clickListener, rv, category);

        return v;
    }
}