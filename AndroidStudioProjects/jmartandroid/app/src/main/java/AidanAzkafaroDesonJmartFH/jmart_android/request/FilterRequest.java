package AidanAzkafaroDesonJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import AidanAzkafaroDesonJmartFH.jmart_android.model.ProductCategory;

/**
 * handle request untuk Filter
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class FilterRequest extends StringRequest {

    /**
     * URL untuk digunakan saat request filter
     */
    private static final String URL_FORMAT_PRODUCT = "http://10.0.2.2:6969/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";
    private final Map<String , String> params;

    /**
     * Instantiates a new Filter request.
     *
     * @param page          the page
     * @param pageSize      the page size
     * @param accountId     the account id
     * @param search        the search
     * @param minPrice      the min price
     * @param maxPrice      the max price
     * @param category      the category
     * @param conditionUsed the condition used
     * @param listener      the listener
     * @param errorListener the error listener
     */
    public FilterRequest(int page, int pageSize, int accountId, String search, int minPrice, int maxPrice, ProductCategory category, boolean conditionUsed,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.GET, URL_FORMAT_PRODUCT, listener, errorListener);
        params = new HashMap<>();
        params.put("page", Integer.toString(page));
        params.put("pageSize", Integer.toString(pageSize));
        params.put("accountId", Integer.toString(accountId));
        params.put("search", search);
        params.put("minPrice", Integer.toString(minPrice));
        params.put("maxPrice", Integer.toString(maxPrice));
        params.put("category", category.toString());
        params.put("conditionUsed", String.valueOf(conditionUsed));
    }

    public Map<String , String> getParams() {
        return params;
    }
}
