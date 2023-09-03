## AuthFilter加入静态资源文件的过滤

```java
private static final String[] FILE_SUFFIX = {"jpeg", "jpg","png","gif","bmp", "webp", "css","js", "woff", "woff2"};

if (Arrays.stream(WHITE_URLS).anyMatch(url -> url.equals(servletPath)) || endWith(servletPath)) {  // java8 Stream API
    filterChain.doFilter(request, response);  // 放行请求
}

private boolean endWith(String path) {
    for (String fileSuffix : FILE_SUFFIX) {
        if (path.endsWith(fileSuffix)) {
            return true;
        }
    }
    return false;
}
```

## 前端消息提示插件
- 使用message.min.js
- $.message({message: "请输入账号和密码", type: 'warning'})
- success, warning, error, info

## 封装统一的返回结果
- 了解什么是json格式
- 封装Result对象（使用泛型）
- 封装几个常用的方法 success、error等
- 前台通过 fetch获取到的数据需要改成json() 处理
  .then(res => res.text()) 换成  .then(res => res.json())

## 统一异常处理
- 作用：在业务类里面抛出异常，被全局捕获，然后返回给前台
- 通过 @ResponseBody 转换对象成json
- 注意使用log记录后台错误信息

## 自定义业务异常
- 实现 CustomerException，拦截业务异常
- 返回前台业务错误

