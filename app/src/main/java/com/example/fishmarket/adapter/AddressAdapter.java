package com.example.fishmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fishmarket.R;
import com.example.fishmarket.activity.AddAddressActivity;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.databinding.InflateAddedAddressBinding;
import com.example.fishmarket.model.AddressPOJO;
import com.example.fishmarket.utils.PrefManager;
import com.example.fishmarket.utils.UtilityFunction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {
    Context context;
    ArrayList<AddressPOJO> addressPOJOS;
    OnSelected onSelected;

    public AddressAdapter(Context context, ArrayList<AddressPOJO> addressPOJOS, OnSelected selected) {
        this.context = context;
        for (AddressPOJO addressPOJO : addressPOJOS) {
            addressPOJO.isActive = false;
            if (PrefManager.GetDefaultAddressData(context) != null && PrefManager.GetDefaultAddressData(context).pinCode != null) {
                if (addressPOJO.addressID == PrefManager.GetDefaultAddressData(context).addressID) {
                    addressPOJO.isActive = true;
                }
            }
        }
        this.addressPOJOS = addressPOJOS;
        this.onSelected = selected;
    }

    public interface OnSelected {
        void onSelected(int position, AddressPOJO addressPOJO);

        void onUnSelected(int position, AddressPOJO addressPOJO);

    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_added_address, parent, false);
        return new AddressHolder(view);
    }

    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;

    @Override
    public void onBindViewHolder(@NonNull final AddressHolder holder, final int position) {
        try {
            final AddressPOJO addressPOJO = addressPOJOS.get(position);
           /* UtilityFunction.checkTextViewSetValue(addressPOJO.title + " " + addressPOJO.name, holder.tv_name);
            UtilityFunction.checkTextViewSetValue(addressPOJO.houseNo + ", " + addressPOJO.streetNo + ", " + addressPOJO.address, holder.tv_address);
            UtilityFunction.checkTextViewSetValue(addressPOJO.pinCode, holder.tv_pincode);
            UtilityFunction.checkTextViewSetValue("Mobile : " + addressPOJO.mobileNo, holder.tv_phone);
           */

            //holder.ch_mark_default.setTag(new Integer(position));
            //   holder.tv_phone.setText("Mobile Number :  "+addressPOJO.phone);
           /* if (Pref.GetAddressData(context)!=null){
                if (addressPOJO.addressID==Pref.GetAddressData(context).addressID && addressPOJO.name.equals(Pref.GetAddressData(context).name)){
                    holder.ch_select.setChecked(true);
                }
            }*/
/*
            if (Pref.GetDefaultAddressData(context)!=null){
                if (Pref.GetDefaultAddressData(context).address!=null){
                    if (Pref.GetDefaultAddressData(context).addressID==addressPOJOS.get(position).addressID){
                        holder.ch_mark_default.setChecked(true);
                        lastCheckedPos=position;
                    }else {
                        holder.ch_mark_default.setChecked(false);
                    }
                }else {
                    holder.ch_mark_default.setChecked(false);
                }
            }else {
                holder.ch_mark_default.setChecked(false);
            }*/
        //    holder.ch_mark_default.setChecked(addressPOJO.isActive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (context instanceof AddressListActivity) {
        holder.view_margin.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(view -> {
            PrefManager.SaveDefualtAddressData(context, addressPOJOS.get(position));
            ((Activity)context).onBackPressed();
          //  notifyDataSetChanged();
        });
      /*  }else {
            holder.ch_select.setVisibility(View.GONE);
            holder.view_margin.setVisibility(View.VISIBLE);
        }*/



        try {
            if (PrefManager.GetDefaultAddressData(context) != null) {
                if (PrefManager.GetDefaultAddressData(context).address != null) {
                    if (PrefManager.GetDefaultAddressData(context).addressID == addressPOJOS.get(position).addressID) {
                        holder.iv_status.setVisibility(View.VISIBLE);
                    } else {
                        holder.iv_status.setVisibility(View.GONE);

                    }
                } else {
                    holder.iv_status.setVisibility(View.GONE);

                }
            } else {
                holder.iv_status.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*holder.ch_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        onSelected.onSelected(position, addressPOJOS.get(position));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        */

        holder.tv_edit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddAddressActivity.class);
            intent.putExtra(UrlContainer.TRANSFER_MODEL, addressPOJOS.get(position));
            context.startActivity(intent);
        });

        holder.tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  deleteAddress(position);
            }
        });

     /*   if (addressPOJOS.get(position).isSelected){
            holder.ch_select.setChecked(true);
        }
*/

    }


    Boolean isVisible = false;

    private void checkAvailablity(AddressPOJO addressPOJO, final AddressHolder holder) {
        try {
            if (UtilityFunction.isNetworkConnected(context)) {
              /*progressDialog=new ProgressDialog(this);
              progressDialog.setMessage("Checking Availablity...");
              progressDialog.setCancelable(false);
              progressDialog.show();
              */

            /*    Call<SuccessPOJO> call = NetworkManager.getInstance().getApiServices().checkAvailablity(StringUtils.CHECK_AVAILABILITY + addressPOJO.pinCode);
                call.enqueue(new Callback<SuccessPOJO>() {
                    @Override
                    public void onResponse(Call<SuccessPOJO> call, Response<SuccessPOJO> response) {
                        try {
                            if (response.isSuccessful()) {
                                holder.iv_status.setVisibility(View.VISIBLE);
                                isVisible = true;
                                if (response.body().is_Available) {
                                    holder.iv_status.setImageResource(R.drawable.check);
                                } else {
                                    holder.iv_status.setImageResource(R.drawable.cancel);
                                    showToast("Delivery service is not available in this area.");
                                    // Toast.makeText(context, "Delivery service is not available in this area.", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessPOJO> call, Throwable t) {
                        Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();

                    }
                });*/
            } else {
                Toast.makeText(context, "Network is Not Available", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Toast toast;

    public void showToast(String message) {
        try {
            if (toast != null) {
                toast.cancel();
            }
            toast = new Toast(context);
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return addressPOJOS.size();
    }

    public class AddressHolder extends RecyclerView.ViewHolder {
      /*  CheckBox ch_select;
        CheckBox ch_mark_default;*/
        TextView tv_edit;
        TextView tv_remove;
        TextView tv_phone;
        InflateAddedAddressBinding binding;

        public AddressHolder(@NonNull View itemView) {
            super(itemView);
            binding=InflateAddedAddressBinding.bind(itemView);
           /* ch_select = itemView.findViewById(R.id.ch_select);
            ch_mark_default = itemView.findViewById(R.id.ch_mark_default);*/
            tv_edit = itemView.findViewById(R.id.tv_edit);
            tv_remove = itemView.findViewById(R.id.tv_remove);
            tv_phone = itemView.findViewById(R.id.tv_mobile_number);

            setAddressView(itemView);

        }

        TextView tv_address;
        TextView tv_name;
        TextView tv_state;
        TextView tv_pincode;
        ImageView iv_status;
        LinearLayout ll_address_info;
        View view_margin;

        private void setAddressView(View view) {
            iv_status = view.findViewById(R.id.iv_status);
            tv_name = view.findViewById(R.id.tv_name);
            tv_address = view.findViewById(R.id.tv_address);
            tv_state = view.findViewById(R.id.tv_state);
            tv_pincode = view.findViewById(R.id.tv_pincode);
            view_margin = view.findViewById(R.id.view_margin);
            tv_state.setVisibility(View.GONE);
            ll_address_info = view.findViewById(R.id.ll_address_info);
        }
    }
}
