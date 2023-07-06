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

        <%-- 搜索条件区域 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
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

        <%-- 头部工具栏区域 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <%-- 表格区域 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏区域 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-delete"></i>删除</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <%-- 添加隐藏域--%>
                <input type="hidden" name="id">

                <div class="layui-form-item">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="deptName" lay-verify="required" autocomplete="off"
                               placeholder="请输入部门名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="address" lay-verify="required" autocomplete="off" placeholder="请输入部门地址"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style=" ooext-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            table = layui.table;
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/dept/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 120, title: "部门编号", align: 'center'},
                {field: 'deptName', minWidth: 120, title: '部门名称', align: 'center'},
                {field: 'address', minWidth: 150, title: '部门地址', align: 'center'},
                {field: 'createDate', minWidth: 120, title: '创建时间', align: 'center'},
                {field: 'remark', minWidth: 120, title: '备注', align: 'center'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done:function (res, curr, count) {
                //判断当前页码是否是大于1，并且当前页的数据量为0
                if(curr >1 &&res.data.length==0){
                    var pageValue= curr-1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page:{curr:pageValue}
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


        var url;//提交地址
        var mainIndex;//打开窗口的索引
        /**
         * 打开添加的窗口
         */
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//表示打开的类型
                title: "添加部门",//窗口的标题
                area: ["800px", "400px"],//窗口的高度宽度
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //每次打开 清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/dept/addDept";//提交地址
                }
            })
        }

        /**
         * 打开修改窗口
         * @param data  是当前行的数据
         */
        function openEditWindow(data) {

            mainIndex = layer.open({
                type: 1,//表示打开的类型
                title: "修改部门",//窗口的标题
                area: ["800px", "400px"],//窗口的高度宽度
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //每次打开 数据回显
                 form.val("dataFrm",data)//参数一是数据在表单中 所以是表单的lay-filter的
                    //修改的提交请求
                    url = "/admin/dept/editDept";//提交地址
                }
            })

        }


        /**
         * 删除部门
         * @param data 当前行数据
         */
        function deleteById(data){

            //判断当前部门下是否存在员工 查询数量是否>0
            $.get("/admin/dept/checkDeptHasEmployee",{"id":data.id},function (resp) {
                if(resp.exist){
                    //提示用户无法删除
                    layer.msg(resp.message)
                }else{
                    //提示用户是否删除
                layer.confirm("确定要删除[<font color='red'>"+data.deptName+"</font>]吗",{icon:3 ,title:'提示'},function (index) {

                    //确定之后发送ajax请求 删除
                    $.post("/admin/dept/deleteDeptById",{"id":data.id},function (resp) {
                        if(resp.success){
                            //刷新数据
                            tableIns.reload();
                        }
                        layer.msg(resp.message);
                    },"json")
                    layer.close(index)
                })
                }

            })


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