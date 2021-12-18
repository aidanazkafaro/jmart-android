package AidanAzkafaroDesonJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import AidanAzkafaroDesonJmartFH.jmart_android.model.ProductCategory;

public class RequestFactory
{
    /**
     * URL untuk
     */
    private static final String URL_FORMAT_ID = "http://10.0.2.2:6969/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:6969/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_FILTER = "http://10.0.2.2:6969/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";
    private static final String URL_PAYMENT_ALL = "http://10.0.2.2:6969/payment/getPaymentTable";


    public static StringRequest getPaymentTable
            (
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_PAYMENT_ALL);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /**
     * @param parentURI
     * @param page
     * @param pageSize
     * @param listener
     * @param errorListener
     * @return
     */
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /**
     * @param page 
     * @param pageSize
     * @param search
     * @param minPrice
     * @param maxPrice
     * @param category
     * @param conditioUsed
     * @param listener
     * @param errorListener
     * @return
     */
    public static StringRequest getProductFiltered
            (
                    int page,
                    int pageSize,
                    String search,
                    double minPrice,
                    double maxPrice,
                    ProductCategory category,
                    boolean conditioUsed,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_FILTER,page,pageSize,search,minPrice,maxPrice,category, conditioUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}

