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
<!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">消息传递</a>
			<a> <cite>公告</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			onclick="location.reload()" title="刷新"> <i
			class="layui-icon layui-icon-refresh" style="line-height: 30px"></i></a>
	</div>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
				
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
					[ 'laydate', 'form', 'table', 'util' ],
					function() {
						var laydate = layui.laydate;
						var form = layui.form;
						var table = layui.table;
						var util = layui.util;
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
												field : 'messageType.type',
												title : '消息类型 ',
												sort : true,
												align : 'center',
												templet : function(obj) {
													if (obj.messageType == null) {
														return "<div>无类型</div>";
													} else {
														return "<div>"+obj.messageType.type+"</div>";
													}
												}
											},
											{
												field : 'content',
												title : '消息内容',
												sort : true,
												align : 'center',
												
												edit : "text"
											},	
											{
												field : 'record_time',
												title : '发布时间',
												sort : true,
												align : 'center',
												templet : "<div>{{layui.util.toDateString(d.record_time, 'yyyy年MM月dd日HH点mm分ss秒')}}</div>"
											}] ]
								});

					});


			

</script>

</html>
