部署地址：http://xxx.xxx.xxx.xxx:8082/bu_file

0.权限接口
	0.0 联通性
		-api:  http://localhost:8080/bu_file/client/monitor/connect
    	-demo: curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/auth/connect -d '{}'
 		-result: {"error":{"code":0,"msg":"success"},"rst":"2a5f9c4b7f5d42959e778e8b50a4fa6d","count":0}
	0.1 机器信息
		-api:  http://localhost:8080/bu_file/client/monitor/hardware
    	-demo: curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/client/monitor/hardware
 		-result: {"error":{"code":0,"msg":"success"},"rst":{"uid":"84543581","md5":null,"token":"988437","expired":1440384115198,"userDetail":{"uid":"84543581","phone":"18693108721","email":"378917280@qq.com","portrait":"0A4319D30E1B938961618E52299D8036","name":"，笑笑，","gender":1,"level":0,"score":0,"type":9,"label":"呵呵，你好了!","url":null,"intro":null,"t":0,"programId":null}},"count":0}
 	0.2 建立数据中心
 		-api:  http://localhost:8080/bu_file/mgr/config/data_center/edit
		-demo: curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/data_center/edit -d '{"centerName":"白银市数据中心", "centerIp":"10.18.24.241","centerDesc":"我仅仅是个描述信息","centerPath":"/sharefile/ds_files"}'
		-result:{"error":{"code":0,"msg":"success","key":""},"rst":{"centerName":"白银市数据中心","centerIp":"10.18.24.241","centerDesc":"我仅仅是个描述信息","centerPath":"/sharefile/ds_files","sys_id":"bf43f486-db6d-4e2a-aff1-ffe3706f6709","createdTime":"2015-09-18 11:28:02","updatedTime":"2015-09-18 11:28:02","status":0},"count":0}
	0.3 获取数据中心
		-api:  http://localhost:8080/bu_file/mgr/config/data_center/get
    	-demo: curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/data_center/get
    	-result:{"error":{"code":0,"msg":"success","key":""},"rst":{"centerName":"白银市数据中心","centerIp":"10.18.24.241","centerDesc":"我仅仅是个描述信息","centerPath":"/sharefile/ds_files","sys_id":"bf43f486-db6d-4e2a-aff1-ffe3706f6709","createdTime":"2015-09-18 11:28:02","updatedTime":"2015-09-18 11:28:02","status":0},"count":0}
		
 1.数据机器管理
 	1.0 增加一台机器
		-api:  http://localhost:8080/bu_file/mgr/config/server/create
		-demo: curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/server/create -d '{"serverName":"统一申报前置库", "serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息","rootPath":"/Users/jxs"}'
		-result: {"buRst":{"error":{"code":0,"msg":"success","key":""},"rst":{"sys_id":"58f303a0-0026-485b-8b14-670ac0d9da87","createdTime":1442227021121,"updatedTime":1442227021121,"serverName":"统一申报前置库","serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息","rootPath":"/Users/jxs"},"count":0}}
 	1.1 获取机器列表
 		-api:  http://localhost:8080/bu_file/mgr/config/server/list
 		-demo: curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/server/list
 	    -result:{"buRst":{"error":{"code":0,"msg":"success","key":""},"rst":[{"sys_id":"58f303a0-0026-485b-8b14-670ac0d9da87","createdTime":1442227021000,"updatedTime":1442227021000,"serverName":"统一申报前置库","serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息"}],"count":1}}
	
 	
 2.发布资源管理
 	2.0 发布一个资源
	 	-api:  http://localhost:8080/bu_file/mgr/config/menu/create/{server_id}
	 	-params: path——路径 desc——描述信息
	 	-demo: curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/menu/create/58f303a0-0026-485b-8b14-670ac0d9da87 -d '{"path":"/sharefile/ds_files/bjsb","desc":" 我是发布目录描述"}'
		-result: 
 	2.1 获取发布资源列表
 		-api:  http://localhost:8080/bu_file/mgr/config/menu/list
 		-demo: curl -i -X GET -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/menu/list
 	    -result:{"error":{"code":0,"msg":"success","key":""},"rst":[{"mgrServer":{"serverName":"统一申报前置库1","serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息111","sys_id":"58f303a0-0026-485b-8b14-670ac0d9da87","createdTime":"2015-09-15 19:41:03","updatedTime":"2015-09-15 19:41:03","status":0},"path":"/sharefile/ds_files/bjsb","desc":"111我是发布目录描述","sys_id":"10fca211-c418-42d3-a05a-9a3f73734853","createdTime":"2015-09-15 16:53:48","updatedTime":"2015-09-15 16:53:48","status":2},{"mgrServer":{"serverName":"统一申报前置库1","serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息111","sys_id":"58f303a0-0026-485b-8b14-670ac0d9da87","createdTime":"2015-09-15 19:41:03","updatedTime":"2015-09-15 19:41:03","status":0},"path":"/sharefile/ds_files/tysb","desc":"1tt我是发布目录描述","sys_id":"520ece18-f755-4d3c-a5d2-fc8f974d321a","createdTime":"2015-09-15 18:49:53","updatedTime":"2015-09-15 18:49:53","status":0}],"count":2}
 	2.2 取消发布一个资源
	 	-api:  http://localhost:8080/bu_file/mgr/config/menu/cancel/{menu_id}
	 	-demo: curl -i -X POST -H "Content-Type: application/json" -H "Accept: application/json"  http://localhost:8080/bu_file/mgr/config/menu/cancel/10fca211-c418-42d3-a05a-9a3f73734853
		-result: {"error":{"code":0,"msg":"success","key":""},"rst":{"mgrServer":{"serverName":"统一申报前置库1","serverIp":"10.18.24.241","serverDesc":"我仅仅是个描述信息111","sys_id":"58f303a0-0026-485b-8b14-670ac0d9da87","createdTime":"2015-09-15 19:37:39","updatedTime":"2015-09-15 19:37:39","status":0},"path":"/sharefile/ds_files/bjsb","desc":"111我是发布目录描述","sys_id":"10fca211-c418-42d3-a05a-9a3f73734853","createdTime":"2015-09-15 16:53:48","updatedTime":"2015-09-15 16:53:48","status":2},"count":0}
 3.
 
 
 
 
 
 
 