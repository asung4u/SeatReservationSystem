package program;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 실행 함수와 GUI 프로그램을 작성한 클래스 입니다. 
 * 
 * @author Kim Hee Jin (hjkim010509@gmail.com)
 * @version 1.0
 * @since 1.0
 * 
 * @created 2024-12-26
 * @lastModified 2024-12-26
 * 
 * @changelog
 * <ul>
 * 		<li>2024-12-26: 최초 생성(Kim Hee Jin)</li>
 * </ul>
 */

public class MainFrame extends JFrame{
	// 참조
	private ReservationSystem reservationSystem;
	
	public MainFrame(String attendanceFilePath) {
		/**
		 * Swing을 이용한 GUI 프로그램 함수입니다.
		 * 
		 * @author Kim Hee Jin (hjkim010509@gmail.com)
		 * @param attendanceFilePath
		 * 
		 * @created 2024-12-26
		 * @lastModified 2024-12-26
		 * 
		 * @changelog
		 * <ul>
		 * 		<li>2024-12-26: 최초 생성(Kim Hee Jin)</li>
		 * </ul>
		 */
		reservationSystem = new ReservationSystem(attendanceFilePath);
		
		setTitle("좌석 예약 시스템");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600, 400);
	    
	 // 레이아웃 설정
        setLayout(new BorderLayout());

        // 상단 패널: 좌석 예약
        JPanel reservePanel = new JPanel();
        reservePanel.setLayout(new FlowLayout());

        JLabel seatLabel = new JLabel("좌석 번호:");
        JTextField seatField = new JTextField(5);

        JLabel studentLabel = new JLabel("학번:");
        JTextField studentField = new JTextField(10);

        JButton reserveButton = new JButton("예약");

        reservePanel.add(seatLabel);
        reservePanel.add(seatField);
        reservePanel.add(studentLabel);
        reservePanel.add(studentField);
        reservePanel.add(reserveButton);

        add(reservePanel, BorderLayout.NORTH);

        // 중앙 패널: 예약 현황
        JTextArea seatStatusArea = new JTextArea();
        seatStatusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(seatStatusArea);
        add(scrollPane, BorderLayout.CENTER);

        // 하단 버튼: 예약 현황 갱신
        JButton refreshButton = new JButton("예약 현황 갱신");
        add(refreshButton, BorderLayout.SOUTH);

        // 예약 버튼 동작
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int seatNumber = Integer.parseInt(seatField.getText());
                    String studentId = studentField.getText();

                    if (!reservationSystem.getStudentReservations().containsKey(studentId)) {
                        if (reservationSystem.reserveSeat(seatNumber, studentId)) {
                            JOptionPane.showMessageDialog(MainFrame.this, 
                                    "좌석 " + seatNumber + " 예약 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.this, 
                                    "예약 실패! 좌석이 이미 예약되었거나 유효하지 않습니다.", 
                                    "실패", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, 
                                "이미 예약한 학생입니다. 다른 좌석을 예약할 수 없습니다.", 
                                "예약 불가", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                            "좌석 번호는 숫자여야 합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
     // 예약 현황 갱신 버튼 동작
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder status = new StringBuilder();
                for (Map.Entry<Integer, Seat> entry : reservationSystem.getSeats().entrySet()) {
                    int seatNumber = entry.getKey();
                    Seat seat = entry.getValue();
                    status.append("좌석 ").append(seatNumber).append(": ")
                          .append(seat.isReserved() ? "예약됨 (" + seat.getReservedId() + ")" : "사용 가능")
                          .append("\n");
                }
                seatStatusArea.setText(status.toString());
            }
        });

        setVisible(true);
    }
	

	public static void main(String[] args) {
		
		/**
		 * 실행 함수입니다.
		 * 
		 * @author Kim Hee Jin (hjkim010509@gmail.com)
		 * 
		 * @created 2024-12-26
		 * @lastModified 2024-12-26
		 * 
		 * @changelog
		 * <ul>
		 * 		<li>2024-12-26: 최초 생성(Kim Hee Jin)</li>
		 * </ul>
		 */
		
		// 출석부 파일 경로 입력
        String attendanceFilePath = "D:\\middle_test_java\\seatResevation\\src\\program\\ai_students.csv";
        
        // GUI 실행
        SwingUtilities.invokeLater(() -> new MainFrame(attendanceFilePath));

	}

}
