package com.webbfontaine.wu.profile

class AttachedFile implements Serializable {
    Long id

    byte[] data

    static belongsTo = [organization: Organization]

    static constraints = {
        data nullable: true
        organization nullable: true
    }

    static mapping = {
        data sqlType: 'BLOB'
    }
}

