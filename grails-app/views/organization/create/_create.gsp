<bootstrap:form small="${true}" name="appMainForm" id="appMainForm"  action="save" class="form-horizontal">
    <bootstrap:div id="formContent">
        <g:render template="/organization/temps/formContent" model="[orgInstance:orgInstance]"/>
    </bootstrap:div>
</bootstrap:form>