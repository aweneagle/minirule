package org.minirule.grammar;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class GrammarTest {
    @Test
    public void funcCall() throws Exception {
        // 测函数不存在
        // 测无返回
        // 测返回null
        // 测固定参数
        // 测不定参数 + 返回
        // 测有异常
        // 测参数类型转换
        // 测试函数不可访问(私有函数)
        // 测试参数类型转换失败
        // 测试参数数量不够
        // 测试重载函数(A, B)
        Func f;
        int a = 10;
        int b = 10;
        // 测函数不存在
        try {
            f = new Func(FuncForTest.class, "noFunc");
        } catch (Exception e ) {
            assertEquals(true, e instanceof NoSuchMethodException);
        }
        // 测无返回
        f = new Func("org.minirule.grammar.FuncForTest", "noReturn");
        assertEquals(null, f.call().getValue());
        // 测返回null
        f = new Func(FuncForTest.class, "returnNull");
        assertEquals(null, f.call().getValue());
        // 测固定参数
        f = new Func(FuncForTest.class, "mul", int.class, int.class);
        assertEquals(100, f.call(Var.New(a), Var.New(b)).getValue());
        // 测不定参数 + 返回
        f = new Func(FuncForTest.class, "sum", int.class, int[].class);
        assertEquals(30, f.call(Var.New(a), Var.New(new int[]{a,b})).getValue());
        // 测有异常
        f = new Func(FuncForTest.class, "throwsExp");
        try {
            f.call();
        } catch (Exception e) {
            assertEquals(true, e instanceof FuncCallException);
            assertEquals("test func exception", e.getMessage());
        }
        // 测参数类型转换
        f = new Func(FuncForTest.class, "mul", int.class, int.class);
        assertEquals(100, f.call(Var.New(10.1), Var.New(10.2)).getValue());
        // 测试函数不可访问(私有函数)
        // TODO 
        // f = new Func("org.minirule.grammar.FuncForTest", "prov", int.class);
        // try {
        //     f.call(Var.New(10.1));
        // } catch (Exception e) {
        //     assertEquals(true, e instanceof FuncCallException);
        //     assertEquals("test func exception", e.getMessage());
        // }
        // 测试参数类型转换失败
        f = new Func(FuncForTest.class, "mul", int.class, int.class);
        try {
            f.call(Var.New("10a"), Var.New(1.2));
        } catch (Exception e) {
            assertEquals(true, e instanceof RuleException);
        }
        // 测试参数数量不够
        f = new Func(FuncForTest.class, "mul", int.class, int.class);
        try {
            f.call(Var.New("10"));
        } catch (Exception e) {
            assertEquals(true, e instanceof RuleException);
        }
        // 测试重载函数(A, B)
        f = new Func(FuncForTest.class, "mul", int.class, int.class, int.class);
        IVar res = f.call(Var.New(10), Var.New(10), Var.New(10));
        assertEquals(110, res.getValue());
    }

    @Test
    public void list() throws Exception {
        // getElement():
        // 1. 测试[0] == 10
        // 2. 测试list = null
        // 3. 测试list[2]超出范围
        // property(int):
        // 1. 测试[0] == 10
        // 2. 测试list = null
        // 3. 测试list[2]超出范围
        // property(string) 异常

        // getElement():
        // 1. 测试[0] == 10
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(10);
        assertEquals(10, Var.New(l).getElement(0).getValue());
        // 2. 测试list = null
        ArrayList<Integer> n = null;
        assertEquals(null, Var.New(n).getValue());
        assertEquals(null, Var.New(n).getElement(0).getValue());
        // 3. 测试list[2]超出范围
        try {
            Var.New(l).getElement(2).getValue();
        } catch (RuleException e) {
            assertEquals(RuleError.IndexOutOfRange, e.error);
        }

        // property(int):
        // 1. 测试[0] == 10
        assertEquals(10, Var.New(l).property(Var.New(0)).getValue());
        // 2. 测试list = null
        assertEquals(null, Var.New(n).getValue());
        assertEquals(null, Var.New(n).property(Var.New(0)).getValue());
        // 3. 测试list[2]超出范围
        try {
            Var.New(l).property(Var.New(2)).getValue();
        } catch (RuleException e) {
            assertEquals(RuleError.IndexOutOfRange, e.error);
        }
    }

    @Test
    public void map() throws Exception {
        // map[string]object
        // getProperty():
        // 1. 测试["hello"] == 1
        // 2. 测试map = null
        // 3. 测试["world"] 不存在, 返回null
        // property(string):
        // 1. 测试["hello"] == 1
        // 2. 测试map = null
        // 3. 测试["world"] 不存在, 返回null

        // 1. 测试["hello"] == 1
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        m.put("hello", 1);
        IVar vm = Var.New(m);
        assertEquals(1, vm.getProperty("hello").getValue());
        // 2. 测试map = null
        HashMap<String, Integer> n = null;
        assertEquals(null, Var.New(n).getValue());
        assertEquals(null, Var.New(n).getProperty("hello").getValue());
        // 3. 测试["world"] 不存在, 返回null
        assertEquals(null, vm.getProperty("world").getValue());
        // property(string):
        // 1. 测试["hello"] == 1
        assertEquals(1, vm.property(Var.New("hello")).getValue());
        // 2. 测试map = null
        assertEquals(null, Var.New(n).getValue());
        assertEquals(null, Var.New(n).property(Var.New("hello")).getValue());
        // 3. 测试["world"] 不存在, 返回null
        assertEquals(null, vm.property(Var.New("world")).getValue());
    }

    @Test
    public void calculater() throws Exception {
        // +, -, *, /, ^
        Calculater c = new Calculater();
        VarNumber i = new VarNumber(10);
        VarNumber f = new VarNumber(10.101);
        assertEquals(20.101, c.add(i, f).getValue());
        assertEquals(20.0, c.add(i, Var.New(10)).getValue());
        assertEquals(20, c.add(i, Var.New(10)).cast(int.class));
        assertEquals(2, c.dec(i, Var.New(8)).cast(int.class));
        assertEquals(80, c.mul(i, Var.New(8)).cast(int.class));
        assertEquals(1.25, c.div(i, Var.New(8)).getValue());
        assertEquals(10000.0, c.pow(i, Var.New(4)).getValue());
    }

    @Test
    public void compare() throws Exception {
        // >, <, >=, <=, ==, !=
        Compare c = new Compare();
        IVar i = Var.New(10);
        IVar f = Var.New(10.101);
        assertEquals(false, c.gt(i, f).getValue());
        assertEquals(true, c.gt(f, i).getValue());
        assertEquals(true, c.st(i, f).getValue());
        assertEquals(false, c.st(f, i).getValue());
        assertEquals(false, c.gte(i, f).getValue());
        assertEquals(true, c.gte(f, i).getValue());
        assertEquals(true, c.ste(i, f).getValue());
        assertEquals(false, c.ste(f, i).getValue());
        assertEquals(false, c.eq(f, i).getValue());
        assertEquals(true, c.neq(f, i).getValue());

        assertEquals(true, c.ste(f, f).getValue());
        assertEquals(true, c.gte(f, f).getValue());
        assertEquals(true, c.eq(f, f).getValue());
        assertEquals(false, c.neq(f, f).getValue());
    }

    @Test
    public void property() throws Exception {
        IVar a = Var.New(new FuncForTest());
        Calculater c = new Calculater();
        a.setProperty("number", c.add(a.getProperty("number"), Var.New(20)));
        assertEquals(30, a.getProperty("number").getValue());
    }

}
