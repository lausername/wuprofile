package com.webbfontaine.wu.profile

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class RegistrationServiceSpec extends Specification implements ServiceUnitTest<RegistrationService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test register"() {
        expect:"when a organization registers it is recorded in org and owners"
        service.register(new Organization(), new Owner())
        Organization.list() == 1
        Owner.list() == 1
    }
}
