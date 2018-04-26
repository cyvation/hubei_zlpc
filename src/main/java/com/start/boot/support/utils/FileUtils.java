package com.start.boot.support.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URLConnection;
import java.util.UUID;

/**
 * <h3>文件工具类</h3>
 *
 * @author 符黄辰君
 * @since 2017年6月27日
 */
public final class FileUtils {

    /**
     * 创建一个新文件
     *
     * @param filepath 文件路径
     * @return 文件
     */
    public static File newFileObject(String filepath) {
        return new File(StringUtils.trim(filepath));
    }

    /***
     * 获取文件编码
     *
     * @param file 文件
     * @return 问文件的编码
     * @throws IOException  IO异常
     */
    public static String getFileCharset(File file) throws IOException {
        String charset = "UTF-8";

        BufferedInputStream bis = null;
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                bis.close();
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
        return charset;
    }

    /**
     * 创建文件
     *
     * @param file 文件
     * @return 是否创建成功
     * @throws IOException
     */
    public static boolean createFile(File file) throws IOException {
        if (file.exists()) {
            delete(file);
        }
        if (!ensureParentPath(file)) {
            throw new IOException("文件" + file.getName() + "创建失败");
        }
        return file.createNewFile();
    }

    /**
     * 删除文件或文件夹
     *
     * @param file 待删除的文件
     * @return 文件删除成功返回true, 否则返回false
     */
    public static boolean delete(File file) {
        boolean b = true;
        if (file.exists()) {
            if (file.isFile())
                b = deleteFile(file);
            else
                b = deleteDirectory(file);
        } else {
        }
        return b;
    }

    /**
     * 删除文件或文件夹
     *
     * @param file 文件路径
     * @return 件删除成功返回true, 否则返回false
     */
    public static boolean delete(String file) {
        return delete(new File(file));
    }

