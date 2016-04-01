package excel;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class Evaluate {
	public static void main(String[] args){
		String expression = "=40+(2/20)+(50)";
		try {
			System.out.println(evaluate(expression));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	} 

	public static double evaluate(String expression) throws ScriptException{
		double result = 0;
		if (expression.startsWith("=")) {
			expression = expression.substring(1);
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			try {
				result = (double)engine.eval(expression);
			} catch (ScriptException e) {
				throw new ScriptException("Operation is not supported");
			}
		}
		return result;
	}
}