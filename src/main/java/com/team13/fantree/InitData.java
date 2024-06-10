// package com.team13.fantree;
//
// import java.util.Arrays;
// import java.util.List;
//
// import com.team13.fantree.entity.Comment;
// import com.team13.fantree.repository.CommentRepository;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.team13.fantree.entity.Post;
// import com.team13.fantree.entity.User;
// import com.team13.fantree.repository.PostRepository;
// import com.team13.fantree.repository.UserRepository;
//
// import jakarta.annotation.PostConstruct;
// import lombok.RequiredArgsConstructor;
//
// @Component
// @RequiredArgsConstructor
// public class InitData {
//
// 	private final UserRepository userRepository;
// 	private final PostRepository postRepository;
// 	private final PasswordEncoder passwordEncoder;
//     private final CommentRepository commentRepository;
//
//     @Transactional
// 	@PostConstruct
// 	public void init() {
// 		User user1 = User.builder()
// 			.username("MODUtripleSS1")
// 			.name("윤서연")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("tripleS1@gmail.com")
// 			.headline("잘 부탁해 WAV")
// 			.build();
// 		User user2 = User.builder()
// 			.username("MODUtripleSS2")
// 			.name("정혜린")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("tripleS2@gmail.com")
// 			.headline("잘 부탁해 WAV")
// 			.build();
// 		User user3 = User.builder()
// 			.username("MODUtripleSS3")
// 			.name("이지우")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("tripleS3@gmail.com")
// 			.headline("잘 부탁해 WAV")
// 			.build();
// 		User user4 = User.builder()
// 			.username("MODUtripleSS4")
// 			.name("김채연")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("tripleS4@gmail.com")
// 			.headline("잘 부탁해 WAV")
// 			.build();
//		 User user5 = User.builder()
//				 .username("MODUtripleSS5")
//				 .name("김유연")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS5@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user6 = User.builder()
//				 .username("MODUtripleSS6")
//				 .name("김수민")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS6@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user7 = User.builder()
//				 .username("MODUtripleSS7")
//				 .name("김나경")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS7@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user8 = User.builder()
//				 .username("MODUtripleSS8")
//				 .name("공유빈")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS8@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user9 = User.builder()
//				 .username("MODUtripleSS9")
//				 .name("카에데")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS9@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user10 = User.builder()
//				 .username("MODUtripleSS10")
//				 .name("서다현")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS10@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user11 = User.builder()
//				 .username("MODUtripleSS11")
//				 .name("코토네")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS11@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user12 = User.builder()
//				 .username("MODUtripleSS12")
//				 .name("곽연지")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS12@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user13 = User.builder()
//				 .username("MODUtripleSS13")
//				 .name("니엔")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS13@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user14 = User.builder()
//				 .username("MODUtripleSS14")
//				 .name("박소현")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS14@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user15 = User.builder()
//				 .username("MODUtripleSS15")
//				 .name("신위")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS15@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user16 = User.builder()
//				 .username("MODUtripleSS16")
//				 .name("마유")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS16@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user17 = User.builder()
//				 .username("MODUtripleSS17")
//				 .name("린")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS17@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user18 = User.builder()
//				 .username("MODUtripleSS18")
//				 .name("주빈")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS18@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user19 = User.builder()
//				 .username("MODUtripleSS19")
//				 .name("정하연")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS19@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user20 = User.builder()
//				 .username("MODUtripleSS20")
//				 .name("박시온")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS20@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user21 = User.builder()
//				 .username("MODUtripleSS21")
//				 .name("김채원")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS21@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user22 = User.builder()
//				 .username("MODUtripleSS22")
//				 .name("설린")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS22@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user23 = User.builder()
//				 .username("MODUtripleSS23")
//				 .name("서아")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS23@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//		 User user24 = User.builder()
//				 .username("MODUtripleSS24")
//				 .name("지연")
//				 .password(passwordEncoder.encode("1234"))
//				 .email("tripleS24@gmail.com")
//				 .headline("잘 부탁해 WAV")
//				 .build();
//
// 		List<User> userList = Arrays.asList(user1, user2, user3, user4);
//
// 		userRepository.saveAll(userList);
//
// 		List<String> stringList = Arrays.asList(
//				 "WAV들 배지전쟁 잘 봤어? 내가 X맨이다~! -윤서연-",
//				"안뇽! 나는 혜린이얌 우리 앞으로 재밌게 소통하자! -정혜린-",
//				"아까 채연언니 폰 뺐어서 내가 글 올렸거든 ㅋㅋㅋ 다들 속았어? -이지우-",
// 			"채연이의 오.노.추 'Knew Better / Forever Boy' - Ariana Grande" ,
// 			"닭강정 먹음 -김유연-",
// 			"WAV들 여행갈 때 유연 언니 5명 vs 소현언니 5명 누구랑 갈거야?? -김수민-",
// 			"오늘 상탔어!!!!!!!! WAV에게 고마운게 너무 많아. 음방 1위해서 라이브하고 팬들과 인사하고 티비에서 나오는 이루고 싶었던 것들 이루게해줘서 고마워 ㅠㅠ-김나경-",
// 			"유연 언니랑 밥 먹으러 갈건데 점메추 해줄사람? -공유빈-",
// 			"(배지전쟁) 안봤냐?! -카에데-",
// 			"웨이브 안녕 나 소다야 웨이브가 있기에 우리 트리플에스가 더더욱 빛날 수 있게 되는 것 같아 나에게 이런 소중한 감정을 알려줘서 정말 고마워 다들 잘자 뿅-서다현-",
// 			"코토네 네버 다이 -코토네-",
// 			"연지는_밥_먹는중 냠냠냠 -곽연지-",
// 			"강아지보다 더 강아지 같은 니엔이다! -니엔-",
// 			"웨이브 1위 축하해 ! 1위를 할 수 있게 만들어 준 우리 웨이브 진심으로 너무 고마워 이건 너도 함께 받은 상이니까 같이 뿌듯해해줘 -박소현-",
// 			"안녕하세요 신위에요. 오랜 기다림 끝에 드디어 만났네요! 앞으로 잘 부탁해요>_< -신위-",
// 			"항상 내 마음이 가는 곳은 웨이브라는걸 잊지 말라줘 이건 시작이고 앞으로도 계속 함께 달려가자!! -마유-",
// 			"수트 린 어때?-린-",
// 			"다들 인기가요 샌드위치 먹어봤어? -주빈-",
// 			"육각단 오늘도 화이팅! 육각! -정하연-",
// 			"아까 올라온 ASMR 본사람?! -박시온-",
// 			"한결 자연스러워진 시선 처리-김채원-",
// 			"WAV들 รักคุณ -설린-",
// 			"안녕 안녕 안녕하십니까 민족고대 29학번 서아입니다 !! -서아-",
// 			"WAV와 함께 있다면 이젠 무서울 것 없지! -지연-"
// 		);
//
//         List<String> commentList = Arrays.asList(
//                 "진짜 여긴 24명이 다 개그캐인듯"
//         );
//
// 		for (int i = 0; i < stringList.size(); i++) {
//             Post post = postRepository.save(new Post(stringList.get(i), userList.get(i % 24)));
//             commentRepository.save(new Comment(post,userList.get(i % 24), commentList.get(0)));
// 		}
//
//
//
//
// 	}
// }
