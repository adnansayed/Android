import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;


public class javaapp {
	 public static int collectionN = 0;
	    public static ArrayList<String> tokens = new ArrayList<>(); //will store the list of tokens
	    public static ArrayList<Integer> frequency = new ArrayList<>();
	    public static ArrayList<ArrayList<String>> matrix = new ArrayList<>(); //will store the matrix 
	    public static ArrayList<ArrayList<Integer>> freqMatrix = new ArrayList<>(); //Occurence matrix 
	    public static ArrayList<String> userQueryTokens = new ArrayList<>();
	    public static ArrayList<Integer> wtq = new ArrayList<>();

	    public static void main(String[] args) throws IOException {
	        processCorpus();
	        displayIndexMatrix();
	        inputQuery();
	        int[] documentFrequency = getDf();
	        double[] inverserDocumentFrequency = geIDF(documentFrequency);
	        double[][] termFreqMatrix = initialisetermFreqMatrix();
	        displayTermFrequencyMatrix(termFreqMatrix);
	        double[][] wtd = tfIdfWeight(termFreqMatrix, inverserDocumentFrequency);
	        displayTfIdfMatrix(wtd);
	        double[] docScore = docScore(wtd);
	        int[] sortIndices = sortBycosine(docScore);
	        displaySortedFileCosine(docScore, sortIndices);
	    }

	    public static void processCorpus() throws IOException {
	        File folder = new File("Mithila\\");
	        File[] listOfFiles = folder.listFiles();
	        collectionN = listOfFiles.length;
	        for (int i = 0; i < collectionN; i++) {
	            FileReader file = new FileReader(listOfFiles[i]);
	            StreamTokenizer st = new StreamTokenizer(file);
	            st.lowerCaseMode(true);
	            st.whitespaceChars('.', '.');
	            boolean eof = false;
	            do {
	                int token = st.nextToken();
	                if (token == StreamTokenizer.TT_WORD) {

	                    {
	                        if (tokens.indexOf(st.sval) == -1) //This token is not in the token list
	                        {
	                            tokens.add(st.sval); //Add token to the tokens list

	                            ArrayList<Integer> tempfrequency = new ArrayList<>();
	                            tempfrequency.add(1);
	                            freqMatrix.add(tempfrequency);

	                            ArrayList<String> tempMatrix = new ArrayList<>();
	                            tempMatrix.add(listOfFiles[i].getName());
	                            matrix.add(tempMatrix);

	                            frequency.add(1);
	                        } else {
	                            // Token already exists in the token list so dont add again. just update file name /count
	                            int tokenIndex = tokens.indexOf(st.sval);
	                            ArrayList<Integer> tempfrequency = freqMatrix.get(tokenIndex);
	                            ArrayList<String> tempMatrix = matrix.get(tokenIndex);

	                            if (tempMatrix.indexOf(listOfFiles[i].getName()) == -1) {
	                                //filename does not exist
	                                tempMatrix.add(listOfFiles[i].getName());
	                                tempfrequency.add(1);

	                            } else {
	                                int fileIndex = tempMatrix.indexOf(listOfFiles[i].getName());
	                                int newFrequency = (tempfrequency.get(fileIndex) + 1);
	                                tempfrequency.set(fileIndex, newFrequency);

	                            }
	                            //done with file now increment token no. with respect to entire collection
	                            freqMatrix.set(tokenIndex, tempfrequency);
	                            matrix.set(tokenIndex, tempMatrix);
	                            int newFrequency = (frequency.get(tokenIndex) + 1);
	                            frequency.set(tokenIndex, newFrequency);
	                        }

	                    }
	                } else if (token == StreamTokenizer.TT_EOF) {
	                    eof = true;
	                }
	            } while (!eof);
	        }

	    }

	    public static void displayIndexMatrix() {
	        System.out.printf(" Corpus Tokens                \n");
	        for (int i = 0; i < tokens.size(); i++) {
	            ArrayList<Integer> tempfrequency = freqMatrix.get(i);
	            ArrayList<String> tempMatrix = matrix.get(i);

	            System.out.printf(" %-20s ", tokens.get(i) + "(" + frequency.get(i) + ")");
	            for (int j = 0; j < tempMatrix.size(); j++) {
	                System.out.printf(" %-15s", tempMatrix.get(j) + "(" + tempfrequency.get(j) + ")");
	            }
	            System.out.println();
	        }

	    }

	    public static void inputQuery() throws IOException {
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("\nEnter requirement for search : ");
	        String input = bufferRead.readLine();
	        StringTokenizer st = new StringTokenizer(input);
	        while (st.hasMoreTokens()) {
	            String curToken = st.nextToken();
	            if (userQueryTokens.indexOf(curToken) == -1) {
	                userQueryTokens.add(curToken);
	                wtq.add(1);
	            } else if (userQueryTokens.indexOf(curToken) > -1) {
	                int tempIndex = userQueryTokens.indexOf(curToken);
	                int newWtq = wtq.get(tempIndex) + 1;
	                wtq.set(tempIndex, newWtq);
	            }
	        }
	    }

	    public static int[] getDf() {
	        int[] df = new int[userQueryTokens.size()];
	        for (int i = 0; i < userQueryTokens.size(); i++) {
	            int tempIndex = tokens.indexOf(userQueryTokens.get(i));
	            if (tempIndex == -1) {
	                df[i] = 0;
	                continue;
	            }
	            ArrayList<String> tempMatrix = matrix.get(tempIndex);
	            df[i] = tempMatrix.size();
	        }
	        return df;
	    }

