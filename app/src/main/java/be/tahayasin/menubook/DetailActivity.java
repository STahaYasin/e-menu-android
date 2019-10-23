package be.tahayasin.menubook;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DetailActivity extends AppCompatActivity implements IProductOptionsListener {

    private Context context;
    private MainActivity mainActivity;
    private Category category;
    private Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        index = holder.getSelectedProductIndex();
        category = holder.getCategory();

        RecyclerView rv = findViewById(R.id.rv_detail_activity);
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new ProductDetailAdapter(this, category.getProducts()));
        rv.smoothScrollToPosition(index);
    }
}
