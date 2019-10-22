package be.tahayasin.menubook;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProductsActivity extends AppCompatActivity {

    private Context context;
    private MainActivity mainActivity;
    private Menu menu;
    private Integer categoryIndex;

    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        this.context = this;
        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        this.menu = holder.getMenu();
        this.categoryIndex = holder.getSelectedCategoryIndex();

        FragmentManager fragmentManager = getSupportFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, mainActivity, menu);

        viewPager = findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);
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