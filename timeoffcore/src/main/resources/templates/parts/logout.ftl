<#macro logout>
  <form action="/logout" method="post">
    <input type="submit" value="Выйти"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
  </form><br>
</#macro>