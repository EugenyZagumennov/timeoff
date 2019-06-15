<#import "/parts/common.ftl" as common>
<#import "/parts/logout.ftl" as logout>

<@common.common>
<@logout.logout/>
    <form method="post" action="/departments">
        <input type="text" name="name" placeholder="Название" /><br>
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
        </#list>
    </table>
</@common.common>