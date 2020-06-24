/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;
import jaco.mp3.player.MP3Player;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.nio.file.Paths;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Musica;
import model.UtilitarioMusica;
/**
 *
 * @author rosim
 */

public class MusicaQuiz extends javax.swing.JFrame {
    
    UtilitarioMusica utilitario = new UtilitarioMusica();

       // Define MP3Player Class From JACO MP3Player Lib
    MP3Player player;
    // Define File For Song
    File songFile;
    // Define Current Directory Like If We Use JFileChooser then where it statys.
    String currentDirectory = "home.user"; // I am using home.user this will call file chooser in user documents.
    // Here I define currentPath of the running app class
    String currentPath;
    // This String Will Be For Image Name or Path
    String imagePath;
    // We need to set app title as string
    String appName;
    
    // Now Check If Repeat Button is Enabled or not
    boolean repeat = false;
    
    // Here I am making a boolean for windowCollapsed
    boolean windowCollapsed = false;
    
    // Here I need to define mouse x and y for to get mouse position on screen.
    int xMouse, yMouse;
 
    int indice = 0;
    
    int totalRespostasCorretas = 0;
    /**
     * Creates new form Musica1
     */
    
    private Resultado telaResultado;
    private static final String caminho = "src\\music\\";

    public MusicaQuiz() {        
        initComponents();
        
        playMusica(indice); 
        telaResultado = new Resultado();
    }
    
    private void paraTocadorDeMusica() {
        player.stop();
    }

    private void playMusica(int index) {
        this.appName = "English Exercise " + (indice + 1)  + " - (MP3 Player)";
        appTitle.setText(appName);
        songFile = new File(caminho + utilitario.getMusicas().get(index).getNomeArquivo());
        
        // Now make a string to get file name
        String fileName = songFile.getName();
        // Here set our song name label with this name
        songNameDisplay.setText(fileName);
        
        // Add our method to player variable
        player = mp3Player();
        // Now add song to player as playlist.
        player.addToPlayList(songFile);
        
        // Here get current path and images path in strings
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        imagePath = "\\images";
    }
    
        private MP3Player mp3Player(){
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }
    
