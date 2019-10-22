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

public class CategoriesAdapter_Model_1 extends RecyclerView.Adapter<CategoriesAdapter_Model_1.ItemHolder> {

    private Context context;
    private MainActivity mainActivity;
    private Menu menu;

    public CategoriesAdapter_Model_1(Context context, MainActivity mainActivity, Menu menu){
        this.context = context;
        this.mainActivity = mainActivity;
        this.menu = menu;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout_model_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.title.setText(menu.getCategories()[position].getName());

        final ImageView fimageview = holder.imageView;
        final Integer fpos = position;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = ImageFactory.Load(context, menu.getCategories()[fpos].getImgsrc());
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        fimageview.setImageBitmap(ImageFactory.Load(context, menu.getCategories()[position].getImgsrc()));
    }

    @Override
    public int getItemCount() {
        return menu != null & menu.getCategories() != null? menu.getCategories().length: 0;
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