package be.tahayasin.menubook.Activities.MainActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.tahayasin.menubook.Interfaces.OnLanguageSelectListener;
import be.tahayasin.menubook.Interfaces.OnMenuClickListener;
import be.tahayasin.menubook.Models.HoofdModel;
import be.tahayasin.menubook.Models.Menu;
import be.tahayasin.menubook.R;

public class MenuAdapter_Model_1 extends RecyclerView.Adapter<MenuAdapter_Model_1.ItemHolder> {

    private Context context;
    private OnMenuClickListener menuClickListener;
    private Menu[] menus;

    public MenuAdapter_Model_1(Context context, OnMenuClickListener menuClickListener, Menu[] menus){
        this.context = context;
        this.menuClickListener = menuClickListener;
        this.menus = menus;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item_model_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Menu menu = menus[position];

        holder.tv_name.setText(menu.getName());

        /*if(menu.getLanguage().isSupported_lang()){
            holder.iv_img.setImageDrawable(context.getResources().getDrawable(LanguagesAdapterManager.getSupportedLangImage(menu.getLanguage().getLanguage_id())));
        }
        else{
            holder.iv_img.setImageBitmap(null);
        }*/

        holder.iv_img.setImageResource(R.drawable.wifi);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClickListener.OnMenuSelect(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.length;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        LinearLayout ll;

        TextView tv_name;
        ImageView iv_img;

        public ItemHolder(View itemView) {
            super(itemView);

            ll = itemView.findViewById(R.id.language_item_model_1_lang);

            tv_name = itemView.findViewById(R.id.language_item_model_1_name);
            iv_img = itemView.findViewById(R.id.language_item_model_1_img);
        }
    }
}