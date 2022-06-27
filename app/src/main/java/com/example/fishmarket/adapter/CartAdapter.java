package com.example.fishmarket.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.fishmarket.R;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.LayoutCartBinding;
import com.example.fishmarket.model.CartPOJO;
import com.example.fishmarket.utils.UtilityFunction;
import com.google.gson.Gson;


import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.BookOrderHolder> {
    private Context context;
    private ArrayList<CartPOJO> servicesPrices;
    private OnAddRemove onAddRemove;

    public CartAdapter(Context context, ArrayList<CartPOJO> servicesPrices) {
        this.context = context;
        this.servicesPrices = servicesPrices;
    }

    public interface OnAddRemove {
        void onUpdate(int position, CartPOJO addedCartPOJO);

        void onRemove(int position, CartPOJO addedCartPOJO);
    }

    @NonNull
    @Override
    public BookOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(context).inflate(R.layout.infalte_added_services, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cart, parent, false);
        return new BookOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookOrderHolder holder, final int position) {
        try {
            holder.binding.tvMrp.setPaintFlags(holder.binding.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            /*if (servicesPrices.get(position).color_image==null){
                return;
            }*/
           // UtilityFunction.checkTextViewSetValue(servicesPrices.get(position).productQuantity + "", holder.binding.tvQuantity);

           /* if (servicesPrices.get(position).potencyItem!=null){
                VariantPOJO variantPOJO=servicesPrices.get(position).potencyItem;
                if (variantPOJO.name!=null && !variantPOJO.name.equals("NA") && !variantPOJO.name.equals("N/A") && !variantPOJO.name.equals("N//A") && !variantPOJO.name.equals("N\\A") ) {

                    UtilityFunction.checkTextViewSetValue(servicesPrices.get(position).productName + " " + servicesPrices.get(position).potencyItem.name, holder.tv_name);
                }else {
                    UtilityFunction.checkTextViewSetValue(servicesPrices.get(position).productName, holder.tv_name);

                }
            }else {
                UtilityFunction.checkTextViewSetValue(servicesPrices.get(position).productName, holder.tv_name);
            }*/
            //UtilityFunction.checkTextViewSetValue(servicesPrices.get(position).colorName, holder.tv_variant);

            /*if (servicesPrices.get(position).status != null && servicesPrices.get(position).status.equals("Not Available")) {
                holder.tv_packing.setTextColor(context.getResources().getColor(R.color.red));
                holder.tv_packing.setVisibility(View.VISIBLE);
                holder.tv_packing.setText(servicesPrices.get(position).status);
            } else if (servicesPrices.get(position).status != null && servicesPrices.get(position).status.equals("Available")) {
                holder.tv_packing.setTextColor(context.getResources().getColor(R.color.green));
                holder.tv_packing.setVisibility(View.VISIBLE);
                holder.tv_packing.setText(servicesPrices.get(position).status);
            }else {
                holder.tv_packing.setVisibility(View.GONE);
            }*/


            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.placeholder);
            requestOptions.error(R.drawable.placeholder);

            Glide.with(context).setDefaultRequestOptions(requestOptions).load(UrlContainer.IMAGE_BASE_URL + servicesPrices.get(position).productImage.replace(" ", "%20")).
                    diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.binding.ivProductImageCart);


        } catch (Exception e) {
            e.printStackTrace();
        }

         CartPOJO productPOJO = servicesPrices.get(position);
        try {

           /* UtilityFunction.checkTextViewSetValue(productPOJO.variantItem.offer_price+"", holder.tv_price);

            VariantPOJO variantPOJO = productPOJO.potencyItem;
            if (variantPOJO==null){
                variantPOJO=productPOJO.variantItem;
            }
            holder.tv_price.setText(context.getString(R.string.Rs) + variantPOJO.offer_price);
            holder.tv_mrp.setText(context.getString(R.string.Rs) + variantPOJO.mrp);
            Double price =variantPOJO.offer_price;// Double.parseDouble(variantPOJO.offer_price);
            Double mrp = Double.parseDouble(variantPOJO.mrp);
            holder.tv_mrp.setText(context.getString(R.string.Rs) + UtilityFunction.round(mrp,2));

            Double total = price * Integer.parseInt(productPOJO.productQuantity);
            holder.tv_total_price.setText(context.getString(R.string.Rs) + UtilityFunction.round(total,2));
            if (mrp > price) {
                Double off = ((mrp - price) * 100) / mrp;
                holder.tv_percentage_off.setText(off.intValue() + "% OFF");
                holder.tv_mrp.setVisibility(View.VISIBLE);
                holder.tv_percentage_off.setVisibility(View.VISIBLE);
            } else {
                holder.tv_mrp.setVisibility(View.GONE);
                holder.tv_percentage_off.setVisibility(View.GONE);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int qty = Integer.parseInt(holder.tv_quantity.getText().toString());
                    qty++;
                    servicesPrices.get(position).productQuantity = qty + "";

                    updateToCart(position, servicesPrices.get(position), holder);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int qty = Integer.parseInt(holder.tv_quantity.getText().toString());
                    qty--;
                    if (qty == 0) {
                        alertGenerate(position, servicesPrices.get(position));
                    } else {
                        servicesPrices.get(position).productQuantity = qty + "";
                        updateToCart(position, servicesPrices.get(position), holder);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    alertGenerate(position, servicesPrices.get(position));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return servicesPrices.size();
    }

    private void alertGenerate(final int position, final CartPOJO addedCartPOJO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert").setMessage("Are you sure?\nYou want to delete this item.");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //removeFromCart(position, addedCartPOJO);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

   /* private void removeFromCart(final int position, final CartPOJO addedCartPOJO) {
        try {
            if (PrefManager.GetCartList(context) != null && PrefManager.GetCartList(context).size() > 0) {
                try {

                    try {
                        //Integer selected=null;
                        ArrayList<CartPOJO> cartPOJOS = PrefManager.GetCartList(context);

                        for (int i = 0; i < PrefManager.GetCartList(context).size(); i++) {
                            Log.e("fafdaas",new Gson().toJson(PrefManager.GetCartList(context).get(i)));
                            Log.e("fafdaas",new Gson().toJson(addedCartPOJO));
                            if (PrefManager.GetCartList(context).get(i).productId.equals(addedCartPOJO.productId)
                                    && PrefManager.GetCartList(context).get(i).variantItem.variantId.equals(addedCartPOJO.variantItem.variantId)
                                    && addedCartPOJO.potencyItem.variantId.equals(PrefManager.GetCartList(context).get(i).potencyItem.variantId)
                            ) {
                                //   selected=i;
                                cartPOJOS.remove(i);
                            }
                        }
                        //cartPOJOS.remove(selected);
                        PrefManager.SaveCartList(context, cartPOJOS);
                        if (context instanceof CartActivity && (PrefManager.GetCartList(context)==null || PrefManager.GetCartList(context).size()==0 )){
                            ((CartActivity)context).cartPOJOS=new ArrayList<>();
                            ((CartActivity)context).setUpData();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            servicesPrices.remove(position);

            notifyDataSetChanged();
            //onAddRemove.onRemove(position, addedCartPOJO);
            Toast.makeText(context, "Item remove successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateToCart(final int position, final CartPOJO productPOJO, final BookOrderHolder holder) {
        try {
            try {
                if (PrefManager.GetCartList(context) != null && PrefManager.GetCartList(context).size() > 0) {
                    try {
                        ArrayList<CartPOJO> cartPOJOS = PrefManager.GetCartList(context);
                        for (int i = 0; i < PrefManager.GetCartList(context).size(); i++) {
                            if (productPOJO.productId.equals(PrefManager.GetCartList(context).get(i).productId)
                                    && productPOJO.variantId.equals(PrefManager.GetCartList(context).get(i).variantId)
                                    && productPOJO.potencyItem.variantId.equals(PrefManager.GetCartList(context).get(i).potencyItem.variantId)
                            ) {
                                cartPOJOS.get(i).productQuantity = productPOJO.productQuantity;
                            }
                        }
                        PrefManager.SaveCartList(context, cartPOJOS);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                holder.tv_quantity.setText(productPOJO.productQuantity);
                Toast.makeText(context, "Quantity updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/
    public class BookOrderHolder extends RecyclerView.ViewHolder {
        LayoutCartBinding binding;

        public BookOrderHolder(@NonNull View itemView) {
            super(itemView);
            binding=LayoutCartBinding.bind(itemView);
        }
    }
}
