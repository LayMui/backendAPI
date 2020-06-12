package com.go;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileList implements IList  {
    @Override
    public List<String> getData(File file1, File file2) {
       List list1 = new ArrayList();
       List list2 = new ArrayList();
       List comList = new ArrayList();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1))) {
            for (String line1; (line1 = br1.readLine()) != null; ) {
                list1.add(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
            for (String line2; (line2 = br2.readLine()) != null; ) {
                list2.add(line2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int counter = 0; counter < list1.size(); counter++) {
            if (counter < list2.size()) {
                String s = (String)list1.get(counter).toString()
                        .concat(" ")
                        .concat((String)list2.get(counter).toString());

               comList.add(s);
            }
        }
        return comList;
    }
}


