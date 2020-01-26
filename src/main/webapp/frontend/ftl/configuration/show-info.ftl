<#if errors??>
    <#list errors as error>
        <div style="text-align: center">
            <a style="color:red">${error}</a>
        </div>
    </#list>
</#if>
<#if success??>
    <div style="text-align: center">
        <a style="color:green">${success}</a>
    </div>
</#if>