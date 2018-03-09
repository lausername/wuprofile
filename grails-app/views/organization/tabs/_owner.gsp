<%@ page import="com.webbfontaine.grails.plugins.utils.TypesCastUtils;" %>

<bootstrap:div id="ownerContainer">
    <bootstrap:grid grid="12-12-12">
        <bootstrap:formSection>
            <bootstrap:formGroup groupSpan="1">
            <wf:select name="module" from="${[
                    [key: 'MR', value: message(code: 'select.documentType.sad', default: 'MR')],
                    [key: 'MS', value: message(code: 'select.documentType.license', default: 'MS')],
                    [key: 'MRS', value: message(code: 'select.documentType.all', default: 'MRS')]]}"
                       value="${searchCommand?.module ?: ''}"
                       optionKey="key"
                       optionValue="value"
                       noSelection="['': '']"
                       class="selectTitle"/>
            </bootstrap:formGroup>
            <bootstrap:genericInput field="firstname" bean="${orgInstance}" labelCode="org.owner.firstname.label"
                                    inputSpan="3" maxlength="70"
                                    class="mandatory"/>
            <bootstrap:genericInput field="lastname" bean="${orgInstance}" labelCode="org.owner.lastname.label"
                                    inputSpan="3" maxlength="35"
                                    class="mandatory"/>
            <bootstrap:genericInput field="job" bean="${orgInstance}" labelCode="org.owner.job.label"
                                    inputSpan="3" maxlength="70"
                                    class="mandatory"/>
            <bootstrap:genericInput field="dnipassport" bean="${orgInstance}" labelCode="org.owner.dnipassport.label"
                                    inputSpan="3" maxlength="35"
                                    class="mandatory"/>
            <bootstrap:genericInput field="phone" bean="${orgInstance}" labelCode="org.owner.phone.label"
                                    inputSpan="3" maxlength="70"
                                    class="mandatory"/>
            <bootstrap:genericInput field="email" bean="${orgInstance}" labelCode="org.owner.email.label"
                                    inputSpan="3" maxlength="70"
                                    class="mandatory"/>
        </bootstrap:formSection>
    </bootstrap:grid>


</bootstrap:div>
