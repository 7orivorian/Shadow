/*
 * Copyright (c) 2024 7orivorian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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