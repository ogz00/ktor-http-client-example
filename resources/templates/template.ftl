<#-- @ftlvariable name="converter" type="com.oguz.thebase.data.Converter" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->

<#macro mainLayout title="Welcome" converter=converter>
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
        <link rel="stylesheet" type="text/css" href="/styles/main.css">
    </head>
    <body>
    <div class="pure-g">
        <div class="sidebar pure-u-1 pure-u-md-1-4">
            <div class="content">
                <div class="brand-title">
                    Exchange Test Demo ${.now?string("yyyy")}
                </div>
                <div>
                    <h3>Converter </h3>
                    <form class="pure-form-stacked" action="/" method="post"
                          enctype="application/x-www-form-urlencoded">
                        <#if error??>
                            <p class="error">${error}</p>
                        </#if>
                        <label for="post-text">Source Currency:
                            <input type="text" name="source" id="sourceId" value="${converter.source}">
                        </label>

                        <label for="post-text">Target Currency:
                            <input type="text" name="target" id="targetId" value="${converter.target}">
                        </label>

                        <label for="post-text">Amount:
                            <input type="text" name="amount" id="targetId" value="${converter.amount}">
                        </label>

                        <input class="pure-button pure-button-primary" type="submit" value="Calculate">

                    </form>

                    <div class="description">Calculated Value: ${converter.result}</div>

                </div>
            </div>
        </div>

        <div class="content pure-u-1 pure-u-md-3-4">
            <h2>${title}</h2>
            <#nested />
        </div>
        <div class="footer">
        </div>
    </div>
    </body>
    </html>
</#macro>

<#macro rate_li rate>
<#-- @ftlvariable name="rate" type="com.oguz.thebase.data.Rate" -->

    <section class="post">
        <p class="meta">
            <label> ${rate.key} - ${rate.value}</label>
        </p>
    </section>
</#macro>

<#macro rates_list rates info info>
<#-- @ftlvariable name="info" type="com.oguz.thebase.data.Info" -->
    <h3> Base: ${info.base} - Date: ${info.date}</h3>
    <ul>
        <#list rates as rate>
            <@rate_li rate=rate></@rate_li>
        <#else>
            <li>There are no rates info yet</li>
        </#list>
    </ul>
</#macro>