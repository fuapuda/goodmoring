      公司后台管理系统使用SpringCloud框架、MybitasPlus、Nginx、element ui实现对建筑设计公司网站的资质、新闻和各业务的业务成绩进行CRUD，实时更新网站数据，使用数据库和阿里云oss实现数据互通。实现招投标的功能（包括对招标项目及其文件的增删改查，以及对客户投标项目的审核功能，如审核通过，则下线该招标项目信息、并发布公告到中标模板中。）能接收客户对后台发送的信息。
      项目展示：
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302203808.png)
首先当然是登录页面啦 element ui的登录模块好像会用到SpringSecurity的token授权，似乎太高大上了，于是我给它改low了，把id当token传
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302203901.png)
然后是首页 展示头像名字角色
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302203923.png)
资质列表 有检索功能，也有修改和删除入口，修改中会把字段status改为未发布，除非修改完成确认发布
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302203944.png)
添加资质 分步骤 在未确认发布前离开添加路由并不会展示在前台页面上，但能在资质列表中找到（状态为未发布） 点击修改能继承数据并继续步骤
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204007.png)
新闻列表 大同小异 上面有的功能这都有
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204035.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204055.png)
添加新闻 大同小异 信息更多 步骤就更多
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204112.png)
确认发布页 把数据库字段status改为已发布并展示在前台页面的唯一方法
项目管理就不展示了 跟上面一个模板
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204147.png)
招标列表 大同小异 能通过超链接下载到后台添加招标项目的文件
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204205.png)
添加招标 上传招标项目基本信息
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204218.png)
添加招标步骤二 上传相关文件
步骤三是发布页 同上
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204514.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204529.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204624.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204731.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204804.png)
![Image text](https://github.com/fuapuda/goodnight/blob/main/deploys/QQ%E5%9B%BE%E7%89%8720220302204835.png)
