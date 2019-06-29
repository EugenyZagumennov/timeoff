<#import "/parts/common.ftl" as common>
<#include "/parts/security.ftl">

<@common.common>
    <form method="get" action="/users">
        <input type="text" name="filter" placeholder="Имя" value="${filter?ifExists}"/><br>
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
            <td><img src="/files/avatar/${u.login}"/></td>
            <td><a href="/users/${u.id}">Edit</a></td>
        </tr>
        <#else>
            No users available
        </#list>
    </table>

    <button ${isAdmin?then("", "disabled")} class="btn btn-primary my-2" type="button" data-toggle="collapse" data-target="#createUser" aria-expanded="false" aria-controls="createUser">
        Создать нового пользователя
    </button>
    <#if message??>${message}<br></#if>
    <div class="collapse" id="createUser">
        <div class="card card-body">
            <form method="post" enctype="multipart/form-data" action="/users">
                <input type="text" name="login" placeholder="Логин" /><br>
                <input type="text" name="firstName" placeholder="Имя" /><br>
                <input type="text" name="lastName" placeholder="Фамилия" /><br>
                <input type="password" name="password" placeholder="Пароль"><br>
                <input type="file" name="file" accept="image/jpeg,image/png"/><br>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <select name="departmentId">
                    <#list departments as d>
                    <option value="${d.id}">${d.name}</option>
                    </#list>
                </select><br>
                <button type="submit">Добавить</button>
            </form>
        </div>
    </div>
</@common.common>