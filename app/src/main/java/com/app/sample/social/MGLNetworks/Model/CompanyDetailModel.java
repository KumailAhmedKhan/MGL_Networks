package com.app.sample.social.MGLNetworks.Model;

/*
    CI=CompanyInformation 
    KCD =Key Contact Detail
    RCD = Reference Key Detail
*/
 
public class CompanyDetailModel
{
    private String CICurrentAddress;
    private String CICountry;
    private String CIAddress;
    private String CIOfficeNumber;
    private String CIFaxNumber;
    private String CIRegistrationNumber;
    private String CIEstablishmentYear;
    private String CIAnnualTurnover;
    private String CITotalEmployees;
    private String CIServices;
    private String CIBranches;
    private String CIIATACode;
    private String CI_MTO_Certifications;
    private String CI_IATA_Certifications;
    private String CI_FIATA_Certifications;
    private String CI_NAFL_Certifications;

    public String getCICity() {
        return CICity;
    }

    public void setCICity(String CICity) {
        this.CICity = CICity;
    }

    private String CICity;

    public String getCI_MTO_Certifications() {
        return CI_MTO_Certifications;
    }

    public void setCI_MTO_Certifications(String CI_MTO_Certifications) {
        this.CI_MTO_Certifications = CI_MTO_Certifications;
    }

    public String getCI_IATA_Certifications() {
        return CI_IATA_Certifications;
    }

    public void setCI_IATA_Certifications(String CI_IATA_Certifications) {
        this.CI_IATA_Certifications = CI_IATA_Certifications;
    }

    public String getCI_FIATA_Certifications() {
        return CI_FIATA_Certifications;
    }

    public void setCI_FIATA_Certifications(String CI_FIATA_Certifications) {
        this.CI_FIATA_Certifications = CI_FIATA_Certifications;
    }

    public String getCI_NAFL_Certifications() {
        return CI_NAFL_Certifications;
    }

    public void setCI_NAFL_Certifications(String CI_NAFL_Certifications) {
        this.CI_NAFL_Certifications = CI_NAFL_Certifications;
    }

    public String getCI_AEO_Certifications() {
        return CI_AEO_Certifications;
    }

    public void setCI_AEO_Certifications(String CI_AEO_Certifications) {
        this.CI_AEO_Certifications = CI_AEO_Certifications;
    }

    public String getCI_Custom_Certifications() {
        return CI_Custom_Certifications;
    }

    public void setCI_Custom_Certifications(String CI_Custom_Certifications) {
        this.CI_Custom_Certifications = CI_Custom_Certifications;
    }

    public String getCI_DG_Certifications() {
        return CI_DG_Certifications;
    }

    public void setCI_DG_Certifications(String CI_DG_Certifications) {
        this.CI_DG_Certifications = CI_DG_Certifications;
    }

    public String getCI_ISO_Certifications() {
        return CI_ISO_Certifications;
    }

    public void setCI_ISO_Certifications(String CI_ISO_Certifications) {
        this.CI_ISO_Certifications = CI_ISO_Certifications;
    }

    public String getCI_COC_Certifications() {
        return CI_COC_Certifications;
    }

    public void setCI_COC_Certifications(String CI_COC_Certifications) {
        this.CI_COC_Certifications = CI_COC_Certifications;
    }

    public String getCI_OtherNet_Certifications() {
        return CI_OtherNet_Certifications;
    }

    public void setCI_OtherNet_Certifications(String CI_OtherNet_Certifications) {
        this.CI_OtherNet_Certifications = CI_OtherNet_Certifications;
    }

    private String CI_AEO_Certifications;
    private String CI_Custom_Certifications;
    private String CI_DG_Certifications;
    private String CI_ISO_Certifications;
    private String CI_COC_Certifications;
    private String CI_OtherNet_Certifications;




    private String KCD1Name;
    private String KCD1Designation;
    private String KCD1OfficeNumber;
    private String KCD1MobileNumber;
    private String KCD1Email;
    private String KCD1Skype;
    private String KCD2Name;
    private String KCD2Designation;
    private String KCD2OfficeNumber;
    private String KCD2MobileNumber;
    private String KCD2Email;
    private String KCD2Skype;
    private String RCD1CompanyName;
    private String RCD1Contact;
    private String RCD1TypeOfBusiness;
    private String RCD1Designation;
    private String RCD1Telephone;
    private String RCD1FaxNumber;
    private String RCD1Email;
    private String RCD2CompanyName;
    private String RCD2Contact;
    private String RCD2TypeOfBusiness;
    private String RCD2Designation;
    private String RCD2Telephone;
    private String RCD2FaxNumber;
    private String RCD2Email;


