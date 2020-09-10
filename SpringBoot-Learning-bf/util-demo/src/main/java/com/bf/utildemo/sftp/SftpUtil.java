package com.bf.utildemo.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;


import java.io.*;
import java.util.Properties;

/**
 * @description:
 * @author: bofei
 * @date: 2020-09-10 18:27
 **/
@Slf4j
public class SftpUtil {

    private String userName; // FTP 登录用户名
    private String password; // FTP 登录密码
    private String keyFilePath;// 私钥文件的路径
    private String passphrase;// 密钥的密码
    private String host; // FTP 服务器地址IP地址
    private int port; // FTP 端口
    private ChannelSftp sftp;
    private Session sshSession;

    /**
     * 构造基于“密码”认证的sftp对象
     *
     * @param userName 用户名
     * @param password 登陆密码
     * @param host     服务器ip
     * @param port     fwq端口
     */
    public SftpUtil(String userName, String password, String host, int port) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于“密钥”认证的sftp
     *
     * @param userName    用户名
     * @param password    登陆密码
     * @param host        服务器ip
     * @param port        fwq端口
     * @param keyFilePath 密钥路径
     * @param passphrase  密钥的密码
     */
    public SftpUtil(String userName, String password, String host, int port, String keyFilePath, String passphrase) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
        this.keyFilePath = keyFilePath;
        this.passphrase = passphrase;
    }

    /**
     * 连接sftp服务器
     * 如果connect过程出现：Kerberos username [xxx]   Kerberos password
     * 解决办法：移步https://blog.csdn.net/a718515028/article/details/80356337
     *
     * @throws Exception
     */
    public void connect() throws Exception {
        try {
            JSch jsch = new JSch();
            if (keyFilePath != null) {
                if (passphrase != null) {
                    jsch.addIdentity(keyFilePath, passphrase);// 设置私钥
                } else {
                    jsch.addIdentity(keyFilePath);// 设置私钥
                }
                log.info("连接sftp，私钥文件路径：" + keyFilePath);
            }
            log.info("SFTP Host: " + host + "; UserName:" + userName);
            sshSession = jsch.getSession(userName, host, port);
            log.debug("Session 已建立.");
            if (password != null) {
                sshSession.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.setConfig("kex", "diffie-hellman-group1-sha1");
            sshSession.connect();
            log.debug("Session 已连接.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            log.info("连接到SFTP成功.Host: " + host);
        } catch (Exception e) {
            log.error("连接SFTP失败：", e);
        }
    }

    /**
     * 关闭连接 server
     */
    public void disconnect() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                sshSession.disconnect();
                log.info("SFTP连接关闭成功！");
            } else if (sftp.isClosed()) {
                log.warn("SFTP已经关闭,不需要重复关闭！");
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws Exception {
        try {
            try {// 如果cd报异常，说明目录不存在，就创建目录
                sftp.cd(directory);
            } catch (Exception e) {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            sftp.put(input, sftpFileName);
            log.info("SFTP上传成功！文件名：" + sftpFileName);
        } catch (Exception e) {
            log.error("SFTP上传失败！文件名" + sftpFileName, e);
            throw e;
        }
    }

    /**
     * 上传单个文件
     *
     * @param directory  上传到sftp目录
     * @param uploadFile 要上传的文件,包括路径
     * @throws Exception
     */
    public void upload(String directory, String uploadFile) throws Exception {

        File file = new File(uploadFile);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            upload(directory, file.getName(), in);
        } catch (Exception ex) {
            if (in != null) {
                in.close();
            }
            ex.printStackTrace();
        }
    }

    /**
     * 将byte[]上传到sftp，作为文件。注意:从String生成byte[]是，要指定字符集。
     *
     * @param directory    上传到sftp目录
     * @param sftpFileName 文件在sftp端的命名
     * @param byteArr      要上传的字节数组
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, byte[] byteArr) throws Exception {
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));

    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @throws Exception
     */
    public byte[] download2Byte(String directory, String downloadFile, String saveFile) throws Exception {
        connect();
        byte[] fileData = null;
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            synchronized (this) {
                sftp.cd(directory);
                sftp.get(downloadFile, outSteam);
                fileData = outSteam.toByteArray();
            }
            System.out.println("SFTP下载文件成功！文件名：" + downloadFile);
            return fileData;
        } catch (Exception e) {
            System.out.println("SFTP下载文件失败！文件名：" + downloadFile);
            throw e;
        } finally {
            disconnect();
        }
    }

    public static void main(String[] args) {

        SftpUtil sftpUtil = new SftpUtil("name", "pwd", "10.0.0.1", 22);
//        // 上传
        try {
            sftpUtil.connect();
            sftpUtil.upload("/remote/", "D:\\testzip2.zip");
            sftpUtil.upload("/remote/", "D:\\testzipxx.zip");
            sftpUtil.upload("/remote/", "D:\\testzipss.zip");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sftpUtil.disconnect();
        }
        // 下载
        try {
            byte[] bytes = sftpUtil.download2Byte("/remote/", "30000.txt", "");
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
