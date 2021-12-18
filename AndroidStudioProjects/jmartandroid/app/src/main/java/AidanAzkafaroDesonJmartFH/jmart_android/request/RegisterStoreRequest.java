package AidanAzkafaroDesonJmartFH.jmart_android.request;
import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import AidanAzkafaroDesonJmartFH.jmart_android.LoginActivity;

public class RegisterStoreRequest extends StringRequest {
    /**
     * URL untuk register store
     */
    private static final String URL =  "http://10.0.2.2:6969/account/" + LoginActivity.getLoggedAccount().id + "/registerStore";
    private final Map<String , String> params;


    /**
     * @param id
     * @param name
     * @param address
     * @param phoneNumber
     * @param listener
     * @param errorListener
     */
    public RegisterStoreRequest(int id, String name, String address, String phoneNumber, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        Integer  i = id;
        params = new HashMap<>();
        params.put("id", i.toString());
        params.put("name", name);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
    }

    public Map<String , String> getParams() {
        return params;
    }
}
