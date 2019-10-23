package be.tahayasin.menubook;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    private Context context;
    private MainActivity mainActivity;
    private Category category;
    private Integer position;
    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        category = holder.getCategory();

        FragmentManager fragmentManager = getSupportFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, category);

        viewPager = findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public SectionPagerAdapter(FragmentManager fm, Context context, Category category) {
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
