package com.example.fishmarket.view.home;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.print.PrintDocumentAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.FilterDialogAdapter;
import com.example.fishmarket.databinding.FragFilterDialogBinding;
import com.example.fishmarket.model.CategoryPOJO;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FilterDialogFragment extends BottomSheetDialogFragment {
    FragFilterDialogBinding binding;
    Context context;
    public interface OnSearchClick{
        void onSearch(ArrayList<CategoryPOJO> categoryPOJOS);
    }
    OnSearchClick onSearchClick;

    public FilterDialogFragment(OnSearchClick onSearchClick) {
        this.onSearchClick = onSearchClick;
    }

    @Override
    public int getTheme() {
        //return super.getTheme();
        return R.style.AppBottomSheetDialogTheme;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        return new BottomSheetDialog(requireContext(), getTheme());  //set your created theme here

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragFilterDialogBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);


        View contentView = view1;
        context = contentView.getContext();
        //tv_title.setText(getString(R.string.app_name)); R.style.AppBottomSheetDialogTheme
        binding.ivBack.setOnClickListener(view -> dismiss());
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int maxHeight = (int) (height*0.7); //custom height of bottom sheet

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        ((BottomSheetBehavior) behavior).setPeekHeight(maxHeight);  //changed default peek height of bottom sheet

        if (behavior != null && behavior instanceof BottomSheetBehavior)
        {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
            {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState)
                {
                    String state = "";
                    switch (newState)
                    {
                        case BottomSheetBehavior.STATE_DRAGGING: {
                            //imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "DRAGGING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {
                            // imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "SETTLING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            // imgBtnClose.setVisibility(View.VISIBLE);
                            state = "EXPANDED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            //imgBtnClose.setVisibility(View.INVISIBLE);
                            state = "COLLAPSED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {
                            // imgBtnClose.setVisibility(View.INVISIBLE);
                            dismiss();
                            state = "HIDDEN";
                            break;
                        }
                    }
                    Log.i("BottomSheetFrag", "onStateChanged: "+ state);
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
        setUpChatList();

        //close dialog
        binding.ivBack.setOnClickListener(view -> dismiss());
        binding.tvSearch.setOnClickListener(view ->
        {
            ArrayList<CategoryPOJO> arrayList=new ArrayList<>();
            for (CategoryPOJO pojo : categoryPOJOS){
                if (pojo.getSelected()){
                    arrayList.add(pojo);
                }
            }

            onSearchClick.onSearch(arrayList);
            dismiss();
        }
        );

    }


    ArrayList<CategoryPOJO> categoryPOJOS;
    FilterDialogAdapter adapter;
    private void setUpChatList(){
        categoryPOJOS=new ArrayList<>();
        categoryPOJOS.add(new CategoryPOJO("Papda"));
        categoryPOJOS.add(new CategoryPOJO("Red Tilapia"));
        categoryPOJOS.add(new CategoryPOJO("Amur Carp"));
        categoryPOJOS.add(new CategoryPOJO("Genetically Improved Katla"));
        categoryPOJOS.add(new CategoryPOJO("Jayanti Rohu"));
        categoryPOJOS.add(new CategoryPOJO("Tilapia"));
        categoryPOJOS.add(new CategoryPOJO("Ornamental Fish"));
        categoryPOJOS.add(new CategoryPOJO("Singhi"));
        categoryPOJOS.add(new CategoryPOJO("Vietnam Kawai"));
        categoryPOJOS.add(new CategoryPOJO("Roopchanda"));
        categoryPOJOS.add(new CategoryPOJO("Catla"));
        categoryPOJOS.add(new CategoryPOJO("Other"));



        adapter=new FilterDialogAdapter(context,categoryPOJOS);
        binding.rvCategories.setAdapter(adapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
