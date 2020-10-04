package com.roger.tomcat.chapter2;


import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/**
 * HTTP响应
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE=1024;
    Request request;
    OutputStream output;
    PrintWriter writer;
    public Response(OutputStream output){
        this.output=output;
    }
    public void setRequest(Request request){
        this.request=request;
    }

    /**
     * 发送请求的静态资源
     * @throws IOException
     */
    public void sendStaticResource() throws IOException{
        Path path=Paths.get(Constants.WEB_ROOT.toString(),request.getUri());
        if(Files.exists(path)){
            /**
             * 手动返回响应头 response-header属性
             * content-type: text/html;charset=utf-8
             * 防止浏览器无法解析
             */
            String successMessage="HTTP/2.0 200 OK\r\n" +
                    "content-type: text/html;charset=utf-8\r\n" +
                    "content-language: zh-CN\r\n" +
                    "\r\n";
            output.write(successMessage.getBytes());




            byte[] bytes=Files.readAllBytes(path);
            output.write(bytes);
        }else{
            String errorMessage="HTTP/1.1 404  File Not Found\r\n"+
                    "Content-Type: text/html\r\n"+
                    "Content-Length: 23\r\n"+
                    "\r\n"+
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer=new PrintWriter(output,true);
        return writer;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }



    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
