package be.tahayasin.menubook.Activities.Product;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.tahayasin.menubook.Activities.Detail.DetailActivity;
import be.tahayasin.menubook.Activities.Order.OrderActivity;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Interfaces.OnProductClickListener;
import be.tahayasin.menubook.R;

public class ProductsActivity extends AppCompatActivity implements OnProductClickListener {

    private Context context;
    private Menu menu;
    private Integer categoryIndex;
    ImageView Wifi,battery,ober,order,backButton,receipt;
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

        ober = findViewById(R.id.product_ober_button);
        order = findViewById(R.id.product_order_button);
        backButton = findViewById(R.id.product_back_button);
        receipt = findViewById(R.id.getReceipt);

        FragmentManager fragmentManager = getSupportFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, this, menu);

        viewPager = findViewById(R.id.activity_main_fragment_categories_container);
        viewPager.setAdapter(sectionPagerAdapter);
        viewPager.setCurrentItem(categoryIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ((TextView)findViewById(R.id.product_catagory_title)).setText(menu.getCategories()[position].getName());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, OrderActivity.class));
            }
        });

        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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

            fragments = new ProductFragment[menu.getCategories().length];
            for(int i = 0; i < fragments.length; i ++){
                ProductFragment fr = new ProductFragment();
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