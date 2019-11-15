package be.tahayasin.menubook.Activities.Detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import be.tahayasin.menubook.Constants.MyHttp;
import be.tahayasin.menubook.Handlers.ImageFactory;
import be.tahayasin.menubook.Models.Product;
import be.tahayasin.menubook.R;

public class DetailFragment extends Fragment {

    private Context context;
    private Product product;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void Setup(Context context, Product product){
        this.context = context;
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, container, false);
       ((TextView) v.findViewById(R.id.description)).setText(product.getDescription());
        ((TextView)v.findViewById(R.id.price)).setText("€ "+ product.getPrice());
        ImageView imageView = v.findViewById(R.id.foto);
        final Bitmap bitmap;
        try {
            String URI = "";
            if(product.getHas1_1()){
                URI = MyHttp.een_een;
            }
            else{
                URI = MyHttp.NenN;
            }
            bitmap = ImageFactory.Load(context, URI, product.getImgId());
            imageView.setImageBitmap(bitmap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return v;
    }
}