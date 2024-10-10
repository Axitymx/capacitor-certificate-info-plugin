package com.axity.plugin.certificate;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CertificateInfo {
  private final Context context;

  private final CertificateInfoConfig config;

  public CertificateInfo(Context context, CertificateInfoConfig config) {
    this.context = context;
    this.config = config;
  }

  public Date getExpirationDate(String filename) throws Exception {
    if (filename == null) {
      throw new CertificateInfoException(CertificateInfoError.FILE_NAME_EMPTY);
    }

    X509Certificate cert = openCertificate(filename);

    return cert.getNotAfter();
  }

  public List<Date> getExpirationDates() throws Exception {
    List<Date> expirationDates = new ArrayList<>();

    List<X509Certificate> certificates = openCertificates();

    for (X509Certificate certificate : certificates) {
      expirationDates.add(certificate.getNotAfter());
    }

    return expirationDates;
  }

  public boolean checkValidation(String date) throws Exception {

    Date dateToCompare = getToday();

    if(!date.isEmpty()){
      dateToCompare = parseDate(date);
    }

    List<X509Certificate> certificates = openCertificates();
    try{

      for (X509Certificate certificate : certificates) {
          certificate.checkValidity(dateToCompare);
      }

      return true;

    }catch(CertificateExpiredException | CertificateNotYetValidException e){
      Log.i("CERTIFICATE_INFO", "Some certificate is expired" + e.getMessage());
      return false;
    }
  }

  public String formatDateToISO(Date dt) {
    SimpleDateFormat isoFormat = new SimpleDateFormat(config.getDateFormat(), java.util.Locale.getDefault());
    return isoFormat.format(dt);
  }

  private Date parseDate(String date){
    SimpleDateFormat sdf = new SimpleDateFormat(config.PARSE_DATE_FORMAT, Locale.getDefault());
    sdf.setTimeZone(TimeZone.getTimeZone(config.TIME_ZONE));

    try {
      return sdf.parse(date);
    } catch (ParseException e) {
      Log.i("CERTIFICATE_INFO", "WARNING: Error parsing date, returning current date");
      return getToday();
    }

  }

  private List<X509Certificate> openCertificates() throws Exception {
    List<X509Certificate> certificates = new ArrayList<>();
    AssetManager assetManager = context.getAssets();
    String[] certificateFiles = assetManager.list(config.getCertificatePath());

    if (certificateFiles == null) {
      throw new CertificateInfoException(CertificateInfoError.READ_DIRECTORY_FILES);
    }

    for (String fileName : certificateFiles) {
      if (fileName.endsWith(config.CERTIFICATE_EXTENSION)) {
        X509Certificate certificate = openCertificate(fileName);
        certificates.add(certificate);
      }
    }

    return certificates;
  }

  private X509Certificate openCertificate(String filename) throws Exception {
    try {
      AssetManager assetManager = context.getAssets();
      InputStream inputStream = assetManager.open(config.getCertificatePath() + "/" + filename);

      CertificateFactory cf = CertificateFactory.getInstance(config.CERTIFICATE_FORMAT);
      Certificate cert = cf.generateCertificate(inputStream);
      inputStream.close();

      if (cert instanceof X509Certificate x509Cert) {
        return x509Cert;
      } else {
        throw new CertificateInfoException(CertificateInfoError.CERTIFICATE_FORMAT_SUPPORT);
      }

    } catch (IOException e) {
      throw new CertificateInfoException(CertificateInfoError.FILE_NOT_FOUND, filename);
    }catch (CertificateException e){
      throw new CertificateInfoException(CertificateInfoError.READ_FILE_ERROR, filename);
    }

  }

  private Date getToday(){
    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.MILLISECOND, 0);

    return today.getTime();
  }

}
