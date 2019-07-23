package ru.rbc.kskabort;

import org.openqa.selenium.WebDriver;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Just_for_trottling {

    public static void SetTrottling(String NameOfTrorrlingProfile, String DownloadSpeed, String UploadSpeed, String Latency, WebDriver driver) throws AWTException {
        Robot robot = new Robot();
        //Открываем консоль
        robot.keyPress(KeyEvent.VK_CONTROL) ;
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_J);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_J);
        robot.delay(500);

        //Переключаемся на вкладку Network
        for (int i = 0; i < 2; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(']'));
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(']'));
            robot.delay(200);}

        //Открытие инспектора в отдельном окне
        PressKeyMy("Tab",12, robot);
        PressKeyMy("Enter",1, robot);
        PressKeyMy("Down",1, robot);
        PressKeyMy("Right",1, robot);
        PressKeyMy("Enter",1, robot);

        //Открытие настроек троттлинга
        PressKeyMy("Tab",12, robot);
        PressKeyMy("Enter",1, robot);
        PressKeyMy("Down",4, robot);
        PressKeyMy("Enter",2, robot);
        //PressKeyMy("Enter",1, robot);

        //Ввод настроек нового профиля троттлинга
        SendStringKeys(NameOfTrorrlingProfile, robot);
        robot.delay(200);
        PressKeyMy("Tab",1, robot);
        SendStringKeys(DownloadSpeed, robot);
        PressKeyMy("Tab",1, robot);
        SendStringKeys(UploadSpeed, robot);
        PressKeyMy("Tab",1, robot);
        SendStringKeys(Latency, robot);
        PressKeyMy("Enter",1, robot);

        //Переключение профиля троттлинга
        PressKeyMy("Escape",1, robot);
        PressKeyMy("Enter",1, robot);
        PressKeyMy("Down",4, robot);
        PressKeyMy("Enter",1, robot);

        //Переключение на окно драйвера
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(200);
    }

    private static void PressKeyMy(String K, int num, Robot robot){
        if (K.equals("Enter")){
            for (int i = 0; i < num; i++) {
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(200);
                System.out.println("I pressed - " + K + " in " + i + " time");
            }
            robot.delay(300);
        }
        if (K.equals("Down")) {
            for (int i = 0; i < num; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                robot.delay(200);
                System.out.println("I pressed - " + K + " in " + i + " time");
            }
            robot.delay(300);
        }
        if (K.equals("Right")) {
            for (int i = 0; i < num; i++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.delay(200);
                System.out.println("I pressed - " + K + " in " + i + " time");
            }
            robot.delay(300);
        }
        if (K.equals("Tab")) {
            for (int i = 0; i < num; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.delay(200);
                    System.out.println("I pressed - " + K + " in " + i + " time");
                }
            robot.delay(300);
        }
        if (K.equals("Escape")) {
            for (int i = 0; i < num; i++) {
                robot.keyPress(KeyEvent.VK_ESCAPE);
                robot.keyRelease(KeyEvent.VK_ESCAPE);
                robot.delay(200);
                System.out.println("I pressed - " + K + " in " + i + " time");
            }
            robot.delay(300);
        }
        else if (!K.equals("Enter") && !K.equals("Right") && !K.equals("Down") && !K.equals("Tab")) throw new Error("Can't find key: "+K);
    }

    private static void SendStringKeys(String Str, Robot r){
        char[] st = Str.toCharArray();
        for (char i : st){
            r.keyPress(KeyEvent.getExtendedKeyCodeForChar(i));
            r.keyRelease(KeyEvent.getExtendedKeyCodeForChar(i));
            r.delay(100);
        }
        r.delay(200);
    }
}
