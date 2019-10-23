package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityCategoryFragment extends Fragment {

    private Context context;
    private Category[] categories;
    private OnCategoryClickListener clickListener;

    public MainActivityCategoryFragment() {
        // Required empty public constructor
    }

    public void Setup(Context context, OnCategoryClickListener clickListener, Category[] categories){
        this.context = context;
        this.categories = categories;
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_category_fragment, container, false);

        RecyclerView rv = v.findViewById(R.id.recycler_products);

        CategoriesAdapterManager.Setup(context, clickListener, rv, categories);

        return v;
    }
}