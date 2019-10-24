package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.R;

public class MainActivityFragmentLanguages extends Fragment {

    private Context context;
    private Menu[] menus;
    private MainActivity mainActivity;

    public MainActivityFragmentLanguages() {
        // Required empty public constructor
    }

    public void Setup(Context context, MainActivity mainActivity, Menu[] menus){
        this.context = context;
        this.menus = menus;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_fragment_languages, container, false);
        RecyclerView rv = v.findViewById(R.id.activity_main_languages_rv);

        LanguagesAdapterManager.SetupRv(context, mainActivity, rv, menus);

        return v;
    }
}