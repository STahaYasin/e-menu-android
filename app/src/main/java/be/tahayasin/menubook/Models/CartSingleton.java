package be.tahayasin.menubook.Models;

public class CartSingleton {
    private static CartSingleton cartSingleton=null;




    private CartSingleton(){

    }
    public static CartSingleton getInstance()
    {
        // To ensure only one instance is created
        if (cartSingleton == null)
        {
            cartSingleton = new CartSingleton();
        }
        return cartSingleton;
    }
}
