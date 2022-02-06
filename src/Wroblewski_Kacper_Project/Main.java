package Wroblewski_Kacper_Project;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* KACPER WROBLEWSKI (D00227356) */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Decryptor decryptor = new Decryptor();

        boolean exit = false;

        int menuInput;
        int key = 3;

        String keyWord = "DONE";
        String cipher1 = "";
        String cipher2 = "";

        try
        {
            File cipher = new File("ciphertext.txt");
            Scanner file = new Scanner(cipher);

            //Reading the two ciphers from the file and storing them in variables

            cipher1 = file.nextLine();
            cipher2 = file.nextLine();

            System.out.println("Cipher 1: " + cipher1);
            System.out.println("Cipher 2: " + cipher2);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            exit = true;
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Given file is empty or contains only one line");
            exit = true;
        }

        while (!exit)
        {
            try
            {
                System.out.println("\n+---------------------------------------+");
                System.out.println("| 1. Decryption with a known key        |");
                System.out.println("| 2. Decryption with an unknown key     |");
                System.out.println("| 3. Quit                               |");
                System.out.println("+---------------------------------------+");

                System.out.print("Choose a menu item: ");
                menuInput = input.nextInt();

                if (menuInput == 1)
                {
                    decryptor.decryptCipher(key, cipher1);
                }
                else if (menuInput == 2)
                {
                    decryptor.decryptCipher(cipher2, keyWord);
                }
                else if (menuInput == 3)
                {
                    exit = true;
                }
                else
                {
                    System.out.println("\nInvalid input, please try again");
                }
            }
            catch (InputMismatchException e)
            {
                input.nextLine(); // <= To prevent infinite loop
                System.out.println("\nInvalid input, please try again");
            }
            catch (StringIndexOutOfBoundsException e) //This normally should not happen. If this exception is caught, that means that there is something inherently wrong with the code
            {                                         //and there is no good reason for the program to continue running
                System.out.println("\nFATAL ERROR: Attempted to access non-existent index");
                System.out.println("================================================================================================");
                exit = true;
            }
        }

        System.out.println("Program closing.....");
    }
}
