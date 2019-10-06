<#import "/parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.common>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>
<form method="post" action="/login">
    <div class="form-group row">
        <label class="col-sm-1 col-form-label"><@spring.message "loginpage.login"/></label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="username" id="username" placeholder="<@spring.message "loginpage.login"/>" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-1 col-form-label">Пароль</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" name="password" id="password" placeholder="Пароль" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="form-group row">
        <div class="col-sm-10">
          <button type="submit" class="btn btn-primary">Войти</button>
        </div>
    </div>
</form>
</@c.common>