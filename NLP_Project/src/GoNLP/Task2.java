package GoNLP;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.queryParser.ParseException;

public class Task2 {
    
    static String indexDir = "/Users/feizou/Desktop/NLP_Project/Index/";
    static String corpusDir = "/Users/feizou/Desktop/NLP_Project/sentences/";
    static String query;
    
    public static void main(String[] args) throws IOException {
        
        File file = new File("/Users/feizou/Desktop/NLP_Project/queryOriginal.txt");
        @SuppressWarnings("resource")
        Scanner inputFile = new Scanner(file);
        query = inputFile.nextLine();
        
        Driver driver = new Driver();
        
        if (driver.testQuery(query)) {
            try {               
                System.out.println("Indexing corpus......");
                System.out.println("Query is: " + query);
                driver.createIndex(corpusDir);
                Driver.printContends(driver.search(query));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("EMPTY query!");
                    
    }
    
}
