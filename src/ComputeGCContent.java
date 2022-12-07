import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ComputeGCContent {
    private String id;
    private String dna;
    private double gcContent;

    /**
     * @param id the id of the DNA sequence
     * @param dna the DNA sequence
     */
    public ComputeGCContent(String id, String dna) {
        this.id = id;
        this.dna = dna;
        this.gcContent = findGCContent(dna); //Rosalind_4113\n 53.412784398699884
    }

    public static void main(String[] args) {
        String filename = "src/Rosalind-gc.txt";
        ArrayList<ComputeGCContent> dnaList = parseFASTA(filename);
        findHighestGC(dnaList);
    }

    /**
     * Find the highest GC content in a ArrayList of ComputeGCContent objects and then return the ID and GC content on separate lines
     *
     * @param list the list of DNA sequences
     */
    public static void findHighestGC(ArrayList<ComputeGCContent> list) {
        ComputeGCContent highest = list.get(0);
        for (ComputeGCContent c : list) {
            if (c.getGcContent() > highest.getGcContent()) {
                highest = c;
            }
        }
        System.out.println(highest.getId());
        System.out.println(highest.getGcContent());
    }

    /**
     * @param dna the DNA sequence
     * @return the GC content of the DNA sequence
     */
    public static double findGCContent(String dna) {
        // Calculate GC percentage
        int gCount = 0;
        int cCount = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'G') {
                gCount++;
            } else if (dna.charAt(i) == 'C') {
                cCount++;
            }
        }
        return (double) (gCount + cCount) / dna.length() * 100;
    }

    /**
     * Parse the FASTA file into an ArrayList of ComputeGCContent objects
     *
     * @param filename the name of the file to parse
     * @return an ArrayList of ComputeGCContent objects
     */
    public static ArrayList<ComputeGCContent> parseFASTA(String filename) {
        ArrayList<ComputeGCContent> list = new ArrayList<ComputeGCContent>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            String id = "";
            StringBuilder dna = new StringBuilder();
            while (line != null) {
                if (line.startsWith(">")) {
                    if (id.length() > 0) {
                        list.add(new ComputeGCContent(id, dna.toString()));
                    }
                    id = line.substring(1);
                    dna = new StringBuilder();
                } else {
                    dna.append(line);
                }
                line = br.readLine();
            }
            list.add(new ComputeGCContent(id, dna.toString()));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the ArrayList of ComputeGCContent objects
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }


    @Override
    public String toString() {
        return "ComputeGCContent{" +
                "id='" + id + '\'' +
                ", dna='" + dna + '\'' +
                ", gcContent=" + gcContent +
                '}';
    }


    public double getGcContent() {
        return gcContent;
    }

    public void setGcContent(double gcContent) {
        this.gcContent = gcContent;
    }
}