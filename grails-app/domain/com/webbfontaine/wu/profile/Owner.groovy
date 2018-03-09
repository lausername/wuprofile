package com.webbfontaine.wu.profile

class Owner {

    String salutation

    String firstName

    String lastName

    String job

    String dniOrPassport

    String phone

    String email

    byte[] picture

    static constraints = {
    }

    static mapping = {
        picture column: 'picture', sqlType: 'longblob'
    }
}
