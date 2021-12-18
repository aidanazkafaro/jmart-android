package AidanAzkafaroDesonJmartFH.jmart_android.request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * handle request untuk Register Account
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class RegisterRequest extends StringRequest {
    //URL untuk mengarahkan ke end point register
    private static final String URL =  "http://10.0.2.2:6969/account/register";
    private final Map<String , String> params;

    /**
     *
     * @param name
     * @param email
     * @param password
     * @param listener
     * @param errorListener
     */
    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    /**
     *
     * @return params
     */
    public Map<String , String> getParams() {
        return params;
    }
}
