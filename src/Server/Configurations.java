package Server;
import java.util.Properties;
import java.io.*;



public class Configurations {

    public static String getProperty (String str) {
        
        /*string file_name = "config.properties";
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream(file_name))// במקום מה שלמטה 
        {
            Properties newProper= new Properties();
            if (input != null) { newProper.load(input);}// if null it means that resource could not be found
            else {return null;}

            return newProper.getProperty(str);
        }
        catch (IOException ex){ ex.printStackTrace();}
        return null;
        */
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties"))//trying to get the properties from config file, 
        {
            
            Properties newProper = new Properties();

            if (input == null) {return null;}// if null it means that resource could not be found

            //load a properties file from class path, inside static method
            newProper.load(input);
            return newProper.getProperty(str);

        }
        catch (IOException ex)
        { ex.printStackTrace();}
        return null;
    }


    public static void setThread(int numOfThreads) //determine the number of threads the client want to use
    {
        try
        {
            Properties newProper= new Properties(); //open new object of Properties

            newProper.load(new FileInputStream("resources/config.properties")); //load to the property new file with string name

            OutputStream tempFile = new FileOutputStream("resources/config.properties"); //open new  OutputStream and write the file

            newProper.setProperty("threadPoolSize",Integer.toString(numOfThreads));
            newProper.store(tempFile,null);

        }
        catch(IOException e){ e.printStackTrace(); }
    }

    public static int getThread() //
    {
        try (InputStream input = new FileInputStream("resources/config.properties"))
        {

            Properties newProper = new Properties();

            newProper.load(input);

            int numOfThreads;
            try
            {
                numOfThreads = (int)Integer.parseInt(newProper.getProperty("threadPoolSize"));
            }
            catch (NumberFormatException e) { numOfThreads = 5;}
            return numOfThreads;

        }
        catch (IOException e) { e.printStackTrace(); }
        return 5;
    }


    public static void setSearchingAlgorithm(String searcher)
    {
        try
        {
            Properties newProper= new Properties();
            newProper.load(new FileInputStream("resources/config.properties"));
            OutputStream temp = new FileOutputStream("resources/config.properties");
            newProper.setProperty("mazeSearchingAlgorithm",searcher);
            newProper.store(temp,null);
        }
        catch(IOException e){e.printStackTrace();}
    }

    public static String getSearchingAlgorithm()
    {
        try (InputStream input = new FileInputStream("resources/config.properties"))
        {
            Properties newProper = new Properties();
            newProper.load(input);
            return newProper.getProperty("mazeSearchingAlgorithm");
        }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public static void setMazeAlgorithm(String generator)
    {
        try
        {
            Properties newProper= new Properties();
            newProper.load(new FileInputStream("resources/config.properties"));
            OutputStream temp = new FileOutputStream("resources/config.properties");
            newProper.setProperty("mazeGeneratingAlgorithm",generator);
            newProper.store(temp,null);

        }
        catch(IOException e){e.printStackTrace();}
    }

    public static String getMazeAlgorithm()
    {
        try (InputStream input = new FileInputStream("resources/config.properties"))
        {
            Properties newProper = new Properties();
            newProper.load(input);
            return newProper.getProperty("mazeGeneratingAlgorithm");
        }
        catch (IOException e) {e.printStackTrace();}
        return null;
    }




}
