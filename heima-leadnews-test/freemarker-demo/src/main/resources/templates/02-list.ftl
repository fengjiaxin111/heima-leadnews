<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>

<#-- list 数据的展示 -->
<b>展示list中的stu数据:</b>
<br>
<br>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>

    <#list stus as stu>
        <#if stu.name!="jack">
             <tr style="color: aquamarine">
            <td>${stu_index+1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.money}</td>
        </tr>
        <#else >
    <tr >
        <td>${stu_index+1}</td>
        <td>${stu.name}</td>
        <td>${stu.age}</td>
        <td>${stu.money}</td>
        </#if>

    </#list>
</table>
<hr>

<#-- Map 数据的展示 -->
<b>map数据的展示：</b>
<br/><br/>
<a href="###">方式一：通过map['keyname'].property</a><br/>
输出stu1的学生信息：<br/>
姓名：<br/>
年龄：<br/>
<br/>
<a href="###">方式二：通过map.keyname.property</a><br/>
输出stu2的学生信息：<br/>
姓名：${stumap.stu2.name}<br/>
年龄：${stumap.stu2.age}<br/>
<br/>
<a href="###">遍历map中两个学生信息：</a><br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stumap?keys as key>
        <tr>
            <td>${key_index}</td>
            <td>${stumap[key].name}</td>
            <td>${stumap[key].age}</td>
            <td>${stumap[key].money}</td>
        </tr>
    </#list>
</table>
<hr>
显示年月日: ${todat?date}
显示时分秒：${todat?time}
显示日期+时间：${todat?datetime}
自定义格式化：${todat?string("yyyy年MM月")}
</body>
</html>