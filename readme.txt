包括
1. thymeleaf集成
http://localhost:8080/thymeleaf/hello

创建步骤：
a. classpath:/templates 目录下，创建hello.html
	spring-boot很多配置都有默认配置,比如默认页面映射路径为 
	classpath:/templates/*.html 
b. 填充html文件
c. 创建controller，注解应是@Controller，不是@RestController
d. application.properties添加模板配置（可不配置）
	#thymeleaf start
	spring.thymeleaf.mode=HTML5
	spring.thymeleaf.encoding=UTF-8
	spring.thymeleaf.content-type=text/html
	#\u5F00\u53D1\u65F6\u5173\u95ED\u7F13\u5B58,\u4E0D\u7136\u6CA1\u6CD5\u770B\u5230\u5B9E\u65F6\u9875\u9762
	spring.thymeleaf.cache=false
	#thymeleaf end

maven中直接引入

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
    在application.properties中可以配置thymeleaf模板解析器属性.就像使用springMVC的JSP解析器配置一样

#thymeleaf start
spring.thymeleaf.mode=HTML5
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.content-type=text/html
#开发时关闭缓存,不然没法看到实时页面
spring.thymeleaf.cache=false
#thymeleaf end



1.获取变量值

<p th:text="'Hello！, ' + ${name} + '!'" >3333</p>
可以看出获取变量值用$符号,对于javaBean的话使用变量名.属性名方式获取,这点和EL表达式一样.

另外$表达式只能写在th标签内部,不然不会生效,上面例子就是使用th:text标签的值替换p标签里面的值,至于p里面的原有的值只是为了给前端开发时做展示用的.这样的话很好的做到了前后端分离.

2.引入URL

Thymeleaf对于URL的处理是通过语法@{…}来处理的

<a th:href="@{http://blog.csdn.net/u012706811}">绝对路径</a>
<a th:href="@{/}">相对路径</a>
<a th:href="@{css/bootstrap.min.css}">Content路径,默认访问static下的css文件夹</a>

类似的标签有:th:href和th:src

3.字符串替换

很多时候可能我们只需要对一大段文字中的某一处地方进行替换，可以通过字符串拼接操作完成：

<span th:text="'Welcome to our application, ' + ${user.name} + '!'">
一种更简洁的方式是：

<span th:text="|Welcome to our application, ${user.name}!|">
当然这种形式限制比较多，|…|中只能包含变量表达式${…}，不能包含其他常量、条件表达式等。

4.运算符

在表达式中可以使用各类算术运算符，例如+, -, *, /, %

th:with="isEven=(${prodStat.count} % 2 == 0)"
逻辑运算符>, <, <=,>=，==,!=都可以使用，唯一需要注意的是使用<,>时需要用它的HTML转义符：

    th:if="${prodStat.count} &gt; 1"
    th:text="'Execution mode is ' + ( (${execMode} == 'dev')? 'Development' : 'Production')"

5.条件

if/unless

Thymeleaf中使用th:if和th:unless属性进行条件判断，下面的例子中，标签只有在th:if中条件成立时才显示：

<a th:href="@{/login}" th:unless=${session.user != null}>Login</a>
th:unless于th:if恰好相反，只有表达式中的条件不成立，才会显示其内容。

Switch

Thymeleaf同样支持多路选择Switch结构：

<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
</div>

默认属性default可以用*表示：

<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
  <p th:case="*">User is some other thing</p>
</div>

6.循环

渲染列表数据是一种非常常见的场景，例如现在有n条记录需要渲染成一个表格

，该数据集合必须是可以遍历的，使用th:each标签：
<body>
  <h1>Product list</h1>

  <table>
    <tr>
      <th>NAME</th>
      <th>PRICE</th>
      <th>IN STOCK</th>
    </tr>
    <tr th:each="prod : ${prods}">
      <td th:text="${prod.name}">Onions</td>
      <td th:text="${prod.price}">2.41</td>
      <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
    </tr>
  </table>

  <p>
    <a href="../home.html" th:href="@{/}">Return to home</a>
  </p>
</body>

可以看到，需要在被循环渲染的元素（这里是）中加入th:each标签，
其中th:each=”prod : ${prods}”意味着对集合变量prods进行遍历，循环变量是prod在循环体中可以通过表达式访问。