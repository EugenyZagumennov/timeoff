<#import "/spring.ftl" as spring/>
<#macro logout>
  <form action="/logout" method="post">
    <button type="submit" class="btn btn-primary"><@spring.message "logout.logout"/></button>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
  </form><br>
</#macro>