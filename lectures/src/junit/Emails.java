package junit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyarudyak on 12/02/15.
 */
public class Emails {

    private String text;

    // Sets up a new Emails obj with the given text
    public Emails(String text) {
        this.text = text;
    }

    // Returns a list of the usernames found in the text.
    // We'll say that a username is one or more letters, digits,
    // or dots to the left of a @.
    public List<String> getUsers() {
        int pos = 0;
        List<String> users = new ArrayList<String>();
        while (true) {
            int at = text.indexOf('@', pos);
            if (at == -1) break;
    // Look backwards from at
            int back = at - 1;
            while (back >= 0 &&
                    (Character.isLetterOrDigit(text.charAt(back)) ||
                            text.charAt(back)=='.')) {
                back--;
            }
    // Now back is before start of username
            String user = text.substring(back + 1, at);
            if (user.length() > 0) users.add(user);
    // Advance pos for next time
            pos = at + 1;
        }
        return users;
    }

    public static void main(String[] args) {

        String s = "this is an example address example@example.com and " +
                "one more john@harvard.edu";
        Emails emails = new Emails(s);
        System.out.println(emails.getUsers());
    }

}
