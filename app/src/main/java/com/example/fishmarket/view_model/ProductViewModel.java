package com.example.fishmarket.view_model;

import static com.example.fishmarket.MainActivity.userId;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fishmarket.R;
import com.example.fishmarket.adapter.HomeAdapter;
import com.example.fishmarket.adapter.ProductCategoryAdapter;
import com.example.fishmarket.adapter.ProductSubCategoryAdapter;
import com.example.fishmarket.api_services.ApiManager;
import com.example.fishmarket.api_services.UrlContainer;
import com.example.fishmarket.model.CategoryPOJO;
import com.example.fishmarket.model.CommonModel;
import com.example.fishmarket.model.ProductPOJO;
import com.example.fishmarket.model.SubCategoryPOJO;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    public CategoryPOJO categoryPOJO;
    public ArrayList<CategoryPOJO> categoryPOJOS=new ArrayList<>();
    public ArrayList<CategoryPOJO> subCategoryList=new ArrayList<>();
    public ProductCategoryAdapter categoryAdapter=new ProductCategoryAdapter(categoryPOJOS);

    public void setUpData(){
        if (categoryPOJOS.size()>0){
            return;
        }
       // categoryPOJOS =new ArrayList<>();
        categoryPOJOS.add(new CategoryPOJO("AQUACULTURE EQUIPMENT", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("AQUACULTURE MEDICINE", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH FEED", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH PRODUCTS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FISH SEEDS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("FRESH FROZEN FISH (ice BOX)", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("LIVE FISH", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("RAW MATERIALS", R.drawable.feed));
        categoryPOJOS.add(new CategoryPOJO("TABLE SIZE FISH", R.drawable.feed));

        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));

        categoryAdapter.notifyDataSetChanged();

    }
    /*public ProductSubCategoryAdapter subCategoryAdapter=new ProductSubCategoryAdapter(subCategoryList);

    public void setUpSubCategoryData(){
        if (subCategoryList.size()>0){
            return;
        }
        subCategoryList=new ArrayList<>();
        ArrayList<SubCategoryPOJO> subCategoryPOJOS=new ArrayList<>();
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryPOJOS.add(new SubCategoryPOJO());
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));
        subCategoryList.add(new CategoryPOJO(false,subCategoryPOJOS));



        // categoryPOJOS.add(new CategoryPOJO("AQUARIUM", R.drawable.feed));

        subCategoryAdapter.notifyDataSetChanged();

    }
*/
   /* public ArrayList<CategoryPOJO> subCategoryPOJOS;

    public ArrayList<CategoryPOJO> addDataInSubCategory(){
        subCategoryPOJOS =new ArrayList<>();
        subCategoryPOJOS.add(new CategoryPOJO("FISHERIES EQUIPMENTS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("COLD CHAIN FACILITIES", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("BIO FLOC/ RAS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FISH FEED MILL/ PLANT EQUIPMENT", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("VEHICLES WITH ICE BOX", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("MARINE FISHERIES", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("SEA CAGE / RESERVOIRS CAGE/PEN", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("HATCHERY", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FISH VALUE ADD ENTERPRISE UNITS", R.drawable.department_fish));
        subCategoryPOJOS.add(new CategoryPOJO("FABRICATION MATERIAL ", R.drawable.department_fish));


        return  subCategoryPOJOS;
    }

*/
   public MutableLiveData<ArrayList<ProductPOJO>> mutableLiveData=new MutableLiveData<>();

    public MutableLiveData<CommonModel> successLiveData=new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData=new MutableLiveData<>();

    public HomeAdapter homeAdapter=new HomeAdapter();
    public void getProducts(String id){
        ApiManager.getApiService().getProductList(id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){

                    homeAdapter.updateList(response.body().productPOJOS);
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
       /* ArrayList<ProductPOJO> arrayList=new ArrayList<>();
        arrayList.add(new ProductPOJO("AIR BLOWER"));
        arrayList.add(new ProductPOJO("OXYGEN GENERATOR/DIFUSER"));
        arrayList.add(new ProductPOJO("ELECTRICAL PUMPS"));
        arrayList.add(new ProductPOJO("PUMP ACCESSORIES"));
        arrayList.add(new ProductPOJO("AERATOR/ PADDLE WHEEL AERATOR"));
        arrayList.add(new ProductPOJO("DO METER"));
        arrayList.add(new ProductPOJO("ELECTRONIC WEIGHING BALANCE"));
        arrayList.add(new ProductPOJO("COMPRESSORS"));
        arrayList.add(new ProductPOJO("GENERATOR SET"));
        arrayList.add(new ProductPOJO("FEED DISPENSER"));
        arrayList.add(new ProductPOJO("PLASTIC CRATES"));

        homeAdapter.updateList(arrayList);

        mutableLiveData.postValue(arrayList);*/
    }

    public void addToFavourite(ProductPOJO model){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("product_id",model.getId().toString());
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }
        ApiManager.getApiService().commonRequest(UrlContainer.FAVOURITE,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){
                    model.setProduct_favourite_status("Yes");

                    homeAdapter.updateIndex(model);
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
    }
    public void removeFromFavourite(ProductPOJO model){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("product_id",model.getId().toString());
        if (!userId.isEmpty()) {
            hashMap.put("user_id", userId);
        }

        ApiManager.getApiService().commonRequest(UrlContainer.UNFAVOURITE,hashMap).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful() && response.body().status){
                    model.setProduct_favourite_status("No");

                    homeAdapter.updateIndex(model);
                    successLiveData.postValue(response.body());

                }else if (response.message()!=null){
                    errorLiveData.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                errorLiveData.postValue("Something went wrong...");
            }
        });
    }


}
