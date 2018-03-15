package com.webbfontaine.wu.profile

import com.webbfontaine.wu.profile.conversation.ConversationService
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional
class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    OrganizationService organizationService

    ConversationService conversationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        Organization organization = new Organization()
        conversationService.addToSessionStore(organization)
        [model: organization]
    }

    def show(Long id) {
        respond organizationService.get(id)
    }

    def create() {
        Organization organization = new Organization(params)
        conversationService.addToSessionStore(organization)
        respond new Organization(params)
    }

    def uploadFeaturedImage(){
        Organization organization = conversationService.mergeConversationInstance()
        if(!params?.imageFile?.filename?.isEmpty()) {
            organization.featuredImageBytes = params.imageFile.bytes
            organization.featuredImageContentType = params.imageFile.contentType
        }
        params.activeTab = 'PROFILE'
        conversationService.addToSessionStore(organization)
        render(view: '/organization/index', model: [orgInstance: organization])
    }

    def featuredImage() {
        Organization organization = conversationService.mergeConversationInstance()
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
