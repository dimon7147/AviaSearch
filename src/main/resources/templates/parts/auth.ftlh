<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<#macro login>
    <form action="/login" method="post">
        <div><label> Login : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>
<#macro register>
    <form action="/register" method="post">
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <div><label> UserName : <input type="text" name="login"/> </label></div>
        <div><label> FullName : <input type="text" name="fullName"/> </label></div>
        <div><label> Email : <input type="email" name="email"/> </label></div>
        <div><label> Password : <input type="password" name="password"/> </label></div>
        <div><label> Passport : <input type="number" name="passport"/> </label></div>
        <div><label> Date Of Birth : <input type="date" name="dateOfBirth"/> </label></div>
        <div><input type="submit" value="Register"/></div>
    </form>
</#macro>
<#macro logout>

</#macro>