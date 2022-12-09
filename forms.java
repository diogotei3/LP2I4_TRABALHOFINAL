import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class forms extends Frame implements ActionListener {

    //elementos
    private Label lnome=new Label("Nome: ");
    private TextField tnome = new TextField();
    private Label lidade=new Label("Idade: ");
    private TextField tidade= new TextField();
    private Label lpeso=new Label("Peso: ");
    private TextField tpeso = new TextField();
    private Label laltura=new Label("Altura: ");
    private TextField taltura = new TextField();
    private Label lobj=new Label("Objetivo: ");
    private TextField tobj = new TextField();
    private Button binc = new Button("Incluir");
    private Button blimp = new Button("Limpar");
    private Button bapre = new Button("Apresentar");
    private Button bsair = new Button("Sair");

    public int i = 0;

    //frame
    forms(){
        Frame f = new Frame();
        f.setTitle("TRABALHO FINAL");
        f.setLocation(200, 200);
        f.setSize(400,200);
        f.setBackground(Color.LIGHT_GRAY);
        f.setVisible(true);
        f.addWindowListener(new FechaJanela());
        f.setLayout(new BorderLayout());

        Panel p1 = new Panel(new GridLayout(6,2, 1, 1));
        p1.add(lnome); p1.add(tnome);
        p1.add(lidade); p1.add(tidade);
        p1.add(lpeso); p1.add(tpeso);
        p1.add(laltura); p1.add(taltura);
        p1.add(lobj); p1.add(tobj);

        Panel p2 = new Panel(new GridLayout(1,2, 1, 1));
        p2.add(binc); p2.add(blimp); p2.add(bapre); p2.add(bsair);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.SOUTH);

        //eventos
        binc.addActionListener(this);
        blimp.addActionListener(this);
        bapre.addActionListener(this);
        bsair.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=tpfinal;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "sa";

        if (e.getSource() == binc) {

            try{

                String nome = tnome.getText();
                int idade = Integer.parseInt(tidade.getText());
                float peso = Float.parseFloat(tpeso.getText());
                float altura = Float.parseFloat(taltura.getText());
                String obj = tobj.getText();

                if(nome.isEmpty() | obj.isEmpty() ){
                    JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios/Preencha corrretamente os campos!");
                }

            }catch(Exception err){

                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios/Preencha corrretamente os campos!");

            }

            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url,username, password);
                System.out.println ("Conexao OK");

                Statement st = con.createStatement();
                ResultSet rs;

                String sql = "INSERT INTO tbcadastro VALUES ('" +tnome.getText() +"', " +Integer.parseInt(tidade.getText()) +
                        ", " +Float.parseFloat(tpeso.getText()) +", " +Float.parseFloat(taltura.getText())
                        +", '"+tobj.getText() + "');";

                System.out.println(sql);

                rs = st.executeQuery(sql);


                con.close();
            }
            catch (Exception erro)
            {
                //nada
            }
        }

        if (e.getSource() == blimp) {
            tnome.setText("");
            tpeso.setText("");
            tidade.setText("");
            tobj.setText("");
            taltura.setText("");
        }

        if (e.getSource() == bapre) {
            JOptionPane.showMessageDialog(null, "Nome: "+tnome.getText()
            +"\nIdade: " + tidade.getText()
            +"\nPeso: " + tpeso.getText()
            +"\nAltura: " + taltura.getText()
            +"\nObjetivo: " + tobj.getText());

        }

        if (e.getSource() == bsair) {
            System.exit(0);
        }

    }
}
