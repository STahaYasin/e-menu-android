package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityCategoryFragment extends Fragment {

    private Context context;
    private Category category;
    private MainActivity mainActivity;

    public MainActivityCategoryFragment() {
        // Required empty public constructor
    }

    public void Setup(Context context, MainActivity mainActivity, Category category){
        this.context = context;
        this.category = category;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_category_fragment, container, false);

        ((TextView) v.findViewById(R.id.category_name)).setText(category.getName());

        return v;
    }
}