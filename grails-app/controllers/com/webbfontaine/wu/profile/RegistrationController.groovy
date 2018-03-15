package com.webbfontaine.wu.profile

class RegistrationController {

    OrganizationService organizationService

    RegistrationService registrationService



    def uploadFeaturedImage(FeaturedImageCommand cmd) {
        if (cmd == null) {
//            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond(cmd.errors, model: [restaurant: cmd], view: 'editFeaturedImage')
            return
        }

        def organization = organizationService.uploadFeatureImage()

        if (organization == null) {
//            notFound()
            return
        }

        if (organization.hasErrors()) {
            respond(organization.errors, model: [restaurant: restaurant], view: 'editFeaturedImage')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), restaurant.id])
                redirect organization
            }
            '*' { respond organization, [status: OK] }
        }
    }



}
