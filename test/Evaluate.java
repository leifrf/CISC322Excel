package test;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class Evaluate {
	public static void main(String[] args) throws Exception{
		String expression = "=40+(2/20)+cos(50)";
		if (expression.startsWith("=")) {
			expression = expression.substring(1);
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			try {
				System.out.println(engine.eval(expression));
			} catch (ScriptException e) {
				System.err.println("Operation is not supported");
			}
		}
	} 
}