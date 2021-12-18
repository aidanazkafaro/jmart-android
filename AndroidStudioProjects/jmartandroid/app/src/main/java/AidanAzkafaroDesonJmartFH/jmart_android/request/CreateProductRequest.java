package AidanAzkafaroDesonJmartFH.jmart_android.request;
import androidx.annotation.Nullable;

import AidanAzkafaroDesonJmartFH.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * handle request untuk Create Product
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class CreateProductRequest extends StringRequest {

    /**
     * URL yang dipakai untuk membuat product.
     */
    public static final String URL = "http://10.0.2.2:6969/product/create";
    public final Map<String,String> params;

    /**
     * Instantiates a new Create product request.
     *
     * @param name          the name
     * @param weight        the weight
     * @param conditionUsed the condition used
     * @param price         the price
     * @param discount      the discount
     * @param category      the category
     * @param shipmentPlans the shipment plans
     * @param listener      the listener
     * @param errorListener the error listener
     */
    public CreateProductRequest(String name, String weight, String conditionUsed, String price, String discount, String category, String shipmentPlans, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        Integer i = LoginActivity.loggedAccount.id;
        params.put("accountId", i.toString());
        params.put("name",name);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("price", price);
        params.put("discount", discount);
        params.put("category", category);
        params.put("shipmentPlans", shipmentPlans);
    }

    public Map<String,String> getParams(){return params;}
}