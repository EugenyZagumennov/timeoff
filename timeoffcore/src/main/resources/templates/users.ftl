<#import "/parts/common.ftl" as common>
<#import "/parts/logout.ftl" as logout>

<@common.common>
        <@logout.logout/>
        <#if message??>
          ${message}
        </#if>
        <form method="post" action="/users">
            <input type="text" name="login" placeholder="Логин" /><br>
            <input type="text" name="firstName" placeholder="Имя" /><br>
            <input type="text" name="lastName" placeholder="Фамилия" /><br>
            <input type="password" name="password" placeholder="Пароль"><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <select name="departmentId">
                <#list departments as d>
                <option value="${d.id}">${d.name}</option>
                </#list>
            </select><br>
            <button type="submit">Добавить</button>
        </form><br>

        <form method="get" action="/users">
            <input type="text" name="filter" placeholder="Имя" value="${filter}"/><br>
            <button type="submit">Найти</button>
        </form><br>

        <div>Список пользователей:</div>
        <table>
            <#list users as u>
            <tr>
                <td>${u.login}</td>
                <td>${u.firstName}</td>
                <td>${u.lastName}</td>
                <td>${u.role}</td>
                <td>${u.status}</td>
                <td>${u.department.name}</td>
                <td><a href="/users/${u.id}">Edit</a></td>
            </tr>
            </#list>
        </table>
</@common.common>