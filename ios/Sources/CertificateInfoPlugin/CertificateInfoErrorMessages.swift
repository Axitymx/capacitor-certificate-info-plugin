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
    public static let fileNameEmpty =  "ERROR: File name must be provide."
    public static let readDirectoryFiles = "ERROR: Could not read directory files."
    public static let certificateFormatSupport = "ERROR: Format error. Please only use X.509 certificates."
    public static let extractDateError = "ERROR: Could not extract date from certificate."

    public static let fileNotFound: String = "ERROR: File was not found: "
    public static let readFileError: String = "ERROR: Could not read file: "
    public static let genericErrorMessage: String = "ERROR: Something went wrong: "

    public static let generalError: String = "ERROR: Some error was launched."

    public static func getErrorMessage(error: CertificateInforError) -> String {
        switch error {
        case .fileNameEmpty:
            return fileNameEmpty
        case .fileNotFound(let filename):
            return fileNotFound + filename
        case .readFileError(let filename):
            return readFileError + filename
        case .readDirectoryFiles:
            return readDirectoryFiles
        case .certificateFormatSupport:
            return certificateFormatSupport
        case .extractDateError:
            return extractDateError
        case .genericError(let message):
            return genericErrorMessage + message
        }
    }
}
