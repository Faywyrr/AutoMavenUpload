package fr.faywyrr_.auto;

public class Main {

    public Main(String[] args) {

        if (args.length < 5) return;

        String host = args[0];
        String user = args[1];
        String pass = args[2];

        String filePath = args[3];
        String targetPath = args[4];

        sftpConnexion sftp = new sftpConnexion(host, user, pass);

        System.out.println("\n");
        sendInfo("-------------------------------------------------------------------");
        sendInfo("Uploading file to " + host + "...");

        sftp.uploadFile(filePath, targetPath);

        sendInfo("Upload successfully completed! (Location: " + targetPath + ")");

        if(args.length > 5) {

            String command = args[5];

            sendInfo("Sending command " + command + "...");

            sftp.sendCommand("screen -S Minecraft -p 0 -X stuff \"" + command + "^M\"");

            sendInfo("Command sent successfully!");

        }

        sftp.session.disconnect();

        sendInfo("-------------------------------------------------------------------");
        System.out.println("\n");

    }

    private void sendInfo(String info) {
        System.out.println("[INFO SFTP] " + info);
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
