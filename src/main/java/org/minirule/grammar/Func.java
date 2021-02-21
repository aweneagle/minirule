package org.minirule.grammar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class Func
{
    private Method method;
    private Object obj;
    private Class<?>[] parametersType;
    // 注册函数
    public Func(Class<?> c, String methodName, Class<?>...parametersType) throws Exception {
        this.parametersType = parametersType;
        this.obj = c.getDeclaredConstructor().newInstance();
        this.method = c.getMethod(methodName, parametersType);
    }

    // 注册函数
    public Func(String className, String methodName, Class<?>...parametersType) throws Exception {
        Class<?> c = Class.forName(className);
        this.parametersType = parametersType;
        this.obj = c.getDeclaredConstructor().newInstance();
        this.method = c.getDeclaredMethod(methodName, parametersType);
    }

    // 调用函数
    public IVar call(IVar... args) throws FuncCallException, RuleException {
        if (parametersType.length != args.length) {
            throw new RuleException(RuleError.FuncArgsNumber, 
                String.format("need %d args, but %d found", parametersType.length, args.length));
        }
        Object[] argObjs = new Object[args.length];
        for (int i = 0; i < args.length; i ++) {
            argObjs[i] = args[i].cast(parametersType[i]);
        }
        try {
            Object res = this.method.invoke(obj, argObjs);
            return Var.New(res);
        } catch (IllegalArgumentException e) {
            throw new RuleException(RuleError.FuncArgsNumber, e.getMessage());
        } catch (InvocationTargetException e) {
            throw new FuncCallException(e.getTargetException().getMessage());
        } catch (IllegalAccessException e) {
            throw new FuncCallException(RuleError.FuncNotAccessable);
        }
    }
}