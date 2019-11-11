package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import be.tahayasin.menubook.Activities.Catagory.CategoriesActivity;
import be.tahayasin.menubook.Interfaces.OnMenuClickListener;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Handlers.MenuHandler;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;
import be.tahayasin.menubook.R;

public class MainActivity extends AppCompatActivity implements OnLanguageSelectListener, OnMenuClickListener {

    Fragment fragmentMenu;

    private HoofdModel[] hoofdModels;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("Mevlana");
        setContentView(R.layout.activity_main);

        hoofdModels = MenuHandler.getMenu(this);

       rv = findViewById(R.id.activity_main_languages_rv);

        LanguagesAdapterManager.SetupRv(this, this, rv, hoofdModels);
    }

    public void OnLanguageSelected(Menu[] menus){
        if (menus.length == 1)
            goToMenu(menus[0]);
        else
            MenuAdapterManager.SetupRv(this, this, rv, menus);

    }

    public void goToMenu(Menu menu){
        MenuHolderSingleton holderSingleton = MenuHolderSingleton.getInstance();
        holderSingleton.setMenu(menu);
        startActivity(new Intent(this, CategoriesActivity.class));
    }

    @Override
    public void OnMenuSelect(Menu menu) {
        goToMenu(menu);
    }
}