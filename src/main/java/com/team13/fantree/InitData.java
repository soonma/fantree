// package com.team13.fantree;
//
// import java.util.Arrays;
// import java.util.List;
//
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
//
// 	@Transactional
// 	@PostConstruct
// 	public void init() {
// 		User user1 = User.builder()
// 			.username("ckehqja")
// 			.name("차도범")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("ckehqja@gmail.com")
// 			.headline("열심히 합시다")
// 			.build();
// 		User user2 = User.builder()
// 			.username("dltnsah")
// 			.name("이순모")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("dltnsah@gmail.com")
// 			.headline("잘부탁드립니다.")
// 			.build();
// 		User user3 = User.builder()
// 			.username("dltldud")
// 			.name("이시영")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("dltldud@gmail.com")
// 			.headline("잘 부탁드려요~!")
// 			.build();
// 		User user4 = User.builder()
// 			.username("skagus")
// 			.name("남현")
// 			.password(passwordEncoder.encode("1234"))
// 			.email("skagus@gmail.com")
// 			.headline("화티잉!")
// 			.build();
//
// 		List<User> userList = Arrays.asList(user1, user2, user3, user4);
//
// 		userRepository.saveAll(userList);
//
// 		List<String> stringList = Arrays.asList(
// 			"인생은 모험이거나, 아무것도 아니다.",
// 			"Life is either a daring adventure or nothing at all.",
// 			"미래는 꿈의 아름다움을 믿는 사람들의 것이다.",
// 			"The future belongs to those who believe in the beauty of their dreams.",
// 			"네가 세상에서 보고 싶은 변화가 되어라",
// 			"당신의 시간은 한정되어 있습니다. 다른 사람의 삶을 사느라 시간을 낭비하지 마십시오.",
// 			"Your time is limited, so don't waste it living someone else's life.",
// 			"윈스턴 처칠 (Winston Churchill):",
// 			"성공은 최종적인 것이 아니고 실패는 치명적인 것이 아니다. 중요한 것은 계속해 나가는 용기다.",
// 			"Success is not final, failure is not fatal: It is the courage to continue that counts.",
// 			"20년 후에는 당신이 하지 않은 일들에 대해 더 후회할 것이다. 그러니 밧줄을 풀고 안전한 항구에서 벗어나 항해를 떠나라. 탐험하고, 꿈꾸고, 발견하라.",
// 			"Twenty years from now you will be more disappointed by the things you didn’t do than by the ones you did do. So throw off the bowlines. Sail away from the safe harbor. Catch the trade winds in your sails. Explore, Dream, Discover.",
// 			"가장 큰 실수는 평생 실수를 두려워하는 것이다.", "The greatest mistake you can make in life is to be continually fearing you will make one.",
// 			"행복은 준비된 사람에게 찾아온다.", "Happiness is not something ready made. It comes from your own actions.",
// 			"상상력은 지식보다 더 중요하다.", "Imagination is more important than knowledge.",
// 			"위대한 일은 동기에서 나오지 않고, 집념에서 나온다.", "Doing the best at this moment puts you in the best place for the next moment.",
// 			"우리는 위대한 일을 할 수 없다. 다만 위대한 사랑으로 작은 일들을 할 수 있을 뿐이다.", ("We can do no great things, only small things with great love."),
// 			"삶은 자신을 찾는 것이 아니라 자신을 만드는 것이다.", ("Life isn't about finding yourself. Life is about creating yourself."),
// 			"일반적인 삶은 결코 평범하지 않다.", ("To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment."),
// 			"내면의 목소리를 따르라. 그것은 네 진정한 자아의 소리다.", ("Go confidently in the direction of your dreams. Live the life you have imagined."),
// 			"삶은 당신이 다른 계획을 세우느라 바쁠 때 일어나는 일들이다.", ("Life is what happens to you while you're busy making other plans."),
// 			"살아야 할 이유가 있는 사람은 어떤 어려움도 견딜 수 있다.", ("He who has a why to live can bear almost any how."),
// 			"중요한 것은 삶의 길이가 아니라 삶의 깊이다.", ("The only way to deal with an unfree world is to become so absolutely free that your very existence is an act of rebellion."),
// 			"내가 배운 한 가지는 사람들은 당신이 한 말을 잊고, 당신이 한 행동도 잊지만, 당신이 그들에게 어떻게 느끼게 했는지는 절대 잊지 않는다는 것이다.",
// 			("I've learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel."),
// 			"힘든 시기는 오래가지 않지만, 강인한 사람은 오래간다.", ("Tough times never last, but tough people do."),
// 			"미래를 예측하는 가장 좋은 방법은 그것을 창조하는 것이다.", "The best way to predict your future is to create it.",
// 			"영혼이 도약할 때, 주저하지 마라.", ("When you are inspired by some great purpose, some extraordinary project, all your thoughts break their bonds."),
// 			"당신이 비행할 수 없다면 달려라. 달릴 수 없다면 걸어라. 걸을 수 없다면 기어가라. 무엇을 하든 계속 나아가라.",
// 			("If you can't fly then run, if you can't run then walk, if you can't walk then crawl, but whatever you do you have to keep moving forward."),
// 			"목표가 없는 사람은 절대로 아무 일도 시작하지 않는다.", ("What you get by achieving your goals is not as important as what you become by achieving your goals.")
// 		);
//
// 		for (int i = 0; i < stringList.size(); i++) {
// 			postRepository.save(new Post(stringList.get(i), userList.get(i % 4)));
// 		}
// 	}
// }
