<#import "/parts/common.ftl" as c>

<@c.common>
<form method="post" action="/login">
    <div class="form-group row">
        <label class="col-sm-1 col-form-label">Логин</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="username" id="username" placeholder="Логин" />
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