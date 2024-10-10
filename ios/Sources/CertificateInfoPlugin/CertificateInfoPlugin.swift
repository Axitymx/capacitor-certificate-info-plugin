import Foundation
import Capacitor

@objc(CertificateInfoPlugin)
public class CertificateInfoPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "CertificateInfoPlugin"
    public let jsName = "CertificateInfo"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "getExpirationDate", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getExpirationDates", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "checkValidation", returnType: CAPPluginReturnPromise),
    ]
  private var implementation: CertificateInfo?

  override public func load() {
    self.implementation = CertificateInfo(config: getCertificateInfoConfig())
  }
  
  @objc func getExpirationDate(_ call: CAPPluginCall) {
          do {
            let filename: String = (call.getString("filename") ?? "").trimmingCharacters(in: .whitespacesAndNewlines);
            
            let certData = try implementation!.getExpirationDate(from: filename);
            
            call.resolve([
              "expirationDate": implementation!.formatDateToISO(certData),
            ])
          } catch let error as CertificateInforError {
            let errorMessage = CertificateInfoErrorMessages.getErrorMessage(error: error)
            call.reject(errorMessage, "500")
            
          }catch {
            call.reject("Uknown error", "500")
        }
      }
  
  @objc func getExpirationDates(_ call: CAPPluginCall) {
          do {
            var expirationStringDates: [String] = []
            let expirationDates = try implementation!.getExpirationDates()
            
            for expirationDate in expirationDates {
              expirationStringDates.append(implementation!.formatDateToISO(expirationDate))
            }
            
            call.resolve([
              "expirationDates": expirationStringDates,
            ])
          } catch let error as CertificateInforError {
            let errorMessage = CertificateInfoErrorMessages.getErrorMessage(error: error)
            call.reject(errorMessage, "500")
            
          }catch {
            call.reject("Uknown error", "500")
        }
      }
  
  @objc func checkValidation(_ call: CAPPluginCall){
    do {
      let dateToCompare = call.getString("date");
      
      let certData = try implementation!.checkValidation(date: dateToCompare)
      
      call.resolve([
        "valid": certData,
      ])
    } catch let error as CertificateInforError {
      let errorMessage = CertificateInfoErrorMessages.getErrorMessage(error: error)
      call.reject(errorMessage, "500")
      
    }catch {
      call.reject("Uknown error", "500")
  }
  }
  
  
  private func getCertificateInfoConfig() -> CertificateInfoConfig {
    var config = CertificateInfoConfig();
    
    if let path = getConfig().getString("certificatesPath") {
      config.certificatePath = path
    }
    
    return config
  }
  
}
