package douglas.develop.utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;

public class Utils {

    public static String nomeUser() throws JSONException {
        String name = (String) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String[] parts = name.split("\\.");
        String decode = decode(parts[1]);
        JSONObject payload = null;
        try {
            payload = new JSONObject(decode(parts[1]));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String userId = payload.getString("sub");
        return userId;
    }

    public static Integer clientUser() throws JSONException {
        String client = (String) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String[] parts = client.split("\\.");
        JSONObject payload = null;
        try {
            payload = new JSONObject(decode(parts[1]));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Integer clientId = payload.getInt("client");
        return clientId;
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
