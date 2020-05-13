<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<title>欢迎页面-X-admin2.2</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/xadmin.css">
<script src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/xadmin.js"></script>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">系统管理</a>
			<a> <cite>角色管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			onclick="location.reload()" title="刷新"> <i
			class="layui-icon layui-icon-refresh" style="line-height: 30px"></i></a>
	</div>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body ">
						<form class="layui-form layui-col-space5">
							<div class="layui-inline layui-show-xs-block">
								<input type="text" name="search" placeholder="请输入员工姓名"
									autocomplete="off" class="layui-input" id="search">
							</div>
							<div class="layui-inline layui-show-xs-block">
								<button class="layui-btn" lay-submit="" lay-filter="search">
									<i class="layui-icon">&#xe615;</i>
								</button>
							</div>
						</form>
					</div>
					<div class="layui-card-body layui-table-body layui-table-main">
						<table class="layui-table" id="table" lay-filter="test">


						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	layui
			.use(
					[ 'laydate', 'form', 'table' ],
					function() {
						var laydate = layui.laydate;
						var form = layui.form;
						var table = layui.table;
						//第一个实例
						// 需要引入table模块,会自动向url发送两条数据 page 第几页 limit 每页显示多少条
						table
								.render({
									elem : '#table',
									height : 312,
									url : 'pager' //数据接口
									,
									page : true //开启分页
									,
									cols : [ [ //表头
											{
												field : 'id',
												title : '编号',
												width : 80,
												sort : true,
												align : 'center'
											},
											{
												field : 'name',
												title : '员工姓名',
												sort : true,
												align : 'center'
											},
											{
												field : 'role.name',
												title : '角色类型 ',
												sort : true,
												align : 'center',
												templet : function(obj) {
													if (obj.role.id == 1) {
														return "<div><input type=\"checkbox\" name=\"role\" class=\"role\" lay-skin=\"switch\"  lay-filter=\"role\" lay-text=\"管理员|普通用户\" value='"+obj.id+"'  checked></div>";
													} else {
														return "<div><input type=\"checkbox\" name=\"role\" class=\"role\" lay-skin=\"switch\"  lay-filter=\"role\" lay-text=\"管理员|普通用户\" value='"+obj.id+"' ></div>";
													}

												}
											} ] ]
								});

						// 搜索监听
						form.on('submit(search)', function(data) {
							// table 模块 有个重载表格数据
							table.reload('table', {
								where : { //设定异步数据接口的额外参数，任意设
									search : $("#search").val()
								},
								page : {
									curr : 1
								//重新从第 1 页开始
								}
							});
							// 只重载数据
							return false;
						});
						//监听指定开关
						form.on('switch(role)', function(data) {
							// ID
							var id = data.value;
							// 是否开启
							var isChecked = this.checked;
							// 提示信息
							var str = "";

							// 角色ID  1是管理员,2是普通用户
							var role_id = 1;

							if (isChecked) {
								str = "设置管理员成功";
								role_id = 1;
							} else {
								str = "设置普通用户成功";
								role_id = 2;
							}

							$.post("updateRole", {
								"id" : id,
								"role_id" : role_id
							}, function(obj) {
								if (obj.result == 1) {
									layer.msg(str, {
										icon : 1,
										time : 1000
									});
								} else {
									layer.msg(obj.msg, {
										icon : 2,
										time : 1000
									}, function() {
										// 刷新当前窗口
										window.location.reload();
									});
								}
							});
						});
					});
</script>
</html>
