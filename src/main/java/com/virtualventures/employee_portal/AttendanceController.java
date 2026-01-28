package com.virtualventures.employee_portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

	private final AttendanceRepository attendanceRepository;

	@Autowired
	public AttendanceController(AttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	}

	@GetMapping
	public List<Attendance> list() {
		return attendanceRepository.findAll();
	}

	@PostMapping
	public Attendance create(@RequestBody Attendance attendance) {
		if (attendance == null) {
			throw new IllegalArgumentException("Attendance cannot be null");
		}
		
		// Validate email domain - only @virtualventures.com allowed
		String email = attendance.getEmpEmail();
		if (email == null || !email.endsWith("@virtualventures.com")) {
			throw new IllegalArgumentException("Only @virtualventures.com email addresses are allowed for registration");
		}
		
		return attendanceRepository.save(attendance);
	}

}
