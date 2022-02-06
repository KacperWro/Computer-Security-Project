package Wroblewski_Kacper_Project;

public class Decryptor {
    CipherValidator validate = new CipherValidator();

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int indexMinusKey;
    int finalIndex;

    //METHOD TO DECRYPT CIPHER USING A KNOWN KEY
    public void decryptCipher(int key, String cipher)
    {
        if (cipher.length() > 0 && !validate.checkIfEmpty(cipher)) //Checks if the cipher has no characters, or if it's only made up of spaces
        {
            System.out.println("\nDecryption with a known key");
            System.out.println("================================================================================================");
            System.out.println("Encrypted message: " + cipher);
            System.out.println("Decrypted message: " + decryptedCipher(cipher, key));
            System.out.println("================================================================================================");
        }
        else
        {
            System.out.println("ERROR: Cipher is an empty string which cannot be decrypted");
        }
    }

    //OVERLOADING METHOD
    //METHOD TO DECRYPT CIPHER WITH UNKNOWN KEY, USING A KEY WORD

    public void decryptCipher(String cipher, String keyWord)
    {
        boolean foundKeyWord = false;
        int keyCount = 1;

        String decrypted = "";

        if (cipher.length() > 0 && !validate.checkIfEmpty(cipher)) //Checks if the cipher has no characters, or if it is only made up of spaces
        {
            System.out.println("\nDecryption with an unknown key");
            System.out.println("================================================================================================");
            System.out.println("Encrypted message: " + cipher);

            //Performing exhaustive key search. It calls the 'decryptedCipher' method until the final decrypted message contains the word 'DONE'.
            //If it doesn't contain the word 'DONE' the key will be incremented and 'decryptedCipher' will be called again with the new key passed in as an argument
            while(!foundKeyWord && keyCount < alphabet.length())
            {
                decrypted = decryptedCipher(cipher, keyCount);

                if (decrypted.contains(keyWord))
                {
                    foundKeyWord = true;
                }
                else
                {
                    keyCount++;
                }
            }

            if(foundKeyWord)
            {
                System.out.println("Decrypted message: " + decrypted);
                System.out.println("\nCipher key: " + keyCount);
            }
            else
            {
                System.out.println("Decryptor was unable to find the key word");
            }
            System.out.println("================================================================================================");
        }
        else
        {
            System.out.println("ERROR: Cipher is an empty string which cannot be decrypted");
        }
    }

    public String decryptedCipher(String cipher, int key)
    {
        String decrypted = "";
        String tempCipher = cipher.toUpperCase();

        for (int i = 0; i < tempCipher.length(); i++)
        {
            if (tempCipher.charAt(i) == ' ')
            {
                decrypted += " "; //Preserves spaces
            }
            else if (tempCipher.charAt(i) >= 'A' && tempCipher.charAt(i) <= 'Z') //Completely disregards non alpha characters and doesn't include them in the final decrypted message
            {
                indexMinusKey = alphabet.indexOf(tempCipher.charAt(i))-key; //Gets index of the current cipher character in the 'alphabet' String and subtracts the key
                finalIndex = Math.floorMod(indexMinusKey, alphabet.length()); // indexMinusKey mod 26, which is the length of the 'alphabet' String
                decrypted += alphabet.charAt(finalIndex);  // The final index is used to get the character in the 'alphabet' String at that index and is appended to the decrypted message
            }
        }

        return decrypted;
    }
}
