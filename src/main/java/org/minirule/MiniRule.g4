// 示例:
// A(1) | A(2) as A2 {
// 	 A.level > 10 && A2.level < 11 + A.level {
// 		return {
// 			A: A2,
// 			A2: A,
// 		}
// 	 }
//   else {
//      return { A: A, A2: A2 }
//   }
// }
grammar MiniRule;		//文法名字为 MiniRule

// 动作
actionblock
	: action block		#actCondition
	;

block
	:  '{' (ifcmp block) + ('else' block)? '}' #blockIfcmp //条件
	|  '{' (action block) ? '}' 	 		   #blockAction    //动作
	|  '{' 'return' '{' (Str ':' param )(',' Str ':' param)* '}' '}' 	 		   #blockReturn    //返回
	;


action
	: fcall (',' fcall) ?  #fcallSerial    //串行执行
	| fcall ('|' fcall) ?  #fcallParallel  //并行执行
	| 'Tran' ':' fcall (',' fcall) + #tranSerial    //串行事务(至少有两个fcall)
	| 'Tran' ':' fcall ('|' fcall) + #tranParallel  //并行事务(至少有两个fcall)
	;


fcall
	 : ID '(' ( param (',' param) * )? ')' ('as' ID)?
	 ;

	// 条件判断
ifcmp
	: True  				#ifcmpTrue
	| False 				#ifcmpFalse
	| param op=('>'|'>='|'<'|'<='|'!='|'==') param   #ifcmpOp
	| '(' ifcmp ')'         #ifcmpAgain
    | '!' ifcmp             #ifcmpNot
    | ifcmp '&&' ifcmp      #ifcmpAnd
	| ifcmp '||' ifcmp      #ifcmpOr
    ;



// 数学运算
mathcal
    : Int			       			#mathcalInt
    | Float		       				#mathcalFloat
	| variable		       			#mathcalVariable
    | '(' mathcal ')'		    	#mathcalAgain
    | mathcal ('^') mathcal     	#mathcalMM
    | mathcal op=('*'|'/') mathcal     #mathcalMulDiv
    | mathcal op=('+'|'-') mathcal     #mathcalAddDec
    ;

// 函数参数
param
	: True     		#paramTrue
	| False    		#paramFalse
	| Int      		#paramInt
	| Float     	#paramFloat
	| Str       	#paramStr
	| variable   	#paramVariable
	| mathcal   	#paramMathcal
	;

// 定义只读变量（其定义与可写变量相同）
variable
	: ID                   		#variableID
	| variable '.' ID      		#variableMap
	| variable '[' Int ']' 		#variableList
	| variable '[' variable ']' #variableVarList
	;

True: 'true';
False: 'false';
ID : [a-zA-Z_][a-zA-Z0-9_]*;
Str: '"' (ESC|.)*? '"' ;
Int: '-'?[0-9]+;
Float: '-'?[0-9]+ '.' [0-9]+;
WS : [ \t\r\n]+ -> skip ;
ESC : '\\"' | '\\\\' ;    // 匹配字符\"和\\