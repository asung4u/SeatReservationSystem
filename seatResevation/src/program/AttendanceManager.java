package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * 해당 강의실을 이용하는 학생 정보를 불러오는 클래스 입니다.
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

public class AttendanceManager {
	// 학생부에 있는 학생 정보를 저장하는 HashSet 입니다.
	private HashSet<String> registeredStudents = new HashSet<>();
	
	// 파일 불러오기
	public AttendanceManager(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            
            // 첫 줄 헤더 제거
            br.readLine();
            
            // 한 줄씩 입력
            while ((line = br.readLine()) != null) {
            	
                String[] parts = line.split(",", 2);
                if (parts.length > 0) {
                    registeredStudents.add(parts[0].trim()); // 학번 추가
                }
            }
        } catch (IOException e) {
            System.out.println("출석부 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
	
	// 해당 학생이 학생부에 등록되어있는지 확인하는 함수
	 public boolean isRegistered(String studentId) {
	        return registeredStudents.contains(studentId);
	    }
}
