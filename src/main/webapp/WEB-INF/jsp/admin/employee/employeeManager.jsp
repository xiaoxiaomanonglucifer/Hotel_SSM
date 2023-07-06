<%--
  Created by IntelliJ IDEA.
  User: 曾平
  Date: 2023/6/3
  Time: 15:28
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">员工姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="loginName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">员工性别</label>
                            <div class="layui-input-inline">
                                <select name="sex" autocomplete="off" class="layui-input">
                                    <option value="">请选择性别</option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-inline">
                                <select name="deptId" autocomplete="off" id="s_deptId" class="layui-input">
                                    <option value="">请选择部门</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startDate" id="startTime" autocomplete="off"
                                       class="layui-input" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endDate" id="endTime" autocomplete="off" class="layui-input"
                                       readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon layui-icon-search"></i>搜索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i>重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格工具栏 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-close"></i>删除</a>
            <button class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd"><i
                    class="layui-icon layui-icon-refresh"></i>重置密码
            </button>
            <button class="layui-btn layui-btn-xs" lay-event="grantRole"><i class="layui-icon layui-icon-edit"></i>分配角色
            </button>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="id">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" id="loginName" lay-verify="required" autocomplete="off"
                                   placeholder="请输入登陆名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="请输入员工姓名" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入职日期</label>
                        <div class="layui-input-block">
                            <input type="Date" name="hireDate" id="hireDate" lay-verify="required" autocomplete="off"
                                   placeholder="请选择入职日期" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属部门</label>
                        <div class="layui-input-block">
                            <select name="deptId" id="deptId" class="layui-input">
                                <option value="">请选择部门</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">员工性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="1" title="男" checked>
                            <input type="radio" name="sex" value="2" title="女">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                    </div>
                </div>


                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="button" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 员工分配角色弹出层 -->
        <div style="display: none;padding: 5px" id="selectUserRoleDiv">
            <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        //渲染日期组件
        laydate.render({
            elem: '#startTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#hiredate' //指定元素
        });

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/employee/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '员工编号', align: "center"},
                {field: 'loginName', width: 120, title: '登录名', align: "center"},
                {field: 'name', width: 120, title: '真实姓名', align: "center"},
                {
                    field: 'sex', width: 80, title: '性别', align: "center", templet: function (d) {
                        return d.sex == 1 ? "男" : "女"
                    }
                },
                {field: 'deptName', width: 120, title: '所属部门', align: "center"},
                {field: 'hireDate', width: 180, title: '入职日期', align: "center"},
                {field: 'createDate', width: 180, title: '创建时间', align: "center"},
                {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });


        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        //发送请求查询部门下拉列表

        $.get("/admin/dept/deptList", function (resp) {
            var html = "";
            for (var i = 0; i < resp.length; i++) {
                html += "<option value='" + resp[i].id + "'>" + resp[i].deptName + "</option>"
            }
            $("[name='deptId']").append(html);
            //重新渲染下拉列表
            form.render("select")
        }, "json")

        //监听头部工具栏事件  因为添加按钮在头部工具栏
        table.on("toolbar(currentTableFilter)", function (resp) {
            switch (resp.event) {
                case "add":
                    openAddWindow();
                    break;
            }
        })

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
                case"resetPwd":
                    resetPwd(resp.data);
                    break;
                case "grantRole":
                    grantRole(resp.data);
                    break;
            }
        })
        var url;
        var mainIndex;

        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title: "添加员工",
                area: ["800px", "400px"],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    $("#dataFrm")[0].reset();
                    url = "/admin/employee/addEmployee";
                }
            });

        }

        function openEditWindow(data) {
            mainIndex = layer.open({
                type: 1,
                title: "修改员工",
                area: ["800px", "400px"],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    //表单数据回显
                    form.val("dataFrm", data);
                    url = "/admin/employee/updateEmployee";
                }
            });
        }

        function deleteById(data) {
            $.get("/admin/employee/checkHasEmployee",{"id":data.id},function (resp) {
                if (resp.exist) {
                    //提示用户无法删除
                    layer.msg(resp.message)
                } else {
                    layer.confirm("确定要删除[<font color='red'>" + data.name + "</font>]吗", {
                        icon: 3,
                        title: '提示'
                    }, function (index) {
                        //确定之后发送ajax请求 删除
                        $.post("/admin/employee/deleteById", {"id": data.id}, function (resp) {
                            if (resp.success) {
                                //刷新数据
                                tableIns.reload();
                            }
                            layer.msg(resp.message);
                        }, "json")
                        layer.close(index)
                    })
                }
            },"json")
        }
        function resetPwd(data) {
            layer.confirm("确定要重置[<font color='red'>"+data.name+"</font>]密码吗",{icon:3 ,title:'提示'},function (index) {
                //确定之后发送ajax请求重置密码
                $.post("/admin/employee/resetPwd",{"id":data.id},function (resp) {
                    if(resp.success){
                        //刷新数据
                        tableIns.reload();
                    }
                    layer.msg(resp.message);
                },"json")
                layer.close(index)
            })
        }

        /**
         * 分配角色
         *
         * @param data
         */
        function grantRole(data) {
            mainIndex = layer.open({
                type: 1,
                title: "分配[<font color='red'>"+data.name+"</font>]角色",
                area: ["800px", "500px"],
                content: $("#selectUserRoleDiv") ,
                btn: ['<i class="layui-icon layui-icon-ok"></i>确定', '<i class="layui-icon layui-icon-close"></i>取消']
                ,yes: function(index, layero){
                    //获取选中行
                    var checkStatus = table.checkStatus('roleTable'); //roleTable 即为基础参数 id 对应的值
                    //判断是否有选中行
                    // layer.msg(c)
                    if(checkStatus.data.length>0){
                        //获取选中的角色id
                        //定义数组保存选中的id
                        var idArr=[];
                        for(var i=0;i<checkStatus.data.length;i++){
                            idArr.push(checkStatus.data[i].id);
                        }
                        //将数组 使用,分割，因为前端不能传数组到后端
                        var ids=idArr.join(",");
                        $.post("/admin/employee/saveEmployeeRole",{"roleIds":ids,"empId":data.id},function (resp) {

                            layer.msg(resp.message);
                        },"json");
                        //关闭当前窗口
                        layer.close(mainIndex);
                    }else{
                        layer.msg("请选择要分配的角色！")
                    }

                }
                ,btn2: function(index, layero) {
                    //按钮【按钮二】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                },
                success: function () {
                //显示角色列表的数据表格
                    initTableData(data)
                }
            });
        }

        function initTableData(data) {
            table.render({
                elem: '#roleTable',
                url: '${pageContext.request.contextPath}/admin/role/initRoleListByEmpId?id='+data.id,
                cols: [[
                    {type: "checkbox", width: 50},
                    {field: 'id', minWidth: 100, title: '角色编号', align: "center"},
                    {field: 'rolename', minWidth: 120, title: '角色名称', align: "center"},
                    {field: 'roledesc', minWidth: 120, title: '角色描述', align: "center"},
                    //因为返回值不是实体类类型 返回的是一个map 所以 和数据库的名字相同
                ]]
            });
        }

        //监听表单提交事件
        form.on("submit(doSubmit)", function (data) {
            //发送ajax请求提交
            $.post(url, data.field, function (resp) {
                if (resp.success) {
                    //刷新数据
                    tableIns.reload();
                    //关闭窗口
                    layer.close(mainIndex);
                }
                layer.msg(resp.message);
            }, "json")
            //禁止刷新页面
            return false;

        })

    });

</script>

</body>
</html>
