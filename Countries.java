/**
 * @author Jerry Zhu
 * @version 1.0, April 1, 2019
 * <p>
 * This program is the 3rd problem in the ArrayList problems.
 * It reads the data in "Countries-Populations.txt" into 4 separate arrays, sorts them, and writes them to respective files. 
 * </p>
 */
import java.io.*;
import javax.swing.JOptionPane;

public class Countries
{
  /** Stores the area after reading it from the file. */
  double[] area;
  
  /** Stores the population after reading it from the file. */
  int[]population;
  
  /** Stores the capital after reading it from the file. */
  String[] capital;
  
  /** Stores the countries after reading it from the file. */
  String[] countries;
  
  /**
   * The read method reads the "Countries-Populations.txt" file and stores them into the four arrays declared as
   * instance variables.
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>line </b> This creates a String object called line that tests for whether each line of the notepad is null before counting it as a valid.
   * <p>
   * <b>numberOfLine </b> This creates a int called numberOfLine that stores the number of lines in the notepad.
   * <p>
   * <b>input </b> This creates a BufferedReader object called input that reads the notepad.
   * <p>
   * <b>r </b> This creates a BufferedReader object called r that reads the file for the second time.
   * <p>
   * <b>linesFile </b> This creates a String array called linesFile that stores all of the notepad lines as a string.
   * <p>
   * <b>countryStr </b> This creates a String array called countryStr that stores the country part of the line.
   * <p>
   * <b>capitalStr </b> This creates a String array called capitalStr that stores the capital part of the line.
   * <p>
   * <b>intArray </b> This creates a int array called intArray that stores the population part of the line.
   * <p>
   * <b>doubleArray </b> This creates a double array called doubleArray that stores the area part of the line.
   * <p>
   * <b>ppltnStr </b> This creates a String array called ppltnStr that stores the population as a String.
   * <p>
   * <b>areaStr </b> This creates a String array called areaStr that stores the area as a String.
   */
  public void read()
  {
    String line = "";
    int numberOfLine = 0;
    BufferedReader input;
    
    try
    {
      input = new BufferedReader (new FileReader ("Countries-Population.txt"));
      
      //loop for as long as there is data in the file
      while (line != null)
      {
        line = input.readLine (); //reads each line in the file
        numberOfLine++;
      }
      input.close (); //closes the stream
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (null, "Sorry, this file cannot be found. Please enter a different file name.");   //error message
    }
    
    //create array with size to match number of lines in file
    String[] linesFile = new String [numberOfLine - 1], countryStr = new String[numberOfLine-1], capitalStr = new String[numberOfLine-1]; 
    int[] intArray = new int[numberOfLine - 1];
    double[] doubleArray = new double[numberOfLine - 1];
    
    try
    {
      //open the same file again
      BufferedReader r = new BufferedReader (new FileReader ("Countries-Population.txt")); // reset the buffer
      int x = 0;
      
      while (x < numberOfLine - 1) //loop until end of file is reached
      {
        linesFile [x] = r.readLine (); //feed each data line into an array
        x++;
      }
      
      String []ppltnStr = new String[numberOfLine-1];
      String [] areaStr = new String[numberOfLine-1];
      
      //works backwards and extracts the data part by part
      for(int i = 0; i<linesFile.length; i++)
      {
        if(linesFile[i].charAt(linesFile[i].length()- 1) != ' ') //no space exceptions - some lines don't have spaces at the end
        {
          //finds the the last space to the last character, which is the population
          ppltnStr[i] = linesFile[i].substring(linesFile[i].lastIndexOf(' '), linesFile[i].length());
          ppltnStr[i] = ppltnStr[i].replaceAll("[, ]", "");  //remove the commas and spaces
          
          linesFile[i] = linesFile[i].substring(0, linesFile[i].lastIndexOf(' ')); //removes the population from the line
          
          //finds the the last space to the last character, which is the area
          areaStr[i] = linesFile[i].substring(linesFile[i].lastIndexOf(' '), linesFile[i].length());
          areaStr[i] = areaStr[i].replaceAll("[, ]", "");
          
          linesFile[i] = linesFile[i].substring(0, linesFile[i].lastIndexOf(' ')); //removes the area
        }
        else //has space
        {
          //finds the the second last space to the last space, which is the population
          ppltnStr[i] = linesFile[i].substring(linesFile[i].lastIndexOf(' ', linesFile[i].lastIndexOf(' ') - 1), linesFile[i].length()); 
          ppltnStr[i] = ppltnStr[i].replaceAll("[, ]", ""); //remove the commas and spaces
          
          linesFile[i] = linesFile[i].substring(0, linesFile[i].lastIndexOf(' ', linesFile[i].lastIndexOf(' ') - 1) + 1); //removes the population from the line
          
          //finds the the second last space to the last space for the new line, which is the area
          areaStr[i] = linesFile[i].substring(linesFile[i].lastIndexOf(' ', linesFile[i].lastIndexOf(' ') - 1), linesFile[i].length());
          areaStr[i] = areaStr[i].replaceAll("[, ]", "");
          
          linesFile[i] = linesFile[i].substring(0, linesFile[i].lastIndexOf(' ', linesFile[i].lastIndexOf(' ') - 1) + 1); //removes the area
        }
      }
      
      //parses the string array into an integer array
      for (int i = 0 ; i < numberOfLine - 1 ; i++)
      {
        intArray [i] = Integer.parseInt (ppltnStr [i]);
        doubleArray [i] = Double.parseDouble (areaStr [i]);
      }
      
      //hardcodes the exceptions of multi-word capitals/countries
      for(int i = 0; i<linesFile.length;i++)
      {
        String[] lol= linesFile[i].split(" "); //stores the parts of the line in lol
        if(lol.length == 2) //has to be one or the other
        {
          if(lol[0].substring(0, 3).equals("Vat")|| lol[0].substring(0, 3).equals("Wes")) //exceptions
          {
            countryStr[i] = lol[0] + " " + lol[1];
            capitalStr[i] = null;
          }
          else
          {
            countryStr[i] = lol[0];
            capitalStr[i] = lol[1];
          }
        }
        else if(lol.length == 3) //3 words
        {
          if(lol[0].substring(0, 2).equals("Uk") || lol[0].substring(0, 2).equals("Be")|| lol[0].substring(0, 2).equals("In") || lol[0].substring(0, 2).equals("Et")
               || lol[0].substring(0, 2).equals("Ar")|| lol[0].substring(0, 3).equals("Cam")|| lol[0].substring(0, 2).equals("Bo")
               || lol[0].charAt(0)=='G'|| lol[0].substring(0, 2).equals("Ku")|| lol[0].substring(0, 2).equals("Mo")|| lol[0].substring(0, 2).equals("Pa")
               || lol[0].substring(0, lol[0].length()).equals("Malaysia")|| lol[0].substring(0, lol[0].length()).equals("Malaysia")
               || lol[0].substring(0, lol[0].length()).equals("Mexico")|| lol[0].substring(0, lol[0].length()).equals("Mauritius")) //1 word country
          {
            countryStr[i] = lol[0];
            capitalStr[i] = lol[1] + " " +  lol[2];
          }
          else //2 word country
          {
            countryStr[i] = lol[0] + " " + lol[1];
            capitalStr[i] = lol[2];
          }
        }
        else if(lol.length == 4) //4 words
        {
          if(lol[0].substring(0, 2).equals("An") || lol[0].substring(0, 2).equals("Br")) //1 word country
          {
            countryStr[i] = lol[0];
            capitalStr[i] =  lol[1] + " " + lol[2] + " " + lol[3];
          }
          else if(lol[0].substring(0, 2).equals("Ce") || lol[0].substring(0, lol[0].length()).equals("Congo,") || lol[0].substring(0, 2).equals("Tr")
                    || lol[0].substring(0, 2).equals("Bo")) //3 word country
          {
            countryStr[i] = lol[0] + " " + lol[1] + " " + lol[2];
            capitalStr[i] = lol[3];
          }
          else //2 word country
          {
            countryStr[i] = lol[0] + " " + lol[1];
            capitalStr[i] = lol[2] + " " + lol[3];
          }
        }
        else if(lol.length == 5) //5 words
        {
          if(lol[0].substring(0, 2).equals("St")) //4 word country
          {
            countryStr[i] = lol[0] + " " + lol[1] + " " + lol[2]+ " " + lol[3];
            capitalStr[i] = lol[4];
          }
          else //3 word country
          {
            countryStr[i] = lol[0] + " " + lol[1] + " " + lol[2];
            capitalStr[i] = lol[3]+ " " + lol[4];
          }
        }
        else //6 word country
        {
          if(lol[0].substring(0, 2).equals("Sã")) //4 word country
          {
            countryStr[i] = lol[0] + " " + lol[1] + " " + lol[2]+ " " + lol[3];
            capitalStr[i] = lol[4]+ " " +lol[5];
          }
          else //5 word country
          {
            countryStr[i] = lol[0] + " " + lol[1] + " " + lol[2]+ " " + lol[3]+ " " +lol[4];
            capitalStr[i] = lol[5];
          }
        }
      }
      r.close (); //close data file
    }
    catch (IOException e)  //handle file related errors
    {
      System.out.println (e); //error msg
    }
    catch (NumberFormatException e)  //error trap
    {
      JOptionPane.showMessageDialog (null, "The file is corrupt because it contains non-integer data. Please try again!"); //error message
    }
    population = intArray; //sets the local array equal to the global array so that other methods can process it
    area = doubleArray;
    countries = countryStr;
    capital = capitalStr;
  }
  
