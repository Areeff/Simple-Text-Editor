package mainproject;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.SANS_SERIF;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
public class MainProject implements ActionListener{
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File,Edit,Themes;
    JMenuItem NewFile,SaveFile,OpenFile,PrintFile,copy,cut,paste,CloseEditor,SelectAll,FontSize,Normal,Dark,Moonlight;
    JScrollPane scroll;
    MainProject()
    {
        frame=new JFrame("Simple Text Editor");
        frame.setBounds(50,50,800,600);
        jTextArea= new JTextArea("");
        frame.add( jTextArea);
        jMenuBar=new JMenuBar();
        File=new JMenu("File  ");
        Edit=new JMenu("Edit  ");
        Themes=new JMenu("Themes  ");
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Themes);
        NewFile=new JMenuItem("New");
        NewFile.addActionListener(this);
        SaveFile=new JMenuItem("Save");
        SaveFile.addActionListener(this);
        OpenFile=new JMenuItem("Open");
        OpenFile.addActionListener(this);
        PrintFile=new JMenuItem("Print");
        PrintFile.addActionListener(this);
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        CloseEditor=new JMenuItem("Close");
        CloseEditor.addActionListener(this);
        SelectAll=new JMenuItem("SelectAll");
        SelectAll.addActionListener(this);
        FontSize=new JMenuItem("Font");
        FontSize.addActionListener(this);
        Normal=new JMenuItem("Normal Theme");
        Normal.addActionListener(this);
        Dark=new JMenuItem("Dark Theme");
        Dark.addActionListener(this);
        Moonlight=new JMenuItem("Gray Theme");
        Moonlight.addActionListener(this);
        File.add(NewFile);
        File.add(SaveFile);
        File.add(OpenFile);
        File.add(PrintFile);
        File.add(CloseEditor);
        Edit.add(copy);
        Edit.add(cut);
        Edit.add(paste);
        Edit.add(SelectAll);
        Edit.add(FontSize);
        Themes.add(Normal);
        Themes.add(Dark);
        Themes.add(Moonlight);
        frame.setJMenuBar(jMenuBar);
        scroll=new JScrollPane(jTextArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        MainProject m = new MainProject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s.equals("Copy")){
            jTextArea.copy();
        }
        else if(s.equals("Cut")){
            jTextArea.cut();
        }
        else if(s.equals("Paste")){
            jTextArea.paste();
        }
        else if(s.equals("SelectAll")){
            jTextArea.selectAll();
        }
        else if(s.equals("Font")){
            String size=JOptionPane.showInputDialog("Enter Font Size" ,JOptionPane.OK_CANCEL_OPTION);
            if(size!=null){
                int convertSize=Integer.parseInt(size);
                Font font=new Font(SANS_SERIF,Font.PLAIN,convertSize);
                jTextArea.setFont(font);
            }
        }
        else if(s.equals("Normal Theme")){
            jTextArea.setBackground(Color.WHITE);
            jTextArea.setForeground(Color.BLACK);
        }
        else if(s.equals("Dark Theme")){
            jTextArea.setBackground(Color.DARK_GRAY);
            jTextArea.setForeground(Color.WHITE);
        }
        else if(s.equals("Gray Theme")){
            jTextArea.setBackground(Color.lightGray);
            jTextArea.setForeground(Color.BLACK);
        }
        else if(s.equals("Print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("New")){
            jTextArea.setText("");
        }
        else if(s.equals("Close")){
            frame.setVisible(false);
            
        }
        else if(s.equals("Open")){
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    jTextArea.setText(s2);                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainProject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              
              
        }
        else if(s.equals("Save")){
            JFileChooser fileChooser=new JFileChooser();
              FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only txt files(.txt)","txt");
              fileChooser.setAcceptAllFileFilterUsed(false);
              fileChooser.addChoosableFileFilter(textFilter);
              int action=fileChooser.showSaveDialog(null);
              if(action!=JFileChooser.APPROVE_OPTION){
                  return;
              }else{
                  String fileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
                  if(!fileName.contains(".txt")){
                      fileName=fileName+".txt";
                      try {
                          BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
                          jTextArea.write(writer);
                      } catch (IOException ex) {
                          Logger.getLogger(MainProject.class.getName()).log(Level.SEVERE, null, ex);
                      }
                   
                  }
              }
            
        }
    }
    
}
