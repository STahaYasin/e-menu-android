package be.tahayasin.menubook.Activities.Detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.tahayasin.menubook.Activities.MainActivity.MainActivity;
import be.tahayasin.menubook.Activities.Product.ProductFragment;
import be.tahayasin.menubook.Activities.Product.ProductsActivity;
import be.tahayasin.menubook.Interfaces.OnProductClickListener;
import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Interfaces.IProductOptionsListener;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.R;

public class DetailActivity extends AppCompatActivity implements IProductOptionsListener {

    private Context context;
    private Category category;
    private Integer index;
    private TextView name;
    ImageView back;

    ViewPager viewPager;
    SectionPagerAdapter sectionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back_button);
        name = findViewById(R.id.product_detail_title);
        this.context = this;
        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        this.index = holder.getSelectedProductIndex();
        this.category = holder.getCategory();
        name.setText(category.getProducts()[index].getName());
        FragmentManager fragmentManager = getSupportFragmentManager();
        sectionPagerAdapter = new SectionPagerAdapter(fragmentManager, context, category);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ViewPager vp = findViewById(R.id.vp_detail_activity);
        vp.setAdapter(sectionPagerAdapter);
        vp.setCurrentItem(index, true);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                name.setText(category.getProducts()[position].getName());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // vp.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] fragments;

        public SectionPagerAdapter(FragmentManager fm, Context context, Category category) {
            super(fm);

            fragments = new Fragment[3];

            fragments = new DetailFragment[category.getProducts().length];
            for(int i = 0; i < fragments.length; i ++){
                DetailFragment df = new DetailFragment();
                df.Setup(context,  category.getProducts()[i]);
                fragments[i] = df;
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
