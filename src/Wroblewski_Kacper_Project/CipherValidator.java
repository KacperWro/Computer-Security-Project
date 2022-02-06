package Wroblewski_Kacper_Project;

public class CipherValidator {

    //This method checks if a cipher is composed of only white spaces
    // If it finds a character that is not a white space, it stops the loop and returns 'isEmpty' as false
    public boolean checkIfEmpty(String cipher)
    {
        boolean isEmpty = true;

        int count = 0;

        while (count < cipher.length() && isEmpty)
        {
            if(cipher.charAt(count) != ' ')
            {
                isEmpty = false;
            }
            count++;
        }

        return isEmpty;
    }
}
