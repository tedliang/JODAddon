package net.jodreport.ooAddon.report;

import java.io.File;

/**
 *
 * @author tedliang
 */
public class Setting {

    private String dataFilePath;

    public Setting(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public boolean isDataFileExist(){
        if(dataFilePath!=null && !dataFilePath.equals("")){
            File file = new File(dataFilePath);
            return file.exists();
        }
        return false;
    }
}
