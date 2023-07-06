<%--
  Created by IntelliJ IDEA.
  User: 曾平
  Date: 2023/6/4
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/css/layuimini.css?v=2.0.4.2"
          media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/css/themes/default.css"
          media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/lib/font-awesome-4.7.0/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">

    <%--    添加 layui-dtree 样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-row">
            <%-- 左侧菜单树 --%>
            <div class="layui-col-md2">
                <!-- 树节点容器开始 -->
                <ul id="menuTree" class="dtree" data-id="0" style="width: 240px;"></ul>
                <!-- 树节点容器结束 -->
            </div>
            <%-- 右侧数据表格 --%>
            <div class="layui-col-md10">
                <%-- 表格工具栏 --%>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn"
                                lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加
                        </button>
                    </div>
                </script>
                <%-- 数据表格 --%>
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
                <%-- 行工具栏 --%>
                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">
                        <i class="layui-icon layui-icon-edit"></i>编辑</a>
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete"
                       lay-event="delete">
                        <i class="layui-icon layui-icon-close"></i>删除</a>
                </script>
                <%-- 添加和修改窗口 --%>
                <div style="display: none;padding: 5px" id="addOrUpdateWindow">
                    <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                        <%-- 菜单编号 --%>
                        <input type="hidden" name="id">
                        <div class="layui-form-item">
                            <label class="layui-form-label">父级菜单</label>
                            <div class="layui-input-block">
<%--                          <ul id="menuSelectTree" class="dtree" data-id="0"></ul>  ul标签添加隐藏域 保存选中 的菜单ID--%>
                                <input type="hidden" name="pid" id="pid">
                                <ul id="menuSelectTree" class="dtree" data-id="0"></ul>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" lay-verify="required"
                                       autocomplete="off"
                                       placeholder="请输入菜单名称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="href" autocomplete="off"
                                       placeholder="请输入菜单地址"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单图标</label>
                            <div class="layui-input-block">
<%--                             改名字 并且添加一个隐藏域 保存选中的图标Id--%>
                                <input type="hidden" name="icon" id="icon">
                                <input type="text" name="iconFa" id="iconPicker" lay-filter="iconPicker"
                                       autocomplete="off" placeholder="请输入菜单图标"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">是否展开</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="spread" value="1"
                                           title="是">
                                    <input type="radio" name="spread" value="0" title="否"
                                           checked>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <div class="layui-input-block" style="text-align: center;">
                                <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit">
                                    <span class="layui-icon layui-icon-add-1"></span>提交
                                </button>
                                <button type="reset" class="layui-btn layui-btn-warm">
                                    <span class="layui-icon layui-icon-refresh-1"></span>
                                    清空
                                </button>
                                <button type="button" class="layui-btn layui-btn-danger"
                                        id="resetMenu">
                                    <span class="layui-icon layui-icon-return"></span>重置
                                    菜单
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%-- 导入layui的js文件--%>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js"
        charset="utf-8"></script>

<script>
    layui.extend({
        dtree: "${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree",//dtree脚本文件路径
        //第一个dtree是文件夹名称   第二个是js文件，他自己会识别
        iconPickerFa: '${pageContext.request.contextPath}/statics/layui/js/lay-module/iconPicker/iconPickerFa',
    }).use(['form', 'table', 'layer', 'jquery', 'dtree', 'iconPickerFa'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var dtree = layui.dtree;
        var iconPickerFa = layui.iconPickerFa;

        //    初始化树
        var menuTree = dtree.render({
            elem: "#menuTree",//设置的dtree组件的id  ul标签的id
            url: "/admin/menu/loadMenuTree",//请求地址
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {message: "msg", statusCode: 0},
        });


        //初始化渲染数据表格
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/menu/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 100, title: "菜单编号", align: 'center'},
                {field: 'title', width: 180, title: '菜单名称', align: 'center'},
                {field: 'href', minWidth: 200, title: '菜单地址', align: 'center'},
                {
                    field: 'spread', width: 100, title: '是否展开', align: 'center', templet: function (d) {
                        return d.spread == 1 ? "<font color='red'>是</font>" : "<font color='blue'>否</font>"
                    }
                },
                {
                    field: 'icon', width: 100, title: '菜单图标', align: 'center', templet: function (d) {
                        return "<i class='" + d.icon + "'> </i>"
                    }
                },
                {title: '操作', width: 200, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1，并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });
        dtree.on("node(menuTree)", function (obj) {
            console.log(obj)
            tableIns.reload({
                where: {
                    "id": obj.param.nodeId //其中nodeId属性就是选中节点的id值
                },
                page: {curr: 1}
            });
        });
        //监听头部工具栏事件toolbar(currentTableFilter) 里面填的是lay-filter里面的值
        //toolbar是监听头部工具栏的事件
        table.on("toolbar(currentTableFilter)", function (data) {
            //监听事件
            switch (data.event) {
                case 'add'://添加按钮事件
                    openAddWindow();
                    //case里面的值就是lay-event=""属性的值
                    break;
            }
        });
        //监听 行工具栏事件 tool
        table.on("tool(currentTableFilter)", function (resp) {
            // /**/   alert(resp.data)
            switch (resp.event) {
                case "edit"://添加按钮事件
                    openEditWindow(resp.data);
                    //case里面的值就是lay-event=""属性的值
                    break;
                case "delete":
                    deleteById(resp.data);
                    break;
            }
        })

        var mainIndex;
        var url;

        //打开窗口之前就要渲染下拉树
       var menuSelectTree= dtree.renderSelect({
           elem: "#menuSelectTree",//设置的dtree组件的id  ul标签的id
           url: "/admin/menu/loadMenuTree",//请求地址
           dataStyle: "layuiStyle",
           dataFormat: "list",
           response: {message: "msg", statusCode: 0},
        })

        //监听下拉菜单树节点选中事件
        dtree.on("node(menuSelectTree)",function (data) {
            $("#pid").val(data.param.nodeId);

        })
        /*
        打开添加窗口
         */
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title: "添加菜单",
                area: ["800px", "400px"],
                content: $('#addOrUpdateWindow'),
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/admin/menu/addMenu";
                    //设置默认图标
                    iconPickerFa.checkIcon('iconPicker','fa fa-star');
                }
            })
        };
