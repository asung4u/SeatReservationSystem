package program;
/**
 * 좌석 에약을 관리하는 클래스입니다.
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
import java.util.HashMap;
import java.util.Map;

public class ReservationSystem {
	// 전체 좌석 정보 hashmap
	private Map<Integer, Seat> seats = new HashMap<>();
	
	// 학생별 예약 기록
	private Map<String, Integer> studentReservations = new HashMap<>();
	
	// 학생부의 등록된 학생 정보관련 함수
	private AttendanceManager attendanceManager;
	
	// 총 좌석 갯수(갯수에 따라 변경)
	private static final int TOTAL_SEATS = 50;
	
	
	// 초기 함수-좌석 정보 입력
	public ReservationSystem(String attendanceFilePath) {
		
		for (int i = 1; i <= TOTAL_SEATS; i++) {
			seats.put(i, new Seat(i));
		}
		
		attendanceManager = new AttendanceManager(attendanceFilePath);
	}
	
	// 좌석 예약 함수
	public boolean reserveSeat(int seatNumber, String studentId) {

		if (!attendanceManager.isRegistered(studentId)) {
            return false; // 출석부에 등록되지 않은 학생
        }
		
		// 이미 예약한 학생인지 확인
        if (studentReservations.containsKey(studentId)) {
            return false; // 이미 예약한 학생은 예약 불가
        }
		
		// 좌석 정보 불어오기
        Seat seat = seats.get(seatNumber);
        
        // 잘못된 좌석이 아니고, 예약유무 확인
        if (seat != null && !seat.isReserved()) {
        	// 좌석 예약
            seat.reserve(studentId);
            // 학생의 예약 기록 추가
            studentReservations.put(studentId, seatNumber); 
            // 에약 성공
            return true; 
        }
		
		// 예약 실패
		return false;
	}
	
	
	// 좌석 정보 불러오기
	public Map<Integer, Seat> getSeats() {
        return seats;
    }
	
	
	// 학생이 예약한 좌석 번호 가져오기
	public Map<String, Integer> getStudentReservations() {
        return studentReservations;
    }
	
}
