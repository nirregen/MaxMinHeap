import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Build Max-Min-heap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        JPanel panal = new JPanel();
        panal.setLayout(null);
        //frame.add(panal);
        //frame.setLayout(new java.awt.BorderLayout());
        JLabel Welcome = new JLabel("welcome to Build Max-Min heap program");
        Welcome.setFont(new Font("Arial", Font.BOLD, 12));
        Welcome.setBounds(110, 30, 250, 20);
        panal.add(Welcome);
        JButton Build_Heap = new JButton("Build Max-Min-Heap");
        JButton exit = new JButton("Exit");
        JButton extractMax = new JButton("Extract Max");
        JButton extractMin = new JButton("Extract Min");
        extractMin.setBounds(160, 140, 120, 50);
        extractMax.setBounds(30, 140, 120, 50);//where is the button in the frame. 
        Build_Heap.setBounds(150, 80, 180, 80);//where is the button in the frame. 
        exit.setBounds(150,180, 180, 80);//where is the button in the frame.
        
        panal.add(Build_Heap);
        panal.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Build_Heap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                chooser.setFileFilter(filter);
                int result = chooser.showOpenDialog(panal);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    int[] arr = new int[0];
                    int index = 0;
                    try {
                        // Read the contents of the file
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] parseString = line.split(",");
                            arr = new int[parseString.length];
                            for (int i = 0; i < parseString.length; i++) {
                                arr[index] = Integer.parseInt(parseString[i]);
                                index++;
                            }
                        //}
                            MaxMinHeap myHeap = new MaxMinHeap(arr);
                            panal.remove(Welcome);
                            panal.remove(Build_Heap);
                            panal.remove(exit);
                            panal.revalidate();
                            panal.repaint();
                            JLabel printHeap = new JLabel("the heap is: " + myHeap);
                            printHeap.setBounds(110, 30, 400, 20);
                            printHeap.setFont(new Font("Arial", Font.BOLD, 16));
                            panal.add(printHeap);
                            panal.add(extractMax);
                            panal.add(extractMin);


                            JLabel max = new JLabel("");
                            JLabel min = new JLabel("");
                            JTextField UserPlace = new JTextField();
                            UserPlace.setBounds(110, 80, 200, 30);
                            panal.add(UserPlace);
                            extractMax.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panal.remove(printHeap);
                                panal.remove(min);
                                max.setBounds(110, 50, 400, 20);
                                max.setFont(new Font("Arial", Font.BOLD, 16));
                                //panal.remove(printHeap);
                                max.setText("The Max is: " + myHeap.Heap_Extract_Max());
                                printHeap.setText("the heap is: " + myHeap);
                                panal.add(printHeap);
                                panal.add(max);
                                panal.revalidate();
                                panal.repaint();


                            
                        }
                    });
                        
                            extractMin.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    panal.remove(printHeap);
                                    panal.remove(max);
                                    min.setBounds(110, 50,400,20);
                                    min.setFont(new Font("Arial", Font.BOLD, 16));
                                    min.setText("The Min is: "+ myHeap.Heap_extract_Min());
                                    printHeap.setText("The heap is: " + myHeap);
                                    panal.add(printHeap);
                                    panal.add(min);
                                    panal.revalidate();
                                    panal.repaint();
                                };
                            });


                        scanner.close();
                    } 
                }
                    catch (FileNotFoundException ex) {
                        System.err.println("File not found: " + ex.getMessage());
                    }
                }
            }
        });

        


      
       
        panal.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        frame.add(panal,BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}