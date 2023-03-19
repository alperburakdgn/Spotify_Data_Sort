import java.io.*;
import java.util.*;

public class SortData {
    public static void main(String[] args)
    {
        String filePt = "YOU NEED WRITE YOUR .JSON FILES FILE PATH";
        addData(filePt);
    }


    private static void addData(String inputName){
        Scanner inputStream =  null;
        try{
            inputStream = new Scanner(new FileInputStream(inputName));

        } catch(FileNotFoundException e){
            System.out.println("error!");
            System.exit(0);
        }

        HashMap<String,Long> data = new HashMap<>();

        String line = null;
        String songName= null;
        Long playedTime = 0L;


        while (inputStream.hasNextLine()){

            line = inputStream.nextLine();
            if (line.contains("trackName")){
                 songName = line.substring(17,line.length()-1);
            }else{
                songName = "pas gec";
            }
            line = inputStream.nextLine();
            if (line.contains("msPlayed")){
                playedTime = Long.parseLong(line.substring(17));
            }else{
                playedTime = 0L;
            }
            if (!data.containsKey(songName) )
                data.put(songName,playedTime);
           else if(data.containsKey(songName))
               data.put(songName, data.get(songName) + playedTime);


        }
   
       writeCumulativeData(sortByValue(data));

    }

    private static void writeCumulativeData(HashMap<String, Long> data){
        try {
            PrintWriter outputStream = outputStream = new PrintWriter(new FileOutputStream(("YOUR CUMULATIVE DATA WILL BE WRITE HERE.<file_format>")));
            for (Map.Entry<String, Long> entry : data.entrySet() ) {
                if (entry.getKey().equals("pas gec"));
                else{
                    outputStream.println(entry.getKey()+":" + " " + entry.getValue()*0.001*0.0166666667);
                }
            }
            outputStream.close();
       }catch (IOException ignored){}
    }


    private static HashMap<String, Long> sortByValue(HashMap<String, Long> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Long> > list =
                new LinkedList<Map.Entry<String, Long> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Long> >() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


}
