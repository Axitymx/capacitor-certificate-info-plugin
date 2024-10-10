
public enum CertificateInforError: Error {
  case fileNameEmpty
  case fileNotFound(String)
  case readFileError(String)
  case readDirectoryFiles
  case certificateFormatSupport
  case extractDateError
  case genericError(String)
}


public struct CertificateInfoErrorMessages {
  public static let FILE_NAME_EMPTY =  "ERROR: File name must be provide."
  public static let READ_DIRECTORY_FILES = "ERROR: Could not read directory files."
  public static let CERTIFICATE_FORMAT_SUPPORT = "ERROR: Format error. Please only use X.509 certificates."
  public static let EXTRACT_DATE_ERROR = "ERROR: Could not extract date from certificate."
  
  public static let FILE_NOT_FOUND : String = "ERROR: File was not found: "
  public static let READ_FILE_ERROR: String = "ERROR: Could not read file: "
  public static let GENERIC_ERROR_MESSAGE: String = "ERROR: Something went wrong: "
  
  public static let GENERAL_ERROR: String = "ERROR: Some error was launched."
  
  public static func getErrorMessage(error: CertificateInforError) -> String {
    switch error {
       case .fileNameEmpty:
           return FILE_NAME_EMPTY
       case .fileNotFound(let filename):
           return FILE_NOT_FOUND + filename
       case .readFileError(let filename):
           return READ_FILE_ERROR + filename
       case .readDirectoryFiles:
           return READ_DIRECTORY_FILES
       case .certificateFormatSupport:
           return CERTIFICATE_FORMAT_SUPPORT
       case .extractDateError:
           return EXTRACT_DATE_ERROR
       case .genericError(let message):
           return GENERIC_ERROR_MESSAGE + message
       }
  }
}
