import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserLoggedIn extends JFrame {

    Database d = new Database();

    private JTextArea infoField = new JTextArea();
    private JButton courseSearch = new JButton("Sök efter kurser");
    private JButton teacherSearch = new JButton("Sök efter lärare");
    private JButton studentSearch = new JButton("Sök efter elever");
    private JButton infoButton = new JButton("Tryck för info om skolan");
    private JButton scheduleButton = new JButton("Schema");
    private JButton compareScheduleButton = new JButton("Jämför schema");
    private JTextField searchField = new JTextField("Sök här");
    private JPanel bottomPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel centerPanel = new JPanel();



    public UserLoggedIn(){

        bottomPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new GridLayout(3, 3));
        centerPanel.setLayout(new BorderLayout());

        bottomPanel.add(northPanel, BorderLayout.NORTH);
        bottomPanel.add(southPanel, BorderLayout.SOUTH);
        bottomPanel.add(centerPanel, BorderLayout.CENTER);

        southPanel.add(teacherSearch);
        southPanel.add(studentSearch);
        southPanel.add(courseSearch);
        southPanel.add(infoButton);
        southPanel.add(scheduleButton);
        southPanel.add(compareScheduleButton);

        centerPanel.add(infoField, BorderLayout.NORTH);
        centerPanel.add(searchField, BorderLayout.SOUTH);
        centerPanel.setBackground(Color.WHITE);
        infoField.setFont(new Font("Monaco", Font.PLAIN, 18));

        infoField.setEditable(false);


        add(bottomPanel);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        courseSearch.addActionListener(e->{
            if (!searchField.getText().isEmpty()) {
                Course course = d.searchCourse(searchField.getText());
                if (course == null){
                    infoField.setText("Kursen finns inte");
                }
                else {
                    infoField.setText(d.printCourse(searchField.getText()));
                }

            }
        });

        studentSearch.addActionListener(e->{
            if (!searchField.getText().isEmpty()) {
                Person student = d.searchStudent(searchField.getText());
                if (student == null)
                infoField.setText("Eleven finns inte");
                else infoField.setText(d.printStudent(searchField.getText()));
            }
        });

        teacherSearch.addActionListener(e->{

            if (!searchField.getText().isEmpty()) {
                Person teacher = d.searchTeacher(searchField.getText());
                if (teacher == null)
                infoField.setText("Läraren finns inte");
                else infoField.setText(d.printTeacher(searchField.getText()));
            }
        });

        infoButton.addActionListener(e->{
            infoField.setText("Välkommen till Nackamagin!" +
                    "\nDu kan hitta oss på exempelgatan 12" +
                    "\n------------------Kontakt------------------\n" +
                    "Telefonnummer: 0738347612\n" +
                    "Mail: nackamagin@gmail.com");
        });

        scheduleButton.addActionListener(e->{
            if (!searchField.getText().isEmpty()){
               String output = d.findSchedule(searchField.getText());
               if (d.searchCourse(searchField.getText()) == null && d.searchStudent(searchField.getText()) == null && d.searchTeacher(searchField.getText()) == null){
                   output = "Kunde inte hitta något!";
               }
               infoField.setText(output);
            }
        });

        compareScheduleButton.addActionListener(e -> {
            if (!searchField.getText().isEmpty()){
               String output = d.compareSchedule(searchField.getText());
                infoField.setText(output);
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (searchField.getText().equals("Sök här")){
                    searchField.setText("");
                }
            }
        });
    }
}