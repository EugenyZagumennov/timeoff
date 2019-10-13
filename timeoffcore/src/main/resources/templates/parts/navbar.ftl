<#include "security.ftl">
<#import "logout.ftl" as logout>
<#import "/spring.ftl" as spring/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand">TimeOff</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item ">
        <a class="nav-link" href="/departments"><@spring.message "navber.departments"/></a>
      </li>
      <li class="nav-item ">
        <a class="nav-link" href="/users"><@spring.message "navber.users"/></a>
      </li>
    </ul>

    <div class="navbar-text mr-2">${username}</div>
    <@logout.logout/>
  </div>
</nav>