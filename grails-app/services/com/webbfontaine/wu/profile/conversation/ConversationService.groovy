package com.webbfontaine.wu.profile.conversation

import com.webbfontaine.wu.profile.Organization
import grails.web.databinding.DataBinder

import static com.webbfontaine.grails.plugins.utils.WebRequestUtils.*


class ConversationService implements DataBinder {

    def sessionStoreService

    Organization mergeConversationInstance() {
        Organization organization = findFromSessionStore()
        if (organization) {
            bindData(organization, getParams())
            return organization
        } else {
            log.error("ConversationService findFromSessionStore() | Conversation id: ${getParams()?.conversationId}")
            return new Organization(getParams())
        }
    }

    def addToSessionStore(Object object, conversationId = null){
        if (conversationId) {
            getParams().conversationId = sessionStoreService.put(conversationId, object)
        } else {
            getParams().conversationId = sessionStoreService.put(object)
        }
    }


    def findFromSessionStore() {
        Organization org = sessionStoreService.get(getParams().conversationId) as Organization
        return org
    }
}
