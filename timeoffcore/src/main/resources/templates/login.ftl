<#import "/parts/common.ftl" as c>

<@c.common>
<form method="post" action="/login">
    <input type="text"     name="username" id="username" placeholder="Логин" /><br>
    <input type="password" name="password" id="password" placeholder="Пароль" /><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Войти</button>
</form>
</@c.common>