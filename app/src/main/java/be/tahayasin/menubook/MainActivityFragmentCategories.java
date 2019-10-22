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

public class MainActivityFragmentCategories extends Fragment {

    private Context context;
    private MainActivity mainActivity;
    private Menu menu;

    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;

    public MainActivityFragmentCategories() {
        // Required empty public constructor
    }

    public void Setup(Context context, MainActivity mainActivity, Menu menu){
        Setup(context, mainActivity, menu, 0);
    }
    public void Setup(Context context, MainActivity mainActivity, Menu menu, Integer position){
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
        View v = inflater.inflate(R.layout.activity_main_fragment_categories, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, mainActivity, menu);

        viewPager = v.findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);

        //CategoriesAdapterManager.Setup(context, mainActivity, (RecyclerView) v.findViewById(R.id.activity_main_fragment_categories_rv), menu);

        return v;
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public SectionPagerAdapter(FragmentManager fm, Context context, MainActivity mainActivity, Menu menu) {
            super(fm);

            fragments = new Fragment[3];

            fragments = new MainActivityCategoryFragment[menu.getCategories().length];
            for(int i = 0; i < fragments.length; i ++){
                MainActivityCategoryFragment fr = new MainActivityCategoryFragment();
                fr.Setup(context, mainActivity, menu.getCategories()[i]);
                fragments[i] = fr;
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}