package AidanAzkafaroDesonJmartFH.jmart_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;

import AidanAzkafaroDesonJmartFH.jmart_android.model.Product;
import AidanAzkafaroDesonJmartFH.jmart_android.request.RequestFactory;

public class ProductFragment extends Fragment {

    ProductFragmentListener fragmentListener;
    public static ArrayList<Product> productsList = new ArrayList<>();
    static ArrayAdapter<Product> listViewAdapter;
    static ArrayAdapter<Product> listViewAdapter2;

    public static ListView listView;


    public final int pageSize = 20;
    static int page = 0;
    private static final Gson gson = new Gson();
    public static Product productClicked = null;

    public interface ProductFragmentListener {
        void getProductList(ArrayAdapter<Product> listViewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View productView = inflater.inflate(R.layout.fragment_product,container,false);
        listView = (ListView) productView.findViewById(R.id.list_view);
        Button prevButton = (Button) productView.findViewById(R.id.btnPrev);
        Button nextButton = (Button) productView.findViewById(R.id.btnNext);
        Button goButton = (Button) productView.findViewById(R.id.btnGo);
        EditText inputPage = (EditText) productView.findViewById(R.id.inputPage);

        if (FilterFragment.status == 1){
            listViewAdapter2 = new ArrayAdapter<Product>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    FilterFragment.filteredList
            );
            listView.setAdapter(listViewAdapter2);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //productClicked = gson.fromJson(lv.getItemAtPosition(i).toString(),Product.class);
                    productClicked = (Product) listView.getItemAtPosition(i);
                    Toast.makeText(getActivity(),"See Product", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductFragment.this.getActivity(), ProductDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray object = new JSONArray(response);
                        if(object != null){
                            productsList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Product>>(){}.getType()); // line 6
                            System.out.println("LIST PRODUCT: " +  productsList);
                            listViewAdapter = new ArrayAdapter<Product>(
                                    getActivity(),
                                    android.R.layout.simple_list_item_1,
                                    productsList
                            );

                            listView.setAdapter(listViewAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    //productClicked = gson.fromJson(lv.getItemAtPosition(i).toString(),Product.class);
                                    productClicked = (Product) listView.getItemAtPosition(i);
                                    Toast.makeText(getActivity(),"See Product", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ProductFragment.this.getActivity(), ProductDetailActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            //action saat next button diklik
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"Next Page", Toast.LENGTH_SHORT).show();
                    page += 1; //increment page untuk pindah ke page berikutnya
                    getActivity().finish();
                    getActivity().startActivity(getActivity().getIntent());
                }
            });

            //action saat prev button diklik
            prevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"Prev Page", Toast.LENGTH_SHORT).show();
                    page -= 1; //decrement page by 1 untuk pindah ke page sebelumnya
                    //hanya berlaku saat page != 0
                    if(page < 0){
                        page = 0;
                    }
                    getActivity().finish();
                    getActivity().startActivity(getActivity().getIntent());
                }
            });

            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Go!", Toast.LENGTH_SHORT).show();
                    page = Integer.parseInt(inputPage.getText().toString()) - 1;
                    getActivity().finish();
                    getActivity().startActivity(getActivity().getIntent());
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            requestQueue.add(RequestFactory.getPage("product",page,pageSize,listener,null));
        }
        return productView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ProductFragmentListener){
            fragmentListener = (ProductFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ProductFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }
}