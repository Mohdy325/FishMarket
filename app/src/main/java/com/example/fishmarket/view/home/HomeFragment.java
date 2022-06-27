package com.example.fishmarket.view.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.MainActivity;
import com.example.fishmarket.adapter.HomeAdapter;
import com.example.fishmarket.databinding.FragmentHomeBinding;
import com.example.fishmarket.view.bottom_dialogs.LocationDialogFragment;
import com.google.gson.Gson;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
context=getContext();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        binding.setViewModel(homeViewModel);
        homeViewModel.successLiveData.observe(getViewLifecycleOwner(),commonModel -> {
            binding.progressBar.setVisibility(View.GONE);
            Log.e("fdassfa",new Gson().toJson(commonModel.in_trending));
        });
        homeViewModel.errorLiveData.observe(getViewLifecycleOwner(),text ->{
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        });


        //binding.rvHome.setAdapter(new HomeAdapter(this));
        /*new Handler().postDelayed(()->{
            try {


            }catch (Exception e){
                e.printStackTrace();
            }
        },3000);
        */
        homeViewModel.setUpData();

       /* homeViewModel.getProducts();
        binding.rvHome.setAdapter(homeViewModel.homeAdapter);
        binding.rvCategories.setAdapter(homeViewModel.categoryAdapter);
*/
       if (context instanceof MainActivity &&  ((MainActivity) context).binding!=null){
           ((MainActivity) context).binding.appBarMain.llAddress.setOnClickListener(view -> {
               LocationDialogFragment bf = new LocationDialogFragment((address, latitude, longitude) -> {
                   ((MainActivity) context).binding.appBarMain.tvAddress.setText(address);
               });
               // Bundle bundle=new Bundle();
               //bundle.putSerializable(UrlContainer.TRANSFER_MODEL,new ChatUserPOJO("Alex","just now",true, R.drawable.user));
               // bf.setArguments(bundle);
               bf.show(getChildFragmentManager(), bf.getTag());
           });

           ((MainActivity) context).binding.appBarMain.ivFilter.setOnClickListener(view -> {
               FilterDialogFragment bf = new FilterDialogFragment(list -> {
                   //binding.tvAddress.setText(address);
               });
               // Bundle bundle=new Bundle();
               //bundle.putSerializable(UrlContainer.TRANSFER_MODEL,new ChatUserPOJO("Alex","just now",true, R.drawable.user));
               // bf.setArguments(bundle);
               bf.show(getChildFragmentManager(), bf.getTag());
           });
       }
        return root;
    }

    Dialog dialog;

    private void openFilterDialog(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}