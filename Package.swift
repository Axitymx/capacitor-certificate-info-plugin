// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CertificateInfo",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CertificateInfo",
            targets: ["CertificateInfoPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0"),
        .package(url: "https://github.com/filom/ASN1Decoder.git", branch: "master")
    ],
    targets: [
        .target(
            name: "CertificateInfoPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                .product(name: "ASN1Decoder", package: "ASN1Decoder")
            ],
            path: "ios/Sources/CertificateInfoPlugin"),
        .testTarget(
            name: "CertificateInfoPluginTests",
            dependencies: ["CertificateInfoPlugin"],
            path: "ios/Tests/CertificateInfoPluginTests")
    ]
)