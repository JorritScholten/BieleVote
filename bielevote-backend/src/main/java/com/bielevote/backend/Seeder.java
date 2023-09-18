package com.bielevote.backend;

import com.bielevote.backend.news.Category;
import com.bielevote.backend.news.NewsArticle;
import com.bielevote.backend.news.NewsArticleRepository;
import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.project.ProjectStatus;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import com.bielevote.backend.user.rewardpoint.RewardPoint;
import com.bielevote.backend.user.rewardpoint.RewardPointRepository;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private NewsArticleRepository newsArticleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RewardPointRepository rewardPointRepository;
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
        rewardPointRepository.saveAllAndFlush(List.of(
                RewardPoint.builder()
                        .amount(3)
                        .date(LocalDateTime.now())
                        .reason(TransactionReason.PROJECT_ACCEPTED)
                        .user(userRepository.findByUsername("citizen1").orElseThrow())
                        .build(),
                RewardPoint.builder()
                        .amount(-1)
                        .date(LocalDateTime.now())
                        .reason(TransactionReason.REDEEMED_REWARD)
                        .user(userRepository.findByUsername("citizen1").orElseThrow())
                        .build(),
                RewardPoint.builder()
                        .amount(Integer.MIN_VALUE)
                        .date(LocalDateTime.ofEpochSecond(1,0, ZoneOffset.UTC))
                        .reason(TransactionReason.ADMINISTRATOR_CHANGE)
                        .user(userRepository.findByUsername("municipal1").orElseThrow())
                        .build()
        ));
    }

    private void seedProjects() {
        projectRepository.saveAllAndFlush(List.of(
                Project.builder()
                        .title("Greening Our Urban Oasis")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now())
                        .status(ProjectStatus.PROPOSED)
                        .content("This proposal aims to transform our city into a greener, more sustainable urban environment. By planting more trees and creating urban green spaces, we can combat air pollution, provide shade during hot summers, and enhance the overall well-being of our residents. Additionally, this proposal includes initiatives for community gardens, promoting local agriculture, and increasing access to fresh, healthy food.")
                        .build(),
                Project.builder()
                        .title("Smart Transportation Network")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(1))
                        .status(ProjectStatus.ACTIVE)
                        .content("This initiative focuses on improving our city's transportation infrastructure. It aims to create a smart, efficient, and eco-friendly transportation system through investments in public transit, bike lanes, and pedestrian-friendly streets. ")
                        .build(),
                Project.builder()
                        .title("Education for All")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(2))
                        .status(ProjectStatus.OLD)
                        .content("This proposal seeks to enhance access to quality education for all residents. It includes plans for building new schools, improving existing ones, and increasing funding for educational programs. By ensuring that every child has access to a great education, we can create a brighter future for our city.")
                        .build(),
                Project.builder()
                        .title("Clean Energy Revolution")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(3))
                        .status(ProjectStatus.ACCEPTED)
                        .content("This proposal advocates for a transition to clean and renewable energy sources. It includes plans to install solar panels on public buildings, promote energy-efficient practices, and incentivize renewable energy adoption among businesses and residents")
                        .build(),
                Project.builder()
                        .title("Youth Empowerment Centers")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(4))
                        .status(ProjectStatus.REJECTED)
                        .content("This initiative aims to provide safe spaces and opportunities for the city's youth. The proposal includes plans for building youth centers with educational, recreational, and counseling services. By investing in our youth, we can reduce crime rates and foster future leaders.")
                        .build(),
                Project.builder()
                        .title("Historical Preservation and Cultural Revival")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(5))
                        .status(ProjectStatus.PROPOSED)
                        .content("This proposal focuses on preserving our city's historical landmarks and promoting its rich cultural heritage. It includes plans for restoring historical buildings, creating cultural festivals, and supporting local artists and artisans.")
                        .build(),
                Project.builder()
                        .title("Housing Affordability Initiative")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(6))
                        .status(ProjectStatus.ACTIVE)
                        .content("This proposal addresses the city's housing affordability crisis. It includes measures to increase affordable housing units, rent control policies, and housing assistance programs.")
                        .build(),
                Project.builder()
                        .title("Clean Water for All")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(7))
                        .status(ProjectStatus.OLD)
                        .content("This proposal advocates for improved access to clean and safe drinking water. It includes plans for upgrading the city's water infrastructure, removing lead pipes, and implementing water quality monitoring systems.")
                        .build(),
                Project.builder()
                        .title("Tech Innovation Hub")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(8))
                        .status(ProjectStatus.ACCEPTED)
                        .content("This initiative aims to establish a technology innovation hub within the city. The hub would provide resources and support for startups and tech companies, fostering economic growth and job creation.")
                        .build(),
                Project.builder()
                        .title("Community Policing and Public Safety")
                        .author(userRepository.findByUsername("citizen1").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(9))
                        .status(ProjectStatus.REJECTED)
                        .content("This proposal focuses on improving community policing efforts and enhancing overall public safety. It includes measures for community policing training, the establishment of neighborhood watch programs, and increased investment in mental health crisis response teams.")
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
