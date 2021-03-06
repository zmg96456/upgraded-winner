<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/xadmin.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row">
			<form class="layui-form">
				<div class="layui-form-item">
					<label for="L_id" class="layui-form-label"> <span
						class="x-red">*</span>编号
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_id" name="id" required=""
							placeholder="请输入编号" lay-verify="id" value="${note.id }"
							autocomplete="off" class="layui-input" readonly="readonly">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>由系统生成不可更改
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> <span
						class="x-red">*</span>标题
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_name" name="title" required=""
							placeholder="请输入标题" lay-verify="title" autocomplete="off"
							value="${note.title }" class="layui-input">
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<label for="L_content" class="layui-form-label"> <span
						class="x-red">*</span>内容
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_content" name="content" required=""
							placeholder="请输入内容" lay-verify="content" autocomplete="off"
							value="${note.content }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label for="L_user_id" class="layui-form-label"> <span
						class="x-red">*</span>创建者
					</label>
					<div class="layui-input-inline">
						<input type="radio" name="user.id" value="${loginUser.id }"
							title="${loginUser.name }" checked="checked" />
					</div>
				</div>

					<div class="layui-form-item">
						<label for="L_repass" class="layui-form-label"></label>
						<button class="layui-btn" lay-filter="update" lay-submit="">更新</button>
					</div>
			</form>
		</div>
	</div>
	<script>
		layui.use([ 'form', 'layer', 'jquery', 'laydate' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;
			var laydate = layui.laydate;
			//自定义验证规则
			form.verify({
				title : function(value) {
					if (value == null || value.trim() == "") {
						return '标题不能为空';
					}
				},
				address : function(value) {
					if (value == null || value.trim() == "") {
						return '地址不能为空';
					}
				},
				content : function(value) {
					if (value == null || value.trim() == "") {
						return '内容不能为空';
					}
				},
				begin_time : function(value) {
					if (value == null || value.trim() == "") {
						return '开始时间不能为空';
					}
				},
				end_time : function(value) {
					if (value == null || value.trim() == "") {
						return '结束时间不能为空';
					}
				}
			});

			
			laydate.render({
				elem : '#begin_time',
				type : "datetime",
				calendar : true
			});
			laydate.render({
				elem : '#end_time',
				type : "datetime",
				calendar : true
			});
		

			//监听提交
			form.on('submit(update)', function(data) {
				console.log(data);
				//发异步，把数据提交给php
				$.post("update", data.field, function(obj) {

					if (obj.result == 1) {
						layer.alert(obj.msg, {
							icon : 6
						}, function() {
							//关闭当前frame
							xadmin.close();
							// 可以对父窗口进行刷新 
							xadmin.father_reload();
						});
					} else {
						layer.alert(obj.msg, {
							icon : 5
						}, function() {
							//关闭当前frame
							xadmin.close();
						});
					}
				});
				return false;
			});
		});
	</script>
</body>

</html>
