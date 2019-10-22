package be.tahayasin.menubook;

public class MenuHolderSingleton {
    private Menu menu;
    private int selectedCategoryIndex;
    private int selectedProductIndex;

    private static MenuHolderSingleton instance = null;

    private MenuHolderSingleton(){

    }

    public static MenuHolderSingleton getInstance(){
        if(instance == null) instance = new MenuHolderSingleton();

        return instance;
    }

    public void putMenu(Menu menu){
        this.menu = menu;
    }
    public Menu getMenu(){
        return menu;
    }

    public int getSelectedCategoryIndex() {
        return selectedCategoryIndex;
    }

    public void setSelectedCategoryIndex(int selectedCategoryIndex) {
        this.selectedCategoryIndex = selectedCategoryIndex;
    }

    public int getSelectedProductIndex() {
        return selectedProductIndex;
    }

    public void setSelectedProductIndex(int selectedProductIndex) {
        this.selectedProductIndex = selectedProductIndex;
    }
}
