文件上传说明文档：
	
     访问域名/分类/方法/UUID文件名/session
    
     目前访问域名为：http://www.iwxlh.com:8088/wxlh
       
      分类有：
      	 head:头像
      	 chat:聊天
      	 event:事情
      方法有:
      	put:上传 支持 POST 请求方式，文件的二进制名称为 file
      		.jpg 结尾文件，如果是分类是head,则生成 '文件名_mid.jpg'、'文件名_mid.jpg' 两个所列图，
      		如果分类是event.则生成 '文件名_mid.jpg' 一个缩列图，如果分类是chat 暂且未生成缩列图
      		
      	get:获取 支持 POST、GET 两种方式
      
      session ： session的值是登录后获取的值。
      
     举例：
     	上传一个事情文件的地址为：http://www.iwxlh.com:8088/wxlh/event/put/jiangxs_session_id.jpg/session_only_session
     	http://www.iwxlh.com:8088/wxlh 为访问域名
     	/event/put/jiangxs_session_id.jpg/session_only_session 为相对路径
     	event 为类型
     	put  为方法
     	jiangxs_session_id.jpg 为文件名
     	session_only_session   为session session的值是登录后获取的值。
     	
     	获取一个文件地址：http://www.iwxlh.com:8088/wxlh/event/get/jiangxs_session_id.jpg/session_only_session
  
  操作返回结果：
  		{"rst":-1,"fid":"jiangxs_session_id.jpg","msg":"success"}
  	rst:取值
	  	SUCCESS(-1, "success"), 成功：主要是上传时
		FAILURE(0, "failure"),   操作失败，服务端出现异常或者忘记传文件名等异常信息
		FILE_NOT_FOUND(1, "fileNotFound"), 文件找不到 获取时文件没有该文件，或者是没有上传没有传文件名
		NO_PERMISSIONS(2, "No permissions"); 无权限没有session
  	 
     
  	
服务端目前配置文件存放地址为：     	
chat.file.path=/home/wxlh/wxlh_wm/lagerFileServer/file/
head.file.path=/home/wxlh/wxlh_wm/ptasFileServer/file/
event.file.path=/home/wxlh/wxlh_wm/eventFileServer/file/

文件在redis中存放一周，之后从数据库中删除，如果一周之后还有访问，再从文件路径中获取
