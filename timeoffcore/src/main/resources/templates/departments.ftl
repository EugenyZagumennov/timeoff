<#import "/parts/common.ftl" as common>

<@common.common>
    <form method="post" action="/departments">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               name="name" placeholder="Название"/>
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <br>
        <select name="parentId">
          <option value="" selected>Выберите родительский департамент</option>
          <#list departments as d>
            <option value="${d.id}">${d.name}</option>
          </#list>
        </select><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить</button>
    </form><br>

    <form method="get" action="/departments">
        <input type="text" name="filter" placeholder="Название" value="${filter}"/><br>
        <button type="submit">Найти</button>
    </form><br>

    <div>Список департаментов:</div>
    <table>
        <#list departments as d>
        <tr>
            <td>${d.name}</td>
        </tr>
        <#else>
            No departments available
        </#list>
    </table>
</@common.common>