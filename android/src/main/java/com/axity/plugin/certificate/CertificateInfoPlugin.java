package com.axity.plugin.certificate;

import android.util.Log;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@CapacitorPlugin(name = "CertificateInfo")
public class CertificateInfoPlugin extends Plugin {

    private CertificateInfo implementation;

    @Override
    public void load() {
        CertificateInfoConfig config = getCertificateInfoConfig();

        implementation = new CertificateInfo(getContext(), config);
    }

    @PluginMethod
    public void getExpirationDate(PluginCall call) {
        try {
            String filename = call.getString("filename");
            Date expirationDate = implementation.getExpirationDate(filename);

            JSObject response = new JSObject();
            response.put("expirationDate", implementation.formatDateToISO(expirationDate));

            call.resolve(response);
        } catch (CertificateInfoException e) {
            call.reject(e.getMessage(), "500");
        } catch (Exception e) {
            call.reject("Uknown error", "500");
        }
    }

    @PluginMethod
    public void getExpirationDates(PluginCall call) {
        try {
            List<String> expirationStringDates = new ArrayList<>();
            List<Date> expirationDates = implementation.getExpirationDates();

            for (Date exp : expirationDates) {
                expirationStringDates.add(implementation.formatDateToISO(exp));
            }

            JSObject response = new JSObject();
            response.put("expirationDates", new JSONArray(expirationStringDates));

            call.resolve(response);
        } catch (CertificateInfoException e) {
            call.reject(e.getMessage(), "500");
        } catch (Exception e) {
            call.reject("Uknown error", "500");
        }
    }

    @PluginMethod
    public void checkValidation(PluginCall call) {
        try {
            String date = call.getString("date", "");
            boolean valid = implementation.checkValidation(date);
            JSObject response = new JSObject();
            response.put("valid", valid);

            call.resolve(response);
        } catch (CertificateInfoException e) {
            call.reject(e.getMessage(), "500");
        } catch (Exception e) {
            call.reject("Uknown error", "500");
        }
    }

    private CertificateInfoConfig getCertificateInfoConfig() {
        CertificateInfoConfig config = new CertificateInfoConfig();

        String certificatePath = getConfig().getString("certificatesPath", config.getCertificatePath());
        config.setCertificatePath(certificatePath);

        String dateFormat = getConfig().getString("dateFormat", config.getDateFormat());
        config.setDateFormat(dateFormat);

        return config;
    }
}
