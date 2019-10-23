package be.tahayasin.menubook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductDetailAdapter extends RecyclerView.Adapter {

    IProductOptionsListener optionsListener;
    Product[] products;

    public ProductDetailAdapter(IProductOptionsListener optionsListener, Product[] products){
        this.optionsListener = optionsListener;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_detail_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // TODO hier dingen doen
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        public ItemHolder(View itemView) {
            super(itemView);
        }
    }
}
