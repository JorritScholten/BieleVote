package com.bielevote.backend;

import com.bielevote.backend.news.Category;
import com.bielevote.backend.news.NewsArticle;
import com.bielevote.backend.news.NewsArticleRepository;
import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.project.ProjectStatus;
import com.bielevote.backend.reward_shop.Reward;
import com.bielevote.backend.reward_shop.RewardRepository;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import com.bielevote.backend.user.rewardpoint.RewardPoint;
import com.bielevote.backend.user.rewardpoint.RewardPointRepository;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import lombok.RequiredArgsConstructor;
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
    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;
    private final RewardPointRepository rewardPointRepository;
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedUsers();
        seedProjects();
        seedNewsArticles();
        seedRewards();
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
                        .role(UserRole.CITIZEN)
                        .username("WilliamSmith123")
                        .password(passwordEncoder.encode("123"))
                        .legalName("William Smith")
                        .phone("1234567")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("ElizabethJohnson456")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Elizabeth Johnson")
                        .phone("2345815")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("JamesDavis789")
                        .password(passwordEncoder.encode("123"))
                        .legalName("James Davis")
                        .phone("3456321")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("SarahBrown101")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Sarah Brown")
                        .phone("4567937")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("JohnWilson202")
                        .password(passwordEncoder.encode("123"))
                        .legalName("John Wilson")
                        .phone("1836491")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("EmilyTaylor303")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Emily Taylor")
                        .phone("5678905")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("ThomasAnderson404")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Thomas Anderson")
                        .phone("2345673")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("MaryWhite505")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Mary White")
                        .phone("8901234")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("RobertJones606")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Robert Jones")
                        .phone("8901239")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("CharlotteClark707")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Charlotte Clark")
                        .phone("9876543")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("BenjaminHarris808")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Benjamin Harris")
                        .phone("4567890")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("OliviaMiller909")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Olivia Miller")
                        .phone("7654321")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("SamuelThompson010")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Samuel Thompson")
                        .phone("2345678")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("AmeliaMoore111")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Amelia Moore")
                        .phone("8765432")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("DavidTurner212")
                        .password(passwordEncoder.encode("123"))
                        .legalName("David Turner")
                        .phone("3456789")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("SophiaCarter313")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Sophia Carter")
                        .phone("6543210")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("MatthewHall414")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Matthew Hall")
                        .phone("4321098")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("EmmaLewis515")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Emma Lewis")
                        .phone("8765439")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("DanielParker616")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Daniel Parker")
                        .phone("5678901")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("GraceWalker717")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Grace Walker")
                        .phone("3456780")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("MichaelBennett818")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Michael Bennett")
                        .phone("6789012")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("LilyEvans919")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Lily Evans")
                        .phone("8901234")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("ChristopherReed020")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Christopher Reed")
                        .phone("1238901")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("AbigailMartin121")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Abigail Martin")
                        .phone("7890123")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("AndrewMurphy222")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Andrew Murphy")
                        .phone("2345670")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("KatherineGreen323")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Katherine Green")
                        .phone("8901235")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("JosephMitchell424")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Joseph Mitchell")
                        .phone("4567891")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("HannahKing525")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Hannah King")
                        .phone("5678902")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("NicholasAdams626")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Nicholas Adams")
                        .phone("6789013")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("LucyHayes727")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Lucy Hayes")
                        .phone("3456781")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("AlexanderNelson828")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Alexander Nelson")
                        .phone("9012345")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("EleanorWright929")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Eleanor Wright")
                        .phone("7890124")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("RichardBrooks030")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Richard Brooks")
                        .phone("1234568")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("VictoriaPrice131")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Victoria Price")
                        .phone("8901236")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("CharlesRogers232")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Charles Rogers")
                        .phone("6789014")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("IsabellaScott333")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Isabella Scott")
                        .phone("3456782")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("JonathanPhillips434")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Jonathan Phillips")
                        .phone("5678903")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("GraceThomas535")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Grace Thomas")
                        .phone("9012346")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("SamuelCooper636")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Samuel Cooper")
                        .phone("7890125")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("RebeccaFoster737")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Rebecca Foster")
                        .phone("2345671")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("DanielWood838")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Daniel Wood")
                        .phone("8901237")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("CarolineBennett939")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Caroline Bennett")
                        .phone("6789015")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("WilliamCarter040")
                        .password(passwordEncoder.encode("123"))
                        .legalName("William Carter")
                        .phone("3456783")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("JuliaTurner141")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Julia Turner")
                        .phone("5678904")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("BenjaminParker242")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Benjamin Parker")
                        .phone("9012347")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("LydiaBaker343")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Lydia Baker")
                        .phone("7890126")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("MatthewWright444")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Matthew Wright")
                        .phone("2345672")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("AnnaJohnson545")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Anna Johnson")
                        .phone("8901238")
                        .build(),
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("HenryAdams646")
                        .password(passwordEncoder.encode("123"))
                        .legalName("Henry Adams")
                        .phone("6789016")
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
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal2")
                        .legalName("Alexander Sterling")
                        .password(passwordEncoder.encode("123"))
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal3")
                        .legalName("Isabella Monroe")
                        .password(passwordEncoder.encode("123"))
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal4")
                        .legalName("Sebastian Hawthorne")
                        .password(passwordEncoder.encode("123"))
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal5")
                        .legalName("Victoria Kensington")
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
                        .date(LocalDateTime.ofEpochSecond(1, 0, ZoneOffset.UTC))
                        .reason(TransactionReason.ADMINISTRATOR_CHANGE)
                        .user(userRepository.findByUsername("municipal1").orElseThrow())
                        .build(),
                RewardPoint.builder()
                        .amount(3)
                        .date(LocalDateTime.now().minusMonths(2))
                        .reason(TransactionReason.VOTED_ON_PROJECT)
                        .user(userRepository.findByUsername("admin1").orElseThrow())
                        .build(),
                RewardPoint.builder()
                        .amount(2)
                        .date(LocalDateTime.now().minusWeeks(2))
                        .reason(TransactionReason.VOTED_ON_PROJECT)
                        .user(userRepository.findByUsername("municipal1").orElseThrow())
                        .build()
        ));
    }

    private void seedProjects() {
        projectRepository.saveAllAndFlush(List.of(
                Project.builder()
                        .title("Greening Our Urban Oasis")
                        .author(userRepository.findByUsername("JuliaTurner141").orElseThrow())
                        .datePublished(LocalDateTime.now())
                        .status(ProjectStatus.PROPOSED)
                        .summary("This proposal aims to transform our city into a greener, more sustainable urban environment. By planting more trees and creating urban green spaces, we can combat air pollution, provide shade during hot summers, and enhance the overall well-being of our residents. Additionally, this proposal includes initiatives for community gardens, promoting local agriculture, and increasing access to fresh, healthy food.")
                        .content("This proposal represents a visionary initiative to revamp our city and make it a model of sustainability and environmental consciousness. Its core objective is the strategic proliferation of trees throughout our urban landscape and the development of green spaces, which will collectively act as a potent countermeasure against the alarming issue of air pollution. As these trees flourish, they will not only provide aesthetic beauty but also offer much-needed shade during scorching summer months, creating a more comfortable and livable environment for our citizens.\n" +
                                "\n" +
                                "Beyond the ecological benefits, this proposal encompasses a broader vision for the well-being of our community. One of its key facets is the establishment of community gardens that will encourage residents to actively participate in urban agriculture, fostering a sense of ownership and responsibility for the environment. This approach not only promotes sustainability but also helps to strengthen the bonds among community members.\n" +
                                "\n" +
                                "Furthermore, the proposal is committed to supporting local agriculture, ensuring that fresh and healthy produce is readily accessible to our residents. This not only has the potential to improve the nutritional choices of our citizens but also boosts the local economy and reduces our carbon footprint by reducing food transportation distances.\n" +
                                "\n" +
                                "In summary, this proposal is a comprehensive strategy to turn our city into a greener, healthier, and more sustainable urban haven. It tackles the pressing issues of air pollution, promotes community engagement through gardening, supports local agriculture, and ensures better access to nutritious food. By implementing these initiatives, we are not only safeguarding our environment but also enhancing the quality of life for all our residents.")
                        .build(),
                Project.builder()
                        .title("Smart Transportation Network")
                        .author(userRepository.findByUsername("MatthewWright444").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(1))
                        .status(ProjectStatus.ACTIVE)
                        .summary("This initiative focuses on improving our city's transportation infrastructure. It aims to create a smart, efficient, and eco-friendly transportation system through investments in public transit, bike lanes, and pedestrian-friendly streets. ")
                        .content("The proposal seeks to address pressing transportation issues within our city. It envisions a comprehensive transformation of the current transportation infrastructure to enhance its functionality and sustainability.\n" +
                                "\n" +
                                "Firstly, the initiative prioritizes the development of a smart transportation system. This entails the integration of advanced technologies to improve traffic flow, reduce congestion, and enhance overall efficiency. Smart traffic signals, real-time transit updates, and predictive analytics will play pivotal roles in achieving this goal.\n" +
                                "\n" +
                                "Secondly, there will be a significant focus on bolstering public transit options. This includes expanding bus routes, increasing the frequency of services, and introducing new modes of public transportation such as electric buses and tram lines. The goal is to make public transit more accessible and appealing to a wider population.\n" +
                                "\n" +
                                "Thirdly, the proposal emphasizes the creation of extensive bike lanes and pedestrian-friendly streets. These measures are intended to promote eco-friendly commuting options and reduce the reliance on cars, ultimately decreasing carbon emissions and improving air quality.")
                        .build(),
                Project.builder()
                        .title("Education for All")
                        .author(userRepository.findByUsername("DanielWood838").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(2))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("This proposal seeks to enhance access to quality education for all residents. It includes plans for building new schools, improving existing ones, and increasing funding for educational programs. By ensuring that every child has access to a great education, we can create a brighter future for our city.")
                        .content("The construction of new schools will help accommodate the growing population and reduce overcrowding in existing educational institutions. Moreover, it will provide state-of-the-art learning environments that facilitate effective teaching and learning.\n" +
                                "\n" +
                                "Improvements to existing schools are equally essential, as they ensure that the students attending them have access to the best possible educational resources and facilities. This will contribute to raising the overall educational standards in our city.\n" +
                                "\n" +
                                "Increasing funding for educational programs will allow for the implementation of innovative teaching methods, the introduction of advanced technology in classrooms, and the expansion of extracurricular activities, all of which are critical for a well-rounded education.\n" +
                                "\n" +
                                "By focusing on these initiatives, this proposal aims to create a more equitable and inclusive education system that benefits all residents, regardless of their background or socio-economic status. In doing so, we can build a stronger and more prosperous future for our city, where every child has the opportunity to reach their full potential.")
                        .build(),
                Project.builder()
                        .title("Clean Energy Revolution")
                        .author(userRepository.findByUsername("CarolineBennett939").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(3))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("This proposal advocates for a transition to clean and renewable energy sources. It includes plans to install solar panels on public buildings, promote energy-efficient practices, and incentivize renewable energy adoption among businesses and residents")
                        .content("The primary focus is on transitioning to renewable energy, specifically through the widespread installation of solar panels on public buildings. By harnessing the power of the sun, we can generate clean electricity, reduce our carbon footprint, and lower energy costs.\n" +
                                "\n" +
                                "To complement this, the proposal advocates for energy-efficient practices in both public and private sectors. This entails promoting technologies and behaviors that minimize energy waste, such as LED lighting, smart thermostats, and efficient appliances. These measures not only reduce energy consumption but also save money for both the government and individual citizens.\n" +
                                "\n" +
                                "Furthermore, the proposal emphasizes the need to incentivize renewable energy adoption among businesses and residents. This can be achieved through various means, including tax incentives, rebates, and subsidies for installing renewable energy systems like solar panels or wind turbines. By making renewable energy more accessible and affordable, we can encourage widespread adoption and accelerate the transition away from fossil fuels.\n" +
                                "\n" +
                                "In summary, this proposal outlines a comprehensive approach to combat climate change and promote sustainable energy solutions. It leverages solar power, encourages energy-efficient practices, and provides incentives for renewable energy adoption to create a more environmentally friendly and economically viable future for our community.")
                        .build(),
                Project.builder()
                        .title("Youth Empowerment Centers")
                        .author(userRepository.findByUsername("GraceThomas535").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(4))
                        .status(ProjectStatus.REJECTED)
                        .summary("This initiative aims to provide safe spaces and opportunities for the city's youth. The proposal includes plans for building youth centers with educational, recreational, and counseling services. By investing in our youth, we can reduce crime rates and foster future leaders.")
                        .content("The proposal focuses on creating safe spaces and opportunities for the city's youth. It aims to establish youth centers equipped with educational, recreational, and counseling services. This investment is essential to support the development of the city's younger generation.\n" +
                                "\n" +
                                "Youth centers will serve as a hub for learning, offering various educational programs to enhance their skills and knowledge. Simultaneously, recreational activities will provide them with a healthy outlet for their energy and creativity. Furthermore, counseling services will address their mental health needs, ensuring their emotional well-being.\n" +
                                "\n" +
                                "This initiative recognizes that by investing in youth, we can make our community safer. By providing positive alternatives and support systems, we can deter young people from engaging in criminal activities.\n" +
                                "\n" +
                                "Moreover, nurturing our youth can contribute to the cultivation of future leaders. These centers will encourage leadership development, empowering young individuals to take on responsible roles in society.\n" +
                                "\n" +
                                "In summary, the proposal seeks to establish youth centers with comprehensive services to create safe environments, reduce crime rates, and nurture future leaders among the city's youth.")
                        .build(),
                Project.builder()
                        .title("Historical Preservation and Cultural Revival")
                        .author(userRepository.findByUsername("KatherineGreen323").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(5))
                        .status(ProjectStatus.PROPOSED)
                        .summary("This proposal focuses on preserving our city's historical landmarks and promoting its rich cultural heritage. It includes plans for restoring historical buildings, creating cultural festivals, and supporting local artists and artisans.")
                        .content("Good afternoon! The proposal at hand is a comprehensive initiative aimed at safeguarding the invaluable historical landmarks that grace our beloved city while simultaneously celebrating its vibrant cultural heritage. To achieve these objectives, the proposal encompasses a multifaceted approach that spans various aspects of preservation and promotion.\n" +
                                "\n" +
                                "Firstly, a significant portion of the proposal is dedicated to the restoration of historical buildings. These architectural gems hold within them the stories of our city's past, and their preservation is paramount to maintaining a strong connection to our roots. Funds will be allocated for meticulous restoration work, ensuring that these landmarks not only retain their original charm but also adhere to modern safety and accessibility standards.\n" +
                                "\n" +
                                "Secondly, the proposal envisions the creation of a series of cultural festivals throughout the year. These festivals will serve as platforms for showcasing the diverse cultural tapestry of our city. By organizing events that spotlight music, dance, art, cuisine, and more, we aim to foster a deeper appreciation for our cultural heritage and bring the community together in celebration.\n" +
                                "\n" +
                                "Thirdly, the proposal places a strong emphasis on supporting local artists and artisans. Recognizing that they are the living embodiments of our cultural traditions, we plan to provide grants, workshops, and exhibition spaces to help these talented individuals thrive. This not only ensures the continuation of traditional crafts but also infuses new energy and creativity into our cultural scene.\n" +
                                "\n" +
                                "In conclusion, this proposal represents a dedicated effort to preserve the historical treasures of our city and breathe life into its cultural heritage. By restoring our historical landmarks, organizing cultural festivals, and supporting local talent, we aspire to create a city that stands as a testament to its past while embracing the dynamic spirit of the present. This initiative is a testament to our commitment to fostering a deep sense of pride and identity among our residents and visitors alike.")
                        .build(),
                Project.builder()
                        .title("Housing Affordability Initiative")
                        .author(userRepository.findByUsername("RobertJones606").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(6))
                        .status(ProjectStatus.ACTIVE)
                        .summary("This proposal addresses the city's housing affordability crisis. It includes measures to increase affordable housing units, rent control policies, and housing assistance programs.")
                        .content("Good afternoon! This proposal presents a comprehensive approach to tackle the city's pressing housing affordability crisis, which has been a growing concern for both residents and policymakers. The multifaceted plan consists of a range of initiatives aimed at alleviating the burden on residents struggling to find affordable housing.\n" +
                                "\n" +
                                "Firstly, the proposal emphasizes the need to increase the number of affordable housing units within the city. To achieve this, it suggests collaboration with developers and nonprofits to create new affordable housing projects. These developments would be strategically placed in areas where housing costs have surged, helping to maintain neighborhood diversity and accessibility to urban amenities. Additionally, incentives for affordable housing construction, such as tax breaks and zoning modifications, are outlined to encourage private investment in this critical sector.\n" +
                                "\n" +
                                "Secondly, the proposal advocates for the implementation of rent control policies. This involves placing limitations on rent increases, thereby safeguarding tenants from unjustified hikes in rental rates. Rent control not only helps to stabilize housing costs but also offers a sense of security to renters, enabling them to plan their finances more effectively.\n" +
                                "\n" +
                                "Furthermore, the proposal underscores the importance of housing assistance programs. These programs would target low-income individuals and families struggling to meet their housing needs. Support could come in the form of direct financial aid, housing vouchers, or subsidies, making it more feasible for vulnerable populations to access and maintain affordable housing.\n" +
                                "\n" +
                                "In conclusion, this proposal offers a robust strategy to combat the city's housing affordability crisis. By increasing affordable housing units, implementing rent control policies, and providing housing assistance programs, the plan seeks to create a more equitable and stable housing market for all residents. These measures are essential to ensure that the city remains an inclusive and livable place for everyone, regardless of their income level.")
                        .build(),
                Project.builder()
                        .title("Clean Water for All")
                        .author(userRepository.findByUsername("EmmaLewis515").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(7))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("This proposal advocates for improved access to clean and safe drinking water. It includes plans for upgrading the city's water infrastructure, removing lead pipes, and implementing water quality monitoring systems.")
                        .content("Good afternoon! This proposal represents a crucial initiative aimed at enhancing the accessibility of clean and safe drinking water within our community. Access to clean water is a fundamental human right, and this proposal outlines a comprehensive plan to address the pressing issues related to our city's water supply.\n" +
                                "\n" +
                                "First and foremost, the proposal underscores the importance of upgrading our aging water infrastructure. Many parts of our city's water distribution system are outdated and in dire need of renovation. By investing in infrastructure upgrades, we can ensure a more reliable and efficient water supply system that can meet the growing demands of our population.\n" +
                                "\n" +
                                "One of the key elements of this proposal involves the removal of lead pipes from our water distribution network. Lead pipes pose a severe health risk, as they can contaminate drinking water with toxic substances. Eliminating these pipes is essential to safeguarding the health and well-being of our residents, particularly children and vulnerable populations who are most susceptible to lead exposure.\n" +
                                "\n" +
                                "Furthermore, the proposal emphasizes the implementation of advanced water quality monitoring systems. These systems will allow us to proactively detect and address water quality issues, ensuring that our citizens are consistently provided with clean and safe drinking water. Regular monitoring and reporting will provide transparency and accountability in maintaining water quality standards, ultimately fostering trust within the community.\n" +
                                "\n" +
                                "In conclusion, this proposal is a vital step toward ensuring equitable access to clean and safe drinking water for all residents. By upgrading our water infrastructure, eliminating lead pipes, and implementing rigorous water quality monitoring, we can prioritize the health and well-being of our community members. It is an investment in our future, promoting public health, environmental sustainability, and the overall quality of life within our city.")
                        .build(),
                Project.builder()
                        .title("Tech Innovation Hub")
                        .author(userRepository.findByUsername("AmeliaMoore111").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(8))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("This initiative aims to establish a technology innovation hub within the city. The hub would provide resources and support for startups and tech companies, fostering economic growth and job creation.")
                        .content("Good afternoon! The proposed initiative represents an ambitious endeavor geared towards catalyzing technological innovation within our city. At its core, this initiative seeks to create a dedicated technology innovation hub that will serve as a dynamic epicenter for entrepreneurial growth. The envisioned hub will not only be a physical space but also a comprehensive ecosystem of resources and support structures for startups and tech companies. By doing so, it endeavors to bolster economic development and generate employment opportunities, making it a crucial asset for our community's prosperity.\n" +
                                "\n" +
                                "The technology innovation hub will serve as a vibrant space where aspiring entrepreneurs, inventors, and tech enthusiasts can converge to transform their ideas into thriving businesses. It will offer state-of-the-art facilities and infrastructure, including co-working spaces, advanced laboratories, and cutting-edge equipment, facilitating collaboration and experimentation. Furthermore, this hub will provide a wide array of support services, such as mentorship programs, access to venture capital, and educational workshops, designed to nurture and guide fledgling enterprises towards success.\n" +
                                "\n" +
                                "One of the primary objectives of this initiative is to catalyze economic growth. By nurturing and supporting startups and tech companies, we aim to create a fertile ground for innovation-driven businesses to thrive. This, in turn, will lead to increased economic activity, attracting further investments into our city. As these startups grow and expand, they will not only contribute to the local economy through job creation but also foster a culture of innovation that can have ripple effects throughout the region.\n" +
                                "\n" +
                                "In summary, the proposal to establish a technology innovation hub within our city holds immense potential for our community's future. By providing a comprehensive ecosystem of resources and support for startups and tech companies, this initiative will not only drive economic growth but also position our city as a hub for innovation and technological advancement. It is a visionary undertaking that promises to shape the future of our community by fostering entrepreneurship, creating jobs, and promoting a culture of innovation that will benefit us for generations to come.")
                        .build(),
                Project.builder()
                        .title("Community Policing and Public Safety")
                        .author(userRepository.findByUsername("AmeliaMoore111").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(9))
                        .status(ProjectStatus.REJECTED)
                        .summary("This proposal focuses on improving community policing efforts and enhancing overall public safety. It includes measures for community policing training, the establishment of neighborhood watch programs, and increased investment in mental health crisis response teams.")
                        .content("Good afternoon! This proposal is centered on bolstering community policing initiatives and elevating the level of public safety within our communities. It encompasses a comprehensive approach with several key components aimed at fostering positive relationships between law enforcement agencies and the communities they serve, thereby creating a safer and more harmonious living environment.\n" +
                                "\n" +
                                "To begin, the proposal emphasizes the importance of community policing training. This training would equip law enforcement officers with the necessary skills and knowledge to engage effectively with community members, understand local concerns, and build trust. By promoting community-oriented policing practices, officers can work collaboratively with residents to identify and address public safety issues in a more proactive and community-driven manner. This not only improves the perception of law enforcement but also enhances the quality of policing.\n" +
                                "\n" +
                                "In addition to training, the proposal advocates for the establishment of neighborhood watch programs. These programs empower residents to actively participate in their own safety by serving as extra sets of eyes and ears for law enforcement. Neighborhood watch programs foster a sense of community ownership over safety and can help deter criminal activity through increased vigilance. Furthermore, they provide a platform for open communication between residents and local law enforcement, facilitating the exchange of information and concerns.\n" +
                                "\n" +
                                "Furthermore, the proposal suggests increased investment in mental health crisis response teams. This investment would ensure that individuals experiencing mental health crises receive appropriate and compassionate care rather than encountering law enforcement officers who may not have specialized training in handling such situations. By diverting these cases away from the criminal justice system and towards mental health professionals, we can reduce the risk of escalation and promote the well-being of those in crisis.\n" +
                                "\n" +
                                "In conclusion, this proposal presents a multifaceted strategy for improving community policing efforts and advancing overall public safety. Through community policing training, neighborhood watch programs, and increased support for mental health crisis response teams, we can build stronger, more resilient communities where safety is a shared responsibility, trust between residents and law enforcement is fostered, and the well-being of all community members is prioritized. These measures collectively contribute to a safer and more harmonious society, where law enforcement serves as a partner rather than an entity separate from the community it protects.\n")
                        .build(),
                Project.builder()
                        .title("City Park Renovation")
                        .author(userRepository.findByUsername("JosephMitchell424").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(10))
                        .status(ProjectStatus.ACTIVE)
                        .summary("Renovate the city park to create a greener and more enjoyable space for citizens.")
                        .build(),
                Project.builder()
                        .title("Public Transportation Expansion")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(11))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Expand the public transportation network to improve accessibility for all.")
                        .build(),
                Project.builder()
                        .title("Community Center Upgrade")
                        .author(userRepository.findByUsername("LilyEvans919").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(12))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Upgrade the community center with better facilities and programs for residents.")
                        .build(),
                Project.builder()
                        .title("Street Lighting Improvement")
                        .author(userRepository.findByUsername("LilyEvans919").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(13))
                        .status(ProjectStatus.REJECTED)
                        .summary("Enhance street lighting to improve safety and security in the city.")
                        .build(),
                Project.builder()
                        .title("Public Art Installation")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(14))
                        .status(ProjectStatus.PROPOSED)
                        .summary("Install public art pieces to beautify the city streets and inspire creativity.")
                        .build(),
                Project.builder()
                        .title("Beautify Central Park")
                        .author(userRepository.findByUsername("RichardBrooks030").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(15))
                        .status(ProjectStatus.ACTIVE)
                        .summary("Proposal to beautify Central Park with new landscaping and amenities.")
                        .build(),
                Project.builder()
                        .title("Community Mural Project")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(16))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Proposal to create a community mural in the downtown area.")
                        .build(),
                Project.builder()
                        .title("Green Spaces Initiative")
                        .author(userRepository.findByUsername("OliviaMiller909").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(17))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Proposal to create more green spaces and urban gardens.")
                        .build(),
                Project.builder()
                        .title("Street Art Installation")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(18))
                        .status(ProjectStatus.REJECTED)
                        .summary("Proposal for a street art installation in the city center.")
                        .build(),
                Project.builder()
                        .title("City Fountain Renovation")
                        .author(userRepository.findByUsername("OliviaMiller909").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(19))
                        .status(ProjectStatus.PROPOSED)
                        .summary("New proposal to renovate the city's iconic fountain.")
                        .build()
        ));
        System.out.println(projectRepository.count() + " projects seeded...");
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

    private void seedRewards() {
        rewardRepository.saveAllAndFlush(List.of(
                Reward.builder()
                        .name("Railway museum ticket")
                        .description("This ticket provides entry to the Railway museum in Utrecht! Activating must be completed within 1 year after date of purchase, else it's ")
                        .cost(1000)
                        .inventory(50)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now())
                        .build(),
                Reward.builder()
                        .name("Family Pass Railway museum ticket")
                        .description("Perfect for families! Provides entry for 2 adults and 2 children to the Railway museum in Utrecht. Must activate within 1 year.")
                        .cost(2500)
                        .inventory(30)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(1))
                        .build(),
                Reward.builder()
                        .name("Concert Ticket - John Smith Live")
                        .description("Admission to the live concert of John Smith at the City Arena. Show your ticket at the entrance.")
                        .cost(1200)
                        .inventory(40)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(2))
                        .build(),
                Reward.builder()
                        .name("Movie Ticket - Avengers: Endgame")
                        .description("Enjoy the latest Avengers movie on the big screen at the Silver Screen Cinema.")
                        .cost(120)
                        .inventory(20)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(3))
                        .build(),
                Reward.builder()
                        .name("Museum Entry Ticket - Natural History Museum")
                        .description("Explore the wonders of natural history at the renowned museum. Valid for one day.")
                        .cost(70)
                        .inventory(25)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(4))
                        .build(),
                Reward.builder()
                        .name("Theme Park Season Pass - Adventure World")
                        .description("Unlimited access to Adventure World theme park for the entire season. Fun for the whole family!")
                        .cost(150)
                        .inventory(15)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(5))
                        .build(),
                Reward.builder()
                        .name("Men's Casual T-Shirt - Classic White")
                        .description("A comfortable and stylish white t-shirt for men. Perfect for everyday wear.")
                        .cost(250)
                        .inventory(100)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(6))
                        .build(),
                Reward.builder()
                        .name("Women's Running Shoes - Lightweight and Breathable")
                        .description("High-performance running shoes designed for women. Ideal for jogging and workouts.")
                        .cost(650)
                        .inventory(10)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(7))
                        .build(),
                Reward.builder()
                        .name("Kids' Denim Jeans - Blue Wash")
                        .description("Durable and trendy denim jeans for kids. Available in various sizes.")
                        .cost(300)
                        .inventory(20)
                        .isLimited(true)
                        .datePlaced(LocalDateTime.now().plusMinutes(8))
                        .build()
        ));
        System.out.println(rewardRepository.count() + " rewards seeded...");
    }
}
