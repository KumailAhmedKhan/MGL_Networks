package com.app.sample.social.MGLNetworks.Model;

import android.widget.ImageView;



public class CompanyDataModel  {
    private String CompanyId;
    private String CompanyName;
    private String CompanyPhoneNo;
    private String CompanyEmail;
    private String CompanyCountry;
    private String CompanyMGP;
    private String CompanyStatus;
    private String CompanyAddress;
    private String CompanyCity;
    private String CompanyWebsite;
/*
    public ImageView getCompanyImage() {
        return CompanyImage;
    }

    public void setCompanyImage(ImageView companyImage) {
        CompanyImage = companyImage;
    }

   // private ImageView CompanyImage;*/

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }

    private String CompanyLogo;

    public String getCompanyImageURL() {
        return CompanyImageURL;
    }

    public void setCompanyImageURL(String companyImageURL) {
        CompanyImageURL = companyImageURL;
    }

    private String CompanyImageURL;

    public String getCompanyOfficeNumber() {
        return CompanyOfficeNumber;
    }

    public void setCompanyOfficeNumber(String companyOfficeNumber) {
        CompanyOfficeNumber = companyOfficeNumber;
    }

    public String getCompanyFaxNumber() {
        return CompanyFaxNumber;
    }

    public void setCompanyFaxNumber(String companyFaxNumber) {
        CompanyFaxNumber = companyFaxNumber;
    }

    private String CompanyOfficeNumber;
    private String CompanyFaxNumber;

    public CompanyDataModel() {
    }

    public CompanyDataModel(String companyId, String companyName, String companyPhoneNo, String companyEmail, String companyCountry, String companyMGP, String companyStatus,
                            String companyAddress, String companyCity,String companyWebsite, String companyOfficeNumber,String companyFaxNumber) {
        CompanyId = companyId;
        CompanyName = companyName;
        CompanyPhoneNo = companyPhoneNo;
        CompanyEmail = companyEmail;
        CompanyCountry = companyCountry;
        CompanyMGP = companyMGP;
        CompanyStatus = companyStatus;
        CompanyAddress = companyAddress;
        CompanyCity = companyCity;
        CompanyWebsite = companyWebsite;
        CompanyFaxNumber = companyFaxNumber;
        CompanyOfficeNumber = companyOfficeNumber;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyPhoneNo() {
        return CompanyPhoneNo;
    }

    public void setCompanyPhoneNo(String companyPhoneNo) {
        CompanyPhoneNo = companyPhoneNo;
    }

    public String getCompanyEmail() {
        return CompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        CompanyEmail = companyEmail;
    }

    public String getCompanyCountry() {
        return CompanyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        CompanyCountry = companyCountry;
    }

    public String getCompanyMGP() {
        return CompanyMGP;
    }

    public void setCompanyMGP(String companyMGP) {
        CompanyMGP = companyMGP;
    }

    public String getCompanyStatus() {
        return CompanyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        CompanyStatus = companyStatus;
    }
    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return CompanyCity;
    }

    public void setCompanyCity(String companyCity) {
        CompanyCity = companyCity;
    }

    public String getCompanyWebsite() {
        return CompanyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        CompanyWebsite = companyWebsite;
    }

}
