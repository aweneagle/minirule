package org.minirule;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.minirule.grammar.FailException;
import org.minirule.grammar.FuncCallException;
import org.minirule.grammar.FuncForTest;
import org.minirule.grammar.IVar;
import org.minirule.grammar.Method;
import org.minirule.grammar.RuleException;
import org.minirule.grammar.Tran;
import org.minirule.grammar.TryAgainException;
import org.minirule.grammar.Var;

public class MiniRuleTest {
    public static int storage = 100;
    public static int frozen = 0;
    @Test
    public void tran() throws Exception {
        MiniRule mr = new MiniRule();
         mr.addTran("A", new Tran() {
             private int reward;
            @Override
            public IVar prepare(String uuid, HashMap<String, Object>context, IVar... params) throws TryAgainException, FailException, RuleException {
                reward = (int)params[0].toDouble();
                if (reward > storage) {
                    throw new FailException();
                }
                frozen += reward;
                storage -= reward;
                return Var.New(reward);
            }

            @Override
            public void confirm(String uuid) throws TryAgainException {
                frozen -= reward;
            }

            @Override
            public void cancle(String uuid) throws TryAgainException {
                frozen -= reward;
                storage += reward;
            }
        });

        // A 成功, B 成功
        // A 成功, B 失败
        String script = "Tran:A(20), A(40) as B3 {" + "    return {\"A\":A, \"B3\":B3}" + "}";
        Prog prog = mr.compile(script);
        HashMap <String, Object> res = prog.call(null);

        assertEquals(20, res.get("A"));
        assertEquals(40, res.get("B3"));
        assertEquals(40, storage);
        assertEquals(0, frozen);

        // A 成功, B 失败, TODO 使用context
        try {
            prog.call(null);
        } catch (RuleException e) {

        }
        assertEquals(40, storage);
        assertEquals(0, frozen);

    }

    @Test
    public void globals() throws Exception {
        // MiniRule mr = new MiniRule();
        // String script = "A(2, 10) as a {" +
        // "    a > b {" +
        // "        return {\"A\":\"hello\"}" + 
        // "    }" +
        // "    else {" +
        // "        return {\"A\":\"world\"}" + 
        // "    }" +
        // "}";
        
        // mr.addMethod("A", new Method(){
        //     @Override
        //     public IVar call(IVar... params) throws FuncCallException, RuleException {
        //         double a = params[0].toDouble();
        //         double b = params[1].toDouble();
        //         return Var.New((int)a * (int)b);
        //     }
        // });
        // Prog prog = mr.compile(script);
        // HashMap<String,Object> globals = new HashMap<String, Object>();
        // globals.put("b", 10);
        // HashMap <String, Object> res = prog.call(globals);
        // // 2+10 > 10, print 'hello'
        // System.out.printf("%s\n", res.get("A"));
        // globals.put("b", 13);
        // // 2+10 < 13, print 'world'
        // res = prog.call(globals);
        // System.out.printf("world\n", res.get("A"));
    }

    @Test
    public void method() throws Exception {
        // TODO 测函数不存在
        // TODO 测无返回
        // TODO 测返回null
        // TODO 测有异常
        // TODO 测参数类型转换
        // TODO 测试参数类型转换失败
        // TODO 测试参数数量不够

        // 测试简单函数(不带context)
        // 测试上下文函数(带context)
        MiniRule mr = new MiniRule();
        String script = "A(2,10), A(1,3) as A2 {" 
        + "    return {\"A\":A, \"A2\":A2}" 
        + "}";
        mr.addMethod("A", new Method() {
            @Override
            public IVar call(IVar... params) throws FuncCallException, RuleException {
                double a = params[0].toDouble();
                double b = params[1].toDouble();
                return Var.New((int)a * (int)b);
            }
        });
        Prog prog = mr.compile(script);
        HashMap <String, Object> res = prog.call(null);
        assertEquals(20, res.get("A"));
        assertEquals(3, res.get("A2"));

        script = "B(2,10) {" 
        + "    return {\"B\":B}" 
        + "}";
        mr.addMethod("B", new Method() {
            @Override
            public IVar call(IVar... params) throws FuncCallException, RuleException {
                double a = params[0].toDouble();
                double b = params[1].toDouble();
                return Var.New((int)a * (int)b * (int)context.get("alph"));
            }
        });
        prog = mr.compile(script);
        HashMap<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("alph", 100);
        res = prog.call(ctx);
        assertEquals(2000, res.get("B"));
    }
    @Test
    public void failure() throws Exception {
        // TODO 测试script语法错误

        // TODO 测试script运行错误(类型转换失败)
        // TODO 测试script运行错误(method不存在)
        // TODO 测试script运行错误(自定义函数抛异常)
        // TODO 测试script运行错误(自定义函数throwable异常)
        // ....
        // TODO 测试事务“重试”异常（返回uuid)
        // TODO 测试事务“失败“异常 (返回uuid)
    }
    @Test
    public void minirule() throws Exception {
        // 测试as关键字
        // 测试if分支
        // 测试else分支
        // 测试串行
        // 测试Context透传 TODO
        // 测试并行 TODO

        // 测试as关键字
        // 测试串行
        MiniRule mr = new MiniRule();
        String script = "A(2, 10), A(1,3) as A2 {" +
        "    return {\"A\":A, \"A2\":A2}" +
        "}";
        // mr.addFunc("A", FuncForTest.class, "mul", int.class, int.class);
        mr.addMethod("A", new Method() {
            @Override
            public IVar call(IVar... params) throws FuncCallException, RuleException {
                double a = params[0].toDouble();
                double b = params[1].toDouble();
                return Var.New((int)a * (int)b);
            }
        });
        Prog prog = mr.compile(script);
        HashMap <String, Object> res = prog.call(null);
        assertEquals(20, res.get("A"));
        assertEquals(3, res.get("A2"));
        // 测试if分支
        script = "A(2, 10) {" +
        "    A > 10 {" +
        "       return {\"A\":4}" +
        "    } else {" +
        "       return {\"A\":5}" +
        "    }" +
        "}";
        prog = mr.compile(script);
        res = prog.call(null);
        assertEquals(4, res.get("A"));
        // 测试else分支
        script = "A(2, 10) {" +
        "    A > 100 {" +
        "       return {\"A\":4}" +
        "    } else {" +
        "       return {\"A\":5}" +
        "    }" +
        "}";
        prog = mr.compile(script);
        res = prog.call(null);
        assertEquals(5, res.get("A"));
    }
}
