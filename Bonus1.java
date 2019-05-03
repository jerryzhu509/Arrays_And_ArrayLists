/**
 * @author Jerry Zhu
 * @version 1.0, April 2, 2019
 * <p>
 * This program is the bonus problem in the ArrayList problems.
 * It reads a number and then, in a dictionary, finds and prints all of the words associated with that combination of numebers.
 * </p>
 */
import java.io.*;
import java.util.*;

public class Bonus1
{
  /** Declares the HashSet object that allows me to search the file. */
  private static HashSet<String> set;
  
  
  /**
   * The read method first reads the "DICT.txt" and if the words have a length greater than 0 and less or equal to 4, 
   * it is added to HashSet. It then reads user inputs and outputs the answer.
   * 
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>br </b> This creates a BufferedReader object called br that reads the file.
   * <p>
   * <b>line </b> This creates a String object called line that tests for whether each line of the notepad is null before counting it as a valid.
   * <p>
   * <b>startTime </b> This creates a long called startTime that stores when the search starts.
   * <p>
   * <b>stopTime </b> This creates a long called stopTime that stores when the search stops.
   * <p>
   * <b>letters </b> This creates a String array called letters that stores all of the associated letters with the input number.
   * <p>
   * <b>scanner </b> This creates a Scanner object called scanner that reads input.
   */
  public void read()
  {
    set = new HashSet <String> (60000, 0.9f);
    BufferedReader br;
    String line = "";
    long startTime, stopTime;
    String[] letters;
    Scanner scanner = new Scanner(System.in);
    
    try
    {
      br = new BufferedReader (new FileReader ("DICT.txt"));
      
      while (line != null)
      {
        if (line.length () < 5 && line.length () > 0)
          set.add (line);
        line = br.readLine ();
      }
    }    catch (IOException e)
    {
      System.out.println(e);
    }
    
    OUTER:
      while(true)
    {
      System.out.print ("Enter a number from 1-4 digits, or \"q\" to quit: "); 
      line = scanner.nextLine();
      
      if  (!(line.toLowerCase().equals("q")))
      {
        if (line.length () > 4 || line.length () == 0)
        {
          System.out.println ("You did not enter a valid number. Please try again.");
          continue;
        }
      }
      else 
        break;
      
      startTime = System.nanoTime ();
      letters = new String [line.length()];
      
      for (int i = 0 ; i < line.length () ; i++)
      {
        switch (line.charAt (i))
        {
          case '2':
            letters [i] = "abc";
            break;
          case '3':
            letters [i] = "def";
            break;
          case '4':
            letters [i] = "ghi";
            break;
          case '5':
            letters [i] = "jkl";
            break;
          case '6':
            letters [i] = "mno";
            break;
          case '7':
            letters [i] = "pqrs";
            break;
          case '8':
            letters [i] = "tuv";
            break;
          case '9':
            letters [i] = "wyxz";
            break;
          case '1':
          case '0':
          default:
            System.out.println ("You did not enter a valid number. Please try again.");
            continue OUTER;
        }
      }
      find (letters, line.length ());
      stopTime = System.nanoTime();
      System.out.printf ("Time: %fms\n", (stopTime - startTime) / 1000000.0);
    }
  }
  
  /**
   * The find method loops through the letter arrays and searches the HashSet for if the letters make a word.
   * 
   * @param []letters  String array that stores the letters with each associated number.
   * @param length  int that stores the length of the line.
   */
  private void find (String[] letters, int length)
  {
    if (length == 1)
    {
      for (int i = 0 ; i < letters [0].length () ; i++)
      {
        String answer = letters [0].substring (i, i + 1);
        if (set.contains (answer))
          System.out.append (answer).append ('\n');
      }
    }
    else if(length == 2)
    {
      for (int i = 0 ; i < letters [0].length () ; i++)
      {
        for (int j = 0 ; j < letters [1].length () ; j++)
        {
          String answer = letters [0].substring (i, i + 1) + letters [1].substring (j, j + 1);
          if (set.contains (answer))
            System.out.append (answer).append ('\n');
        }
      }
    }
    else if(length == 3)
    {
      for (int i = 0 ; i < letters [0].length () ; i++)
      {
        for (int j = 0 ; j < letters [1].length () ; j++)
        {
          for (int k = 0 ; k < letters [2].length () ; k++)
          {
            String answer = letters [0].substring (i, i + 1) + letters [1].substring (j, j + 1) + letters [2].substring (k, k + 1);
            if (set.contains (answer))
              System.out.append (answer).append ('\n');
          }
        }
      }
    }
    else
    {
      for (int i = 0 ; i < letters [0].length () ; i++)
      {
        for (int j = 0 ; j < letters [1].length () ; j++)
        {
          for (int k = 0 ; k < letters [2].length () ; k++)
          {
            for (int l = 0 ; l < letters [3].length () ; l++)
            {
              String answer = letters [0].substring (i, i + 1) + letters [1].substring (j, j + 1) + letters [2].substring (k, k + 1) + letters [3].substring (l, l + 1);
              if (set.contains (answer))
                System.out.append (answer).append ('\n');
            }
          }
        }
      }
    }
  }
  
  /* The main method creates the Bonus1 object and calls all of its methods.
   * 
   * @param args [ ]  String array that allows command line
   * parameters to be used when executing the program.
   */ 
  public static void main (String[] args)
  {
    Bonus1 d = new Bonus1();
    d.read();
  }
}