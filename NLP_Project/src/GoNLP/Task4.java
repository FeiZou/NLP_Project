package GoNLP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.lucene.queryParser.ParseException;

public class Task4 {
    
	static String nounDir = "/Users/feizou/Desktop/NLP_Project/nouns/";
	static String lemDir = "/Users/feizou/Desktop/NLP_Project/lem/";
	static String stemDir = "/Users/feizou/Desktop/NLP_Project/stem/";
	static String posDir = "/Users/feizou/Desktop/NLP_Project/POS/";
	static String headDir = "/Users/feizou/Desktop/NLP_Project/head/";
	static String hyperDir = "/Users/feizou/Desktop/NLP_Project/hypernymns/";
	static String hypoDir = "/Users/feizou/Desktop/NLP_Project/hyponymns/";
	static String meroDir = "/Users/feizou/Desktop/NLP_Project/meronyms/";
	static String holoDir = "/Users/feizou/Desktop/NLP_Project/holonyms/";
	static String outFile = "/Users/feizou/Desktop/NLP_Project/Out.txt";
    Indexer indexer;
    Searcher searcher;
    
    public static void main(String[] args) throws IOException {
        
        File arguFile = new File("/Users/feizou/Desktop/NLP_Project/arguments.txt");
        @SuppressWarnings("resource")
        Scanner argus = new Scanner(arguFile);
        String arguments = argus.nextLine();
        System.out.println("The coefficients are: " + arguments);
        String[] argu = arguments.split(" ");;
        int[] weights = new int[8];
        for (int i = 0; i < 8; i++) {
            weights[i] = Integer.parseInt(argu[i]); 
        }
        
        File file = new File("/Users/feizou/Desktop/NLP_Project/queryTreated.txt");
        @SuppressWarnings("resource")
        Scanner inputFile = new Scanner(file);
        String[] queries = new String[8];
        int i = 0;
        while (inputFile.hasNextLine() && i < 8) {
            queries[i++] = inputFile.nextLine();
        }
        
        Driver driver = new Driver();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        if (driver.testQuery(queries[0]) && weights[0] != 0) {
            try {               
                System.out.println("\nIndexing lemma......");
                driver.createIndex(lemDir);
                ArrayList<String> fileNames = driver.search(queries[0]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for lemma!");
        
        if (driver.testQuery(queries[1]) && weights[1] != 0) {
            try {               
                System.out.println("\nIndexing stem......");
                driver.createIndex(stemDir);
                ArrayList<String> fileNames = driver.search(queries[1]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for stem!");
        
        if (driver.testQuery(queries[2]) && weights[2] != 0) {
            try {               
                System.out.println("\nIndexing POS......");
                driver.createIndex(posDir);
                ArrayList<String> fileNames = driver.search(queries[2]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[2]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for POS!");
        
        if (driver.testQuery(queries[3]) && weights[3] != 0) {
            try {               
                System.out.println("\nIndexing noun......");
                driver.createIndex(nounDir);
                ArrayList<String> fileNames = driver.search(queries[3]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[3]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for noun!");
        
        if (driver.testQuery(queries[4]) && weights[4] != 0) {
            try {               
                System.out.println("\nIndexing hypernymns......");
                driver.createIndex(hyperDir);
                ArrayList<String> fileNames = driver.search(queries[4]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[4]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for hypernymns!");
        
        if (driver.testQuery(queries[5]) && weights[5] != 0) {
            try {               
                System.out.println("\nIndexing hyponymns......");
                driver.createIndex(hypoDir);
                ArrayList<String> fileNames = driver.search(queries[5]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[5]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for hyponymns!");
        
        if (driver.testQuery(queries[6]) && weights[6] != 0) {
            try {               
                System.out.println("\nIndexing meronyms......");
                driver.createIndex(meroDir);
                ArrayList<String> fileNames = driver.search(queries[6]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[6]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for meronymns!");
        
        if (driver.testQuery(queries[7]) && weights[7] != 0) {
            try {               
                System.out.println("\nIndexing holonyms......");
                driver.createIndex(holoDir);
                ArrayList<String> fileNames = driver.search(queries[7]);
                Driver.printContends(fileNames);
                for (String fileName : fileNames) {
                    map.put(fileName, map.getOrDefault(fileName, 0) + weights[7]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }           
        } else System.out.println("\nEMPTY query for holonymns!");  
        
        Map<String, Integer> sortedMap = Driver.sortByValue(map);
        for (Entry<String, Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String str = key + " " + value;
            //System.out.println(str);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, true));
            writer.append(str + '\n');            
            writer.close();
        }
        
        System.out.println("\nAfter combining all features, the resluts are:");
        Driver.printTop10();                
    }
    
}
