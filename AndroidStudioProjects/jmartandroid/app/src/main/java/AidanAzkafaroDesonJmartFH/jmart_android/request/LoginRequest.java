package AidanAzkafaroDesonJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * handle request untuk Login
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class LoginRequest extends StringRequest {

    /**
     * URL untuk membuat login request
     */
    private static final String URL = "http://10.0.2.2:6969/account/login";
    private final Map<String , String> params;

    /**
     * Instantiates a new Login request.
     *
     * @param email         the email
     * @param password      the password
     * @param listener      the listener
     * @param errorListener the error listener
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
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