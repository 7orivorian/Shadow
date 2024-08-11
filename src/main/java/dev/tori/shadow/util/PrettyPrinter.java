package dev.tori.shadow.util;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class PrettyPrinter {

    public static void printAsPrettyString(Object object) {
        System.out.println(prettyPrint(object.toString()));
    }

    public static String toPrettyString(Object object) {
        return prettyPrint(object.toString());
    }

    public static String prettyPrint(String input) {
        StringBuilder result = new StringBuilder();
        int indentLevel = 0;
        boolean inQuote = false;

        for (char c : input.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                    if (!inQuote) {
                        result.append(c).append("\n");
                        indentLevel++;
                        appendIndent(result, indentLevel);
                    } else {
                        result.append(c);
                    }
                    break;
                case '}':
                case ']':
                    if (!inQuote) {
                        result.append("\n");
                        indentLevel--;
                        appendIndent(result, indentLevel);
                        result.append(c);
                    } else {
                        result.append(c);
                    }
                    break;
                case ',':
                    if (!inQuote) {
                        result.append(c).append("\n");
                        appendIndent(result, indentLevel);
                    } else {
                        result.append(c);
                    }
                    break;
                case '\'':
                    result.append(c);
                    if (result.charAt(result.length() - 2) != '\\') {
                        inQuote = !inQuote;
                    }
                    break;
                case ' ':
                    if (inQuote) {
                        result.append(c);
                    }
                    break;
                default:
                    result.append(c);
                    break;
            }
        }
        return result.toString();
    }

    private static void appendIndent(StringBuilder sb, int indentLevel) {
        sb.append("    ".repeat(Math.max(0, indentLevel))); // 4 spaces for each indent level
    }
}