    public CompanyDetailModel(String CICurrentAddress, String country, String CIAddress, String CIOfficeNumber, String CIFaxNumber, String CIRegistrationNumber, String CIEstablishmentYear, String CIAnnualTurnover, String CITotalEmployees, String CIServices, String CIBranches, String CIIATACode, String CICertifications, String KCD1Name, String KCD1Designation, String KCD1OfficeNumber, String KCD1MobileNumber, String KCD1Email, String KCD1Skype, String KCD2Name, String KCD2Designation, String KCD2OfficeNumber, String KCD2MobileNumber, String KCD2Email, String KCD2Skype, String RCD1CompanyName, String RCD1Contact, String RCD1TypeOfBusiness, String RCD1Designation, String RCD1Telephone, String RCD1FaxNumber, String RCD1Email, String RCD2CompanyName, String RCD2Contact, String RCD2TypeOfBusiness, String RCD2Designation, String RCD2Telephone, String RCD2FaxNumber, String RCD2Email) {
        this.CICurrentAddress = CICurrentAddress;
        CICountry = country;
        this.CIAddress = CIAddress;
        this.CIOfficeNumber = CIOfficeNumber;
        this.CIFaxNumber = CIFaxNumber;
        this.CIRegistrationNumber = CIRegistrationNumber;
        this.CIEstablishmentYear = CIEstablishmentYear;
        this.CIAnnualTurnover = CIAnnualTurnover;
        this.CITotalEmployees = CITotalEmployees;
        this.CIServices = CIServices;
        this.CIBranches = CIBranches;
        this.CIIATACode = CIIATACode;
        this.KCD1Name = KCD1Name;
        this.KCD1Designation = KCD1Designation;
        this.KCD1OfficeNumber = KCD1OfficeNumber;
        this.KCD1MobileNumber = KCD1MobileNumber;
        this.KCD1Email = KCD1Email;
        this.KCD1Skype = KCD1Skype;
        this.KCD2Name = KCD2Name;
        this.KCD2Designation = KCD2Designation;
        this.KCD2OfficeNumber = KCD2OfficeNumber;
        this.KCD2MobileNumber = KCD2MobileNumber;
        this.KCD2Email = KCD2Email;
        this.KCD2Skype = KCD2Skype;
        this.RCD1CompanyName = RCD1CompanyName;
        this.RCD1Contact = RCD1Contact;
        this.RCD1TypeOfBusiness = RCD1TypeOfBusiness;
        this.RCD1Designation = RCD1Designation;
        this.RCD1Telephone = RCD1Telephone;
        this.RCD1FaxNumber = RCD1FaxNumber;
        this.RCD1Email = RCD1Email;
        this.RCD2CompanyName = RCD2CompanyName;
        this.RCD2Contact = RCD2Contact;
        this.RCD2TypeOfBusiness = RCD2TypeOfBusiness;
        this.RCD2Designation = RCD2Designation;
        this.RCD2Telephone = RCD2Telephone;
        this.RCD2FaxNumber = RCD2FaxNumber;
        this.RCD2Email = RCD2Email;
    }

    public CompanyDetailModel() {
    }

    public String getCICurrentAddress() {
        return CICurrentAddress;
    }

    public void setCICurrentAddress(String CICurrentAddress) {
        this.CICurrentAddress = CICurrentAddress;
    }

    public String getCICountry() {
        return CICountry;
    }

    public void setCICountry(String country) {
        CICountry = country;
    }

    public String getCIAddress() {
        return CIAddress;
    }

    public void setCIAddress(String CIAddress) {
        this.CIAddress = CIAddress;
    }

    public String getCIOfficeNumber() {
        return CIOfficeNumber;
    }

    public void setCIOfficeNumber(String CIOfficeNumber) {
        this.CIOfficeNumber = CIOfficeNumber;
    }

    public String getCIFaxNumber() {
        return CIFaxNumber;
    }

    public void setCIFaxNumber(String CIFaxNumber) {
        this.CIFaxNumber = CIFaxNumber;
    }

    public String getCIRegistrationNumber() {
        return CIRegistrationNumber;
    }

    public void setCIRegistrationNumber(String CIRegistrationNumber) {
        this.CIRegistrationNumber = CIRegistrationNumber;
    }

    public String getCIEstablishmentYear() {
        return CIEstablishmentYear;
    }

    public void setCIEstablishmentYear(String CIEstablishmentYear) {
        this.CIEstablishmentYear = CIEstablishmentYear;
    }

    public String getCIAnnualTurnover() {
        return CIAnnualTurnover;
    }

    public void setCIAnnualTurnover(String CIAnnualTurnover) {
        this.CIAnnualTurnover = CIAnnualTurnover;
    }

    public String getCITotalEmployees() {
        return CITotalEmployees;
    }

    public void setCITotalEmployees(String CITotalEmployees) {
        this.CITotalEmployees = CITotalEmployees;
    }

    public String getCIServices() {
        return CIServices;
    }

    public void setCIServices(String CIServices) {
        this.CIServices = CIServices;
    }

    public String getCIBranches() {
        return CIBranches;
    }

    public void setCIBranches(String CIBranches) {
        this.CIBranches = CIBranches;
    }

    public String getCIIATACode() {
        return CIIATACode;
    }

    public void setCIIATACode(String CIIATACode) {
        this.CIIATACode = CIIATACode;
    }



    public String getKCD1Name() {
        return KCD1Name;
    }

    public void setKCD1Name(String KCD1Name) {
        this.KCD1Name = KCD1Name;
    }

