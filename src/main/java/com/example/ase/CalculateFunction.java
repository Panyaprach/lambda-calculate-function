package com.example.ase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class CalculateFunction implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        try {
            double x = Double.valueOf(event.get("x"));
            double y = Double.valueOf(event.get("y"));
            String operator = event.get("op");
            switch(operator) {
                case "+": return String.format("%.2f %s %.2f = %.2f",x, operator, y, x + y);
                case "*": return String.format("%.2f %s %.2f = %.2f",x, operator, y, x * y);
                case "/": return String.format("%.2f %s %.2f = %.2f",x, operator, y, x / y);
                case "-": return String.format("%.2f %s %.2f = %.2f",x, operator, y, x - y);
                case "%": return String.format("%.2f %s %.2f = %.2f",x, operator, y, x % y);
                default: return  "Invalid event: Parameter op support only [*, -, *, /, %]";
            }
        } catch (NumberFormatException e) {
            return "Invalid event: The parameter x and y must be float number";
        } catch (NullPointerException e) {
            return "Invalid event: Not allow null value";
        }
    }
}
