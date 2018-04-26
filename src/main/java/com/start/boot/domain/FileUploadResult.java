package com.start.boot.domain;

/**
 * Created by user on 2017/11/12.
 */
public class FileUploadResult {
    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String filePathName;//文件路径名
    private String fileName;//文件名
}
