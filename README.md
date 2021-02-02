# minirule

这是个使用java实现的迷你规则引擎，语法简洁可靠，容易上手

它可以帮你实现:

*  灵活的任务链

它支持：

*  事务执行
*  并行执行
*  上下文透传
*  条件嵌套 如：`if 条件1 {  if 条件2 { ... } }`
*  灵活的数学运算  如：` if A.level + 10*A.age > 12 { ... } `
*  灵活的函数调用  ` JobA(Mul(10,1) + C.name*10, "fortest") { ... } `


## 开始使用

#### 1. 编写规则
```
  QueryUser() as u {
      u.age <= 10 {
        return {
          "msg": "this is a child"
        }
      }
      else {
        return {
          "msg": "this is an adult"
        }
      }
  }
```

#### 2. 实现method
```java
MiniRule mr = new MiniRule();
mr.addMethod("QueryUser", new Method{
    public HashMap<String, Object> call(IVar[] ...params) {
       HashMap<String, Object> user = new HashMap<String,Object>();
       // query user info from db , or somewhere else
       // ...
       return user;
    }
});
```

####  3. 编译规则并执行
```java
MiniRule mr = new MiniRule();
String script = "QueryUser() as u { ... }"; // 步骤1.编写得到的规则语法
Prog rule = mr.compile(script);
HashMap<String, Object> result = rule.call(null);
System.out.printf("msg:%s", result.get("msg"));  // 输出得到结果
```
