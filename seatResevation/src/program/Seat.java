package program;
/**
 * 좌석 정보를 관리하는 클래스입니다.
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
public class Seat {
	
	// 좌석 번호
	private int seatNumber;
	
	// 예약한 학번
	private String reservedId;
	
	
	// 초기 설정
	public Seat(int seatNumber) {
		this.seatNumber = seatNumber;
		this.reservedId = null;
	}
	
	// 예약 확인 유무
	public boolean isReserved() {
		return reservedId != null;
	}
	
	// 해당 학번을 저장, 예약 성공
	public void reserve(String studentId) {
		this.reservedId = studentId;
	}
	
	// 예약한 학번 불러오기
	public String getReservedId() {
		return reservedId;
	}
	
}