	    public static double[] geIDF(int[] df) {
	        double[] idf = new double[df.length];
	        for (int i = 0; i < df.length; i++) {
	            idf[i] = (df[i] == 0) ? 0 : Math.log(collectionN / df[i]);
	        }
	        return idf;
	    }

	    public static double[][] initialisetermFreqMatrix() {
	        File folder = new File("Mithila\\");
	        File[] listOfFiles = folder.listFiles();

	        double[][] tf = new double[collectionN][userQueryTokens.size()];

	        for (int j = 0; j < userQueryTokens.size(); j++) {
	            if (tokens.indexOf(userQueryTokens.get(j)) > -1) {
	                int tokenIndex = tokens.indexOf(userQueryTokens.get(j));
	                ArrayList<String> tempFileNamesList = matrix.get(tokenIndex);
	                ArrayList<Integer> tempTermfreq = freqMatrix.get(tokenIndex);
	                for (int i = 0; i < collectionN; i++) {
	                    if (tempFileNamesList.indexOf(listOfFiles[i].getName()) > -1) {
	                        int tempFileIndex = tempFileNamesList.indexOf(listOfFiles[i].getName());
	                        tf[i][j] = 1 + (tempTermfreq.get(tempFileIndex));
	                    } else {
	                        tf[i][j] = 0;
	                    }
	                }
	            } else {
	                for (int i = 0; i < listOfFiles.length; i++) {
	                    tf[i][j] = 0;
	                }
	            }

	        }
	        return tf;
	    }

	    public static void displayTermFrequencyMatrix(double[][] tf) {
	        System.out.println("\n Term Frequency Matrix:");

	        System.out.printf(" File       ");
	        for (int j = 0; j < userQueryTokens.size(); j++) {
	            System.out.printf(" %-10s ", userQueryTokens.get(j));
	        }
	        System.out.println(" ");

	        System.out.println(" ");
	        File folder = new File("Mithila\\");
	        File[] listOfFiles = folder.listFiles();

	        for (int i = 0; i < collectionN; i++) {
	            System.out.printf(" %-20s ", listOfFiles[i].getName());
	            for (int j = 0; j < userQueryTokens.size(); j++) {
	                System.out.printf(" %-20f ", tf[i][j]);
	            }
	            System.out.println(" ");
	        }

	        System.out.println(" ");

	    }

	    public static double[][] tfIdfWeight(double[][] tf, double[] idf) {
	        double[][] tfidf = new double[collectionN][userQueryTokens.size()];
	        for (int i = 0; i < collectionN; i++) {
	            for (int j = 0; j < userQueryTokens.size(); j++) {
	                tfidf[i][j] = tf[i][j] * idf[j];
	            }
	        }
	        return tfidf;
	    }

	    public static void displayTfIdfMatrix(double[][] tf) {
	        System.out.println("\nTFIDF Matrix:");
	        System.out.printf(" File          ");
	        for (int j = 0; j < userQueryTokens.size(); j++) {
	            System.out.printf(" %-20s ", userQueryTokens.get(j));
	        }
	        System.out.println(" ");

	        File folder = new File("Mithila\\");
	        File[] listOfFiles = folder.listFiles();

	        for (int i = 0; i < collectionN; i++) {
	            System.out.printf(" %-10s ", listOfFiles[i].getName());
	            for (int j = 0; j < userQueryTokens.size(); j++) {
	                System.out.printf(" %-20f ", tf[i][j]);
	            }
	            System.out.println(" ");
	        }

	    }

	    public static double[] docScore(double[][] wtd) {
	        double[] score = new double[collectionN];
	        for (int i = 0; i < collectionN; i++) {
	            double tempScore = 0;
	            for (int j = 0; j < userQueryTokens.size(); j++) {
	                tempScore += wtd[i][j] * wtq.get(j);
	            }
	            score[i] = tempScore / collectionN;
	        }
	        return score;
	    }

	    public static int[] sortBycosine(double[] docScore) {
	        int[] sortIndex = new int[docScore.length];
	        for (int i = 0; i < docScore.length; i++) {
	            sortIndex[i] = i;
	        }
	        for (int i = 0; i < docScore.length; i++) {
	            for (int j = 0; j < docScore.length; j++) {
	                if (docScore[sortIndex[i]] >= docScore[sortIndex[j]]) {
	                    int temp = sortIndex[i];
	                    sortIndex[i] = sortIndex[j];
	                    sortIndex[j] = temp;
	                }
	            }

	        }
	        return sortIndex;
	    }

	    public static void displaySortedFileCosine(double[] docScore, int[] sortIndices) {
	        System.out.println("\nCosine And Ranks:");
	        System.out.println("\n");
	        System.out.printf(" File           Cosine              Rank          \n");
	        File folder = new File("Mithila\\");
	        File[] listOfFiles = folder.listFiles();
	        for (int i = 0; i < collectionN; i++) {
	            System.out.printf(" %-13s  %-18f  %-13d \n", listOfFiles[sortIndices[i]].getName(), docScore[sortIndices[i]], i + 1);
	        }

	    }

}
