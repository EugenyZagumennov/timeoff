<#import "/parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.common>
    <form method="post" enctype="multipart/form-data" action="/users/edit/${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="id" value="${user.id}" />
        <label><@spring.message "edituser.login"/>  <input type="text" name="login" value="${user.login}" /></label><br>
        <label><@spring.message "edituser.firstname"/>    <input type="text" name="firstName" value="${user.firstName}" /></label><br>
        <label><@spring.message "edituser.lastname"/><input type="text" name="lastName" value="${user.lastName}" /></label><br>
        <label><@spring.message "edituser.password"/> <input type="password" name="password" value=""></label><br>
        <label><@spring.message "edituser.avatar"/> <input type="file" name="file" accept="image/jpeg,image/png"/></label><br>
        <label><@spring.message "edituser.dep"/>
            <select name="departmentId">
                <#list departments as d>
                    <option value="${d.id}" ${(user.department.id == d.id)?then("selected", "")}>${d.name}</option>
                </#list>
            </select></label><br>
        <label><@spring.message "edituser.role"/>
            <select name="role">
                <#list roles as r>
                    <option value="${r.name()}" ${(user.role.name() == r.name())?then("selected", "")}>${r.name()}</option>
                </#list>
            </select></label><br>
        <label><@spring.message "edituser.status"/>
            <select name="status">
                <#list statuses as s>
                  <option value="${s.name()}" ${(user.status.name() == s.name())?then("selected", "")}>${s.name()}</option>
                </#list>
            </select></label><br>
        <button type="submit"><@spring.message "edituser.save"/></button>
    </form><br>
    <a href="/users"><@spring.message "edituser.back"/></a>
</@common.common>