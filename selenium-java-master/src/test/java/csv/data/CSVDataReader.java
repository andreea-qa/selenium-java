package csv.data;


import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    CSVReader csvReader;
    public void readFile() {
        try {
            // The CSVReader parameters are: the file name, the separator (the comma), the character used for quotes,
            // and the line to start reading from
            csvReader = new CSVReader(new FileReader("Users.csv"), ',', '"', 2);
            String[] userParams;
            //Creates the list of User objects
            List<User> userList = new ArrayList<>();
            while((userParams = csvReader.readNext())!=null)
            {
                //Save the users details in User object
                User user = new User(userParams[0], userParams[1]);
                userList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
