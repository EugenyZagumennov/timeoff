<#import "/parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>

<@common.common>
    <form method="post" action="/departments">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               name="name" placeholder="<@spring.message "departments.name"/>"/>
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <br>
        <select name="parentId">
          <option value="" selected><@spring.message "departments.chooseParentDep"/></option>
          <#list departments as d>
            <option value="${d.id}">${d.name}</option>
          </#list>
        </select><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit"><@spring.message "departments.add"/></button>
    </form><br>

    <form method="get" action="/departments">
        <input type="text" name="filter" placeholder="<@spring.message "departments.name"/>" value="${filter}"/><br>
        <button type="submit"><@spring.message "departments.find"/></button>
    </form><br>

    <div><@spring.message "departments.depList"/></div>
    <table>
        <#list departments as d>
        <tr>
            <td>${d.name}</td>
        </tr>
        <#else>
            <@spring.message "departments.noDeps"/>
        </#list>
    </table>
</@common.common>