  /**
   * The sortAlphabetically method sorts the countries array in increasing alphabetical order and changes the position of the associated
   * population along with it. It then writes the two arrays to a file called "sortedByCountry.txt".
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>output </b> This creates a PrintWriter object called output that writes the data to the file.
   * <p>
   * <b>i </b> This creates an int called i that stores the position of elements in the array.
   * <p>
   * <b>x </b> This creates an int called x that iterates through the array as the loop counter.
   * <p>
   * <b>t1 </b> This creates a String object called t1 that stores, temporarily, the String replaced.
   * <p>
   * <b>t0 </b> This creates an int called t0 that stores, temporarily, the int replaced.
   */
  public void sortAlphabetically()
  {
    PrintWriter output;
    int i, x, t1;
    String t0;
    
    //insertion sort
    for (x = 1; x < countries.length; x++) //iterates through
    {
      t0 = countries[x]; //stores the temps
      t1 = population[x];
      i = x - 1; //sets i to the previous element
      while (i >= 0) {
        if (t0.compareTo(countries[i]) > 0) //if previous is already sorted(greater means it is higher on the alphabet(e.g. t0 could be 'c', countries[i] 'e'))
          break;
        countries[i + 1] = countries[i]; //if not, swap the positions
        population[i + 1] = population[i];
        i--;
      }
      countries[i + 1] = t0; //sets it back to temp
      population[i + 1] = t1;
    }
    try
    {
      output = new PrintWriter (new FileWriter ("sortedByCountry.txt")); //saves
      
      //prints to the file
      for (int a = 0 ; a < countries.length ; a++)
        output.printf("%-1s \t\t\t\t %,d %n", countries[a], population[a]);
      output.close (); //closes stream
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (null, "Something went wrong with the input or output operations!");   //error message
    }
  }
  
