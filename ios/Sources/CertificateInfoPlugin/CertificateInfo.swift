import Foundation
import Security
import ASN1Decoder




@objc public class CertificateInfo: NSObject {
  
  var config: CertificateInfoConfig = CertificateInfoConfig()
  
  public init(config: CertificateInfoConfig) {
    self.config = config;
  }
  
  @objc public func getExpirationDate(from filename: String) throws -> Date {
    
    if(filename.isEmpty){
      throw CertificateInforError.fileNameEmpty
    }
    
    let cerdata = try openCertificate(filename:filename)
    
    
    if let expirationDate = cerdata.notAfter {
      return expirationDate

    } else {
      throw CertificateInforError.extractDateError
    }
  }
  
  @objc public func getExpirationDates() throws -> [Date] {
    var expirationDates: [Date] = []
    
    let cerFiles = try openCertificates()
    
    for cerFile in cerFiles {
      if let expirationDate = cerFile.notAfter {
        expirationDates.append(expirationDate)
      }
    }
    
    return expirationDates
  }
  
  @objc public func checkValidation(date: String?) throws -> NSNumber {
    let today = Calendar.current.startOfDay(for: Date());
    
    var dayToCompare: Date
    if let dateString = date {
      dayToCompare = parseDate(date: dateString)
    }else{
      dayToCompare = today
    }
    
    
    let certificates = try openCertificates()
    
    for certificate in certificates {
      
      if(!certificate.checkValidity(dayToCompare)){
          if let exp = certificate.notAfter {
              print("Some certificate is expired " + formatDateToISO(exp) )
          }
        return NSNumber(value: false)
      }
    }
    
    return NSNumber(value: true)
  }

  
  public func formatDateToISO(_ date: Date) -> String {

    let isoFormatter = ISO8601DateFormatter()
    let isoDateString = isoFormatter.string(from: date)

    return isoDateString
  }
  
  private func parseDate(date: String) -> Date {

    let isoDateFormatter = ISO8601DateFormatter()
    isoDateFormatter.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
    if let date = isoDateFormatter.date(from: date) {
       return date
    }
    
    print("WARNING: Error parsing date, returning current date")
    return Calendar.current.startOfDay(for: Date())
  }
  
  
  private func openCertificates() throws -> [X509Certificate] {
    var certificates: [X509Certificate] = []
    let fileManager = FileManager.default
    let bundlePath = "\(Bundle.main.resourcePath!)/\(self.config.certificatePath)"

    guard let directoryContents = try? fileManager.contentsOfDirectory(atPath: bundlePath) else {
      throw CertificateInforError.readDirectoryFiles
    }
    
    let cerFiles = directoryContents.filter { $0.hasSuffix(self.config.CERTIFICATE_EXTENSION) }
  
    for file in cerFiles {
      let certificate = try self.openCertificate(filename: file)
      certificates.append( certificate )
    }
    
    return certificates
  }
  
  private func openCertificate(filename: String) throws -> X509Certificate {
    
    let fileWithoutExtension = (filename as NSString).deletingPathExtension
    let cerPath = "\(self.config.certificatePath)/\(fileWithoutExtension)"
    
    guard let certificatePath = Bundle.main.path(forResource:cerPath, ofType: "cer") else {
      throw CertificateInforError.fileNotFound(filename)
    }
    
    guard let certificateData = try? Data(contentsOf: URL(fileURLWithPath: certificatePath)) else {
      throw CertificateInforError.readFileError(filename)
    }
    
    guard let cerX509 = try? X509Certificate(data: certificateData) else {
      throw CertificateInforError.certificateFormatSupport
    }
    
    return cerX509
  }
}
