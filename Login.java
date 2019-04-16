package Client;

        import com.alibaba.fastjson.JSONObject;


        import javax.swing.*;

        import java.awt.*;

        import java.awt.event.ActionEvent;

        import java.awt.event.ActionListener;


public class Login extends JFrame {

    //GUI

    private Image loginImage = new ImageIcon("login.jpg").getImage();//login background

    private JTextField accountField = new JTextField(15);

    private JTextField passwordField = new JTextField(15);

    public Login() {

        this.add(new JPanel() {

            protected void paintComponent(Graphics g) {

                g.drawImage(loginImage, 0, 0, this.getWidth(), this.getHeight(), this);//set login background

            }

            {

                this.setLayout(new GridLayout(1, 2));

                //left half of login UI

                this.add(new NonopaquePanel() {

                    {

                        this.setLayout(new GridLayout(3, 1));

                        this.add(new NonopaquePanel());

                        this.add(new NonopaquePanel() {

                            {

                                this.add(new JLabel("图书管理系统"));

                            }

                        });

                    }

                });

                //right half of login UI

                this.add(new NonopaquePanel() {

                    {

                        this.setLayout(new GridLayout(8, 1));

                        this.add(new NonopaquePanel());

                        this.add(new NonopaquePanel() {

                            {

                                this.add(new JLabel("Account"));

                            }

                        });

                        this.add(new NonopaquePanel() {

                            {

                                this.add(accountField);

                            }

                        });

                        this.add(new NonopaquePanel() {

                            {

                                this.add(new JLabel("Password"));

                            }

                        });

                        this.add(new NonopaquePanel() {

                            {

                                this.add(passwordField);

                            }

                        });

                        //to be debug

                        this.add(new NonopaquePanel() {

                            {

                                this.add(new Button("login") {

                                    {

                                        this.addActionListener(new ActionListener() {

                                            @Override

                                            public void actionPerformed(ActionEvent e) {

                                                String account = accountField.getText();

                                                String password = passwordField.getText();

                                                //

                                                JSONObject response = new TcpClient("192.168.43.90", 8080).action(new LoginRequest(account, password));

                                                int confirm = response.getInteger("confirm");

                                                if (confirm == 0)

                                                    run(new JFrame() {

                                                        {

                                                            this.add(new JLabel("login failure!") {

                                                                {

                                                                    this.setHorizontalAlignment(JLabel.CENTER);

                                                                    this.setVerticalAlignment(JLabel.CENTER);

                                                                }

                                                            });

                                                        }

                                                    }, 100, 100);

                                                else {

                                                    int identity = response.getInteger("identity");

                                                    if (identity == 0)

                                                        ManagerUi.run(new StudentUi(account), 100, 200);

                                                    else if (identity == 1)

                                                        ManagerUi.run(new ManagerUi(account), 300, 300);

                                                }

                                            }

                                        });

                                    }

                                });

                            }

                        });

                    }

                });

            }

        });

    }

    public static void run(JFrame jf, int width, int height) {

        jf.setVisible(true);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setSize(width, height);

    }

    public static void main(String[] args) {

        run(new Login(), 500, 300);

    }

}

class NonopaquePanel extends JPanel {

    public NonopaquePanel() {

        super();

        this.setOpaque(false);

        this.setBackground(null);

    }

}

class Button extends JButton {

    public Button(String text) {

        super(text, new ImageIcon("button.png"));

        this.setHorizontalTextPosition(JButton.CENTER);

    }

}

class LoginRequest {

    public String act = "loginrequest";

    public String id;

    public String pwd;

    LoginRequest(String id, String pwd) {

        this.id = id;

        this.pwd = pwd;

    }

}