    public String getKCD1Designation() {
        return KCD1Designation;
    }

    public void setKCD1Designation(String KCD1Designation) {
        this.KCD1Designation = KCD1Designation;
    }

    public String getKCD1OfficeNumber() {
        return KCD1OfficeNumber;
    }

    public void setKCD1OfficeNumber(String KCD1OfficeNumber) {
        this.KCD1OfficeNumber = KCD1OfficeNumber;
    }

    public String getKCD1MobileNumber() {
        return KCD1MobileNumber;
    }

    public void setKCD1MobileNumber(String KCD1MobileNumber) {
        this.KCD1MobileNumber = KCD1MobileNumber;
    }

    public String getKCD1Email() {
        return KCD1Email;
    }

    public void setKCD1Email(String KCD1Email) {
        this.KCD1Email = KCD1Email;
    }

    public String getKCD1Skype() {
        return KCD1Skype;
    }

    public void setKCD1Skype(String KCD1Skype) {
        this.KCD1Skype = KCD1Skype;
    }

    public String getKCD2Name() {
        return KCD2Name;
    }

    public void setKCD2Name(String KCD2Name) {
        this.KCD2Name = KCD2Name;
    }

    public String getKCD2Designation() {
        return KCD2Designation;
    }

    public void setKCD2Designation(String KCD2Designation) {
        this.KCD2Designation = KCD2Designation;
    }

    public String getKCD2OfficeNumber() {
        return KCD2OfficeNumber;
    }

    public void setKCD2OfficeNumber(String KCD2OfficeNumber) {
        this.KCD2OfficeNumber = KCD2OfficeNumber;
    }

    public String getKCD2MobileNumber() {
        return KCD2MobileNumber;
    }

    public void setKCD2MobileNumber(String KCD2MobileNumber) {
        this.KCD2MobileNumber = KCD2MobileNumber;
    }

    public String getKCD2Email() {
        return KCD2Email;
    }

    public void setKCD2Email(String KCD2Email) {
        this.KCD2Email = KCD2Email;
    }

    public String getKCD2Skype() {
        return KCD2Skype;
    }

    public void setKCD2Skype(String KCD2Skype) {
        this.KCD2Skype = KCD2Skype;
    }

    public String getRCD1CompanyName() {
        return RCD1CompanyName;
    }

    public void setRCD1CompanyName(String RCD1CompanyName) {
        this.RCD1CompanyName = RCD1CompanyName;
    }

    public String getRCD1Contact() {
        return RCD1Contact;
    }

    public void setRCD1Contact(String RCD1Contact) {
        this.RCD1Contact = RCD1Contact;
    }

    public String getRCD1TypeOfBusiness() {
        return RCD1TypeOfBusiness;
    }

    public void setRCD1TypeOfBusiness(String RCD1TypeOfBusiness) {
        this.RCD1TypeOfBusiness = RCD1TypeOfBusiness;
    }

    public String getRCD1Designation() {
        return RCD1Designation;
    }

    public void setRCD1Designation(String RCD1Designation) {
        this.RCD1Designation = RCD1Designation;
    }

    public String getRCD1Telephone() {
        return RCD1Telephone;
    }

    public void setRCD1Telephone(String RCD1Telephone) {
        this.RCD1Telephone = RCD1Telephone;
    }

    public String getRCD1FaxNumber() {
        return RCD1FaxNumber;
    }

    public void setRCD1FaxNumber(String RCD1FaxNumber) {
        this.RCD1FaxNumber = RCD1FaxNumber;
    }

    public String getRCD1Email() {
        return RCD1Email;
    }

    public void setRCD1Email(String RCD1Email) {
        this.RCD1Email = RCD1Email;
    }

    public String getRCD2CompanyName() {
        return RCD2CompanyName;
    }

    public void setRCD2CompanyName(String RCD2CompanyName) {
        this.RCD2CompanyName = RCD2CompanyName;
    }

    public String getRCD2Contact() {
        return RCD2Contact;
    }

    public void setRCD2Contact(String RCD2Contact) {
        this.RCD2Contact = RCD2Contact;
    }

    public String getRCD2TypeOfBusiness() {
        return RCD2TypeOfBusiness;
    }

    public void setRCD2TypeOfBusiness(String RCD2TypeOfBusiness) {
        this.RCD2TypeOfBusiness = RCD2TypeOfBusiness;
    }

    public String getRCD2Designation() {
        return RCD2Designation;
    }

    public void setRCD2Designation(String RCD2Designation) {
        this.RCD2Designation = RCD2Designation;
    }

    public String getRCD2Telephone() {
        return RCD2Telephone;
    }

    public void setRCD2Telephone(String RCD2Telephone) {
        this.RCD2Telephone = RCD2Telephone;
    }

    public String getRCD2FaxNumber() {
        return RCD2FaxNumber;
    }

    public void setRCD2FaxNumber(String RCD2FaxNumber) {
        this.RCD2FaxNumber = RCD2FaxNumber;
    }

    public String getRCD2Email() {
        return RCD2Email;
    }

    public void setRCD2Email(String RCD2Email) {
        this.RCD2Email = RCD2Email;
    }
}
