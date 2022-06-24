package ru.vsu.cs.p_p_v;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.Parser;

import javax.swing.*;

public class FormMain extends JFrame {
    private JPanel panelMain;
    private JTextArea textAreaGraph;
    private JButton buttonDrawGraph;
    private JPanel panelGraph;
    private JButton buttonEulerCheck;

    SvgPanel panelGraphvizPainter = new SvgPanel();

    public FormMain() {
        this.setTitle("2_7_17");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setSize(500, 500);

        panelGraph.add(panelGraphvizPainter);
        panelGraph.repaint();

        buttonDrawGraph.addActionListener(e -> buttonDrawGraphPressed());
        buttonEulerCheck.addActionListener(e -> buttonEulerCheckPressed());
    }

    private void drawGraph(String graphvizStr) {
        try {
            MutableGraph g = new Parser().read(graphvizStr);
            panelGraphvizPainter.paint(Graphviz.fromGraph(g).render(Format.SVG).toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    private void buttonDrawGraphPressed() {
        try {
            AdjListsGraph graph = new AdjListsGraph();
            graph.fromStr(textAreaGraph.getText());

            drawGraph(graph.toGraphvizStr());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    private void buttonEulerCheckPressed() {
        try {
            AdjListsGraph graph = new AdjListsGraph();
            graph.fromStr(textAreaGraph.getText());

            if (graph.hasEulerCycle())
                JOptionPane.showMessageDialog(null, "Граф имеет эйлеров цикл, т.к. все вершины четные");
            else
                JOptionPane.showMessageDialog(null, "Граф не имеет эйлеров цикл, т.к. одна из вершин нечетная");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
