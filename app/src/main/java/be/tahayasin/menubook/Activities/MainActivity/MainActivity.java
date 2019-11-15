package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import be.tahayasin.menubook.Activities.Catagory.CategoriesActivity;
import be.tahayasin.menubook.Handlers.ImageFactory;
import be.tahayasin.menubook.Interfaces.OnMenuClickListener;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.Handlers.MenuHandler;
import be.tahayasin.menubook.MenuHolderSingleton;
import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;
import be.tahayasin.menubook.Models.Shop;
import be.tahayasin.menubook.R;

public class MainActivity extends AppCompatActivity implements OnLanguageSelectListener, OnMenuClickListener {

    Fragment fragmentMenu;

    private HoofdModel[] hoofdModels;
    RecyclerView rv;
    ImageView logo;
    Handler mHandler=new Handler();
    Runnable mRunnable,timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("Mevlana");
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        hoofdModels = MenuHandler.getMenu(this);
        Shop shop = MenuHandler.getShop(this);

        timeRunnable=new Runnable(){
            @Override
            public void run() {
                finish();

            }
        };

        logo.setOnTouchListener(buttonOnTouchListener);
//        try {
////            logo.setImageBitmap(ImageFactory.Load(this,shop.getLogo_source()));
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        rv = findViewById(R.id.activity_main_languages_rv);

        LanguagesAdapterManager.SetupRv(this, this, rv, hoofdModels);
    }

    private View.OnTouchListener buttonOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch ( event.getAction() ) {
                case MotionEvent.ACTION_DOWN:
                    mHandler.postDelayed(timeRunnable, 5000);
                    break;
                case MotionEvent.ACTION_UP:
                    mHandler.removeCallbacks(timeRunnable);
                    break;
            }
            return true;
        }
    };


    public void OnLanguageSelected(Menu[] menus, String languageID){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(languageID));
        res.updateConfiguration(conf, dm);
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