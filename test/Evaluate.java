package test;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class Evaluate {
	public static void main(String[] args) throws Exception{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String expression = "40+(2/20)";
		System.out.println(engine.eval(expression));
	} 
}