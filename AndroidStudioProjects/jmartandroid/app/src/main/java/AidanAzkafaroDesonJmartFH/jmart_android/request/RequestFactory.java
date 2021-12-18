package AidanAzkafaroDesonJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import AidanAzkafaroDesonJmartFH.jmart_android.model.ProductCategory;

/**
 * handle beberapa request ke API
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class RequestFactory
{
    /**
     * URL untuk
     */
    private static final String URL_FORMAT_ID = "http://10.0.2.2:6969/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:6969/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_FILTER = "http://10.0.2.2:6969/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";
    private static final String URL_PAYMENT_ALL = "http://10.0.2.2:6969/payment/getPaymentTable";


    /**
     * method untuk mendapatkan history payment (unused, so far)
     * @param listener
     * @param errorListener
     * @return
     */
    public static StringRequest getPaymentTable
            (
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_PAYMENT_ALL);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /**
     * method untuk mendapatkan produk berdasarkan ID nya (unused, so far)
     * @param parentURI
     * @param id
     * @param listener
     * @param errorListener
     * @return
     */
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
     * mengembalikan page berdasarkan page dan pagesize
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
     * menampikan hasil filter terhadap produk berdasarkan parameter di bawah
     * @param page
     * @param pageSize
     * @param search
     * @param minPrice
     * @param maxPrice
     * @param category
     * @param conditionUsed
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
                    boolean conditionUsed,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_FILTER,page,pageSize,search,minPrice,maxPrice,category,conditionUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}

