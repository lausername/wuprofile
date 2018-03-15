<%@ page import="com.webbfontaine.grails.plugins.utils.TypesCastUtils;" %>
<bootstrap:div id="profileContainer">
    <bootstrap:grid grid="12-12-12">
            <g:if test="${orgInstance?.featuredImageBytes}">
                <img src="<g:createLink controller="organization" action="featuredImage" id="${orgInstance?.id}" params="${[conversationId: params.conversationId]}"/>" width="400"/>
            </g:if>

            <g:uploadForm name="uploadFeaturedImage" controller="organization" action="uploadFeaturedImage">
                <g:hiddenField name="conversationId" value="${params.conversationId}"/>
                <input type="file" name="imageFile" id="attachmentUpload" multiple/>
                <input type="submit" id='submitMyAttachment' />

                <bootstrap:formSection>

                    <bootstrap:genericInput field="tin" bean="${orgInstance}" labelCode="org.profile.tin.label"
                                            inputSpan="3" maxlength="35"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="name" bean="${orgInstance}" labelCode="org.profile.name.label"
                                            inputSpan="3" maxlength="70"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="address" bean="${orgInstance}" labelCode="org.profile.address.label"
                                            inputSpan="3" maxlength="35"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="city" bean="${orgInstance}" labelCode="org.profile.city.label"
                                            inputSpan="3" maxlength="70"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="phone" bean="${orgInstance}" labelCode="org.profile.phone.label"
                                            inputSpan="3" maxlength="35"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="country" bean="${orgInstance}" labelCode="org.profile.country.label"
                                            inputSpan="3" maxlength="70"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="fax" bean="${orgInstance}" labelCode="org.profile.fax.label"
                                            inputSpan="3" maxlength="70"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="website" bean="${orgInstance}" labelCode="org.profile.website.label"
                                            inputSpan="3" maxlength="35"
                                            class="mandatory"/>
                    <bootstrap:genericInput field="email" bean="${orgInstance}" labelCode="org.profile.email.label"
                                            inputSpan="3" maxlength="70"
                                            class="mandatory"/>
                </bootstrap:formSection>
            </g:uploadForm>

    </bootstrap:grid>


</bootstrap:div>
