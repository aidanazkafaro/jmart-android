package AidanAzkafaroDesonJmartFH.jmart_android.request;
import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import AidanAzkafaroDesonJmartFH.jmart_android.LoginActivity;

/**
 * handle top up account
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class TopUpRequest extends StringRequest {

    /**
     * URL untuk request Top Up berdasarkan id pengguna
     */
    private static final String URL =  "http://10.0.2.2:6969/account/" + LoginActivity.getLoggedAccount().id + "/topUp";
    private final Map<String , String> params;

    /**
     * Constructor TopUp
     * @param id    id user yang melakukan top up
     * @param balance saldo user
     * @param listener
     * @param errorListener
     */
    public TopUpRequest
            (
                    int id,
                    double balance,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("balance", Double.toString(balance));
    }

    /**
     *
     * @return params
     */
    public Map<String , String> getParams() {
        return params;
    }
}
