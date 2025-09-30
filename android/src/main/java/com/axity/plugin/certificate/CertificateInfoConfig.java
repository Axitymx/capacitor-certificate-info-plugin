package com.axity.plugin.certificate;

public class CertificateInfoConfig {

    public final String CERTIFICATE_EXTENSION = ".cer";
    public final String CERTIFICATE_FORMAT = "X.509";
    public final String PARSE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final String TIME_ZONE = "UTC";

    private String certificatePath = "public/certificates";

    private String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    // Setters
    public void setCertificatePath(String path) {
        this.certificatePath = path;
    }

    public void setDateFormat(String format) {
        this.dateFormat = format;
    }

    // Getters

    public String getCertificatePath() {
        return this.certificatePath;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }
}
