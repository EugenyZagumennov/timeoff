<#import "/parts/common.ftl" as common>
<#import "/parts/logout.ftl" as logout>

<@common.common>
    <@logout.logout/>
    <form method="post" action="/users/edit/${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="hidden" name="id" value="${user.id}" />
        <label>Логин  <input type="text" name="login" value="${user.login}" /></label><br>
        <label>Имя    <input type="text" name="firstName" value="${user.firstName}" /></label><br>
        <label>Фамилия<input type="text" name="lastName" value="${user.lastName}" /></label><br>
        <label>Пароль <input type="password" name="password" value=""></label><br>
        <label>Департамент
            <select name="departmentId">
                <#list departments as d>
                    <option value="${d.id}" >${d.name}</option>
                </#list>
            </select></label><br>
        <label>Роль
            <select name="role">
                <#list roles as r>
                    <option value="${r.name()}" >${r.name()}</option>
                </#list>
            </select></label><br>
        <label>Роль
            <select name="status">
                <#list statuses as s>
                  <option value="${s.name()}" >${s.name()}</option>
                </#list>
            </select></label><br>
        <button type="submit">Сохранить</button>
    </form><br>
    <a href="/users">Назад</a>
</@common.common>