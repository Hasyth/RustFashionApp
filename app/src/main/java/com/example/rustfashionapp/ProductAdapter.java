package com.example.rustfashionapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    List<Product> productList;


    public ProductAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.productList = products;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent){

        LayoutInflater productInflater = LayoutInflater.from(getContext());
        convertView = productInflater.inflate(R.layout.product_card, parent, false);

        Product product = getItem(position);
        TextView productName = (TextView) convertView.findViewById(R.id.product_name);
        TextView productPrice = (TextView) convertView.findViewById(R.id.product_price);
        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_image_view);

        productName.setText(product.getProd_name());
        productPrice.setText(Double.toString(product.getProd_price()));

        int imgId = context.getResources().getIdentifier(product.getProd_image(), "drawable", context.getPackageName());
        productImage.setImageResource(imgId);

        return convertView;
    }

}
