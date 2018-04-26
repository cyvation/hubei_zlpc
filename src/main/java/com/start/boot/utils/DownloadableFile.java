package com.start.boot.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * 可下载的文件,建议通过 try resources 来使用
 * <p>
 * try(DownloadableFile file = new DownloadableFile(response,path);){}
 *
 * @author 符黄辰君
 * @see Closeable
 * @see File
 * @see URL
 * @see HttpServletResponse
 * @since 2017/8/15.
 */
public class DownloadableFile extends File implements Closeable {

	private static final long serialVersionUID = 3270000200276517997L;

	private static final String ENCODE = "UTF-8";

    private HttpServletResponse response;//响应对象

    private InputStream inputStream;

    /**
     * 创建一个可下载的文件
     *
     * @param response 响应对象
     * @param pathname 文件路径
     *
     * @throws IOException I/O error
     */
    public DownloadableFile(HttpServletResponse response, String pathname) throws IOException {
        super(pathname);
        init(response, ENCODE);
    }

    /**
     * Creates a new <code>File</code> instance by converting the given
     * pathname string into an abstract pathname.  If the given string is
     * the empty string, then the result is the empty abstract pathname.
     *
     * @param pathname A pathname string
     *
     * @throws NullPointerException If the <code>pathname</code> argument is <code>null</code>
     */
    public DownloadableFile(HttpServletResponse response, String encode, String pathname) throws IOException {
        super(pathname);
        init(response, encode);
    }

    /**
     * Creates a new <code>File</code> instance from a parent pathname string
     * and a child pathname string.
     * <p>
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     * <p>
     * <p> Otherwise the <code>parent</code> pathname string is taken to denote
     * a directory, and the <code>child</code> pathname string is taken to
     * denote either a directory or a file.  If the <code>child</code> pathname
     * string is absolute then it is converted into a relative pathname in a
     * system-dependent way.  If <code>parent</code> is the empty string then
     * the new <code>File</code> instance is created by converting
     * <code>child</code> into an abstract pathname and resolving the result
     * against a system-dependent default directory.  Otherwise each pathname
     * string is converted into an abstract pathname and the child abstract
     * pathname is resolved against the parent.
     *
     * @param parent The parent pathname string
     * @param child  The child pathname string
     *
     * @throws NullPointerException If <code>child</code> is <code>null</code>
     */
    public DownloadableFile(HttpServletResponse response, String encode, String parent, String child) throws
                                                                                                      IOException {
        super(parent, child);
        init(response, encode);
    }

    /**
     * Creates a new <code>File</code> instance from a parent abstract
     * pathname and a child pathname string.
     * <p>
     * <p> If <code>parent</code> is <code>null</code> then the new
     * <code>File</code> instance is created as if by invoking the
     * single-argument <code>File</code> constructor on the given
     * <code>child</code> pathname string.
     * <p>
     * <p> Otherwise the <code>parent</code> abstract pathname is taken to
     * denote a directory, and the <code>child</code> pathname string is taken
     * to denote either a directory or a file.  If the <code>child</code>
     * pathname string is absolute then it is converted into a relative
     * pathname in a system-dependent way.  If <code>parent</code> is the empty
     * abstract pathname then the new <code>File</code> instance is created by
     * converting <code>child</code> into an abstract pathname and resolving
     * the result against a system-dependent default directory.  Otherwise each
     * pathname string is converted into an abstract pathname and the child
     * abstract pathname is resolved against the parent.
     *
     * @param parent The parent abstract pathname
     * @param child  The child pathname string
     *
     * @throws NullPointerException If <code>child</code> is <code>null</code>
     */
    public DownloadableFile(HttpServletResponse response, String encode, File parent, String child) throws IOException {
        super(parent, child);
        init(response, encode);
    }

    /**
     * 创建一个可下载的文件
     *
     * @param response 响应对象
     * @param uri      uri
     *
     * @throws IOException I/O error
     */
    public DownloadableFile(HttpServletResponse response, URI uri) throws IOException {
        super(uri);
        this.inputStream = extractInputStreamForURI(uri);
        this.initResponse(response, ENCODE);
    }

    /**
     * Creates a new <tt>File</tt> instance by converting the given
     * <tt>file:</tt> URI into an abstract pathname.
     * <p>
     * <p> The exact form of a <tt>file:</tt> URI is system-dependent, hence
     * the transformation performed by this constructor is also
     * system-dependent.
     * <p>
     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
     * <p>
     * <blockquote><tt>
     * new File(</tt><i>&nbsp;f</i><tt>.{@link #toURI() toURI}()).equals(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </tt></blockquote>
     * <p>
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  This relationship typically does not hold,
     * however, when a <tt>file:</tt> URI that is created in a virtual machine
     * on one operating system is converted into an abstract pathname in a
     * virtual machine on a different operating system.
     *
     * @param uri An absolute, hierarchical URI with a scheme equal to
     *            <tt>"file"</tt>, a non-empty path component, and undefined
     *            authority, query, and fragment components
     *
     * @throws NullPointerException     If <tt>uri</tt> is <tt>null</tt>
     * @throws IllegalArgumentException If the preconditions on the parameter do not hold
     * @see #toURI()
     * @see URI
     * @since 1.4
     */
    public DownloadableFile(HttpServletResponse response, String encode, URI uri) throws IOException {
        super(uri);
        this.inputStream = extractInputStreamForURI(uri);
        this.initResponse(response, encode);
    }

    /**
     * 设置下载名称
     *
     * @param fileName 一般为文件名
     */
    public void setDownloadName(String fileName) throws UnsupportedEncodingException {
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
        this.response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    }

    /**
     * 开始下载
     *
     * @throws IOException if an I/O error occurs
     */
    public void download() throws IOException {
        OutputStream outputStream = this.response.getOutputStream();
        byte[] buffer = new byte[1024 * 1024];
        try {
            int index;
            while ((index = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, index);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载错误", e);
        }
        // finally {close();} // 通过try resources 回收资源
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     * <p>
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }


    /**
     * 初始化构造器
     *
     * @param response 响应对象
     * @param encode   编码
     *
     * @throws IOException I/O异常
     */
    private void init(HttpServletResponse response, String encode) throws IOException {
        this.inputStream = new FileInputStream(this);
        this.initResponse(response, encode);
    }

    /**
     * 初始化 Response对象
     *
     * @param encode 编码
     */
    private void initResponse(HttpServletResponse response, String encode) throws IOException {
        this.response = response;
        if (encode == null || encode.length() == 0) {
            encode = ENCODE;
        }
        this.response.reset();
        this.response.setCharacterEncoding(encode);//设置编码
        this.response.setContentType("multipart/form-data");


    }

    /**
     * 抽取输入流
     *
     * @param uri 通过URI
     *
     * @return InputStream
     *
     * @throws IOException I/O异常
     */
    private InputStream extractInputStreamForURI(URI uri) throws IOException {
        URL url = uri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(6000);
        connection.setReadTimeout(6000);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("读取网络文件失败!");
        }
        return connection.getInputStream();
    }
}
