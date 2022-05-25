package com.example.fishmarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.DiscoverAdapter;
import com.example.fishmarket.databinding.FragDiscoverBinding;
import com.example.fishmarket.model.DiscoverPOJO;
import com.example.fishmarket.utils.BaseFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class DiscoverFragment extends BaseFragment {
    FragDiscoverBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragDiscoverBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    ArrayList<DiscoverPOJO> discoverPOJOS=new ArrayList<>();
    DiscoverAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        discoverPOJOS.add(new DiscoverPOJO("Department of Fisheries", R.drawable.department_fish));
        discoverPOJOS.add(new DiscoverPOJO("NFDB", R.drawable.nfdb));
        discoverPOJOS.add(new DiscoverPOJO("PMMSY", R.drawable.nfdb));
        discoverPOJOS.add(new DiscoverPOJO("Pond Farming", R.drawable.department_fish));
        discoverPOJOS.add(new DiscoverPOJO("Boifloc & RAS", R.drawable.department_fish));
        discoverPOJOS.add(new DiscoverPOJO("Fish Disease", R.drawable.nfdb));
        discoverPOJOS.add(new DiscoverPOJO("Other", R.drawable.nfdb));
        discoverPOJOS.add(new DiscoverPOJO("Success Stories", R.drawable.department_fish));
        adapter=new DiscoverAdapter(context,discoverPOJOS);
        binding.rvDiscover.setAdapter(adapter);
    }
}
