<bootstrap:tab name="orgTab" type="flat">

    <bootstrap:tabItem name="ACCOUNT_TYPE" active="true" class= "orgTabTitle orgTabHeader" >
        <g:render template="/organization/tabs/type" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="PROFILE" active="true" class= "orgTabTitle">
        <g:render template="/organization/tabs/profile" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="OWNER" active="true" class= "orgTabTitle">
        <g:render template="/organization/tabs/owner" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
    <bootstrap:tabItem name="DOCUMENTS" active="true" class= "orgTabTitle">
        <g:render template="/organization/tabs/document" model="[orgInstance:orgInstance]"/>
    </bootstrap:tabItem>
</bootstrap:tab>
