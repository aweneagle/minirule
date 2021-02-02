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
      u.age <= 30 {
        return {
          "msg": "this is an adult"
        }
      }
      else {
        return {
          "msg": "this is an older"
        }
      }
  }
```

#### 2. 实现method


