<#-- @ftlvariable name="rateList" type="java.util.List<com.oguz.thebase.data.Rate>" -->
<#-- @ftlvariable name="info" type="com.oguz.thebase.data.Info"  -->
<#-- @ftlvariable name="converter" type="com.oguz.thebase.data.Converter"  -->


<#import "template.ftl" as layout />

<@layout.mainLayout title="Welcome Exchange API" converter=converter>
    <div class="posts">
        <@layout.rates_list rates=rateList info=info></@layout.rates_list>
    </div>
</@layout.mainLayout>