    /**
     * 删除文件
     *
     * @param file 被删除文件
     * @return 单个文件删除成功返回true, 否则返回false
     */
    private static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dirFile 被删除目录的文件
     * @return 目录删除成功返回true, 否则返回false
     */
    private static boolean deleteDirectory(File dirFile) {
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                flag = deleteFile(file);
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(file);
                if (!flag) {
                    break;
                }
            }
        }
        if (flag) {
            flag = dirFile.delete();
        }
        return flag;
    }

    /**
     * 根据输入流，写入文件
     *
     * @param is   输入流
     * @param file 文件名称
     * @throws IOException
     */
    public static void writeFile(InputStream is, File file) throws IOException {

        byte[] date = new byte[8192]; // 输入输出缓存
        int len = -1;
        OutputStream os = null;

        try {
            ensureParentPath(file);
            os = new FileOutputStream(file);
            while ((len = is.read(date)) != -1) {
                os.write(date, 0, len);
            }
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 字符串写入文件
     *
     * @param content     字符串内容
     * @param file        文件
     * @param charsetName 编码
     * @throws IOException
     */
    public static void writeFile(String content, File file, String charsetName) throws IOException {
        OutputStreamWriter osw = null;
        try {
            ensureParentPath(file);
            if (StringUtils.isEmpty(charsetName))
                charsetName = "UTF-8";
            osw = new OutputStreamWriter(new FileOutputStream(file), charsetName);
            osw.write(content);
            osw.flush();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 字符串写入文件
     *
     * @param content 字符串内容
     * @param file    文件
     * @throws IOException
     */
    public static void writeFile(String content, File file) throws IOException {
        writeFile(content, file, "UTF-8");
    }

    /**
     * 读文件
     *
     * @param file        文件
     * @param charsetName 编码
     * @return
     * @throws IOException
     */
    public static String readFile(File file, String charsetName) throws IOException {

        String res = null;
        InputStreamReader is = null;
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            is = new InputStreamReader(new FileInputStream(file), charsetName);
            char[] chars = new char[1024];
            int n = -1;
            while ((n = is.read(chars)) != -1) {
                sb.append(chars, 0, n);
            }
            res = sb.toString();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return res;
    }

    /**
     * 读文件
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        return readFile(file, FileUtils.getFileCharset(file));
    }

    /**
     * 获取文件父路径
     *
     * @param file 文件
     * @return
     */
    public static String getParentPath(File file) {
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            return parentFile.getAbsolutePath();
        }
        return "";
    }

    /**
     * 确保文件父路径
     *
     * @param file 文件
     * @return
     */
    public static boolean ensureParentPath(File file) {
        boolean b = true;
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!b) {
        }
        return b;
    }

    /**
     * 获取文件名字符串的后缀名: aaa.txt -->> .txt
     *
     * @param file 文件
     * @return 后缀名字符串
     */
    public static String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }

    /**
     * 获取文件名字符串的后缀名: aaa.txt -->> .txt
     *
     * @param fileName 文件名
     * @return 后缀名字符串
     */
    public static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index > -1) {
            return fileName.substring(index, fileName.length());
        } else {
            return "";
        }
    }

    /**
     * 获取不包括后缀的文件名: aaa.txt -->> aaa
     *
     * @param file 文件
     * @return 不包括后缀的文件名
     */
    public static String getFilenameWithoutfileExtension(File file) {
        String ext = getFileExtension(file);
        String name = file.getName();
        return name.substring(0, name.length() - ext.length());
    }

    /**
     * 生成随机序列文件名
     *
     * @param fileExtension 后缀名
     * @return 带后缀的文件名
     */
    public static String generateUniqueFileName(String fileExtension) {
        return UUID.randomUUID() + fileExtension;
    }

    /**
     * 获取目录下文件夹列表
     *
     * @param file 文件
     * @return
     */
    public static File[] listSubDirFiles(File file) {
        return file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir + File.separator + name).isDirectory();
            }
        });
    }

    /**
     * 获取目录下指定后缀的文件列表
     *
     * @param file           文件
     * @param fileExtensions 指定的后缀列表
     * @return
     */
    public static File[] listSubFileFiles(File file, final String... fileExtensions) {

        if (fileExtensions != null) {
            return file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    File file2 = new File(dir + File.separator + name);
                    if (file2.isFile()) {
                        for (String fileExtension : fileExtensions) {
                            if (fileExtension.trim().isEmpty()) {
                                return true;
                            }
                            if (fileExtension.equalsIgnoreCase(FileUtils.getFileExtension(file2))) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });
        } else {
            return file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir + File.separator + name).isFile();
                }
            });
        }
    }

    /**
     * 获取合法的文件名,用于检测是否包含指定的文件后缀，如果无后缀则加上
     *
     * @param filename      绝对文件名
     * @param fileExtension 文件后缀
     * @return 文件名
     */
    public static String getValidFilename(String filename, String fileExtension) {
        String _fileExtension = getFileExtension(new File(filename));
        if (!StringUtils.isEmpty(fileExtension)) {
            if (!fileExtension.trim().equalsIgnoreCase(_fileExtension)) {
                return filename + fileExtension.trim();
            }
        }
        return filename;
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException
     */
    public static void copyFile(File source, File target) throws IOException {

        boolean r1 = createFile(target);
        if (!r1) {
            throw new IOException("目标文件创建失败");
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] bs = new byte[8192];
        int len = -1;
        try {
            bis = new BufferedInputStream(new FileInputStream(source));
            bos = new BufferedOutputStream(new FileOutputStream(target));
            while ((len = bis.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 拷贝目录及所有文件
     *
     * @param srcFile 源文件
     * @param tarFile 目标文件
     * @throws IOException
     */
    public static void copyDirectiory(File srcFile, File tarFile) throws IOException {

        if (!tarFile.exists()) {
            tarFile.mkdirs();
        }
        String tarDir = tarFile.getAbsolutePath();

        File[] files = srcFile.listFiles();

        for (int i = 0; i < files.length; i++) {
            File source = files[i];
            if (source.isFile()) {
                copyFile(source, new File(tarDir + File.separator + source.getName()));
            } else {
                copyDirectiory(source, new File(tarDir + File.separator + source.getName()));
            }
        }
    }

    /**
     * 获取文件 字节数组
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(File file) throws IOException {
        if (file == null) {
            return null;
        }
        if (file.isDirectory()) {
            return null;
        }
        ByteArrayOutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[2048];
            int n;
            while ((n = in.read(b)) != -1)
                out.write(b, 0, n);
            in.close();
            out.close();
            return out.toByteArray();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 获取文件的MIME
     *
     * @param file
     * @return
     */
    public static String getMIME(File file) {
        return URLConnection.getFileNameMap().getContentTypeFor(file.getPath());
    }

    /**
     * 将路径解析为绝对路径
     *
     * @param filePath
     * @return
     */
    public static String parseAbsolutePath(String filePath) {
        if (!StringUtils.isEmpty(filePath)) {
            if (filePath.startsWith(":", 1)) {// 全路径
                filePath = new File(filePath).getAbsolutePath();
            } else if (filePath.startsWith("../")) {// 使用../
                File f = new File(System.getProperty("user.dir"));
                int index = 0;
                while ((index = filePath.indexOf("../", index)) != -1) {
                    index = index + "../".length();
                    f = f.getParentFile();
                }
                filePath = new File(f.getAbsolutePath() + "/" + filePath.replaceAll("\\.\\./", "")).getAbsolutePath();
            } else if (filePath.startsWith("${user.dir}")) {
                filePath = new File(filePath.replaceAll("\\$\\{user\\.dir\\}", System.getProperty("user.dir").replaceAll("\\\\", "/"))).getAbsolutePath();
            } else {
                if (filePath.startsWith("/")) {
                    filePath = new File(System.getProperty("user.dir") + filePath).getAbsolutePath();
                } else {
                    filePath = new File(System.getProperty("user.dir") + "/" + filePath).getAbsolutePath();
                }
            }
        }
        return filePath;
    }

    /**
     * 创建文件并写入文件内容
     *
     * @param content  写入的输入流
     * @param path     文件路径
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static String createAndWriteFile(InputStream content, String path, String fileName) throws IOException {
        path = path.trim();
        String fullPath = path + File.separator + fileName;
        File file = new File(fullPath);
        createFile(file);
        writeFile(content, file);
        return fullPath;
    }

    /**
     * 创建文件并写入文件内容
     *
     * @param content  写入的输入流
     * @param fullPath 文件完成路径
     * @return
     * @throws IOException
     */
    public static String createAndWriteFile(InputStream content, String fullPath) throws IOException {
        File file = new File(fullPath);
        createFile(file);
        writeFile(content, file);
        return fullPath;
    }
}
