<%@ page import="com.webbfontaine.grails.plugins.utils.TypesCastUtils;" %>

<bootstrap:div id="documentContainer">
    <wfConversation:conversationSetup conversationId="${params.conversationId ?: ''}"/>
    <bootstrap:icon name="plus"/>

    <bootstrap:submitButton type="success" action="save" controller="organization" labelCode="save.label.button" name="save"/>

</bootstrap:div>
