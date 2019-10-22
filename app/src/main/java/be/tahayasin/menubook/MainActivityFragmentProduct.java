package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragmentProduct extends Fragment {
    private Context context;
    private MainActivity mainActivity;
    private Category category;
    private Integer position;
    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;
    public void Setup(MainActivity mainActivity, MainActivity mainActivity1, Category category, int position) {
        this.context = context;
        this.mainActivity = mainActivity;
        this.category = category;
        this.position = position;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_fragment_categories, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, mainActivity, category);

        viewPager = v.findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);


        return v;
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public SectionPagerAdapter(FragmentManager fm, Context context, MainActivity mainActivity, Category category) {
            super(fm);

            fragments = new Fragment[3];

            fragments = new MainActivityProductFragment[category.getProducts().length];
            for(int i = 0; i < fragments.length; i ++){
                MainActivityProductFragment fr = new MainActivityProductFragment();
                fr.Setup(context, mainActivity, category.getProducts()[i]);
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
