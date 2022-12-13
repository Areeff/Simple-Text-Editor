package mainproject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class MainProject implements ActionListener{
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File,Edit,Close;
    JMenuItem NewFile,SaveFile,OpenFile,PrintFile,copy,cut,paste,CloseEditor;
    MainProject()
    {
        frame=new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,800);
        jTextArea= new JTextArea("Start Writing From Here");
        frame.add( jTextArea);
        jMenuBar=new JMenuBar();
        File=new JMenu("File");
        Edit=new JMenu("Edit");
        Close=new JMenu("Close");
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);
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
        File.add(NewFile);
        File.add(SaveFile);
        File.add(OpenFile);
        File.add(PrintFile);
        Edit.add(copy);
        Edit.add(cut);
        Edit.add(paste);
        Close.add(CloseEditor);
        frame.setJMenuBar(jMenuBar);
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
        else if(s.equals("Print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                //Logger.getLogger(MainProject.class.getName()).log(Level.SEVERE, null, ex);
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
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans=jFileChooser.showSaveDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
}
