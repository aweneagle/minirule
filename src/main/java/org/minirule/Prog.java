package org.minirule;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;
import org.minirule.grammar.FuncCallException;
import org.minirule.grammar.IVar;
import org.minirule.grammar.RuleException;
import org.minirule.grammar.VarError;

public class Prog {
    private MiniRule engine;
    private ParseTree prog;

    protected Prog(MiniRule engine, ParseTree prog) {
        this.engine = engine;
        this.prog = prog;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Object> call(HashMap<String, Object> context) throws RuleException, FuncCallException {
          Visitor v = new Visitor(this.engine);
          if (context != null) {
              v.context = context;
          }
          IVar res = v.visit(prog);
          if (res.getType() == VarError.class) {
              VarError e = (VarError)res;
              throw e.getException();
          } else {
              // 返回内容
              return (HashMap<String, Object>)res.getValue();
          }
    }
}
