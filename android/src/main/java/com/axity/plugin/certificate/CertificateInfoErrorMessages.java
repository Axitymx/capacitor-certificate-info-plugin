package com.axity.plugin.certificate;

enum CertificateInfoError {
    FILE_NAME_EMPTY,
    FILE_NOT_FOUND,
    READ_FILE_ERROR,
    READ_DIRECTORY_FILES,
    CERTIFICATE_FORMAT_SUPPORT,
    EXTRACT_DATE_ERROR,
    GENERIC_ERROR
}

class CertificateInfoErrorMessages {

    public static final String FILE_NAME_EMPTY = "ERROR: File name must be provided.";
    public static final String READ_DIRECTORY_FILES = "ERROR: Could not read directory files.";
    public static final String CERTIFICATE_FORMAT_SUPPORT = "ERROR: Format error. Please only use X.509 certificates.";
    public static final String EXTRACT_DATE_ERROR = "ERROR: Could not extract date from certificate.";

    public static final String FILE_NOT_FOUND = "ERROR: File was not found: ";
    public static final String READ_FILE_ERROR = "ERROR: Could not read file: ";
    public static final String GENERIC_ERROR_MESSAGE = "ERROR: Something went wrong: ";

    public static final String GENERAL_ERROR = "ERROR: Some error was launched.";

    public static String getErrorMessage(CertificateInfoError error, String... details) {
        return switch (error) {
            case FILE_NAME_EMPTY -> FILE_NAME_EMPTY;
            case FILE_NOT_FOUND -> FILE_NOT_FOUND + (details.length > 0 ? details[0] : "");
            case READ_FILE_ERROR -> READ_FILE_ERROR + (details.length > 0 ? details[0] : "");
            case READ_DIRECTORY_FILES -> READ_DIRECTORY_FILES;
            case CERTIFICATE_FORMAT_SUPPORT -> CERTIFICATE_FORMAT_SUPPORT;
            case EXTRACT_DATE_ERROR -> EXTRACT_DATE_ERROR;
            case GENERIC_ERROR -> GENERIC_ERROR_MESSAGE + (details.length > 0 ? details[0] : "");
            default -> GENERAL_ERROR;
        };
    }
}

class CertificateInfoException extends Exception {

    private final CertificateInfoError error;

    public CertificateInfoException(CertificateInfoError error) {
        super(CertificateInfoErrorMessages.getErrorMessage(error));
        this.error = error;
    }

    public CertificateInfoException(CertificateInfoError error, String detail) {
        super(CertificateInfoErrorMessages.getErrorMessage(error, detail));
        this.error = error;
    }

    public CertificateInfoError getError() {
        return error;
    }
}
