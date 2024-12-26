package program;
/**
 * 학생의 개인정보를 관리하는 클래스입니다.
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
public class Student {
	// 학번
	private String studentId;
	
	// 학번 입력
	public Student(String studentId) {
		
		this.studentId = studentId;
	}
	
	// 학번 불러오기
	public String getStudentId() {
		
		return studentId;
	}
}
