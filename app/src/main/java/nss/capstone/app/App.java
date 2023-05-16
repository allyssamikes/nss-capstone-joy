/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package nss.capstone.app;

import nss.capstone.list.LinkedList;

import static nss.capstone.utilities.StringUtils.join;
import static nss.capstone.utilities.StringUtils.split;
import static nss.capstone.app.MessageUtils.getMessage;

import org.apache.commons.text.WordUtils;

public class App {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        System.out.println(WordUtils.capitalize(result));
    }
}
