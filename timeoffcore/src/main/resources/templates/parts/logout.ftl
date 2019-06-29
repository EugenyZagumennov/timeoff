<#macro logout>
  <form action="/logout" method="post">
    <button type="submit" class="btn btn-primary">Выйти</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
  </form><br>
</#macro>