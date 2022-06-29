package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.R;
import com.example.fishmarket.activity.SellerDetailsActivity;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.InflateHomeListBinding;
import com.example.fishmarket.model.ProductPOJO;
import com.example.fishmarket.utils.PrefManager;
import com.example.fishmarket.utils.UtilityFunction;
import com.example.fishmarket.view.home.HomeFragment;
import com.example.fishmarket.view.product.ProductFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    Context context;
    Fragment fragment;

    public HomeAdapter(Fragment fragment) {
        this.fragment = fragment;
    }
    public HomeAdapter() {
    }
    ArrayList<ProductPOJO> productPOJOS=new ArrayList<>();
    public void updateList(ArrayList<ProductPOJO> arrayList){
        productPOJOS=arrayList;
        notifyDataSetChanged();
    }
    public void updateIndex(ProductPOJO productPOJO){
        productPOJOS.set(productPOJO.index,productPOJO);
        notifyDataSetChanged();
    }
    public void setFragment(Fragment fragment){
        this.fragment=fragment;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }

      View view= LayoutInflater.from(context).inflate(R.layout.inflate_home_list,parent,false);
        return new HomeHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {

        productPOJOS.get(position).index=holder.getAdapterPosition();
        ProductPOJO productPOJO=productPOJOS.get(position);
        holder.binding.setModel(productPOJOS.get(position));
        if (!PrefManager.getString(context,PrefManager.LATITUDE).isEmpty()) {
            Log.e("Latitude", PrefManager.getString(context, PrefManager.LATITUDE));
            Log.e("Longitude", PrefManager.getString(context, PrefManager.LONGITUDE));
            Double curLat=Double.parseDouble(PrefManager.getString(context, PrefManager.LATITUDE));
            Double curLng=Double.parseDouble(PrefManager.getString(context, PrefManager.LONGITUDE));
            Double shopLat=Double.parseDouble(productPOJO.getLatitude());
            Double shopLng=Double.parseDouble(productPOJO.getLongitude());
            Geocoder geocoder=new Geocoder(context);
            try {
               List<Address> addressList= geocoder.getFromLocation(shopLat,shopLng,1);
               List<Address> addressList2= geocoder.getFromLocation(curLat,curLng,1);
                Log.e("ShopAddress", addressList.get(0).getAddressLine(0));
                Log.e("CurrentAddress", addressList2.get(0).getAddressLine(0));


            } catch (IOException e) {
                e.printStackTrace();
            }

            double dist= UtilityFunction.distance(curLat,curLng,shopLat,shopLng);
            holder.binding.tvDistance.setText(dist+" KM");
        }
        if (fragment instanceof HomeFragment){

        }else  if (fragment instanceof ProductFragment){
            holder.binding.setViewModel(((ProductFragment)fragment).viewModel);

            Log.e("fdsa","Home");
           // holder.binding.ivFavourite.setOnClickListener(view -> );

          //  holder.binding.tvName.setText();

            if (productPOJO.getProduct_favourite_status().equals("Yes")){
                holder.binding.ivFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
                holder.binding.ivFavourite.setOnClickListener(view -> holder.binding.getViewModel().removeFromFavourite(productPOJO));
            }else {
                holder.binding.ivFavourite.setImageResource(R.drawable.ic_baseline_heart);
                holder.binding.ivFavourite.setOnClickListener(view -> holder.binding.getViewModel().addToFavourite(productPOJO));
            }

        }else {
            Log.e("fdsa","Other");

        }

        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, SellerDetailsActivity.class).putExtra(UrlContainer.TRANSFER_MODEL,productPOJOS.get(position)));
        });


    }

    @Override
    public int getItemCount() {
        /*if (productPOJOS==null || productPOJOS.size()==0) {
            return 6;
        }else {
            */
            return productPOJOS.size();
       // }
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        InflateHomeListBinding binding;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);

                binding = InflateHomeListBinding.bind(itemView);

        }
    }
}
