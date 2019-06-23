<#assign
    contextExists = Session.SPRING_SECURITY_CONTEXT??
>

<#if contextExists>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.getFullName()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        username = "Unknown User"
        isAdmin = false
    >
</#if>