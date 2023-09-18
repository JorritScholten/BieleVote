package com.bielevote.backend;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    NewsArticleRepository newsArticleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedUsers();
        seedProjects();
        seedNewsArticles();
    }

    private void seedUsers() {
        userRepository.saveAllAndFlush(List.of(
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("citizen1")
                        .password(passwordEncoder.encode("123"))
                        .legalName("John Smith")
                        .phone("123")
                        .build(),
                User.builder()
                        .role(UserRole.ADMINISTRATOR)
                        .username("admin1")
                        .password(passwordEncoder.encode("admin"))
                        .legalName("Jack Admin")
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal1")
                        .legalName("Jane Doe")
                        .password(passwordEncoder.encode("123"))
                        .build()
        ));
    }

    private void seedProjects() {
        projectRepository.saveAllAndFlush(List.of(
                Project.builder()
                        .title("Park")
                        .content("new park")
                        .build(),
                Project.builder()
                        .title("Swimming pool")
                        .content("new swimming pool")
                        .build()
        ));
        System.out.println(projectRepository.count() + " projects seeded");
    }

    private void seedNewsArticles() {
        newsArticleRepository.saveAllAndFlush(List.of(
                NewsArticle.builder()
                        .title("Back to School: Preparing for a Successful Year Ahead")
                        .summary("August and September mark the beginning of a new school year. Get ready with our education tips and resources. Help your child thrive academically!")
                        .content("Back-to-school advice, study tips, and educational resources for parents and students. Ensure a smooth transition into the academic year. Receive helpful resources via our app or email.")
                        .author("Jennifer Smith")
                        .datePlaced(LocalDateTime.now())
                        .category(Category.EDUCATION)
                        .build(),
                NewsArticle.builder()
                        .title("Late Summer Sports Spectacular")
                        .summary("Get ready for an action-packed August and September in the world of sports. From major tournaments to local games, stay in the game!")
                        .content("Sports news, athlete interviews, and event schedules. Stay updated on your favorite sports. Receive sports updates via our app or email.")
                        .author("Mike Miller")
                        .datePlaced(LocalDateTime.now().plusMinutes(1))
                        .category(Category.SPORTS)
                        .build(),
                NewsArticle.builder()
                        .title("Sustainability Initiatives for a Greener August and September")
                        .summary("Join the eco-friendly movement this late summer. Discover sustainability tips, green projects, and environmental news. Let's protect our planet together!")
                        .content("Environmental initiatives, green living advice, and conservation updates. Take part in preserving our environment. Receive eco-friendly tips via our app or email.")
                        .author("Linda EcoWarrior")
                        .datePlaced(LocalDateTime.now().plusMinutes(2))
                        .category(Category.SUSTAINABILITY)
                        .build(),
                NewsArticle.builder()
                        .title("Exciting Tech Innovations on the Horizon")
                        .summary("Stay tuned for groundbreaking tech developments. August and September promise to bring new gadgets and game-changing inventions. Be ready for the future!")
                        .content("Cutting-edge technology insights, product releases, and upcoming tech events. Don't miss out on the latest tech trends. Get updates through our app or email.")
                        .author("Alex Williams")
                        .datePlaced(LocalDateTime.now().plusMinutes(3))
                        .category(Category.TECHNOLOGY)
                        .build(),
                NewsArticle.builder()
                        .title("Stay Healthy this Summer with Our Tips")
                        .summary("Beat the summer heat while staying fit and healthy. Discover our expert advice for a vibrant August and September. Your well-being matters!")
                        .content("Wellness guidelines, fitness tips, and nutrition advice for the summer months. Prioritize your health this season. Receive expert tips via our app or email.")
                        .author("Dr. Sarah Johnson")
                        .datePlaced(LocalDateTime.now().plusMinutes(4))
                        .category(Category.HEALTH)
                        .build(),
                NewsArticle.builder()
                        .title("Navigating Financial Markets in Late Summer")
                        .summary("Keep an eye on financial trends in August and September. Insights and strategies for investors. Make informed decisions in the market.")
                        .content("Market analysis, investment strategies, and economic forecasts. Secure your financial future with our guidance. Receive updates through our app or email.")
                        .author("John Financialson")
                        .datePlaced(LocalDateTime.now().plusMinutes(5))
                        .category(Category.ECONOMY)
                        .build(),
                NewsArticle.builder()
                        .title("Explore Exciting Destinations for Late Summer Adventures")
                        .summary("Plan your late summer getaway with our travel recommendations. August and September promise thrilling adventures. Start packing your bags!")
                        .content("Travel tips, destination highlights, and travel deals for the upcoming months. Embark on unforgettable journeys. Receive travel inspiration via our app or email.")
                        .author("Lindsay Traveler")
                        .datePlaced(LocalDateTime.now().plusMinutes(6))
                        .category(Category.CULTURE)
                        .build()
        ));
        System.out.println(newsArticleRepository.count() + " news articles seeded...");
    }
}
