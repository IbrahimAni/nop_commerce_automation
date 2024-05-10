package constants;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class StringConstant {
    Properties prop;
    String CONFIG_FILE_PATH = System.getProperty("user.dir") + File.separator + "config/config.properties";

    public StringConstant(){
        File file = new File(CONFIG_FILE_PATH);
        try{
            FileInputStream fis = new FileInputStream(file);
            prop = new Properties();
            prop.load(fis);
        }catch(Exception e){
            System.out.println("Exception is: " + e.getMessage());
        }
    }

    public String getAppUrl() {
        return prop.getProperty("application_url");
    }

    public String log4jFilePath() {
        return System.getProperty("user.dir") + File.separator + "config/log4j.properties";
    }

    public String getExeclFilePath(){
        try{
            File file = new File(CONFIG_FILE_PATH);
            FileInputStream fileInput = new FileInputStream(file);
            prop = new Properties();
            prop.load(fileInput);
            String path = prop.getProperty("excel_file_path");
            return System.getProperty("user.dir") + File.separator + path;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
