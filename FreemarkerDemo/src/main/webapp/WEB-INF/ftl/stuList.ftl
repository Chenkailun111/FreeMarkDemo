<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<link href="/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/js/bootstrap.js"></script>

 ${name},欢迎您！！
<hr>

<!--stus 遍历集合-->
<table class="table table-bordered table-striped">
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>入学时间</td>
    </tr>
    <#-- 遍历语法  stus是遍历的集合 stu是变量名 -->
    <#list stus as stu>
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.sex}</td>
            <#-- 日期特殊处理 -->
            <td>
                <#--如果字段有空字段，会报错，所以不为空展示-->
                <#if stu.startDate??>
                    ${stu.startDate?string('yyyy-MM-dd HH:mm:ss')}
                 <#else>
                    未知
                </#if>

            </td>
        </tr>
    </#list>
</table>

</body>
</html>