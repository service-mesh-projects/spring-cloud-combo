## 相关
UI界面: http://localhost:8500 

## 启动命令
```shell
consul agent -server -bind=10.0.xx.55 -client=0.0.0.0 -bootstrap-expect=3 -data-dir=/data/application/consul_data/ -node=server1

-server 表示是以服务端身份启动
-bind 表示绑定到哪个ip（有些服务器会绑定多块网卡，可以通过bind参数强制指定绑定的ip）
-client 指定客户端访问的ip(consul有丰富的api接口，这里的客户端指浏览器或调用方)，0.0.0.0表示不限客户端ip
-bootstrap-expect=3 表示server集群最低节点数为3，低于这个值将工作不正常
-data-dir 表示指定数据的存放目录（该目录必须存在）
-node 表示节点在web ui中显示的名称
-ui web管理界面
-dev 开发模式
```

## 接口API

- 注册服务
`PUT` /v1/agent/service/register  
`Content-Type` application/json
```json
{
    "ID": "service-id",
    "Name": "service-name",
    "Tags": [
    // .. 标签
    ],
    "Address": "192.168.1.1",
    "Port": 8080,
    "Meta": {
        // meta
    },
    "EnableTagOverride": false,
    "Check": {
// 健康检查参数    
//        "DeregisterCriticalServiceAfter": "90m",
//        "HTTP": "",
//        "Interval": "10s"
    }
}
```

- 查询服务列表
`GET` /v1/agent/services

- 服务反注册
`PUT` /v1/agent/service/deregister/{service-id}
