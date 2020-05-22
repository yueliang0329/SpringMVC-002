<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url, args, function(data){
				for(var i = 0; i < data.length; i++){
					var id = data[i].id;
					var lastName = data[i].lastName;				
					alert(id + ": " + lastName);
				}
			});
			return false;
		});
	})
</script>
</head>
<body>
		<a href="emps">List All Employees</a>
		<br><br>
		<a href="testJson" id="testJson">Test Json</a>
		<br><br>
</body>
</html>