    // Let's Set Volume Down Method It's not necessary to remeber this code.
    private void volumeDownControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float)currentVolume-(double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    // Let's Set Volume Up Method It's not necessary to remeber this code.
    private void volumeUpControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float)currentVolume+(double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    // Let's Set Volume Method It's not necessary to remeber this code.
    private void volumeControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReposta1 = new javax.swing.JButton();
        jLabelNomeMusica = new javax.swing.JLabel();
        jButtonReposta2 = new javax.swing.JButton();
        jButtonProximaMusica = new javax.swing.JButton();
        jLabelPergunta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        quitBtn = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JLabel();
        appTitle = new javax.swing.JLabel();
        songNameMainPanel = new javax.swing.JPanel();
        songNameSubPanel = new javax.swing.JPanel();
        songNameDisplay = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        repeatBtn = new javax.swing.JLabel();
        pauseBtn = new javax.swing.JLabel();
        playBtn = new javax.swing.JLabel();
        stopBtn = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        volumeDownBtn = new javax.swing.JLabel();
        volumeUpBtn = new javax.swing.JLabel();
        volumeFullBtn = new javax.swing.JLabel();
        muteBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonReposta1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonReposta1.setText(utilitario.getMusicas().get(indice).getResposta());
        jButtonReposta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReposta1ActionPerformed(evt);
            }
        });

        jLabelNomeMusica.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelNomeMusica.setText(utilitario.getMusicas().get(indice).getNome());

        jButtonReposta2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonReposta2.setText(utilitario.getMusicas().get(indice).getRespostaErrada());
        jButtonReposta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReposta2ActionPerformed(evt);
            }
        });

        jButtonProximaMusica.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonProximaMusica.setText("Próxima música ");
        jButtonProximaMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximaMusicaActionPerformed(evt);
            }
        });

        jLabelPergunta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelPergunta.setText(utilitario.getMusicas().get(indice).getPergunta());

        jPanel1.setBackground(new java.awt.Color(7, 63, 86));

        headerPanel.setBackground(new java.awt.Color(36, 93, 116));

        quitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/quit.png"))); // NOI18N
        quitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitBtnMouseClicked(evt);
            }
        });

        settingsBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/settings.png"))); // NOI18N
        settingsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsBtnMouseClicked(evt);
            }
        });

        appTitle.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        appTitle.setForeground(new java.awt.Color(34, 202, 237));
        appTitle.setText("NOME");
        appTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                appTitleMouseDragged(evt);
            }
        });
        appTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appTitleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                appTitleMousePressed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(appTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        songNameMainPanel.setBackground(new java.awt.Color(7, 63, 86));

        songNameSubPanel.setBackground(new java.awt.Color(7, 63, 86));
        songNameSubPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(34, 202, 237), 1, true));

        songNameDisplay.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        songNameDisplay.setForeground(new java.awt.Color(34, 202, 237));
        songNameDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songNameDisplay.setText("TOCANDO");

        javax.swing.GroupLayout songNameSubPanelLayout = new javax.swing.GroupLayout(songNameSubPanel);
        songNameSubPanel.setLayout(songNameSubPanelLayout);
        songNameSubPanelLayout.setHorizontalGroup(
            songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
            .addGroup(songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(songNameSubPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(songNameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        songNameSubPanelLayout.setVerticalGroup(
            songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(songNameSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(songNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout songNameMainPanelLayout = new javax.swing.GroupLayout(songNameMainPanel);
        songNameMainPanel.setLayout(songNameMainPanelLayout);
        songNameMainPanelLayout.setHorizontalGroup(
            songNameMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(songNameMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songNameSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        songNameMainPanelLayout.setVerticalGroup(
            songNameMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(songNameMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songNameSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        controlPanel.setBackground(new java.awt.Color(7, 63, 86));

        repeatBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        repeatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/repeat.png"))); // NOI18N
        repeatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                repeatBtnMouseClicked(evt);
            }
        });

        pauseBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pauseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/pause.png"))); // NOI18N
        pauseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pauseBtnMouseClicked(evt);
            }
        });

        playBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/play.png"))); // NOI18N
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playBtnMouseClicked(evt);
            }
        });

        stopBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stopBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/stop.png"))); // NOI18N
        stopBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopBtnMouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        volumeDownBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeDownBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/volume_down.png"))); // NOI18N
        volumeDownBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeDownBtnMouseClicked(evt);
            }
        });

        volumeUpBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeUpBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/volume_up.png"))); // NOI18N
        volumeUpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeUpBtnMouseClicked(evt);
            }
        });

        volumeFullBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeFullBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/volume_full.png"))); // NOI18N
        volumeFullBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeFullBtnMouseClicked(evt);
            }
        });

        muteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        muteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jobme/pk/images/mute.png"))); // NOI18N
        muteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muteBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(repeatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(volumeDownBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeUpBtn)
                .addGap(18, 18, 18)
                .addComponent(volumeFullBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(muteBtn)
                .addGap(98, 98, 98)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                            .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(volumeFullBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(volumeDownBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(volumeUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(muteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(14, 14, 14)))
                    .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repeatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songNameMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(songNameMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonReposta2)
                    .addComponent(jButtonReposta1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabelNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonProximaMusica)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButtonReposta1)
                .addGap(18, 18, 18)
                .addComponent(jButtonReposta2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButtonProximaMusica)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void appTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_appTitleMouseDragged

    private void appTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            if(windowCollapsed == false){
                windowCollapsed = true;
                this.setSize(new Dimension(this.getSize().width, 50));

                appTitle.setFont(new Font("Nirmala UI", 0, 12));
                appTitle.setText("Tocando agora... | " + songFile.getName());
            }else if(windowCollapsed == true){
                windowCollapsed = false;
                this.setSize(new Dimension(this.getSize().width, 250));

                appTitle.setFont(new Font("Nirmala UI", 0, 18));
                appTitle.setText(appName);
            }
        }
    }//GEN-LAST:event_appTitleMouseClicked

    private void appTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appTitleMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_appTitleMousePressed

    private void quitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitBtnMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_quitBtnMouseClicked

    private void settingsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsBtnMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Suas opções de configurações serão exibido aqui!");
    }//GEN-LAST:event_settingsBtnMouseClicked

    private void repeatBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_repeatBtnMouseClicked
        // TODO add your handling code here:
        if(repeat == false){
            repeat = true;
            player.setRepeat(repeat);

            String image = currentPath+imagePath+"\\repeat_enabled.png";
            repeatBtn.setIcon(new ImageIcon(image));
        }else if(repeat == true){
            repeat = false;
            player.setRepeat(repeat);

            String image = currentPath+imagePath+"\\repeat.png";
            repeatBtn.setIcon(new ImageIcon(image));
        }
    }//GEN-LAST:event_repeatBtnMouseClicked

    private void pauseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseBtnMouseClicked
        // TODO add your handling code here:
        player.pause();
    }//GEN-LAST:event_pauseBtnMouseClicked

    private void playBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playBtnMouseClicked
        // TODO add your handling code here:
        player.play();
    }//GEN-LAST:event_playBtnMouseClicked

    private void stopBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopBtnMouseClicked
        // TODO add your handling code here:
        player.stop();
    }//GEN-LAST:event_stopBtnMouseClicked

    private void volumeDownBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeDownBtnMouseClicked
        // TODO add your handling code here:
        volumeDownControl(0.1);
    }//GEN-LAST:event_volumeDownBtnMouseClicked

    private void volumeUpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeUpBtnMouseClicked
        // TODO add your handling code here:
        volumeUpControl(0.1);
    }//GEN-LAST:event_volumeUpBtnMouseClicked

    private void volumeFullBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeFullBtnMouseClicked
        // TODO add your handling code here:
        volumeControl(1.0);
    }//GEN-LAST:event_volumeFullBtnMouseClicked

    private void muteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muteBtnMouseClicked
        // TODO add your handling code here:
        volumeControl(0.0);
    }//GEN-LAST:event_muteBtnMouseClicked

    private void jButtonReposta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReposta2ActionPerformed
        String reposta = jButtonReposta2.getText();
        verificaReposta(reposta);
    }//GEN-LAST:event_jButtonReposta2ActionPerformed

    public void verificaReposta(String resposta) {
        Musica musicaAtual = utilitario.getMusica(indice);
    
        if (resposta == musicaAtual.getResposta()) {
            musicaAtual.setaRepostaEstaCorreta(true);
        }
    }
    
    private void jButtonProximaMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximaMusicaActionPerformed
           
        System.out.println("indice:" + indice);
        System.out.println("total:" + utilitario.getMusicas().size());
        int index = ++indice;
        
        if(index  < utilitario.getMusicas().size()) {   
            indice = index;
            playMusica(indice);

            Musica musicaAtual = utilitario.getMusica(indice);
            jLabelNomeMusica.setText(musicaAtual.getNome());
            jLabelPergunta.setText(musicaAtual.getPergunta());
            jButtonReposta1.setText(musicaAtual.getRespostaErrada());
            jButtonReposta2.setText(musicaAtual.getResposta());
        } else {
            paraTocadorDeMusica();
            StringBuilder resultados = utilitario.calculaPontuacao();            
                
            System.out.println(resultados);
            
            telaResultado.setVisible(true);
            telaResultado.setTitle("Resultados do Quiz");
            telaResultado.defineResultadojTextArea(resultados.toString());            
            
            this.dispose();
                       
            
            indice = -1;            
        } 
    }//GEN-LAST:event_jButtonProximaMusicaActionPerformed

    private void jButtonReposta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReposta1ActionPerformed
       String reposta = jButtonReposta1.getText();
        verificaReposta(reposta);
    }//GEN-LAST:event_jButtonReposta1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appTitle;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButtonProximaMusica;
    private javax.swing.JButton jButtonReposta1;
    private javax.swing.JButton jButtonReposta2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelNomeMusica;
    private javax.swing.JLabel jLabelPergunta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel muteBtn;
    private javax.swing.JLabel pauseBtn;
    private javax.swing.JLabel playBtn;
    private javax.swing.JLabel quitBtn;
    private javax.swing.JLabel repeatBtn;
    private javax.swing.JLabel settingsBtn;
    private javax.swing.JLabel songNameDisplay;
    private javax.swing.JPanel songNameMainPanel;
    private javax.swing.JPanel songNameSubPanel;
    private javax.swing.JLabel stopBtn;
    private javax.swing.JLabel volumeDownBtn;
    private javax.swing.JLabel volumeFullBtn;
    private javax.swing.JLabel volumeUpBtn;
    // End of variables declaration//GEN-END:variables
}
