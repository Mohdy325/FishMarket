package com.example.fishmarket.view.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fishmarket.adapter.HomeAdapter;
import com.example.fishmarket.databinding.FragmentHomeBinding;
import com.example.fishmarket.view.bottom_dialogs.LocationDialogFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        binding.rvHome.setAdapter(new HomeAdapter(getContext()));
        new Handler().postDelayed(()->{
            binding.progressBar.setVisibility(View.GONE);
        },3000);
        binding.tvAddress.setOnClickListener(view -> {
            LocationDialogFragment bf = new LocationDialogFragment((address, latitude, longitude) -> {
                binding.tvAddress.setText(address);
            });
           // Bundle bundle=new Bundle();
            //bundle.putSerializable(UrlContainer.TRANSFER_MODEL,new ChatUserPOJO("Alex","just now",true, R.drawable.user));
           // bf.setArguments(bundle);
            bf.show(getChildFragmentManager(), bf.getTag());
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}