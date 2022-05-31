package com.testauto.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class FileUtil {

    private static final Logger log = LogManager.getLogger(FileUtil.class);

    public static boolean createFileDirectory(String directoryPath){
        boolean result = false;
        File directory = new File(directoryPath);
        if(directory != null) {
            if(!directory.exists())
                result =  directory.mkdirs();
            else
                result =  true;
        }
        return result;
    }

    public static boolean creatFile(String fileWithPath){
        boolean result = false;
        File file = new File(fileWithPath);
        if(createFileDirectory(file.getParent())){
            if(!file.exists()){
                try {
                    result = file.createNewFile();
                }catch(Exception e){
                    log.error("unable to create file", fileWithPath, e.getMessage());
                }
            }else{
                result = true;
            }
        }
        return result;
    }

    public static String getAbsolutePath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }

    public static boolean deleteFilesInDirectory(String directoryPath, List<String> fileNames){
        boolean result = false;
        File directory = new File(directoryPath);
        if(directory.exists()){
            File[] directoryListing = directory.listFiles();
            if(directoryListing != null){
                for(File child : directoryListing){
                    if(!child.isDirectory()){
                        if(fileNames.contains(child.getName())){
                            if(child.delete()){
                                result = true;
                                log.info("File '{}' deleted successfully", child);
                            }else{
                                log.error("File '{}' not deleted successfully", child);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void deleteFileAndDirectories(String directoryPath, List<String> excludeFiles){
        File directory = new File(directoryPath);
        if(directory.exists()){
            File[] directoryListing = directory.listFiles();
            if(directoryListing != null){
                for( File child : directoryListing){
                    if(child.isDirectory()){
                        deleteFileAndDirectories(child.getPath(), excludeFiles);
                        child.delete();
                    }else{
                        if(excludeFiles != null){
                            boolean excludeFileExists =  false;
                            for(String excludeFile : excludeFiles){
                                if(child.getName().contains(excludeFile)){
                                    excludeFileExists = true;
                                    break;
                                }
                            }
                            if(!excludeFileExists){
                                child.delete();
                            }
                        }else{
                            child.delete();
                        }
                    }
                }
            }
        }
    }

}
