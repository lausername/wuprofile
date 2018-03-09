import grails.util.Environment

/**
 * Copyrights 2002-2018 Webb Fontaine
 * Developer: Diego Israel K. Villanueva
 * Date: 08/03/2018
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */

grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations =false

com {
    webbfontaine {
        grails {
            plugins {
                security {
                    active = false
                    auth = false
                    config = "Requestmap"


                    if (Environment.current == Environment.DEVELOPMENT) {
                        ignore = ['/dbconsole/**']

                    }else if (Environment.current == Environment.PRODUCTION){
                        grails.plugin.springsecurity.securityConfigType = "Annotation"

                    }
                }
            }
        }
    }
}
