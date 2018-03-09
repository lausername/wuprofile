package com.webbfontaine.wu.profile

import com.webbfontaine.grails.plugins.conversation.ConversationService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OrganizationController {

    OrganizationService organizationService

    ConversationService conversationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond organizationService.list(params), model:[organizationCount: organizationService.count()]
    }

    def show(Long id) {
        respond organizationService.get(id)
    }

    def create() {
        respond new Organization(params)
    }

    def uploadFeaturedImage(Long restaurantId, Integer version, byte[] bytes, String contentType){
        Organization organization = new Organization(params)

        organization.featuredImageBytes = bytes
        organization.featuredImageContentType = contentType

        respond model: organization, view:'index'
    }

    def featuredImage(Organization organization) {
        if (organization == null || organization.featuredImageBytes == null) {
            notFound()
            return
        }
        render file: organization.featuredImageBytes, contentType: organization.featuredImageContentType
    }



    def save() {
//        Organization organization = conversationService.getInstanceFromConversation(params.conversationId)
        Organization organization = new Organization(params)

        if (organization == null) {
            notFound()
            return
        }

        try {
            organizationService.save(organization)
        } catch (ValidationException e) {
            render(view: '/organization/index', model: [orgInstance: organization])
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*' { respond organization, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond organizationService.get(id)
    }

    def update(Organization organization) {
        if (organization == null) {
            notFound()
            return
        }

        try {
            organizationService.save(organization)
        } catch (ValidationException e) {
            respond organization.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*'{ respond organization, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        organizationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
