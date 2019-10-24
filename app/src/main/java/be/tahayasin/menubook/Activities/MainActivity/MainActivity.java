package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import be.tahayasin.menubook.Activities.Catagory.CategoriesActivity;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Handlers.MenuHandler;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;
import be.tahayasin.menubook.R;

public class MainActivity extends AppCompatActivity implements OnLanguageSelectListener {

    Fragment fragmentMenu;

    private Menu[] menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("Mevlana");
        setContentView(R.layout.activity_main);

        menus = MenuHandler.getMenu(this);

        RecyclerView rv = findViewById(R.id.activity_main_languages_rv);

        LanguagesAdapterManager.SetupRv(this, this, rv, menus);
    }

    public void OnLanguageSelected(Menu menu){
        MenuHolderSingleton holderSingleton = MenuHolderSingleton.getInstance();
        holderSingleton.setMenu(menu);

        startActivity(new Intent(this, CategoriesActivity.class));
    }
}