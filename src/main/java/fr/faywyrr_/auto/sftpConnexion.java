package fr.faywyrr_.auto;

import com.jcraft.jsch.*;

import java.util.Properties;

public class sftpConnexion extends JSch {

    public Session session;

    public sftpConnexion(String host, String username, String password) {
        super();
        this.setup(host, username, password);
    }

    private void setup(String host, String username, String password) {
        try {
            session = super.getSession(username, host);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(String localFile, String sftpFile) {
        try {
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(localFile, sftpFile);
            channelSftp.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        try {
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            channelExec.connect();
            channelExec.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

}
