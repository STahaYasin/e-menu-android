package be.tahayasin.menubook;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class ProductAdapter_Model_1  extends RecyclerView.Adapter<ProductAdapter_Model_1.ItemHolder> {
    private Context context;
    private OnProductClickListener clickListener;
    private Category category;

    public ProductAdapter_Model_1(Context context, OnProductClickListener clickListener, Category category){
        this.context = context;
        this.clickListener = clickListener;
        this.category = category;
    }

    @NonNull
    @Override
    public ProductAdapter_Model_1.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter_Model_1.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout_model_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter_Model_1.ItemHolder holder, final int position) {
        holder.title.setText(category.getProducts()[position].getName());

        final ImageView fimageview = holder.imageView;
        final Integer fpos = position;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = ImageFactory.Load(context, category.getProducts()[fpos].getSourcePath());
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        fimageview.setImageBitmap(ImageFactory.Load(context, category.getProducts()[position].getSourcePath()));

        fimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnClick(category, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category != null & category.getProducts() != null? category.getProducts().length: 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;

        public ItemHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.category_layout_model_1_title);
            imageView = itemView.findViewById(R.id.category_layout_model_1_imageview);
        }
    }
}
