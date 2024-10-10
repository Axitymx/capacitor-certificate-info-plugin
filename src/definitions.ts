/// <reference types="@capacitor/cli"/>

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    CertificateInfo?: {
      /**
       * Path where certificates are stored within the application. Must start with *public/*. The path must be define in Angular.json file.
       *
       * @default "public/certificates"
       */
      certificatesPath?: string;


      /**
       * Only for Android.
       * The format of the output file. Check the [documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/text/SimpleDateFormat.html) to get more information. The default value use the **ISO8601** format,
       * 
       * @default "yyyy-MM-dd'T'HH:mm:ss'Z'" 
       */
      dateFormat?:string;
    };
  }
}

export type CertFilename = {
  /**
   * Name of the certification file
   */
  filename: string;
}

export type CertExpiration = {
  /**
   * Expiration date in ISO 8601 format
   */
  expirationDate:string;
}

export type CertExpirations = {
  /**
   * List of expiration dates in ISO 8601 format
   */
  expirationDates: string [];
}

export type CertDate = {
  /**
   * Date in format ISO 8601
   */
  date:string;
}

export type CertValid = {
  /**
   * Status of the certificate
   */
  valid: boolean; 
}

export interface CertificateInfoPlugin {

  /**
   * Get the expiration date of the certification file. 
   * @param params CertFile
   */
  getExpirationDate(params: CertFilename): Promise<CertExpiration>;

  /**
   * Get the expiration date of each file in the certificate directory.
   */
  getExpirationDates(): Promise<CertExpirations>;

  /**
   * Check if any certificate in directory is valid compared to today. If param is define, check each certificate with the given date.
   * @param params CertDate | undefined
   */
  checkValidation(params?:CertDate): Promise<CertValid>;
}
