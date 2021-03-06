<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/com.liferay.portal.settings.web/init.jsp" %>

<%
NtlmConfiguration ntlmConfiguration = ConfigurationProviderUtil.getConfiguration(NtlmConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE, new CompanyServiceSettingsLocator(company.getCompanyId(), NtlmConstants.SERVICE_NAME)));

boolean enabled = ntlmConfiguration.enabled();
String domainController = ntlmConfiguration.domainController();
String domainControllerName = ntlmConfiguration.domainControllerName();
String domain = ntlmConfiguration.domain();
String negotiateFlags = ntlmConfiguration.negotiateFlags();
String serviceAccount = ntlmConfiguration.serviceAccount();

String servicePassword = ntlmConfiguration.servicePassword();

if (Validator.isNotNull(servicePassword)) {
	servicePassword = Portal.TEMP_OBFUSCATION_VALUE;
}
%>

<aui:fieldset>
	<aui:input name="<%= ActionRequest.ACTION_NAME %>" type="hidden" value="/portal_settings/ntlm" />

	<aui:input label="enabled" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "enabled" %>' type="checkbox" value="<%= enabled %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain-controller" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domainController" %>' type="text" value="<%= domainController %>" />

	<aui:input cssClass="lfr-input-text-container" helpMessage="domain-controller-name-help" label="domain-controller-name" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domainControllerName" %>' type="text" value="<%= domainControllerName %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domain" %>' type="text" value="<%= domain %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-account" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "serviceAccount" %>' type="text" value="<%= serviceAccount %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-password" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "servicePassword" %>' type="password" value="<%= servicePassword %>" />

	<aui:input cssClass="lfr-input-text-container" label="negotiate-flags" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "negotiateFlags" %>' type="text" value="<%= negotiateFlags %>" />

	<aui:button-row>
		<portlet:actionURL name="/portal_settings/ntlm_delete" var="resetValuesURL">
			<portlet:param name="tabs1" value="ntlm" />
		</portlet:actionURL>

		<%
		String taglibOnClick = "if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-reset-the-configured-values") + "')) {submitForm(document.hrefFm, '" + resetValuesURL.toString() + "');}";
		%>

		<aui:button cssClass="btn-lg" onClick="<%= taglibOnClick %>" value="reset-values" />
	</aui:button-row>
</aui:fieldset>