/*
Title: alibaichuan
Description: alibaichuan
*/

<p style="color: #ccc; margin-bottom: 30px;">来自于：开发者</p>

<ul id="tab" class="clearfix">
  <li class="active"><a href="#method-content">Method</a></li>
</ul>
   
<div class="outline">

* [openDebug](#openDebug)

* [initTaeSDK](#initTaeSDK)

* [showLogin](#showLogin)

* [getUserInfo](#getUserInfo)

* [logout](#logout)

* [showTaokeItemById](#showTaokeItemById)

* [showDetailByURL](#showDetailByURL)

* [openMyCart](#openMyCart)

* [myOrdersPage](#myOrdersPage)

* [openShopPage](#openShopPage)

* [addToCartPage](#addToCartPage)

* [状态码](#errcode)

</div>


#####**此版本赠送chrome调试的功能，高手必备。

##**概述**

##### 开发中遇到问题---> [问题集锦](http://community.apicloud.com/bbs/forum.php?mod=viewthread&tid=59901&extra=)

#**敲黑板**

1、初始化失败可能是，开始开发中的第4点么有完成

2、v1.3.2版本的模块，编译的时候需要选择“使用升级环境编译”

3、v1.3.1版本可以兼容支付宝模块“aliPayTradePlus”，编译的时候不能选择“使用升级环境编译”，（v1.3.2不支持支付宝模块）

4、生成安全图片选择v4

5、iOS版本建议使用自己的证书，本小菜可以协助生成iOS证书，以及代理上架appstore

6、v1.3.1和v1.3.2版本均支持高佣转链接，部分隐藏参数可以见问题集锦

7、其他疑难杂症可以加Q：83967200，验证消息：“仙人指路”

#**开发者入驻**

1、首先需要加入百川，[http://baichuan.taobao.com/](http://baichuan.taobao.com/)，按要求一步一步填写过来，成功后如下图

![](https://lc-nw49fjfa.cn-n1.lcfile.com/0f4d76112e4cdba2a83a.png)

#**开通阿里妈妈**

1、开通阿里妈妈会员，获取淘客PID（返利用），[打开阿里妈妈传送门](http://media.alimama.com/user/limit_status.htm?spm=a219a.7395903.0.0.zr6Ni5),显示以下界面则说明开通成功

![](https://lc-nw49fjfa.cn-n1.lcfile.com/f1e4be609babcf782736.png)

2、此时再回来[查看证书权限管理](http://console.baichuan.taobao.com/applications.htm)，是不是已经有了，这个id在调用showTaokeItemById接口的时候需要用到

![](https://lc-nw49fjfa.cn-n1.lcfile.com/01c66849baf9a9dd50dd.jpg)

![](https://lc-nw49fjfa.cn-n1.lcfile.com/9da20198ca197003e540.jpg)

#**加入淘宝联盟**

1、用于查看收益，并把收益转入支付宝，点此加入淘宝联盟[http://pub.alimama.com/?spm=0.0.0.0.CrMksN](http://pub.alimama.com/?spm=0.0.0.0.CrMksN)

![](https://lc-nw49fjfa.cn-n1.lcfile.com/725a6da38693d438b626.png)

![](https://lc-nw49fjfa.cn-n1.lcfile.com/e6c93ce827ce06e69c82.png)

这个和开店一样，需要人工审核，审核时间也有点长，一般1-2个工作日


#**开始开发**

1、接口使用参考[测试DEMO](http://pan.baidu.com/s/1o8oAb0Y),里面的widget.zip

注意要在config.xml文件里添加这一句" < preference name="urlScheme" value="tbopen23256171"/ > ",其中tbopen23256171是tbopen+appkey, appkey是在阿里百川的平台上可以找到

![](https://lc-nw49fjfa.cn-n1.lcfile.com/01c66849baf9a9dd50dd.jpg)

![](https://lc-nw49fjfa.cn-n1.lcfile.com/d1a147ded18e86012d0e.png)

效果

![](https://lc-nw49fjfa.cn-n1.lcfile.com/f0a7b5f535eba54f70cc.png)

```js
<feature name="alibaichuan">
    <param name="urlScheme" value="tbopen23256171"/>
</feature>

//这一句也很重要，决定了是否能拉起手淘APP
<preference name="querySchemes" value="tmall,tbopen" />
```

2、引入百川模块

![](https://lc-nw49fjfa.cn-n1.lcfile.com/c792001777ad49366cf2.png)

3、生成Android版本APP，点击下载，到本地

![](https://lc-nw49fjfa.cn-n1.lcfile.com/2ae870f21df992485035.png)

4、检查是否申请了初级电商权限和开通了后台阿里百川的功能，高级电商非必须

![](https://lc-nw49fjfa.cn-n1.lcfile.com/2ed91beec42839f1fdd9.png)

![](https://lc-nw49fjfa.cn-n1.lcfile.com/ca0ec4e0797d30119e61.png)

5、获取安全图片 yw_1222.jpg，Android的通过apk文件获取（选择V4，再生成图片），iOS需要上传证书，然后通过bundleid获取

![](https://lc-nw49fjfa.cn-n1.lcfile.com/ff7e04ad512b7321d43f.jpg)

下载安全图片

![](https://lc-nw49fjfa.cn-n1.lcfile.com/52095ad311b01e7b2450.jpg)

替换zip包里的安全图片[自定义模块](http://pan.baidu.com/s/1o8oAb0Y)

android版本的替换如下(替换res/drawable文件夹下的图片)，iOS版本替换target目录下的文件

![](https://lc-nw49fjfa.cn-n1.lcfile.com/1209bfd9979bef45bc18.png)

重新压缩自定义模块，直接在commonkey文件夹上点击压缩,并上传到自定义模块，iOS版本的压缩包最好在mac下压缩

![](https://lc-nw49fjfa.cn-n1.lcfile.com/37cb2cd7e257a517b4f8.png)

4、上传自定义模块，**iOS和android两个zip包**

![](https://lc-nw49fjfa.cn-n1.lcfile.com/992cc013a95b37390970.png)

5、并勾选自定义模块，能在已添加模块中看到即可

![](https://lc-nw49fjfa.cn-n1.lcfile.com/b351127e5d2a7f0ddd1b.png)


##**正文**

<div id="openDebug"></div>
#**openDebug**

    打开 chrome 调试 HTML5 页面的开关，具体使用方法可以参考，[传送门](https://docs.apicloud.com/Client-API/Func-Ext/chromeDebug)

    openDebug(callback(ret, err))

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //打开成功
}
```

err：

- 无


```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
alibaichuan.openDebug(function(ret, err) {
  alert(JSON.stringify(ret));
});
```


##可用性

    Android系统

    可提供的1.3.2及更高版本

<div id="initTaeSDK"></div>
#**initTaeSDK**

    初始化模块信息，打开页面require完成就去执行

    initTaeSDK(param, function(ret, err))

##params

isvcode：

- 类型：字符串
- 默认值：无
- 描述：自定义ISVCode,用于服务器订单跟踪。(如果服务器不做处理，可以随便传)

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //初始化成功信息
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 1000                     //错误码
    message:"user is not exist"     //错误描述
}
```

##示例代码

```js
var alibaichuan = null;
apiready = function() {
    alibaichuan = api.require('alibaichuan');
    initSDK();
};
function initSDK() {
    var param = {
        isvcode : "feelinglife"
    };
    alibaichuan.initTaeSDK(param,function(ret, err) {
        if (ret) {
            alert(JSON.stringify(ret));
        } else {
            alert(JSON.stringify(err));
        }
    });
}
```


##可用性

    Android系统, iOS系統

    可提供的1.1.0及更高版本

<div id="showLogin"></div>
#**showLogin**

    打开手淘授权登陆

    showLogin(function(ret, err))

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
  "userNick": "sin",                //用户昵称
  "avatarUrl": "http://wwc...",     //用户头像
  "userId": "AAEe...",              //用户ID
  "isLogin": true                   //是否登陆，true/false
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 1000                     //错误码
    message:"user is not exist"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
alibaichuan.showLogin(function(ret, err) {
    if (ret) {
        alert("登录success:" + JSON.stringify(ret));
    } else {
        alert("登录error:" + JSON.stringify(ret));
    }
});
```


##可用性

    Android系统, iOS系統

    可提供的1.1.0及更高版本

<div id="getUserInfo"></div>
#**getUserInfo**

    获取已经登陆的用户的相关信息

    getUserInfo(function(ret, err))

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
  "userNick": "sin",                //用户昵称
  "avatarUrl": "http://wwc...",     //用户头像
  "userId": "AAEe...",              //用户ID
  "isLogin": true                   //是否登陆，true/false
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9000                //错误码
    message:"Not logged in"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
alibaichuan.getUserInfo(function(ret, err) {
    if (ret) {
        alert("用户信息 ret - " + JSON.stringify(ret));
    } else {
        alert("用户信息 err - " + JSON.stringify(err));
    }
});
```


##可用性

    Android系统, iOS系統

    可提供的1.1.0及更高版本

<div id="logout"></div>
#**logout**

    退出登陆

    logout(function(ret, err))

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //描述
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9000                //错误码
    message:"Not logged in"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
alibaichuan.logout(function(ret, err) {
    if (ret) {
        alert("ret - " + JSON.stringify(ret));
    } else {
        alert("err - " + JSON.stringify(err));
    }
});
```


##可用性

    Android系统, iOS系統

    可提供的1.1.0及更高版本


<div id="showTaokeItemById"></div>
#**showTaokeItemById**

    通过itemid打开宝贝，以下是正常使用，如果需要使用高佣转链功能，请参考[问题集锦](http://community.apicloud.com/bbs/forum.php?mod=viewthread&tid=59901&extra=)

    showTaokeItemById({params}, function(ret, err))

##params

isvcode：

- 类型：字符串
- 默认值：无
- 描述：自定义ISVCode,用于服务器订单跟踪。(如果服务器不做处理，可以随便传)

itemid：

- 类型：字符串
- 默认值：无
- 描述：宝贝的id，itemid为打开宝贝详情后，看到浏览器里有id一项，如"https://item.taobao.com/item.htm?id=45535180986",这里的id就是itemid

- 注：商品id.支持标准的商品id，eg.37196464781；同时支持openItemId，eg.AAHd5d-HAAeGwJedwSnHktBI；必填，不允许为null；

mmpid：

- 类型：字符串
- 默认值：无
- 描述：阿里妈妈的pid，如果你还没有开通 [阿里妈妈-淘宝联盟账号](http://media.alimama.com/user/limit_status.htm?spm=0.0.0.0.yoiYny)，要去阿里妈妈开通账号并且补全账号信息以及绑定支付宝, 因为分销的商品最后是返回到阿里妈妈的账号，并通过支付宝提现拿到的

nativeview：

- 类型：boolean
- 默认值：false
- 描述：是否唤起手机淘宝客户端打开详情页，true：使用手淘客户端打开， false：使用H5页面打开


##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
//如果nativeview为false，且交易成功，则有返回值

{
    "code":999,
    "ordercode":"2551899489323387" //订单编号
}
```

err：

- 类型：JSON对象

内部字段：

```js
//如果nativeview为false，且交易失败，则有返回值

{
  "code": 10010,
  "message": "alipay支付失败，信息为：使用支付宝支付失败，支付宝错误码为 4000, 含义为订单支付失败"
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    isvcode : "feeling",
    itemid : "537687037290", //或openid:AAHyOaTIADoqwaqgX6Kr_lTS
    mmpid : "mm_119457865_0_0",
    nativeview: true
};
alibaichuan.showTaokeItemById(param, function(ret, err) {
    if (ret) {
        alert("ret - " + JSON.stringify(ret));
    } else {
        alert("err - " + JSON.stringify(err));
    }
});
```


##可用性

    Android系统, iOS系統

    可提供的1.1.0及更高版本


<div id="showDetailByURL"></div>
#**showDetailByURL**

    通过URL打开宝贝详情，此方法主要以url的自带佣金为准，当然也可传入高佣转链的隐藏参数，[传送门](http://community.apicloud.com/bbs/forum.php?mod=viewthread&tid=59901&extra=)

    showDetailByURL({params}, function(ret, err))

##params

url：

- 类型：字符串
- 默认值：无
- 描述：商品URL地址, 或者优惠券地址

mmpid：

- 类型：字符串
- 默认值：无
- 描述：阿里妈妈的pid，如果你还没有开通 [阿里妈妈-淘宝联盟账号](http://media.alimama.com/user/limit_status.htm?spm=0.0.0.0.yoiYny)，要去阿里妈妈开通账号并且补全账号信息以及绑定支付宝, 因为分销的商品最后是返回到阿里妈妈的账号，并通过支付宝提现拿到的

nativeview：

- 类型：boolean
- 默认值：false
- 描述：是否唤起手机淘宝客户端打开详情页，true：使用手淘客户端打开， false：使用H5页面打开(如果是优惠券，请使用长连接)

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
//如果nativeview为false，且交易成功，则有返回值

{
    "code":999,
    "ordercode":"2551899489323387" //订单编号
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9001                    //错误码
    message:"Parameter is null"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    url : "http://h5.m.taobao.com/ump/coupon/detail/index.html?sellerId=2081981169&activityId=8314c70c51674cff90a7a010ae7d4274",
    mmpid : "mm_119457865_0_0",
    nativeview:false
};
alibaichuan.showDetailByURL(param, function(ret, err) {
    if (ret) {
        alert(JSON.stringify(ret));
    } else {
        alert(JSON.stringify(err));
    }
});
```

##可用性

    Android系统，iOS系统

    可提供的1.0.3及更高版本

<div id="openMyCart"></div>
#**openMyCart**

    打开我的购物车

    openMyCart(function(ret, err))

##params

isvcode：

- 类型：字符串
- 默认值：无
- 描述：自定义ISVCode,用于服务器订单跟踪。(如果服务器不做处理，可以随便传)   

nativeview：

- 类型：boolean
- 默认值：false
- 描述：是否唤起手机淘宝客户端打开详情页，true：使用手淘客户端打开， false：使用H5页面打开

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //描述
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9000                //错误码
    message:"Not logged in"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    isvcode: "feeling",
    nativeview:false
};
alibaichuan.openMyCart(param, function(ret, err) {
    if (ret) {
        alert("ret - " + JSON.stringify(ret));
    } else {
        alert("err - " + JSON.stringify(err));
    }
});
```

##可用性

    Android系统，iOS系统

    可提供的1.1.0及更高版本

<div id="myOrdersPage"></div>
#**myOrdersPage**

    打开我的订单

    myOrdersPage({params}, function(ret, err))

##params

status：

- 类型：数字
- 默认值：无
- 描述：默认跳转页面, 0：全部；1：待付款；2：待发货；3：待收货；4：待评价。 若传入的不是这几个数字，则跳转到“全部”页面且“allOrder”失效 

isAllOrder：

- 类型：boolean
- 默认值：无
- 描述：true：显示全部订单，建议填true，不管status传什么都填true

nativeview：

- 类型：boolean
- 默认值：false
- 描述：是否唤起手机淘宝客户端打开详情页，true：使用手淘客户端打开， false：使用H5页面打开

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //描述
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9000                //错误码
    message:"Not logged in"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    status: 2,
    isAllOrder:true,
    nativeview:true
};
alibaichuan.myOrdersPage(param, function(ret, err) {
    if (ret) {
        alert("ret - " + JSON.stringify(ret));
    } else {
        alert("err - " + JSON.stringify(err));
    }
});
```

##可用性

    Android系统，iOS系统

    可提供的1.2.1及更高版本

<div id="openShopPage"></div>
#**openShopPage**

    打开商家店铺

    openShopPage({params}, function(ret, err))

##params

shopid：

- 类型：字符串
- 默认值：无
- 描述：商家店铺ID 

nativeview：

- 类型：boolean
- 默认值：false
- 描述：是否唤起手机淘宝客户端打开详情页，true：使用手淘客户端打开， false：使用H5页面打开

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //描述
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9001                    //错误码
    message:"Parameter is null"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    shopid : "64185146",
    nativeview:false
};
alibaichuan.openShopPage(param, function(ret, err) {
    if (ret) {
        alert(JSON.stringify(ret));
    } else {
        alert(JSON.stringify(err));
    }
});
```

##可用性

    Android系统，iOS系统

    可提供的1.2.1及更高版本

<div id="addToCartPage"></div>
#**addToCartPage**

    打开添加购物车的页面

    addToCartPage({params}, function(ret, err))

##params

itemid：

- 类型：字符串
- 默认值：无
- 描述：宝贝的id，itemid为打开宝贝详情后，看到浏览器里有id一项，如"https://item.taobao.com/item.htm?id=45535180986",这里的id就是itemid

- 注：商品id.支持标准的商品id，eg.37196464781；同时支持openItemId，eg.AAHd5d-HAAeGwJedwSnHktBI；必填，不允许为null；

##callback(ret,err)

ret：

- 类型：JSON对象

内部字段：

```js
{
    code : 0              //正确码
    message:"success"     //描述
}
```

err：

- 类型：JSON对象

内部字段：

```js
{
    code : 9001                    //错误码
    message:"Parameter is null"     //错误描述
}
```

##示例代码

```js
var alibaichuan = api.require('alibaichuan');
var param = {
    itemid : "540384005662"
};
alibaichuan.addToCartPage(param, function(ret, err) {
    if (ret) {
        alert(JSON.stringify(ret));
    } else {
        alert(JSON.stringify(err));
    }
});
```

##可用性

    Android系统，iOS系统

    可提供的1.2.1及更高版本

<div id="errcode"></div>
#**错误码**

code | 描述
:-----------  | :-------------:
0 | 请求成功
999 | 交易成功
9000 | 用户未登陆
9001 | 参数为空
其他 | 阿里返回的code和错误提示

##**后序**

####文档写的不明白，请联系QQ:83967200，备注：仙人指路
