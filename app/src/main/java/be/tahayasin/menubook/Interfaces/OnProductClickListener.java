package be.tahayasin.menubook.Interfaces;

import be.tahayasin.menubook.Activities.Product.ProductsActivity;
import be.tahayasin.menubook.Models.Category;

public interface OnProductClickListener {
    public void OnClick(Category category, int position);
    public ProductsActivity getActivity();
}
