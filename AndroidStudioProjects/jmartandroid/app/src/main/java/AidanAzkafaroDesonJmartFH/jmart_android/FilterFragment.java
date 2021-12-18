package AidanAzkafaroDesonJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;
import AidanAzkafaroDesonJmartFH.jmart_android.model.ProductCategory;
import AidanAzkafaroDesonJmartFH.jmart_android.request.FilterRequest;
import AidanAzkafaroDesonJmartFH.jmart_android.request.RequestFactory;

/**
 * Class untuk fragment bagian Filter
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class FilterFragment extends Fragment{

    //inisialisasi instance variable
    private static final Gson gson = new Gson();

    //ArrayList untuk menampung hasil lists yang sudah difilter
    public static ArrayList<Product> filteredList = new ArrayList<>();

    //ArrayAdapter untuk menampilan filteredList
    public static ArrayAdapter<Product> listViewAdapterFilter;

    //jumlah produk yang ditampilkan dalam satu page
    final int pageSize = 20;

    //variabel status menunjukkan apakah filter sudah dilakukan
    public static int status = 0;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        //inisiasi komponen yang digunakan dari xml
        TextInputLayout filterName = (TextInputLayout) view.findViewById(R.id.nameFilter);
        TextInputLayout filterLowestPrice = (TextInputLayout) view.findViewById(R.id.lowestPriceFilter);
        TextInputLayout filterHighestPrice = (TextInputLayout) view.findViewById(R.id.highestPriceFilter);
        CheckBox newProduct = (CheckBox) view.findViewById(R.id.checkBoxNew);
        CheckBox usedProduct = (CheckBox) view.findViewById(R.id.checkBoxUsed);
        Spinner productCategory = (Spinner) view.findViewById(R.id.productCategory);
        Button applyButton = (Button)  view.findViewById(R.id.btnApply);
        Button clearButton = (Button)  view.findViewById(R.id.btnClear);

        //action saat button apply diklik
        //akan menerapkan filter
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "apply button clicked", Toast.LENGTH_SHORT).show();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray object = new JSONArray(response);
                            if(object != null){
                                //memasukkan hasil filter ke ArrayList filteredList
                                filteredList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Product>>(){}.getType());
                                System.out.println("FILTERED LIST: " + filteredList);
                                //konversi filteredList ke ArrayAdapter agar bisa ditampilkan di aplikasi
                                listViewAdapterFilter = new ArrayAdapter<Product>(
                                        getActivity(),
                                        android.R.layout.simple_list_item_1,
                                        filteredList
                                );

                                //tampilkan di listView pada productFragment
                                //ProductFragment.listView.setAdapter(listViewAdapter);
                                //listView.setAdapter(listViewAdapter);
                                Toast.makeText(getActivity(),"filtered",Toast.LENGTH_SHORT).show();

                                //status 1 berarti filter sudah dilakukan
                                status = 1;

                            }else{
                                Toast.makeText(getActivity(),"tidak ada data",Toast.LENGTH_SHORT).show();
                            }

                            //refresh activity
                            getActivity().finish();
                            getActivity().overridePendingTransition(0,0);
                            getActivity().startActivity(getActivity().getIntent());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                //melakukan request ke API untuk menjalankan method getProductFiltered
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                requestQueue.add(RequestFactory.getProductFiltered(ProductFragment.page,pageSize,filterName.getEditText().getText().toString(),Double.parseDouble(filterLowestPrice.getEditText().getText().toString()),Double.parseDouble(filterHighestPrice.getEditText().getText().toString()),ProductCategory.valueOf(productCategory.getSelectedItem().toString()), usedProduct.isChecked(),listener,null));

            }
        });


        //mengosongkan parameter yang digunakan untuk memfilter
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "clear button clicked", Toast.LENGTH_SHORT).show();
                status = 0;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        return view;
    }
}

