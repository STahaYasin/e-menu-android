package be.tahayasin.menubook.Activities.Catagory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import be.tahayasin.menubook.Activities.Order.OrderActivity;
import be.tahayasin.menubook.Handlers.MenuHandler;
import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Interfaces.OnCategoryClickListener;
import be.tahayasin.menubook.Activities.Product.ProductsActivity;
import be.tahayasin.menubook.R;

public class CategoriesActivity extends AppCompatActivity implements OnCategoryClickListener {
    Menu menu;
    ImageView productOrder, backButton;
    LinearLayout  service, rekening;
    Context context;
    ListView listView;
    Spinner taal;
    private CountryAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        backButton = findViewById(R.id.back_button);
        taal = findViewById(R.id.taal_button);
        service = findViewById(R.id.service_button);
        rekening = findViewById(R.id.rekening_button);
        mAdapter = new CountryAdapter(getApplicationContext(), MenuHandler.getMenu(getApplicationContext()));
        taal.setAdapter(mAdapter);
        taal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HoofdModel clickedItem = (HoofdModel) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getName();
                Toast.makeText(getApplicationContext(),clickedCountryName,Toast.LENGTH_SHORT).show();

                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.setLocale(new Locale(clickedItem.getCode()));
                res.updateConfiguration(conf, dm);

               }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                recreate();
            }
        });
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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
