package com.tg.attendance.client;

import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Scanner;

public class AttendanceScannerInterface {

    private static final String BENTANG = "tg";
    static Color greenColor = new Color(65,129,49);

    static Color redColor = new Color(255,0,0);

    static Color blueColor = new Color(0,0,255);
    public static void main(String[] args) throws IOException {
        JFrame mainFrame = new JFrame();
        JPanel keyInputPanel = new JPanel(new BorderLayout());
        JPanel outputPanel = new JPanel(new GridLayout(3,1));
        mainFrame.setLayout(new BorderLayout());

        JTextField inputText = new JTextField();


        JLabel qrCodeLbl = new JLabel(" 签到  ");

        Font labelFont = qrCodeLbl.getFont();

        int newFontSize = (labelFont.getSize() * 3);

        Font font = new Font(labelFont.getName(), Font.PLAIN, newFontSize);

        qrCodeLbl.setFont(font);
        inputText.setFont(font);

        JLabel outputLbl = new JLabel();
        outputPanel.add(new JPanel());
        JPanel j = new JPanel();
        j.add(outputLbl);
        outputPanel.add(j);

        Font outputFont = outputLbl.getFont();

        int outputFontSize = (outputFont.getSize() * 5 - 10);
        outputFont = new Font(labelFont.getName(), Font.PLAIN, outputFontSize);

        outputLbl.setFont(outputFont);

        mainFrame.setTitle("签到系统");

        keyInputPanel.add(qrCodeLbl, BorderLayout.WEST);
        keyInputPanel.add(inputText, BorderLayout.CENTER);
        mainFrame.add(keyInputPanel, BorderLayout.NORTH);
        mainFrame.add(outputPanel, BorderLayout.CENTER);

        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String qrCodeText = inputText.getText();
                    outputLbl.setForeground(blueColor);
                    try {
                        callAPI(qrCodeText, outputLbl, inputText);
                        super.keyReleased(e);
                    } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }



    private static void callAPI(String qrcode, JLabel outputLbl, JTextField inputText) throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        System.out.println("QRCode: "+ qrcode);
        long currentTs = System.currentTimeMillis();
        String link = "http://xfb.tgone.org:4011/mark/" + BENTANG + "/" +qrcode;

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(link))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();


        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(resp -> {
                    String outputString = resp.replaceAll("\\<.*?\\>", "");
                    if (outputString.endsWith("签到成功！")) {
                        outputLbl.setForeground(greenColor);
                    } else {
                        outputLbl.setForeground(redColor);
                    }
                    System.out.println("Result: "+ outputString + "   , Time: "+ (System.currentTimeMillis() - currentTs) + " ms");
                    outputLbl.setText(outputString);
                    inputText.setText("");
                    inputText.setFocusable(true);
                    System.out.println();
                })
                .join();
    }
}
