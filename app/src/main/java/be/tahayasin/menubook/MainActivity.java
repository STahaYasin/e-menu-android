package be.tahayasin.menubook;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    MainActivityFragmentLanguages fragmentLanguages;
    Fragment fragmentMenu;
    MainActivityFragmentAllCategories fragmentAllCategories;
    MainActivityFragmentCategories fragmentCategories;
    MainActivityFragmentProduct fragmentProduct;

    private Menu[] menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            setupFragmentCategories(menu, 0);
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
    public void OnCategorySelected(Menu menu, int index){
        //setupFragmentCategories(menu, index);

        MenuHolderSingleton holder = MenuHolderSingleton.getInstance();
        holder.putMenu(menu);
        holder.setSelectedCategoryIndex(index);

        startActivity(new Intent(this, ProductsActivity.class));
    }
    private void setupFragmentCategories(Menu menu, int index){
        fragmentCategories = new MainActivityFragmentCategories();
        fragmentCategories.Setup(this, this, menu, index);

        setFragment(fragmentCategories);
    }

    public void OnProductSelected(Category category, int position) {
        fragmentProduct = new MainActivityFragmentProduct();
        fragmentProduct.Setup(this,this,category,position);

        setFragment(fragmentProduct);
    }
}
