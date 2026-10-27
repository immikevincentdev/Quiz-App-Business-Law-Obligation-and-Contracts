package com.mycompany.quiz_app_business_law_obligations_and_contracts;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class QUIZ_APP_BUSINESS_LAW_OBLIGATIONS_AND_CONTRACTS extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final String studentName = "Mike Vincent S. Bautista";
    private final String studentYear = "4th Year BSIT";
    private JTextArea label;
    private JLabel questionCounterLabel, timerLabel;
    private JRadioButton[] radioButtons = new JRadioButton[4];
    private JButton btnNext, btnResult, btnStart;
    private ButtonGroup bg;
    private JProgressBar progressBar;
    private Timer quizTimer;
    private int current = 0, secondsLeft = 30;
    private boolean[] answeredCorrectly = new boolean[10];
    private int[] selectedAnswers = new int[10];
    private int flashcardIndex = 0;
    private boolean flashcardFlipped = false;
    private int scenarioIndex = 0;
    private boolean scenarioAnswered = false;
    private final String[] questions = {
    "Q1 - What is an obligation under Article 1156 of the Philippine Civil Code?",
    "Q2 - Which of the following is a source of obligations under Article 1157?",
    "Q3 - A seller agrees to deliver a laptop to a buyer. What kind of obligation does the seller have?",
    "Q4 - What are the essential requisites of a valid contract under Article 1318?",
    "Q5 - What contractual element is demonstrated when a buyer and seller mutually agree on a sale?",
    "Q6 - Under Article 1191, what remedy may generally be available when one party fails to comply with a reciprocal obligation?",
    "Q7 - What may a creditor do when a debtor unjustifiably refuses to perform a valid obligation?",
    "Q8 - Which is a recognized mode of extinguishing an obligation under Article 1231?",
    "Q9 - True or False: Under Article 1159, contractual obligations have the force of law between the parties and must be performed in good faith.",
    "Q10 - True or False: Every contract must be written and notarized to be valid under Philippine civil law."
    };
    private final String[][] choices = {
    {"A - A personal promise between friends", "B - A juridical necessity to give, to do, or not to do", "C - A voluntary gift", "D - A business rule"},
    {"A - Personal opinions", "B - Business customs alone", "C - Contracts", "D - Informal promises in every circumstance"},
    {"A - To give", "B - To refrain from doing", "C - To pay damages immediately", "D - To perform a personal service"},
    {"A - Registration, notarization, and witnesses", "B - Consent, object certain, and cause of the obligation", "C - Payment, delivery, and government approval", "D - Profit, written evidence, and two witnesses"},
    {"A - Cause", "B - Object", "C - Consent", "D - Formality"},
    {"A - Automatic imprisonment", "B - Cancellation of all contracts", "C - Automatic transfer of property", "D - Fulfillment or rescission, with damages when proper"},
    {"A - Automatically cancel every contract", "B - Pursue appropriate legal remedies", "C - Automatically take all the debtor's property", "D - Demand any replacement without legal basis"},
    {"A - Payment or performance", "B - Changing one's address", "C - A creditor's silence in every circumstance", "D - Reduced business profits"},
    {"A - True", "B - False"},
    {"A - True", "B - False"}
    };
    // Answer positions start at zero (A = 0, B = 1, etc.)
    private final int[] correctAnswers = {1, 2, 0, 1, 2, 3, 1, 0, 0, 1};
    private final String[] explanations = {
    "Article 1156 defines an obligation as a juridical necessity to give, to do, or not to do.",
    "Article 1157 identifies law, contracts, quasi-contracts, acts or omissions punished by law, and quasi-delicts as sources of obligations.",
    "The seller's duty to deliver the laptop is an obligation to give.",
    "Article 1318 requires consent of the contracting parties, a certain object, and the cause of the obligation.",
    "Consent is shown when the buyer and seller agree to the sale and its essential terms.",
    "Under Article 1191, the injured party may generally seek fulfillment or rescission of a reciprocal obligation, with damages when proper.",
    "A creditor may pursue remedies allowed by law. The appropriate remedy depends on the obligation and circumstances.",
    "Article 1231 recognizes payment or performance as a mode of extinguishing obligations, along with other modes provided by law.",
    "True. Article 1159 states that contractual obligations have the force of law between the parties and must be complied with in good faith.",
    "False. Contracts generally do not all need to be written and notarized to be valid. Specific laws require particular forms for some contracts or purposes."
    };
    private final String[] flashcardFronts = {
    "Article 1156: What is an obligation?",
    "Article 1157: Name the sources of obligations.",
    "Article 1159: What is the effect of a contract?",
    "Article 1318: What are the essential requisites of a contract?",
    "What is an obligation to give?",
    "What does consent mean in a contract?",
    "Article 1231: Give one mode of extinguishing an obligation.",
    "What is a reciprocal obligation?",
    "What is rescission in the context of Article 1191?",
    "Must every contract be written and notarized?"
    };
    private final String[] flashcardBacks = {
    "A juridical necessity to give, to do, or not to do.",
    "Law, contracts, quasi-contracts, acts or omissions punished by law, and quasi-delicts.",
    "It has the force of law between the parties and must be performed in good faith.",
    "Consent, a certain object, and the cause of the obligation.",
    "A duty to deliver a thing, such as a seller delivering a laptop.",
    "The parties' agreement to the contract and its essential terms.",
    "Payment or performance. Other modes are also provided by law.",
    "An obligation where each party's performance is related to the other party's performance.",
    "A remedy that may resolve the reciprocal obligation when legal requirements are met.",
    "No. Some contracts or purposes have specific legal form requirements."
    };
    private final String[] scenarioQuestions = {
    "CASE 1: Ana bought a laptop from Ben. Ben agreed to deliver it on Friday but refuses without a valid reason. What is the main obligation involved?",
    "CASE 2: Carlo and Dana agree on the sale of a specific used phone, and both consent to the agreed price. Which contract element is shown by their agreement?",
    "CASE 3: A borrower fully pays the amount owed to a lender. Which mode of extinguishing an obligation applies?",
    "CASE 4: A service provider and customer enter a contract. The provider must perform the service, and the customer must pay the agreed fee. What best describes this relationship?",
    "CASE 5: A person says every contract is invalid unless written and notarized. Which response is most accurate?"
    };
    private final String[][] scenarioChoices = {
    {"A - Obligation to give", "B - Obligation not to do", "C - Quasi-delict only", "D - No obligation can exist"},
    {"A - Formality", "B - Consent", "C - Registration", "D - Damages"},
    {"A - Novation", "B - Compensation", "C - Payment or performance", "D - Confusion"},
    {"A - A gift", "B - A reciprocal contractual obligation", "C - A criminal penalty", "D - A unilateral opinion"},
    {"A - Correct; all contracts require notarization", "B - Incorrect; form requirements depend on the law and purpose", "C - Correct; oral contracts never count", "D - Incorrect; contracts are never enforceable"}
    };
    private final int[] scenarioCorrect = {0, 1, 2, 1, 1};
    private final String[] scenarioExplanations = {
    "Ben's duty is to deliver a specific thing, so it is an obligation to give. The proper remedy depends on the facts and applicable law.",
    "Consent is the meeting of the parties' agreement. A valid contract also requires a certain object and cause.",
    "Payment or performance extinguishes an obligation when it is properly completed, subject to the applicable rules.",
    "The parties have related duties: one performs the service and the other pays the agreed fee.",
    "Many contracts can be valid without being written and notarized, but some laws require a specific form for validity, enforceability, or another legal purpose."
    };
    public QUIZ_APP_BUSINESS_LAW_OBLIGATIONS_AND_CONTRACTS() {
        java.util.Arrays.fill(selectedAnswers, -1);
        setTitle("Business Law: Obligations and Contracts");
        setSize(980, 650);
        setMinimumSize(new java.awt.Dimension(800, 550));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        URL imageURL = getClass().getResource("/images/blue_technology.png");
        if (imageURL == null) {
            JOptionPane.showMessageDialog(null, "Background image not found. Check src/main/resources/images/blue_technology.png.", "Image Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        BackgroundPanel background = new BackgroundPanel(imageURL);
        setContentPane(background);
        background.setLayout(new BorderLayout());
        label = new JTextArea();
        label.setEditable(false);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 19));
        label.setFocusable(false);
        label.setMargin(new java.awt.Insets(4, 4, 4, 4));
        bg = new ButtonGroup();
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new GlossyRadioButton();
            bg.add(radioButtons[i]);
        }
        btnNext = new GlossyButton("Next");
        btnNext.addActionListener(this);
        btnResult = new GlossyButton("Result");
        btnResult.addActionListener(this);
        btnResult.setVisible(false);
        quizTimer = new Timer(1000, e -> {
            secondsLeft--;
            updateTimerLabel();
            if (secondsLeft <= 0) {
                quizTimer.stop();
                Toolkit.getDefaultToolkit().beep();
                handleTimeout();
            }
        }
        );
        showWelcomeScreen();
        setVisible(true);
    }
    private JPanel getBackgroundPanel() {
        return (JPanel) getContentPane();
    }
    private void resetScreen() {
        if (quizTimer != null) quizTimer.stop();
        JPanel background = getBackgroundPanel();
        background.removeAll();
        background.setLayout(new BorderLayout());
    }
    private void showWelcomeScreen() {
        if (quizTimer != null) quizTimer.stop();
        JPanel background = getBackgroundPanel();
        background.removeAll();
        background.setLayout(new BorderLayout());
        JPanel welcomePanel = new JPanel(new GridLayout(9, 1, 8, 8));
        welcomePanel.setOpaque(false);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(28, 70, 28, 70));
        JLabel title = new JLabel("BUSINESS LAW", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        JLabel subtitle = new JLabel("OBLIGATIONS AND CONTRACTS", SwingConstants.CENTER);
        subtitle.setForeground(new Color(190, 230, 255));
        subtitle.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel studentLabel = new JLabel(studentName, SwingConstants.CENTER);
        studentLabel.setForeground(Color.WHITE);
        studentLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel yearLabel = new JLabel(studentYear, SwingConstants.CENTER);
        yearLabel.setForeground(new Color(160, 220, 255));
        yearLabel.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel instruction = new JLabel("Choose a learning activity below", SwingConstants.CENTER);
        instruction.setForeground(Color.WHITE);
        instruction.setFont(new Font("Arial", Font.PLAIN, 14));
        btnStart = new GlossyButton("START TIMED QUIZ");
        btnStart.addActionListener(e -> {
            playSound();
            current = 0;
            resetQuizData();
            startQuiz();
        }
        );
        JButton flashcardButton = new GlossyButton("FLASHCARDS AND REVIEWER");
        flashcardButton.addActionListener(e -> {
            playSound();
            flashcardIndex = 0;
            flashcardFlipped = false;
            showFlashcards();
        }
        );
        JButton scenarioButton = new GlossyButton("REAL-LIFE CASE SCENARIOS");
        scenarioButton.addActionListener(e -> {
            playSound();
            scenarioIndex = 0;
            scenarioAnswered = false;
            showScenarioMode();
        }
        );
        welcomePanel.add(title);
        welcomePanel.add(subtitle);
        welcomePanel.add(studentLabel);
        welcomePanel.add(yearLabel);
        JButton exitButton = new GlossyButton("Exit");
        exitButton.addActionListener(e -> {
            playSound();
            System.exit(0);
        }
        );
        welcomePanel.add(instruction);
        welcomePanel.add(btnStart);
        welcomePanel.add(flashcardButton);
        welcomePanel.add(scenarioButton);
        welcomePanel.add(exitButton);
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(welcomePanel, BorderLayout.CENTER);
        background.add(center, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }
    private void resetQuizData() {
        java.util.Arrays.fill(answeredCorrectly, false);
        java.util.Arrays.fill(selectedAnswers, -1);
    }
    private void startQuiz() {
        if (quizTimer != null) quizTimer.stop();
        JPanel background = getBackgroundPanel();
        background.removeAll();
        background.setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel(new BorderLayout(15, 15));
        quizPanel.setOpaque(false);
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        JPanel statusPanel = new JPanel(new BorderLayout(10, 8));
        statusPanel.setOpaque(false);
        questionCounterLabel = new JLabel();
        questionCounterLabel.setForeground(Color.WHITE);
        questionCounterLabel.setFont(new Font("Arial", Font.BOLD, 15));
        timerLabel = new JLabel();
        timerLabel.setForeground(new Color(170, 230, 255));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JPanel statusLabels = new JPanel(new BorderLayout());
        statusLabels.setOpaque(false);
        statusLabels.add(questionCounterLabel, BorderLayout.WEST);
        statusLabels.add(timerLabel, BorderLayout.EAST);
        progressBar = new JProgressBar(0, questions.length);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 170, 255));
        progressBar.setBackground(new Color(5, 25, 65));
        statusPanel.add(statusLabels, BorderLayout.NORTH);
        statusPanel.add(progressBar, BorderLayout.CENTER);
        JPanel questionPanel = new GlossyPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        JScrollPane questionScroll = new JScrollPane(label);
        questionScroll.setBorder(BorderFactory.createLineBorder(new Color(90, 195, 255), 1));
        questionScroll.setOpaque(false);
        questionScroll.getViewport().setOpaque(false);
        questionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScroll.setPreferredSize(new java.awt.Dimension(700, 110));
        questionPanel.add(questionScroll, BorderLayout.CENTER);
        JPanel answersPanel = new GlossyPanel();
        answersPanel.setLayout(new GridLayout(0, 1, 10, 10));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        for (JRadioButton rb : radioButtons) {
            rb.setPreferredSize(new java.awt.Dimension(700, 48));
            answersPanel.add(rb);
        }
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setOpaque(false);
        contentPanel.add(questionPanel, BorderLayout.NORTH);
        contentPanel.add(answersPanel, BorderLayout.CENTER);
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setOpaque(false);
        centerPanel.add(statusPanel, BorderLayout.NORTH);
        centerPanel.add(contentPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        JButton homeButton = new GlossyButton("Main Menu");
        homeButton.addActionListener(e -> showWelcomeScreen());
        bottomPanel.add(homeButton);
        bottomPanel.add(btnNext);
        quizPanel.add(centerPanel, BorderLayout.CENTER);
        quizPanel.add(bottomPanel, BorderLayout.SOUTH);
        background.add(quizPanel, BorderLayout.CENTER);
        setData();
        background.revalidate();
        background.repaint();
    }
    private void setData() {
        label.setText(questions[current]);
        label.setCaretPosition(0);
        questionCounterLabel.setText("QUESTION " + (current + 1) + " OF " + questions.length);
        progressBar.setValue(current);
        progressBar.setString((current * 100 / questions.length) + "% Complete");
        bg.clearSelection();
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setEnabled(true);
            if (i < choices[current].length) {
                radioButtons[i].setText(choices[current][i]);
                radioButtons[i].setVisible(true);
            }
            else radioButtons[i].setVisible(false);
        }
        btnNext.setText(current == questions.length - 1 ? "Submit" : "Next");
        btnNext.setVisible(true);
        secondsLeft = 30;
        updateTimerLabel();
        if (quizTimer != null) quizTimer.restart();
    }
    private void updateTimerLabel() {
        if (timerLabel == null) return;
        timerLabel.setText("TIME LEFT: " + secondsLeft + "s");
        timerLabel.setForeground(secondsLeft <= 10 ? new Color(255, 120, 120) : new Color(170, 230, 255));
    }
    private void handleTimeout() {
        selectedAnswers[current] = getSelectedAnswer();
        answeredCorrectly[current] = selectedAnswers[current] == correctAnswers[current];
        if (current == questions.length - 1) {
            showResultDashboard();
            return;
        }
        current++;
        setData();
    }
    private int getSelectedAnswer() {
        for (int i = 0; i < radioButtons.length; i++) if (radioButtons[i].isSelected()) return i;
        return -1;
    }
    private boolean checkAns() {
        return getSelectedAnswer() == correctAnswers[current];
    }
    private boolean hasSelection() {
        return getSelectedAnswer() != -1;
    }
    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }
    // Flashcard review screen
    private void showFlashcards() {
        resetScreen();
        JPanel background = getBackgroundPanel();
        JPanel outer = new JPanel(new BorderLayout(15, 15));
        outer.setOpaque(false);
        outer.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45));
        JLabel title = new JLabel("FLASHCARDS AND REVIEWER", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        JLabel count = new JLabel("CARD " + (flashcardIndex + 1) + " OF " + flashcardFronts.length, SwingConstants.CENTER);
        count.setForeground(new Color(170, 225, 255));
        count.setFont(new Font("Arial", Font.BOLD, 15));
        JButton flip = new GlossyButton(flashcardFlipped ? "SHOW QUESTION" : "FLIP CARD / SHOW ANSWER");
        JTextArea cardText = new JTextArea(flashcardFlipped ? flashcardBacks[flashcardIndex] : flashcardFronts[flashcardIndex]);
        cardText.setEditable(false);
        cardText.setLineWrap(true);
        cardText.setWrapStyleWord(true);
        cardText.setFont(new Font("Arial", Font.BOLD, 22));
        cardText.setForeground(Color.WHITE);
        cardText.setBackground(new Color(5, 25, 65));
        cardText.setMargin(new java.awt.Insets(30, 30, 30, 30));
        cardText.setRows(5);
        JPanel cardPanel = new GlossyPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        cardPanel.add(cardText, BorderLayout.CENTER);
        flip.addActionListener(e -> {
            flashcardFlipped = !flashcardFlipped;
            showFlashcards();
        }
        );
        JButton previous = new GlossyButton("Previous Card");
        previous.setEnabled(flashcardIndex > 0);
        previous.addActionListener(e -> {
            flashcardIndex--;
            flashcardFlipped = false;
            showFlashcards();
        }
        );
        JButton next = new GlossyButton(flashcardIndex == flashcardFronts.length - 1 ? "Back to Menu" : "Next Card");
        next.addActionListener(e -> {
            if (flashcardIndex < flashcardFronts.length - 1) {
                flashcardIndex++;
                flashcardFlipped = false;
                showFlashcards();
            }
            else showWelcomeScreen();
        }
        );
        JButton home = new GlossyButton("Main Menu");
        home.addActionListener(e -> showWelcomeScreen());
        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.setOpaque(false);
        top.add(title);
        top.add(count);
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        controls.setOpaque(false);
        controls.add(previous);
        controls.add(flip);
        controls.add(next);
        controls.add(home);
        outer.add(top, BorderLayout.NORTH);
        outer.add(cardPanel, BorderLayout.CENTER);
        outer.add(controls, BorderLayout.SOUTH);
        background.add(outer, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }
    // Practice cases
    private void showScenarioMode() {
        resetScreen();
        JPanel background = getBackgroundPanel();
        // Keep the case layout consistent with the flashcards.
        JPanel outer = new JPanel(new BorderLayout(15, 15));
        outer.setOpaque(false);
        outer.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45));
        JLabel title = new JLabel("REAL-LIFE CASE SCENARIOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        JLabel count = new JLabel(
        "CASE " + (scenarioIndex + 1) + " OF " + scenarioQuestions.length,
        SwingConstants.CENTER
        );
        count.setForeground(new Color(170, 225, 255));
        count.setFont(new Font("Arial", Font.BOLD, 15));
        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.setOpaque(false);
        top.add(title);
        top.add(count);
        JPanel casePanel = new GlossyPanel();
        casePanel.setLayout(new BorderLayout(12, 12));
        casePanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        JTextArea prompt = new JTextArea(scenarioQuestions[scenarioIndex]);
        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setWrapStyleWord(true);
        prompt.setFont(new Font("Arial", Font.BOLD, 18));
        prompt.setForeground(Color.WHITE);
        prompt.setBackground(new Color(5, 25, 65));
        prompt.setMargin(new java.awt.Insets(18, 18, 18, 18));
        prompt.setRows(3);
        JScrollPane promptScroll = new JScrollPane(prompt);
        promptScroll.setBorder(BorderFactory.createLineBorder(new Color(90, 195, 255), 1));
        promptScroll.setPreferredSize(new java.awt.Dimension(700, 105));
        casePanel.add(promptScroll, BorderLayout.NORTH);
        JPanel options = new JPanel(new GridLayout(0, 1, 8, 8));
        options.setOpaque(false);
        ButtonGroup group = new ButtonGroup();
        JTextArea feedback = new JTextArea(
        scenarioAnswered
        ? "Correct answer: " + scenarioChoices[scenarioIndex][scenarioCorrect[scenarioIndex]]
        + "\n\nLegal concept: " + scenarioExplanations[scenarioIndex]
        : "Select the best answer. The correct answer and explanation will appear here."
        );
        feedback.setEditable(false);
        feedback.setLineWrap(true);
        feedback.setWrapStyleWord(true);
        feedback.setFont(new Font("Arial", Font.PLAIN, 14));
        feedback.setForeground(new Color(235, 247, 255));
        feedback.setBackground(new Color(7, 35, 75));
        feedback.setMargin(new java.awt.Insets(12, 12, 12, 12));
        for (int i = 0; i < scenarioChoices[scenarioIndex].length; i++) {
            final int answer = i;
            JRadioButton option = new GlossyRadioButton();
            option.setText(scenarioChoices[scenarioIndex][i]);
            group.add(option);
            options.add(option);
            option.addActionListener(e -> {
                scenarioAnswered = true;
                feedback.setText(
                (answer == scenarioCorrect[scenarioIndex] ? "CORRECT!\n\n" : "NOT QUITE.\n\n")
                + "Correct answer: " + scenarioChoices[scenarioIndex][scenarioCorrect[scenarioIndex]]
                + "\n\nLegal concept: " + scenarioExplanations[scenarioIndex]
                );
            }
            );
        }
        JScrollPane feedbackScroll = new JScrollPane(feedback);
        feedbackScroll.setBorder(BorderFactory.createLineBorder(new Color(90, 195, 255), 1));
        feedbackScroll.setPreferredSize(new java.awt.Dimension(700, 115));
        JPanel cardContent = new JPanel(new BorderLayout(10, 10));
        cardContent.setOpaque(false);
        cardContent.add(options, BorderLayout.CENTER);
        cardContent.add(feedbackScroll, BorderLayout.SOUTH);
        casePanel.add(cardContent, BorderLayout.CENTER);
        JButton previous = new GlossyButton("Previous Case");
        previous.setEnabled(scenarioIndex > 0);
        previous.addActionListener(e -> {
            scenarioIndex--;
            scenarioAnswered = false;
            showScenarioMode();
        }
        );
        JButton next = new GlossyButton(
        scenarioIndex == scenarioQuestions.length - 1 ? "Back to Menu" : "Next Case"
        );
        next.addActionListener(e -> {
            if (scenarioIndex < scenarioQuestions.length - 1) {
                scenarioIndex++;
                scenarioAnswered = false;
                showScenarioMode();
            }
            else {
                showWelcomeScreen();
            }
        }
        );
        JButton home = new GlossyButton("Main Menu");
        home.addActionListener(e -> showWelcomeScreen());
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        controls.setOpaque(false);
        controls.add(previous);
        controls.add(next);
        controls.add(home);
        outer.add(top, BorderLayout.NORTH);
        outer.add(casePanel, BorderLayout.CENTER);
        outer.add(controls, BorderLayout.SOUTH);
        background.add(outer, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }
    // Quiz results and answer review
    private void showResultDashboard() {
        if (quizTimer != null) quizTimer.stop();
        int correctCount = 0;
        for (boolean correct : answeredCorrectly) if (correct) correctCount++;
        int incorrectCount = questions.length - correctCount;
        int percentage = correctCount * 100 / questions.length;
        String performance = percentage >= 90 ? "EXCELLENT!" : percentage >= 80 ? "VERY GOOD!" : percentage >= 70 ? "GOOD EFFORT!" : "KEEP PRACTICING!";
        JPanel background = getBackgroundPanel();
        background.removeAll();
        background.setLayout(new BorderLayout());
        JPanel dashboard = new JPanel(new BorderLayout(12, 12));
        dashboard.setOpaque(false);
        dashboard.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        JPanel header = new GlossyPanel();
        header.setLayout(new GridLayout(4, 1, 3, 3));
        header.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        JLabel title = new JLabel("QUIZ RESULT DASHBOARD", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel student = new JLabel(studentName + " | " + studentYear, SwingConstants.CENTER);
        student.setForeground(new Color(200, 235, 255));
        student.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel score = new JLabel("SCORE: " + correctCount + " / " + questions.length + " (" + percentage + "%)", SwingConstants.CENTER);
        score.setForeground(new Color(190, 240, 255));
        score.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel performanceLabel = new JLabel(performance + " | Correct: " + correctCount + " | Incorrect: " + incorrectCount, SwingConstants.CENTER);
        performanceLabel.setForeground(percentage >= 70 ? new Color(150, 255, 190) : new Color(255, 210, 140));
        performanceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        header.add(title);
        header.add(student);
        header.add(score);
        header.add(performanceLabel);
        JTextArea reviewArea = new JTextArea();
        reviewArea.setEditable(false);
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        reviewArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reviewArea.setForeground(new Color(235, 247, 255));
        reviewArea.setBackground(new Color(5, 20, 55));
        reviewArea.setMargin(new java.awt.Insets(15, 15, 15, 15));
        StringBuilder review = new StringBuilder("STUDENT: ").append(studentName).append("\nYEAR LEVEL: ").append(studentYear).append("\n\nFINAL SCORE: ").append(correctCount).append(" / ").append(questions.length).append(" (").append(percentage).append("%)\n\n========================================\n\n");
        for (int i = 0; i < questions.length; i++) {
            review.append("QUESTION ").append(i + 1).append("\n").append(questions[i]).append("\n\nCORRECT ANSWER: ").append(choices[i][correctAnswers[i]]).append("\nYOUR ANSWER: ");
            if (selectedAnswers[i] != -1 && selectedAnswers[i] < choices[i].length) review.append(choices[i][selectedAnswers[i]]);
            else review.append("No answer selected");
            review.append("\nRESULT: ").append(answeredCorrectly[i] ? "CORRECT" : "INCORRECT").append("\nEXPLANATION: ").append(explanations[i]).append("\n\n----------------------------------------\n\n");
        }
        reviewArea.setText(review.toString());
        reviewArea.setCaretPosition(0);
        JScrollPane scroll = new JScrollPane(reviewArea);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(90, 195, 255), 2));
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                thumbColor = new Color(40, 155, 245);
                trackColor = new Color(3, 20, 60);
            }
            @Override protected JButton createDecreaseButton(int orientation) {
                return zeroButton();
            }
            @Override protected JButton createIncreaseButton(int orientation) {
                return zeroButton();
            }
            private JButton zeroButton() {
                JButton b = new JButton();
                b.setPreferredSize(new java.awt.Dimension(0, 0));
                b.setMinimumSize(new java.awt.Dimension(0, 0));
                b.setMaximumSize(new java.awt.Dimension(0, 0));
                return b;
            }
        }
        );
        JButton retake = new GlossyButton("Retake Quiz");
        retake.addActionListener(e -> {
            current = 0;
            resetQuizData();
            startQuiz();
        }
        );
        JButton menu = new GlossyButton("Main Menu");
        menu.addActionListener(e -> showWelcomeScreen());
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        bottom.setOpaque(false);
        bottom.add(retake);
        bottom.add(menu);
        dashboard.add(header, BorderLayout.NORTH);
        dashboard.add(scroll, BorderLayout.CENTER);
        dashboard.add(bottom, BorderLayout.SOUTH);
        background.add(dashboard, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }
    private void showNoAnswerDialog() {
        javax.swing.JDialog dialog = new javax.swing.JDialog(this, "No Answer Selected", true);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        JPanel panel = new JPanel(new BorderLayout(12, 15)) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(15, 100, 190),
                0, getHeight(), new Color(3, 22, 70)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        }
        ;
        panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(105, 200, 255), 2),
        BorderFactory.createEmptyBorder(22, 30, 22, 30)));
        JLabel heading = new JLabel("NO ANSWER SELECTED", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 19));
        heading.setForeground(new Color(190, 235, 255));
        JLabel message = new JLabel("Please select an answer first.", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 15));
        message.setForeground(Color.WHITE);
        JButton okButton = new GlossyButton("OK");
        okButton.setPreferredSize(new java.awt.Dimension(110, 42));
        okButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 9));
        textPanel.setOpaque(false);
        textPanel.add(heading);
        textPanel.add(message);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setContentPane(panel);
        dialog.setSize(390, 190);
        dialog.setLocationRelativeTo(this);
        dialog.getRootPane().setDefaultButton(okButton);
        dialog.setVisible(true);
    }
    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (!hasSelection()) {
                showNoAnswerDialog();
                return;
            }
            playSound();
            if (quizTimer != null) quizTimer.stop();
            selectedAnswers[current] = getSelectedAnswer();
            answeredCorrectly[current] = checkAns();
            if (current == questions.length - 1) {
                showResultDashboard();
                return;
            }
            current++;
            setData();
        }
        if (e.getSource() == btnResult) showResultDashboard();
    }
    // Shared button style
    class GlossyButton extends JButton {
        private static final long serialVersionUID = 1L;
        private boolean hovered = false;
        public GlossyButton(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                    hovered = true;
                    repaint();
                }
                @Override public void mouseExited(java.awt.event.MouseEvent e) {
                    hovered = false;
                    repaint();
                }
            }
            );
        }
        @Override protected void paintComponent(Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            Color top = hovered ? new Color(100, 215, 255) : new Color(55, 175, 255);
            Color bottom = hovered ? new Color(0, 100, 230) : new Color(0, 55, 165);
            if (getModel().isPressed()) {
                top = new Color(0, 65, 150);
                bottom = new Color(0, 35, 100);
            }
            g2.setColor(new Color(0, 0, 0, 65));
            g2.fillRoundRect(2, 4, w - 4, h - 6, 22, 22);
            g2.setPaint(new GradientPaint(0, 0, top, 0, h, bottom));
            g2.fillRoundRect(2, 2, w - 4, h - 6, 22, 22);
            g2.setPaint(new GradientPaint(0, 3, new Color(255, 255, 255, 150), 0, h / 2, new Color(255, 255, 255, 0)));
            g2.fillRoundRect(5, 4, w - 10, h / 2, 18, 18);
            g2.setColor(new Color(190, 235, 255, 200));
            g2.drawRoundRect(2, 2, w - 5, h - 7, 22, 22);
            g2.dispose();
            super.paintComponent(g);
        }
    }
    // Answer option style
    class GlossyRadioButton extends JRadioButton {
        private static final long serialVersionUID = 1L;
        public GlossyRadioButton() {
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.WHITE);
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 10));
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        @Override protected void paintComponent(Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            Color top, bottom, border;
            if (isSelected()) {
                top = new Color(0, 145, 255, 225);
                bottom = new Color(0, 65, 190, 235);
                border = new Color(150, 230, 255);
            }
            else if (getModel().isRollover()) {
                top = new Color(80, 150, 210, 170);
                bottom = new Color(20, 75, 145, 200);
                border = new Color(130, 210, 255);
            }
            else {
                top = new Color(25, 100, 180, 75);
                bottom = new Color(5, 30, 75, 95);
                border = new Color(140, 210, 255, 110);
            }
            g2.setColor(new Color(0, 0, 0, 35));
            g2.fillRoundRect(2, 3, w - 4, h - 5, 18, 18);
            g2.setPaint(new GradientPaint(0, 0, top, 0, h, bottom));
            g2.fillRoundRect(1, 1, w - 3, h - 5, 18, 18);
            g2.setPaint(new GradientPaint(0, 2, new Color(255, 255, 255, 55), 0, h / 2, new Color(255, 255, 255, 0)));
            g2.fillRoundRect(5, 3, w - 10, h / 2, 14, 14);
            g2.setColor(border);
            g2.drawRoundRect(1, 1, w - 3, h - 5, 18, 18);
            g2.dispose();
            super.paintComponent(g);
        }
    }
    // Translucent card background
    class GlossyPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        public GlossyPanel() {
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(new Color(0, 0, 0, 45));
            g2.fillRoundRect(4, 5, w - 8, h - 9, 24, 24);
            g2.setPaint(new GradientPaint(0, 0, new Color(20, 145, 255, 105), 0, h, new Color(0, 20, 75, 135)));
            g2.fillRoundRect(1, 1, w - 3, h - 7, 24, 24);
            g2.setPaint(new GradientPaint(0, 2, new Color(255, 255, 255, 75), 0, h / 2, new Color(255, 255, 255, 0)));
            g2.fillRoundRect(5, 4, w - 10, h / 2, 20, 20);
            g2.setColor(new Color(105, 200, 255, 155));
            g2.drawRoundRect(1, 1, w - 3, h - 7, 24, 24);
            g2.dispose();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QUIZ_APP_BUSINESS_LAW_OBLIGATIONS_AND_CONTRACTS());
    }
    class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final Image backgroundImage;
        public BackgroundPanel(URL imageURL) {
            backgroundImage = new ImageIcon(imageURL).getImage();
        }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
