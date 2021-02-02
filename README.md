# minirule

这是个使用java实现的迷你规则引擎，语法简洁可靠，容易上手

它可以帮你实现:

*  灵活的任务链

它支持：

*  嵌套
*  事务执行
*  并行执行
*  上下文传参
*  数学运算


## 开始使用

#### 1. 编写规则

*  查询用户信息 QueryUser()
*  如果用户年龄>3, 输出"this is a child"
*  否则，输出"this is an adult"

```
  QueryUser() as u {
      u.age <= 10 {
        return { "msg": "this is a child" }
      }
      else {
        return { "msg": "this is an adult" }
      }
  }
```

#### 2. 实现method
```java
MiniRule mr = new MiniRule();
mr.addMethod("QueryUser", new Method(){
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

## 高级进阶

0.  条件判断的用法
0.  as的用法
1.  嵌套
2.  事务
3.  并行执行
4.  上下文传参
5.  数学运算
6.  类型转换, 比较运算符 和 数学运算符

#### 条件判断的用法
条件是从上至下进行判断，匹配之后则执行对应行为，并不再执行后续的条件逻辑。 当所有的条件都检查完之后，如果仍然没有匹配的条件，执行else(如果存在的话）
以下面的规则为例：
1. 如果u.age == 3, 将输出"this is a child", 且不会继续输出"this is an adult"
2. 如果u.age == 11, 将输出"this is an adult"
3. 如果u.age == 31, 将输出"this is an older"

```
  QueryUser() as u {
      u.age <= 10 {
        return { "msg": "this is a child" }
      }
      u.age <= 30 {
        return { "msg": "this is an adult" }
      }
      else {
        return { "msg": "this is an older" }
      }
  }
 ```
 
 #### as 的用法
 当调用了一个method之后，其返回值会存在跟method同名的局部变量中，你可以在后续的过程中使用它，例如：
 
 ```
    JobA() {
      JobA.errCode != 0 {
        ...
      }
    }
 ```
 
 当method的名字太长的时候，你可以使用别名来重命名这个局部变量, 例如:
 
 ```
    JobA() as A {
      A.errCode != 0 {
        ...
      }
    }
 ```
 
 这个规则与上面是等价的
 
 #### 嵌套
 
 method之间嵌套, jobA 成功了之后再执行jobB
 
 ```
 doJobA() as A {
    doJobB() as B {
      ....
    }
 }
```

条件判断嵌套， 条件A满足了之后再判断条件B
```
doJobA() as A {
   A.errCode == 0 {
     A.level > 10 {
        ....
     }
   }
}
```

任意嵌套
```
doJobA() as A {
   A.errCode == 0 {
      doJobB() as B {
        B.errCode == 0 {
          ...
        }
      }
   }
}
```


 #### 事务
 
 ##### 事务的使用
 使用Tran关键字，可以让一组method的调用满足ACID原则，例如：
 ```
 Tran: doJobA(), doJobB() {
    ...
 }
 ```
 当doJobB()执行失败时，doJobA()会进行回滚，当然，前提时我们要让这两个method支持事务
 ##### 如何让method支持事务
 事务需要实现二阶段提交的三个接口： prepare(), confirm(), cancle()
 
 * prepare() 接口： 对需要执行的job进行预处理, 并返回预处理结果； 如果预处理不成功（条件不满足），抛出FailException；如果不确定预处理的结果（例如网络超时），抛出TryAgainException
 * confirm() 接口： 提交job，如果因为网络超时或其他不确定因素，抛出TryAgainException
 * cancle()  接口： 取消job，如果因为网络超时或其他不确定因素，抛出TryAgainException
 
 这三个接口的实现需要是幂等的
 
 示例代码：
 ```java
 MiniRule mr = new MiniRule();
 mr.addTran("doJobA", new Tran(){
    public IVar prepare(String uuid, IVar[] ... params) throws RuleException, TryAgainException, FailException {
      // prepare job
      // prepared result should be stored somewhere, like database
      return Var.New(null);
    }
    public void confirm(String uuid) throws TryAgainException {
      // confirm job
    }
    public void cancle(String uuid) throws TryAgainException {
      // cancle job
    }
 });
 ```
 
