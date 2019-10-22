package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragmentAllCategories extends Fragment {

    private Context context;
    private MainActivity mainActivity;
    private Menu menu;

    public MainActivityFragmentAllCategories() {
        // Required empty public constructor
    }

    public void Setup(Context context, MainActivity mainActivity, Menu menu){
        this.context = context;
        this.mainActivity = mainActivity;
        this.menu = menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_fragment_all_categories, container, false);

        RecyclerView rv = v.findViewById(R.id.activity_main_fragment_all_categories_rv);
        CategoriesAdapterManager.Setup(context, mainActivity, rv, menu);

        return v;
    }
}