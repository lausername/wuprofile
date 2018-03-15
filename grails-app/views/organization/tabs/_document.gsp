<%@ page import="com.webbfontaine.grails.plugins.utils.TypesCastUtils;" %>

<bootstrap:div id="documentContainer">
    %{--<wfConversation:conversationSetup conversationId="${params.conversationId ?: ''}"/>--}%
    <bootstrap:icon name="plus"/>

    <g:actionSubmit value="Submit" action="save"></g:actionSubmit>
    %{--<bootstrap:formActions type="success" action="save" controller="organization" labelCode="save.label.button" name="save"/>--}%

</bootstrap:div>
