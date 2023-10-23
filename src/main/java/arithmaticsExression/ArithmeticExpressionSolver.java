package arithmaticsExression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ArithmeticExpressionSolver {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            while ((line = reader.readLine()) != null) {
                String expression = line.trim();
                String result = solveArithmeticExpression(engine, expression);
                writer.write(expression + " = " + result);
                writer.newLine();
            }

            System.out.println("Expressions solved and results written to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String solveArithmeticExpression(ScriptEngine engine, String expression) {
        try {
            Object result = engine.eval(expression);
            return result.toString();
        } catch (ScriptException e) {
            return "Error: " + e.getMessage();
        }
    }
}