/*
删除按钮
 */
        function deleteById(data) {
            // console.log(data)
            $.get("/admin/menu/checkMenuHasChild",{"id":data.id},function (resp) {
                if(resp.exist){
                    layer.msg(resp.message);
                }else{
                    layer.confirm("确定要删除[<font color='red'>"+data.title+"</font>]吗",{icon:3 ,title:'提示'},function (index) {

                        //确定之后发送ajax请求 删除
                        $.post("/admin/menu/deleteMenuById",{"id":data.id},function (resp) {
                            if(resp.success){
                                //刷新数据
                                tableIns.reload();
                                //    刷新左侧菜单树
                                menuTree.reload();
                                //    刷新下拉菜单树
                                menuSelectTree.reload();
                            }
                            layer.msg(resp.message);
                        },"json")
                        layer.close(index)
                    })
                }
            },"json")
            //提示用户是否删除
        }
        /*
        修改按钮
         */
        function openEditWindow(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改菜单",
                area: ["800px", "400px"],
                content: $('#addOrUpdateWindow'),
                success: function () {
                    //数据回显
                    form.val("dataFrm",data);
                    url = "/admin/menu/updateMenu";
                    if(data.pid==0){
                        //刷新菜单树
                        menuSelectTree.reload();
                    }
                        //父级菜单回显
                    dtree.dataInit("menuSelectTree",data.pid);//参数一是 下拉菜单树的id属性值 参数二 是父节点的id值
                    dtree.selectVal("menuSelectTree"); //参数一  下拉菜单树的id属性值
                    //图标回显
                    iconPickerFa.checkIcon('iconPicker',data.icon);
                }
            })
        }

        //初始化图标选择器
        iconPickerFa.render({
                // 选择器，推荐使用input
            elem: '#iconPicker',
                // fa 图标接口
            url: "/statics/layui/lib/font-awesome-4.7.0/less/variables.less",
                // 是否开启搜索：true/false，默认true
            search: true,
                // 是否开启分页：true/false，默认true
            page: true,
                // 每页显示数量，默认12
            limit: 12,
                // 点击回调
            click: function (data) {
                //给图标隐藏域赋值
                // console.log(data);
                $("#icon").val("fa "+data.icon);
            },
                        // 渲染成功后的回调
            success: function (d) {
                console.log(d);
            }
        });



        //监听表单提交事件
        form.on("submit(doSubmit)", function (data) {
            //发送ajax请求提交
            $.post(url, data.field, function (resp) {
                if (resp.success) {
                    //刷新数据
                    tableIns.reload();
                    //刷新菜单树
                    menuTree.reload();
                    //刷新下拉菜单树
                    menuSelectTree.reload();
                    //关闭窗口
                    layer.close(mainIndex);
                }
                layer.msg(resp.message);
            }, "json")
            //禁止刷新页面
            return false;
        });

        //重置下拉菜单
        $("#resetMenu").click(function () {
            menuSelectTree.selectResetVal();
        })
    });
</script>
</html>