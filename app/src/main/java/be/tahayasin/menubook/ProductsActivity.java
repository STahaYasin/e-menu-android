package be.tahayasin.menubook;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProductsActivity extends AppCompatActivity implements OnProductClickListener {

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
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, this, menu);

        viewPager = findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);
    }

    @Override
    public void OnClick(Category category, int position) {
        MenuHolderSingleton holderSingleton = MenuHolderSingleton.getInstance();
        holderSingleton.setCategory(category);
        holderSingleton.setSelectedProductIndex(position);

        startActivity(new Intent(this, DetailActivity.class));
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public SectionPagerAdapter(FragmentManager fm, Context context, OnProductClickListener clickListener, Menu menu) {
            super(fm);

            fragments = new Fragment[3];

            fragments = new MainActivityProductFragment[menu.getCategories().length];
            for(int i = 0; i < fragments.length; i ++){
                MainActivityProductFragment fr = new MainActivityProductFragment();
                fr.Setup(context, clickListener, menu.getCategories()[i]);
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