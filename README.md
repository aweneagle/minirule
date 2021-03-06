
# 使用之前

## 一个简单的示例
假设这么个场景，你需要做这么个简单的运营活动，大于10级的用户，发放A奖励；小于等于10级的用户，则发放B奖励

那么我们可以抽象出一个这样的流程图




# minirule

这是个使用java实现的迷你规则引擎，语法简洁可靠，容易上手

它可以帮你实现:

*  灵活的任务链

它支持：

*  嵌套
*  事务执行
*  上下文传参
*  灵活的数学运算


## 开始使用
引入maven repo
```xml
  <repositories>
    <repository>
        <id>github-minirule-repo</id>
        <name>The Maven Repository on Github</name>
        <url>https://github.com/aweneagle/minirule/maven-repo/</url>
    </repository>
</repositories>
```

```xml
      <dependency>
          <groupId>org</groupId>
          <artifactId>minirule</artifactId>
          <version>1.0.0</version>
      </dependency>
```

示例代码：
```java
public static void main( String[] args ) throws Exception
{
    MiniRule mr = new MiniRule();
    String script = "mul(2, 10) as a {" +
    "    a > b {" +
    "        return {\"A\":\"hello\"}" + 
    "    }" +
    "    else {" +
    "        return {\"A\":\"world\"}" + 
    "    }" +
    "}";

    mr.addMethod("mul", new Method(){
        @Override
        public IVar call(IVar... params) throws FuncCallException, RuleException {
            double a = params[0].toDouble();
            double b = params[1].toDouble();
            return Var.New((int)a * (int)b);
        }
    });
    Prog prog = mr.compile(script);
    HashMap<String,Object> globals = new HashMap<String, Object>();
    globals.put("b", 10);
    HashMap <String, Object> res = prog.call(globals);
    // 2*10 > 10, print 'hello'
    System.out.printf("%s\n", res.get("A"));
    globals.put("b", 20);
    // 2*10 <= 20, print 'world'
    res = prog.call(globals);
    System.out.printf("%s\n", res.get("A"));
}
```

## 使用手册

#### 1. 编写规则

*  查询用户信息 QueryUser()
*  如果用户年龄<=10, 输出"this is a child"
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

1.  条件判断的用法
1.  as的用法
1.  嵌套
1.  属性的访问
1.  数组元素的访问
4.  上下文传参
6.  类型转换
5.  数学运算
7.  比较运算
2.  事务
3.  并行执行

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

####  属性的访问
我们使用.进行属性的访问，它可以作用于：
1.  HashMap<String, ?> 的元素
2.  Class对象的public属性
3.  链式访问

###### HashMap<String, ?>的元素
当userInfo()返回一个 HashMap<String, int> 时, 可以这样获取age字段：
```
userInfo() as u {
   u.age > 10 {
      ...
   }
}
```

###### class的public属性

如果userInfo()返回的是个这样的对象：
```
class UserInfo {
   public age int;
}
```
也可以用上述的方式获取age字段：
```
userInfo() as u {
   u.age > 10 {
      ...
   }
}
```

###### 属性的链式访问

如果userInfo()返回的对象是这样的：
```
class UserInfo {
   public HashTable<String, int> profile;
}
```
可以这样获取profile的age属性：
```
userInfo() as u {
    u.profile.age > 10 {
       ...
    }
}
```
`u.profile.age` 相当于 `u.profile.get("age")`

###### 如果属性名称是个变量
假设 chooseFiled() 返回一个字段名字，可以这样获取profile对应的元素
```
userInfo() as u; chooseField() as field {
    u.profile[field] > 10 {
         ...
    }
}
```
`u.profile[field]` 相当于 `u.profile.get(field);`

###### 属性名不存在
如果属性名不存在，将得到一个null，不会有异常抛出

####  数组元素的访问
数组元素的访问使用[]符。

###### 访问数组[]的元素
元素的下标与java一样，从0算起，假设userList()返回一个ArrayList<UserInfo> , 可以这样获取第2个元素
```
userList() as u {
   u[2].age > 10 {
      ...
   }
}
```
  
###### 如果索引是个变量
假设 random(max) 返回一个int, 可以这么随机获取列表中的某个元素:
```
userList() as u; random(len(u)) as index {
    u[index].age > 10 {
       ...
    }
}
```
  
######  数组越界
如果数组越界，RuleException(RuleError.IndexOutOfRange) 将被抛出
  
####  上下文传参
####  +运算符
+ 禁止跨类型的数据运算
+ 支持数字运算和字符串拼接

####  数字类型
在minirule的内存中，所有数字类型都会被转换成double来存储，比较，和运算，当传递到用户定义的函数时，再强制转换成对应的数字类型。minirule不会处理强制转换过程中遇到的精度丢失

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
 
