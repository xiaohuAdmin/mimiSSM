<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <script type="text/javascript">
        if ("${msg}" != "") {
            alert("${msg}");
        }
    </script>

    <c:remove var="msg"></c:remove>

    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bright.css"/>
    <link rel="stylesheet" href="css/addBook.css"/>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <title></title>
</head>
<script type="text/javascript">
    $(function () {
        //为全选复选框绑定事件,触发全选操作
        $("#all").click(function () {
            $("input[name=ck]").prop("checked",this.checked);
        })
        //为数据行的复选框绑定事件 实现全选
        $("#shangpinBody").on("click",$("input[name=ck]"),function (){
            $("#all").prop("checked",$("input[name=ck]").length==$("input[name=ck]:checked").length);
        })
    })


</script>
<body>
<div id="brall">
    <div id="nav">
        <p>商品管理>商品列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform">
            商品名称：<input name="pname" id="pname">&nbsp;&nbsp;&nbsp;
            商品类型：<select name="typeid" id="typeid">
            <option value="-1">请选择</option>
            <c:forEach items="${ptlist}" var="pt">
                <option value="${pt.typeId}">${pt.typeName}</option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;
            价格：<input name="lprice" id="lprice">-<input name="hprice" id="hprice">
            <input type="button" value="查询" onclick="ajaxsplit(${info.pageNum})">
        </form>
    </div>
    <br>
    <div id="table">

        <c:choose>
            <c:when test="${info.list.size()!=0}">

                <div id="top">
                    <input type="checkbox" id="all" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <a href="admin/addproduct.jsp">

                        <input type="button" class="btn btn-warning" id="btn1"
                               value="新增商品">
                    </a>
                    <input type="button" class="btn btn-warning" id="btn1"
                           value="批量删除" onclick="deleteBatch()">
                </div>
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped" id="shangpinBody">
                        <tr>
                            <th></th>
                            <th>商品名</th>
                            <th>商品介绍</th>
                            <th>定价（元）</th>
                            <th>商品图片</th>
                            <th>商品数量</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${info.list}" var="p">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck" value="${p.pId}"></td>
                                <td>${p.pName}</td>
                                <td>${p.pContent}</td>
                                <td>${p.pPrice}</td>
                                <td><img width="55px" height="45px"
                                     src="image_big/${p.pImage}"></td>
                                <td>${p.pNumber}</td>
                                    <td>
                                    <button type="button" class="btn btn-info "
                                            onclick="one(${p.pId},${info.pageNum})">编辑
                                    </button>
                                    <button type="button" class="btn btn-warning" id="mydel"
                                            onclick="del(${p.pId})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <li>
                                        <a href="javascript:ajaxsplit(${info.prePage})" aria-label="Previous">

                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <c:forEach begin="1" end="${info.pages}" var="i">
                                        <c:if test="${info.pageNum==i}">
                                            <li>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: grey">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${info.pageNum!=i}">
                                            <li>
                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                        <a href="javascript:ajaxsplit(${info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">暂时没有符合条件的商品！</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

<script type="text/javascript">
    function mysubmit() {
        $("#myform").submit();
    }

    //批量删除
    function deleteBatch() {

         /*   //取得所有被选中删除商品的pid
            var $xz=$("input[name=ck]:checked");
            var str="";
            var id="";
            if($xz.length==0){
                alert("请选择将要删除的商品！");
            }else{
                // 有选中的商品，则取出每个选 中商品的ID，拼提交的ID的数据
                if(confirm("您确定删除"+zhi.length+"条商品吗？")){
                //拼接ID
                    $.each(zhi,function (index,item) {

                        id=$(item).val(); //22 33
                        alert(id);
                        if(id!=null)
                            str += id+",";  //22,33,44
                    });
                    alert(str+"11111111");


                }
        }*/
        //找到复选框中被选中的复选框的jquery对象
        var $ck = $("input[name=ck]:checked");
        if ($ck.length==0){
            alert("请选择要删除的商品")
        }else {
            if(confirm("您确定删除"+$ck.length+"条商品吗？")){
                //拼接参数
                var param = "";
                for (var i=0;i<$ck.length;i++){
                    param += "id="+ $($ck[i]).val();
                    if (i<$ck.length-1){
                        param += "&";
                    }

                }
                $.ajax({
                    url:"prod/deleteAll.action",
                    type:"post",
                    data:param,
                    dataType: "json",
                    success:function (data) {
                       if (data){
                           alert("批量删除商品成功")
                           ajaxsplit(1);
                       }else {
                           alert("批量删除失败")
                       }

                    }
                })
            }
        }
    }
    //单个删除
    function del(pid) {
        if (confirm("确定删除吗")) {
         $.ajax({
             url:"prod/delete.action",
             data:{"pid":pid},
             type: "get",
             dataType:"text",
             success:function (data) {
                 alert(data);
                 $("#table").load("http://localhost:8080/mimiSSM_war_exploded/admin/product.jsp #table");
             }

         })
        }
    }

    function one(pid, ispage) {
        location.href = "prod/one.action?pid=" + pid;
    }
</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    function  ajaxsplit(page) {
        //异步ajax分页请求
        $.ajax({
        url:"prod/ajaxsplit.action",
            data:{"page":page},
            type:"post",
            success:function () {
                //重新加载分页显示的组件table
                //location.href---->http://localhost:8080/admin/login.action
                $("#table").load("http://localhost:8080/mimiSSM_war_exploded/admin/product.jsp #table");
            }
        })
    };

</script>

</html>