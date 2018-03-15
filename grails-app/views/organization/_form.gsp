<bootstrap:tab name="orgTab" type="flat">
    <wfConversation:conversationSetup conversationId="${params.conversationId ?: ''}"/>
    <bootstrap:tabItem name="ACCOUNT_TYPE" active="${params.activeTab == 'ACCOUNT_TYPE'}" class= "orgTabTitle orgTabHeader" >
        <g:render template="/organization/tabs/type" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="PROFILE" active="${params.activeTab == 'PROFILE'}"  class= "orgTabTitle">
        <g:render template="/organization/tabs/profile" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="OWNER" active="${params.activeTab == 'OWNER'}"  class= "orgTabTitle">
        <g:render template="/organization/tabs/owner" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="DOCUMENTS" active="${params.activeTab == 'DOCUMENTS'}"  class= "orgTabTitle">
        <g:render template="/organization/tabs/document" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
</bootstrap:tab>
