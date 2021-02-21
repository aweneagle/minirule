package org.minirule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Hashtable;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.minirule.grammar.Func;
import org.minirule.grammar.Method;
import org.minirule.grammar.Tran;

public class MiniRule {
    private Hashtable<String, Method> methods;
    private Hashtable<String, Func> funcs;
    private Hashtable<String, Tran> trans;
    public Func func(String name) {
        return this.funcs.get(name);
    } 

    public Tran tran(String name) {
        Tran m = this.trans.get(name);
        if (m == null) {
            return null;
        }
        return m.copy();
    }

    public Method method(String name) {
        Method m = this.methods.get(name);
        if (m == null) {
            return null;
        }
        return m.copy();
    }

    public MiniRule() {
        this.funcs = new Hashtable<String, Func>();
        this.methods = new Hashtable<String, Method>();
        this.trans = new Hashtable<String, Tran>();
    }

    public void addTran(String alias, Tran t) {
        this.trans.put(alias, t);
    }

    public void addMethod(String alias, Method m) {
        this.methods.put(alias, m);
    }

    public void addFunc(String alias, Class<?> c, String methodName, Class<?>...parametersType) throws Exception {
        this.funcs.put(alias, new Func(c, methodName, parametersType));
    }

    public void addFunc(String alias, String className, String methodName, Class<?>...parametersType) throws Exception {
        this.funcs.put(alias, new Func(className, methodName, parametersType));
    }

    public Prog compile(String script) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(script.getBytes());
        return compile(inputStream);
    }

    public Prog compile(InputStream inputStream) throws Exception {
                 /*
          * get the input file as an InputStream
          */
          CharStream input = CharStreams.fromStream(inputStream);
          /*
           * make Lexer
           */
          MiniRuleLexer lexer = new MiniRuleLexer(input);
          /*
           * get a TokenStream on the Lexer
           */
          CommonTokenStream tokenStream = new CommonTokenStream(lexer);
          /*
           * make a Parser on the token stream
           */
          MiniRuleParser parser = new MiniRuleParser(tokenStream);
          /*
           * get the top node of the AST. This corresponds to the topmost rule of minirule.q4, "actionblock"
           */
          ParseTree prog = parser.actionblock();
          return new Prog(this, prog);
    }
}
