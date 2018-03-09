<%@ page import="com.webbfontaine.grails.plugins.utils.TypesCastUtils;" %>

<bootstrap:div id="typeContainer">
    <bootstrap:grid grid="2-2-2">
        <wf:radio name="impactOnRevenue" id="isCompany" value="true"
                  class="rar-radio"
                  checked="${orgInstance?.isCompany()}"/>
        <g:message code="org.account.type.company.label" default="Company"/>
    </bootstrap:grid>

    <bootstrap:grid grid="12-12-12">
        <wf:radio name="impactOnRevenue" id="isBank" value="true"
                  class="rar-radio"
                  checked="${orgInstance?.isCompany()}"/>
        <g:message code="org.account.type.bank.label" default="Bank"/>
    </bootstrap:grid>

    <bootstrap:grid grid="12-12-12">
        <wf:radio name="impactOnRevenue" id="isInsurer" value="true"
                  class="rar-radio"
                  checked="${orgInstance?.isCompany()}"/>
        <g:message code="org.account.type.insurer.label" default="Insurer"/>
    </bootstrap:grid>

    <bootstrap:grid grid="12-12-12">
        <wf:radio name="impactOnRevenue" id="isGovAgency" value="true"
                  class="rar-radio"
                  checked="${orgInstance?.isCompany()}"/>
        <g:message code="org.account.type.govagency.label" default="Government Agency"/>
    </bootstrap:grid>
</bootstrap:div>
