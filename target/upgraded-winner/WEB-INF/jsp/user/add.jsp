<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<label for="L_name" class="layui-form-label"> <span
						class="x-red">*</span>员工姓名
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_name" name="name" required=""
							placeholder="请输入员工姓名" lay-verify="name" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_password" class="layui-form-label"> <span
						class="x-red">*</span>登陆密码
					</label>
					<div class="layui-input-inline">
						<input type="password" id="L_password" name="password" required=""
							placeholder="请输入登陆密码" lay-verify="password" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_sex" class="layui-form-label"> <span
						class="x-red">*</span>性别
					</label>
					<div class="layui-input-inline">
						<input type="radio" name="sex" value="男" title="男"
							checked="checked"> <input type="radio" name="sex"
							value="女" title="女">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_status" class="layui-form-label"> <span
						class="x-red">*</span>状态
					</label>
					<div class="layui-input-inline">
						<input type="radio" name="status" value="1" title="启用"
							checked="checked"> <input type="radio" name="status"
							value="2" title="禁用">
					</div>
				</div>


				<div class="layui-form-item">
					<label for="L_tel" class="layui-form-label">所属部门 </label>
					<div class="layui-input-block">
						<c:forEach items="${depts }" var="dept" varStatus="v">
						<!-- 默认选择第一个 -->
						<c:if test="${v.count eq 1 }">
						<input type="radio" name="dept.id" value="${dept.id }"
								title="${dept.name }"  checked="checked"/>
						</c:if>
						<c:if test="${v.count ne 1 }">
						<input type="radio" name="dept.id" value="${dept.id }"
								title="${dept.name }" />
						</c:if>
						</c:forEach>
					</div>
				</div>

		<div class="layui-form-item">
					<label for="L_tel" class="layui-form-label">权限 </label>
					<div class="layui-input-inline">
						<c:forEach items="${roles }" var="role" varStatus="v">
						<!-- 默认选择第一个 -->
						<c:if test="${v.count eq 1 }">
						<input type="radio" name="role.id" value="${role.id }"
								title="${role.name }"  checked="checked"/>
						</c:if>
						<c:if test="${v.count ne 1 }">
						<input type="radio" name="role.id" value="${role.id }"
								title="${role.name }" />
						</c:if>
						</c:forEach>
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>可在角色管理更改
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label"></label>
					<button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		layui.use([ 'form', 'layer', 'jquery', 'laydate' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;
	

			//自定义验证规则
			form.verify({
				name : function(value) {
					if (value == null || value.trim() == "") {
						return '员工姓名不能为空';
					}
				},
				password : function(value) {
					if (value == null || value.trim() == "") {
						return '登陆密码不能为空';
					}
				}
			});

			//监听提交
			form.on('submit(add)', function(data) {
				console.log(data);
				//发异步，把数据提交给php
				$.post("add", data.field, function(obj) {

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
