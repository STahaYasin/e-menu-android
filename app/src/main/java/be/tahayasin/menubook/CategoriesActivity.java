package be.tahayasin.menubook;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class CategoriesActivity extends AppCompatActivity implements OnCategoryClickListener {
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        menu = holder.getMenu();

        RecyclerView rv = findViewById(R.id.activity_main_fragment_all_categories_rv);

        CategoriesAdapterManager.Setup(this, this, rv, menu.getCategories());
    }

    @Override
    public void OnCategoryClick(Category[] categories, int index) {
        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        holder.setCategory(categories[index]);

        startActivity(new Intent(this, ProductsActivity.class));
    }
}
