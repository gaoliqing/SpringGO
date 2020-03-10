package xyz.gaoliqing.production.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import xyz.gaoliqing.production.exception.CustomException;
import xyz.gaoliqing.production.exception.CustomExceptionType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-05 10:02
 * @description FTP服务器的连接工具类
 */
@Slf4j
public class FtpUtil {

    /**
     *
     * @param host 地址
     * @param port  端口
     * @param username  账户
     * @param password  密码
     * @param basePath  FTP服务器基础目录, 并自动拼接日期目录如: /2020/03
     * @param fileName  上传到 FTP服务器上的文件名
     * @param input 输入流
     * @return  返回图片的存放路径
     */
    public static String fileUpload(String host,
                                    int port,
                                    String username,
                                    String password,
                                    String basePath,
                                    String fileName,
                                    InputStream input) {

        // 返回的文件名
        String remote;
        String filePath = new SimpleDateFormat("yyyy/MM").format(new Date());
        System.out.println(filePath);
        // 创建ftp客户端
        FTPClient ftpClient = new FTPClient();
        // 设置编码字符集
        // ftpClient.setControlEncoding("UTF-8");
        try {
            // 连接FTP服务器
            ftpClient.connect(host, port);
            // 登录
            ftpClient.login(username, password);
            // 获取登录的状态码,判断是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "登录FTP服务器,状态码异常"+reply);
            }
            // 设置上传的目录
            if (!ftpClient.changeWorkingDirectory(basePath+filePath)) {
                // 如果目录不存在, 则创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
                        if (!ftpClient.makeDirectory(tempPath)) {
                            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "创建FTP目录失败");
                        } else {
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }

            // 指定上传方式为二进制方式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 设置为被动模式
            // ftpClient.enterLocalPassiveMode();
            // ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            // 得到文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            remote = UUID.randomUUID().toString().replace("-", "") + suffix;
            // 开始上传文件 ,remote指定上传远程服务器的文件名 local指本地的输入流
            if (!ftpClient.storeFile(remote, input)) {
                throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "FTP上传失败");
            }
            input.close();
            ftpClient.logout();
        } catch (IOException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, CustomExceptionType.SYSTEM_ERROR.getDesc());
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("上传时关闭FTP连接失败");
                }
            }
        }

        return remote;
    }


    /**
     *
     * @param host 地址
     * @param port  端口
     * @param username 账户
     * @param password  密码
     * @param remotePath    FTP服务器上的相对路径
     * @param fileName  要下载的文件名
     * @param localPath 下载后要保存到的本地路径
     * @return  是否成功下载
     */
    public static boolean downloadFile(String host,
                                       int port,
                                       String username,
                                       String password,
                                       String remotePath,
                                       String fileName,
                                       String localPath) {

        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return result;
            }
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile f : fs) {
                if (f.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + f.getName());
                    FileOutputStream out = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(f.getName(), out);
                    out.close();
                }
            }
            ftpClient.logout();
            result = true;
        } catch (IOException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, CustomExceptionType.SYSTEM_ERROR.getDesc());
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("下载时关闭FTP连接失败");
                }
            }
        }
        return result;
    }
}
