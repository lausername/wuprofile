<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<content tag="application">
    <bootstrap:div id="formContent">
        <layout:pageResources dependsOn="application">
            <wf:tagLibSetup locale="${request.locale}" validation="all"/>
            <wf:utilsSetup confirmDialog="true"/>
        </layout:pageResources>
        <wf:confirmDialog model="[formName: 'appMainForm']"/>
        <g:render template="/organization/create/create" model="[orgInstance: orgInstance]"/>
    </bootstrap:div>
</content>
</body>
</html>