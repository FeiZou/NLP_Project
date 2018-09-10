package GoNLP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


public class Driver {
    
	static String indexDir = "/Users/feizou/Desktop/NLP_Project/Index";
	static String corpusDir = "/Users/feizou/Desktop/NLP_Project/sentences/";
	static String lemDir = "/Users/feizou/Desktop/NLP_Project/lem/";
	static String stemDir = "/Users/feizou/Desktop/NLP_Project/stem/";
	static String posDir = "/Users/feizou/Desktop/NLP_Project/POS/";
    static String headDir = "";
    static String query;
    ArrayList<String> fileNames;
    Indexer indexer;
    Searcher searcher;

    public void createIndex(String dataDir) throws IOException {
        
        indexer = new Indexer(indexDir);
        int numIndexed;
        
        long startTime = System.currentTimeMillis();  
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed + " File indexed.");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

    public ArrayList<String> search(String searchQuery) 
            throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();
   
        System.out.println(hits.totalHits + " documents found.");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        fileNames = new ArrayList<>();
        int i = 1;
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File " + i++ + ": " + doc.get("filename"));
            fileNames.add(doc.get("filename"));
    }
        searcher.close();
        
        return fileNames;
    }
    
    public boolean testQuery(String str) {
        
        String[] arr = str.trim().split(" ");
        //System.out.println(arr[0].length());
        return arr[0].length() > 0;     
    }
    
    public static void printContends(ArrayList<String> fn) 
            throws FileNotFoundException {
        
        int i = 1;
        System.out.println();
        for (String fname : fn) {
            File file = new File("/Users/feizou/Desktop/NLP_Project/sentences/" + fname);
            @SuppressWarnings("resource")
            Scanner inputFile = new Scanner(file);          
            System.out.println(i++ + ": " + inputFile.nextLine());          
        }   
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet().stream()
                  .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
                    (e1, e2) -> e1, LinkedHashMap::new ));
    }
    
    public static void printTop10() throws FileNotFoundException {
        File file = new File("/Users/feizou/Desktop/NLP_Project/Out.txt");
        @SuppressWarnings("resource")
        Scanner inputFile = new Scanner(file);
        int i = 0;
        ArrayList<String> top10 = new ArrayList<>();
        System.out.println();
        while (inputFile.hasNextLine() && i++ < 10) {
            String str = inputFile.nextLine().split(" ")[0];
            top10.add(str);
            System.out.println("File: " + str);
        }
        Driver.printContends(top10);
    }
    
}