  /**
   * The sortByPopulation method sorts the population array in decreasing order and changes the position of the associated
   * country along with it. It then writes the two arrays to a file called "sortedByPopulation.txt".
   * <p>
   * <b>Local variables: </b>
   * <p>
   * <b>output </b> This creates a PrintWriter object called output that writes the data to the file.
   * <p>
   * <b>max </b> This creates an int called max that stores the biggest int in the array that has not been sorted.
   * <p>
   * <b>t0 </b> This creates an int called t0 that stores, temporarily, the int replaced.
   * <p>
   * <b>t1 </b> This creates a String object called t1 that stores, temporarily, the String replaced.
   */
  public void sortByPopulation()
  {
    PrintWriter output;
    int max, t0;
    String t1;
    
    //Selection sort
    for(int i = 0; i<population.length;i++) //loops through
    {
      max = i; //Assume first element is max
      for(int x = i + 1;x<population.length;x++) //loops through the rest
      {
        if(population[x] >population[max])  //sets new max if a subsequent element is bigger
        { 
          max = x;
        }
      }
      //change the positions
      t0 = population[i];
      population[i] = population[max];
      population[max] = t0;
      t1 = countries[i];
      countries[i] = countries[max];
      countries[max] = t1;
    }
    
    try
    {
      output = new PrintWriter (new FileWriter ("sortedByPopulation.txt")); //saves
      
      //prints to the file
      for (int x = 0 ; x <population.length ; x++)
        output.printf("%-1s \t\t\t\t %,d %n", countries[x], population[x]);
      output.close (); //closes stream
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (null, "Something went wrong with the input or output operations!");   //error message
    }
  }
  
  /* The main method creates the Countries object and calls all of its methods.
   * 
   * @param args [ ]  String array that allows command line
   * parameters to be used when executing the program.
   */ 
  public static void main(String[]args)
  {
    Countries d = new Countries();
    d.read();
    d.sortAlphabetically();
    d.sortByPopulation();
  }
}
