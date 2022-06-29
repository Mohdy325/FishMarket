package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.R;
import com.example.fishmarket.activity.SellerDetailsActivity;
import com.example.fishmarket.activity.WishListActivity;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.InflateHomeListBinding;
import com.example.fishmarket.databinding.InflateWishlistBinding;
import com.example.fishmarket.model.ProductPOJO;
import com.example.fishmarket.view.product.ProductFragment;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListHolder> {
    Context context;
    public WishListAdapter() {
    }
    ArrayList<ProductPOJO> productPOJOS=new ArrayList<>();
    public void updateList(ArrayList<ProductPOJO> arrayList){
        productPOJOS=arrayList;
        notifyDataSetChanged();
    }
    public void remove(ProductPOJO productPOJO){
        productPOJOS.remove(productPOJO.index);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context= parent.getContext();
        }

        View view= LayoutInflater.from(context).inflate(R.layout.inflate_wishlist,parent,false);
        return new WishListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListHolder holder, int position) {
        productPOJOS.get(position).index=holder.getAdapterPosition();
        ProductPOJO productPOJO=productPOJOS.get(position);
        holder.binding.setModel(productPOJO);
        if (context instanceof WishListActivity) {
            holder.binding.setViewModel(((WishListActivity) context).viewModel);
            holder.binding.ivFavourite.setOnClickListener(view -> holder.binding.getViewModel().removeFromFavourite(productPOJO));
        }
       // holder.binding.setViewModel();
       /* if (productPOJO.getProduct_favourite_status().equals("Yes")){
            holder.binding.ivFavourite.setImageResource(R.drawable.ic_baseline_favorite_24);
            holder.binding.ivFavourite.setOnClickListener(view -> holder.binding.getViewModel().removeFromFavourite(productPOJO));
        }else {*/
           // holder.binding.ivFavourite.setImageResource(R.drawable.ic_baseline_heart);
       // }
        holder.itemView.setOnClickListener(view -> {
            productPOJOS.get(position).setProduct_favourite_status("Yes");
            context.startActivity(new Intent(context, SellerDetailsActivity.class).putExtra(UrlContainer.TRANSFER_MODEL,productPOJOS.get(position)));
        });

    }

    @Override
    public int getItemCount() {
        return productPOJOS.size();
    }

    public class WishListHolder extends RecyclerView.ViewHolder {
        InflateWishlistBinding binding;
        public WishListHolder(@NonNull View itemView) {
            super(itemView);
            binding = InflateWishlistBinding.bind(itemView);
        }
    }
}
