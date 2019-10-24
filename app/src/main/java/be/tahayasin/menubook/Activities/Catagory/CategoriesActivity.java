package be.tahayasin.menubook.Activities.Catagory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import be.tahayasin.menubook.Activities.Order.OrderActivity;
import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Interfaces.OnCategoryClickListener;
import be.tahayasin.menubook.Activities.Product.ProductsActivity;
import be.tahayasin.menubook.R;

public class CategoriesActivity extends AppCompatActivity implements OnCategoryClickListener {
    Menu menu;
    ImageView productOrder;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        menu = holder.getMenu();
        context = this;

        productOrder = findViewById(R.id.product_order_button);
        RecyclerView rv = findViewById(R.id.activity_main_fragment_all_categories_rv);

        CategoriesAdapterManager.Setup(this, this, rv, menu.getCategories());

        productOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, OrderActivity.class));
            }
        });
    }

    @Override
    public void OnCategoryClick(Category[] categories, int index) {
        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        holder.setCategory(categories[index]);
        holder.setSelectedCategoryIndex(index);

        startActivity(new Intent(this, ProductsActivity.class));
    }
}
