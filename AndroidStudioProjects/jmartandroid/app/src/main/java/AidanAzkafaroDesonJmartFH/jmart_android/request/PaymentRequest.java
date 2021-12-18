package AidanAzkafaroDesonJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * handle request untuk Payment
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class PaymentRequest extends StringRequest {

    /**
     * URL untuk melakukan pembayaran
     */
    private static final String URL_PAYMENT_REQUEST = "http://10.0.2.2:6969/payment/create";
    /**
     * URL untuk trace pembelanjaan user
     */
    private static final String URL_PAYMENT_BY_USER = "http://10.0.2.2:6969/payment/byAccount?buyerId=%s";

    private final Map<String , String> params;

    /**
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipmentAddress
     * @param shipmentPlan
     * @param listener
     * @param errorListener
     */
    public PaymentRequest(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL_PAYMENT_REQUEST, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId", Integer.toString(buyerId));
        params.put("productId", Integer.toString(productId));
        params.put("productCount", Integer.toString(productCount));
        params.put("shipmentAddress", shipmentAddress);
        params.put("shipmentPlan", Byte.toString(shipmentPlan));
    }

    public Map<String , String> getParams() {
        return params;
    }

    /**
     * @param userId
     * @param listener
     * @param errorListener
     * @return
     */
    public static StringRequest getPaymentByUser(int userId,Response.Listener<String> listener, Response.ErrorListener errorListener){
        String url = String.format(URL_PAYMENT_BY_USER,userId);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

}