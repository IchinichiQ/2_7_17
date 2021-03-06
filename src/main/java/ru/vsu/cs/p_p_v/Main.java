package ru.vsu.cs.p_p_v;

/*
        В заданном графе необходимо определить, существует ли цикл, проходящий по
    каждому ребру графа ровно один раз.
 */

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }
}
