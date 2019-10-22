package be.tahayasin.menubook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivityProductFragment  extends Fragment {
    private Context context;
    private Product product;
    private MainActivity mainActivity;

    public MainActivityProductFragment() {
        // Required empty public constructor
    }
    public void Setup(Context context, MainActivity mainActivity, Product product) {
        this.context = context;
        this.product = product;
        this.mainActivity = mainActivity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_fragment_product, container, false);

        ((TextView) v.findViewById(R.id.producttitle)).setText(product.getName());


        return v;
    }
}
