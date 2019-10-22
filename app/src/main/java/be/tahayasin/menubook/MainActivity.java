package be.tahayasin.menubook;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MainActivityFragmentLanguages fragmentLanguages;
    Fragment fragmentMenu;
    MainActivityFragmentAllCategories fragmentAllCategories;
    MainActivityFragmentCategories fragmentCategories;

    private Menu[] menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("Mevlana");

        setContentView(R.layout.activity_main);

        menus = MenuHandler.getMenu(this);

        setFragmentLanguages();
    }
    private void setFragmentLanguages(){
        if(fragmentLanguages == null){
            makeFragmentLanguages();
        }

        setFragment(fragmentLanguages);
    }
    private void makeFragmentLanguages(){
        fragmentLanguages = new MainActivityFragmentLanguages();
        fragmentLanguages.Setup(this, this, menus);
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_container, fragment);
        fragmentTransaction.commit();
    }

    public void OnLanguageSelected(Menu menu){
        resetLanguages();

        if(menu.isShow_categories()){
            setupFragmentAllCategories(menu);
        }
        else{
            setupFragmentCategories(menu);
        }
    }
    private void resetLanguages(){
        fragmentLanguages = null;
    }
    private void setupFragmentAllCategories(Menu menu){
        fragmentAllCategories = new MainActivityFragmentAllCategories();
        fragmentAllCategories.Setup(this, this, menu);

        setFragment(fragmentAllCategories);
    }
    private void setupFragmentCategories(Menu menu){
        fragmentCategories = new MainActivityFragmentCategories();
        fragmentCategories.Setup(this, this, menu);

        setFragment(fragmentCategories);
    }
}