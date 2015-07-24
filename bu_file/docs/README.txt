文件上传说明文档：
	
   BaseURL:http://10.20.26.20:8080/bu_file
1、获取文件：
		URI:{BaseURL}/share/get/{secret_key}/{type}?path={path}
		secret_key：访问秘钥
		type：文件类型；取值：tysb(统一申报)、qlsx（权力事项）、bjsb（办件上报）
		path：文件相对路径
	举例：{BaseURL}/share/get/ea8d1d440c24482cba537b26a1c11b6f/tysb?path=/620523/2015/01/02/xxxxxx/abc.txt
	通过授权码：ea8d1d440c24482cba537b26a1c11b6f 访问类型为 tysb（统一申报）的 /620523/2015/01/02/xxxxxx/abc.txt 的申报材料
2、 上传文件：
		URI：{BaseURL}/share/put/{secret_key}/{type}
		secret_key：访问秘钥
		type：文件类型；取值：tysb(统一申报)、qlsx（权力事项）、bjsb（办件上报）
		path：文件相对路径
		举例：
		
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
			<!-- saved from url=(0035)http://m.iwxlh.com:8088/wxlh/index -->
			<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta http-equiv="pragma" content="no-cache">
				<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
				<meta http-equiv="description" content="This is my page">
			</head>
			<body>
				<form method="post" action="http://10.20.26.20:8080/bu_file/share/put/ea8d1d440c24482cba537b26a1c11b6f/tysb?path=/620523/2015/01/02/xxxxxx/abc.txt" 
					enctype="multipart/form-data" method="post">
				     <input id="file" name="file" type="file" />
				         <input type="submit">
				 </form>
			 <body>
			 </html>				
	