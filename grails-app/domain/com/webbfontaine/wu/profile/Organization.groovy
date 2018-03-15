package com.webbfontaine.wu.profile

import com.webbfontaine.grails.plugins.taglibs.ConfigurableFields

class Organization implements ConfigurableFields {

    String accountType

    String tin

    String name

    String address

    String city

    String phone

    String website

    String email

    String country

    String fax

    List<AttachedFile> attachedFiles

    byte[] logo

    byte[] featuredImageBytes

    String featuredImageContentType

    static hasMany = [attachedFiles: AttachedFile]

    static constraints = {

    }

    static mapping = {
        logo column: 'picture', sqlType: 'longblob'
        logo null:true
        featuredImageBytes null:true
        featuredImageContentType null:true
    }

    @Override
    boolean isFieldMandatory(String fieldName) {
        return false
    }

    @Override
    boolean isFieldEditable(String fieldName) {
        return true
    }

    boolean isCompany(){
        WuConstants.COMPANY.equalsIgnoreCase(accountType)
    }

    boolean isBank(){
        WuConstants.BANK.equalsIgnoreCase(accountType)
    }

    boolean isInsurer(){
        WuConstants.INSURER.equalsIgnoreCase(accountType)
    }

    boolean isGovAgency(){
        WuConstants.GOVAGENCY.equalsIgnoreCase(accountType)
    }
}
