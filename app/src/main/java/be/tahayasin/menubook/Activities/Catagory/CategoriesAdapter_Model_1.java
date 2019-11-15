package be.tahayasin.menubook.Activities.Catagory;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import be.tahayasin.menubook.Constants.MyHttp;
import be.tahayasin.menubook.Handlers.ImageFactory;
import be.tahayasin.menubook.Models.Category;
import be.tahayasin.menubook.Interfaces.OnCategoryClickListener;
import be.tahayasin.menubook.R;

public class CategoriesAdapter_Model_1 extends RecyclerView.Adapter<CategoriesAdapter_Model_1.ItemHolder> {

    private Context context;
    private OnCategoryClickListener clickListener;
    private Category[] categories;

    public CategoriesAdapter_Model_1(Context context, OnCategoryClickListener clickListener, Category[] categories){
        this.context = context;
        this.clickListener = clickListener;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout_model_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.title.setText(categories[position].getName());

        final ImageView fimageview = holder.imageView;
        final Integer fpos = position;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap;
                try {

                    bitmap = ImageFactory.Load(context, MyHttp.een_een, categories[fpos].getImgsrc());
//                    fimageview.setImageBitmap(bitmap);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.setPriority(Thread.MAX_PRIORITY);
        t.start();


        fimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnCategoryClick(categories,fpos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories != null & categories != null? categories.length: 0;
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