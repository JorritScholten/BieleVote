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
                        .status(ProjectStatus.EDITING)
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
                        .status(ProjectStatus.EDITING)
                        .summary("Renovate the city park to create a greener and more enjoyable space for citizens.")
                        .content("The proposal aims to revitalize the city park, focusing on transforming it into a greener and more enjoyable space for the community. This project responds to the growing need for urban green spaces that provide not only recreational opportunities but also contribute to the overall well-being of citizens. The renovation plan encompasses various aspects to achieve this goal.\n" +
                                "\n" +
                                "Firstly, the proposal suggests upgrading the park's landscaping by planting more trees, shrubs, and flowers. This will not only enhance the park's aesthetics but also improve air quality and provide shade, creating a more comfortable environment for visitors during hot summer months. Additionally, native and drought-resistant plants should be prioritized to ensure sustainability and reduce water consumption.\n" +
                                "\n" +
                                "Secondly, the project will focus on improving the park's infrastructure. This includes renovating existing walkways and adding new ones for better accessibility. Benches, picnic areas, and water fountains should also be installed to encourage social interaction and relaxation. Furthermore, the proposal advocates for the creation of dedicated spaces for outdoor fitness equipment, promoting physical activity and healthier lifestyles.\n" +
                                "\n" +
                                "Thirdly, the proposal emphasizes environmental sustainability. Implementing eco-friendly features such as solar-powered lighting, rain gardens for water management, and composting facilities will reduce the park's environmental footprint. The renovation should also consider wildlife habitat preservation, allowing local fauna to thrive within the park.\n" +
                                "\n" +
                                "Lastly, community engagement is a vital component of this proposal. Public input will be sought throughout the planning and design phases to ensure the park meets the needs and desires of the citizens. Additionally, educational programs and events related to environmental conservation and the park's history can be organized to foster a sense of ownership and pride among residents.\n" +
                                "\n" +
                                "In conclusion, the proposal to renovate the city park is a comprehensive plan that seeks to transform it into a greener and more enjoyable space for citizens. Through landscaping enhancements, improved infrastructure, environmental sustainability, and community engagement, this project aims to create a vibrant and inclusive urban oasis that enhances the quality of life for all residents.")
                        .build(),
                Project.builder()
                        .title("Public Transportation Expansion")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(11))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Expand the public transportation network to improve accessibility for all.")
                        .content("The proposal to expand the public transportation network with the aim of improving accessibility for all is a significant and forward-thinking initiative. At its core, this proposal seeks to address a variety of challenges faced by communities in terms of transportation, including reducing traffic congestion, lowering carbon emissions, and promoting equity. By expanding public transportation, cities can provide more affordable and efficient options for people to move around, ultimately benefiting both individuals and the environment.\n" +
                                "\n" +
                                "One key aspect of this proposal is the expansion of existing transit systems, such as buses, trains, trams, and subways. This expansion can include adding new routes, increasing the frequency of service, and extending operating hours. These improvements make it easier for people to access jobs, education, healthcare, and other essential services, regardless of their socio-economic status or physical abilities. Moreover, expanding the transportation network can alleviate traffic congestion, reducing the overall carbon footprint of a city.\n" +
                                "\n" +
                                "In addition to expanding traditional public transportation, the proposal may also encompass innovative solutions such as micro-mobility options like electric scooters, bike-sharing programs, and ridesharing services. These options can complement the existing transit system and provide more flexibility for commuters, particularly for short-distance trips. By incorporating technology and data-driven planning, cities can optimize routes, reduce wait times, and enhance the overall user experience.\n" +
                                "\n" +
                                "Furthermore, the proposal recognizes the importance of inclusivity. It should prioritize the needs of vulnerable populations, such as the elderly and individuals with disabilities, by ensuring that public transportation is fully accessible to all. This involves designing stations and vehicles that accommodate wheelchairs, providing clear information for people with visual or hearing impairments, and offering affordable options for low-income individuals. Overall, expanding the public transportation network is a comprehensive strategy that addresses environmental, economic, and social challenges while enhancing the overall quality of life in urban areas.")
                        .build(),
                Project.builder()
                        .title("Community Center Upgrade")
                        .author(userRepository.findByUsername("LilyEvans919").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(12))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Upgrade the community center with better facilities and programs for residents.")
                        .content("The proposal aims to enhance the community center by investing in improved facilities and expanding its program offerings to better serve local residents. The community center is a vital hub for social interaction, recreational activities, and support services, making it essential to ensure it meets the evolving needs of the community. Upgrading the facilities could involve renovations, modernization, and adding new amenities to create a more inviting and functional space for residents. This could include upgrading sports facilities, creating multipurpose rooms for various activities, and ensuring accessibility for all community members.\n" +
                                "\n" +
                                "In addition to physical improvements, the proposal seeks to enrich the center's programs. This may involve introducing new recreational activities, educational workshops, and social events that cater to a wide range of age groups and interests within the community. By diversifying program offerings, the community center can foster greater community engagement, promote physical and mental well-being, and strengthen social bonds among residents. Ultimately, the upgraded community center will serve as a vibrant focal point for community life, promoting inclusivity, and enhancing the overall quality of life for its residents.\n" +
                                "\n" +
                                "Funding for this proposal could be secured through a combination of sources, including government grants, private donations, and community fundraising efforts. Engaging local stakeholders and residents in the planning and decision-making process will be crucial to ensure that the upgraded community center aligns with the specific needs and preferences of the community it serves.")
                        .build(),
                Project.builder()
                        .title("Street Lighting Improvement")
                        .author(userRepository.findByUsername("LilyEvans919").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(13))
                        .status(ProjectStatus.ACTIVE)
                        .summary("Enhance street lighting to improve safety and security in the city.")
                        .content("The proposal aims to enhance street lighting in the city with the primary goal of improving safety and security for its residents and visitors. This initiative recognizes that well-lit streets play a crucial role in deterring crime and creating a safer environment for all. The proposal suggests a comprehensive plan that includes upgrading existing streetlights to more energy-efficient and brighter LED lights, expanding the coverage of street lighting to currently underserved areas, and implementing smart lighting solutions that can adjust brightness levels based on real-time conditions.\n" +
                                "\n" +
                                "By enhancing street lighting, the city can significantly reduce the likelihood of accidents, as well-lit streets provide better visibility for drivers, pedestrians, and cyclists, especially during nighttime and adverse weather conditions. Additionally, improved lighting can act as a deterrent to criminal activities such as vandalism and theft, making the city a safer place to live and work. Moreover, a well-lit environment promotes a sense of community and encourages residents to use public spaces more frequently, fostering social interactions and a sense of belonging.\n" +
                                "\n" +
                                "To fund this proposal, a combination of public and private investment, as well as potential grants and subsidies, can be explored. The benefits of this initiative extend beyond just safety and security; it can also lead to energy savings through the use of more efficient lighting technology, contributing to the city's sustainability goals. Overall, enhancing street lighting is a holistic approach to create a safer, more vibrant, and environmentally responsible city.")
                        .build(),
                Project.builder()
                        .title("Public Art Installation")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(14))
                        .status(ProjectStatus.REJECTED)
                        .summary("Install public art pieces to beautify the city streets and inspire creativity.")
                        .content("The proposal aims to enhance the aesthetic appeal of our city streets while fostering a sense of creativity and inspiration within the community. By installing public art pieces throughout the city, we can transform otherwise mundane urban spaces into vibrant hubs of artistic expression. These artworks will not only contribute to the overall visual attractiveness of our city but also provide a platform for local and emerging artists to showcase their talent, thereby promoting a thriving arts scene.\n" +
                                "\n" +
                                "Public art has the unique ability to engage and connect with people from all walks of life. Whether it's sculptures, murals, or interactive installations, these pieces can spark conversations, evoke emotions, and stimulate the imagination of both residents and visitors. Moreover, the presence of public art can serve as a catalyst for economic development by attracting tourists, supporting local businesses, and increasing property values in the surrounding areas.\n" +
                                "\n" +
                                "To ensure the success of this proposal, a collaborative effort between the city government, local artists, and community members is essential. Public input and engagement should play a crucial role in the selection and placement of these art pieces to reflect the diverse interests and values of our citizens. Furthermore, funding mechanisms such as grants, public-private partnerships, and community fundraising initiatives should be explored to make this project financially sustainable in the long term. Ultimately, the installation of public art pieces is an investment in our city's cultural richness, vitality, and identity, offering benefits that extend far beyond their aesthetic value.")
                        .build(),
                Project.builder()
                        .title("Beautify Central Park")
                        .author(userRepository.findByUsername("RichardBrooks030").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(15))
                        .status(ProjectStatus.REJECTED)
                        .summary("Proposal to beautify Central Park with new landscaping and amenities.")
                        .content("The proposal to beautify Central Park aims to enhance one of New York City's most iconic landmarks by introducing new landscaping and amenities. Central Park, a vast green oasis in the heart of Manhattan, has long been a beloved destination for both residents and tourists. However, over the years, wear and tear have taken a toll on certain areas, and some amenities have become outdated. This proposal seeks to rejuvenate the park while preserving its historic charm.\n" +
                                "\n" +
                                "The first aspect of the proposal involves revitalizing the park's landscaping. This includes planting more trees and flowers, improving the irrigation system, and restoring some of the natural habitats within the park. By doing so, we can create a more vibrant and inviting environment that offers an even greater escape from the bustling city.\n" +
                                "\n" +
                                "The second part of the proposal focuses on updating and expanding amenities within Central Park. This includes renovating playgrounds, adding new picnic areas, and enhancing walking and biking trails. Additionally, the proposal suggests the creation of new cultural spaces, such as outdoor theaters and art installations, to make Central Park an even more diverse and culturally rich destination.\n" +
                                "\n" +
                                "In summary, the proposal to beautify Central Park is a comprehensive plan aimed at preserving the park's natural beauty while enhancing the visitor experience. By investing in landscaping and updating amenities, we can ensure that Central Park remains a cherished and vital part of New York City for generations to come. This project not only serves the local community but also contributes to the city's appeal as a world-class destination.")
                        .build(),
                Project.builder()
                        .title("Community Mural Project")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(16))
                        .status(ProjectStatus.PROPOSED)
                        .summary("Proposal to create a community mural in the downtown area.")
                        .content("The proposal aims to create a vibrant and engaging community mural in the downtown area, with the primary objective of enhancing the visual appeal of the neighborhood while fostering a sense of unity and pride among its residents. This project intends to transform a prominent public wall or space into an artwork that tells a story about the local community, its history, culture, and aspirations. The mural will be a collaborative effort involving local artists, residents, and businesses, reflecting the diverse voices and identities that make up the downtown community.\n" +
                                "\n" +
                                "The project's first phase involves community engagement and consultation to gather ideas, themes, and concepts for the mural. Workshops and public meetings will be organized to ensure that the mural is a true representation of the community's values and aspirations. Local artists, selected through an open call or competition, will work closely with the community to develop a design that encapsulates the collective vision.\n" +
                                "\n" +
                                "The mural creation process will be a community-driven initiative, with volunteers and artists working together to bring the design to life. This hands-on approach not only fosters a sense of ownership and pride among the community members but also provides an opportunity for skill-building and artistic expression. The completed mural will serve as a lasting testament to the creativity, culture, and unity of the downtown area, attracting both residents and visitors while promoting a sense of place and belonging.\n" +
                                "\n" +
                                "To fund the project, we will seek grants and donations from local businesses, organizations, and government agencies, as well as engage in community fundraising initiatives. The project timeline will depend on funding availability, but we aim to complete the mural within one year from its inception. The proposal aims to create a visually stunning and culturally significant mural that not only beautifies the downtown area but also celebrates the diversity and spirit of its community.")
                        .build(),
                Project.builder()
                        .title("Green Spaces Initiative")
                        .author(userRepository.findByUsername("OliviaMiller909").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(17))
                        .status(ProjectStatus.PROPOSED)
                        .summary("Proposal to create more green spaces and urban gardens.")
                        .content("The proposal aims to address the pressing issue of limited green spaces and urban gardens in our community. In recent years, urbanization has led to the proliferation of concrete jungles, leaving little room for nature in our daily lives. This proposal seeks to rectify this imbalance by advocating for the creation of more green spaces and urban gardens. These spaces will not only enhance the aesthetic appeal of our city but also offer numerous social, environmental, and health benefits.\n" +
                                "\n" +
                                "First and foremost, increased green spaces and urban gardens will contribute to improved air quality and reduced urban heat island effects. Trees and plants act as natural air filters, helping to mitigate pollution and providing cooler microclimates in densely populated areas. Moreover, these green areas will promote biodiversity by providing habitats for various species and encouraging the growth of native plants. This will help maintain ecological balance and create a healthier urban environment.\n" +
                                "\n" +
                                "Furthermore, green spaces and urban gardens have positive impacts on mental and physical health. They provide opportunities for relaxation and recreation, reducing stress and anxiety levels among residents. Encouraging community involvement in these spaces can foster a sense of belonging and social cohesion. These areas can also serve as educational hubs, teaching people about sustainable gardening practices and the importance of environmental conservation.\n" +
                                "\n" +
                                "To implement this proposal, we suggest a multi-pronged approach. Firstly, we need to identify suitable locations for green space development, including vacant lots, rooftops, and underutilized public spaces. Secondly, partnerships with local businesses, non-profit organizations, and government agencies can help secure funding, resources, and expertise for creating and maintaining these areas. Thirdly, community engagement and education programs can raise awareness and encourage active participation in the development and upkeep of urban gardens. Finally, we should establish clear guidelines for sustainable gardening practices, ensuring that these spaces are environmentally friendly and benefit the community as a whole.\n" +
                                "\n" +
                                "In conclusion, the proposal to create more green spaces and urban gardens is a holistic solution to many of the challenges faced by modern urban areas. By prioritizing the environment, health, and community well-being, we can create a more vibrant and sustainable city for current and future generations to enjoy. Through careful planning, partnerships, and community involvement, we can transform our urban landscape into a greener, healthier, and more connected space for everyone.")
                        .build(),
                Project.builder()
                        .title("Street Art Installation")
                        .author(userRepository.findByUsername("SamuelThompson010").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(18))
                        .status(ProjectStatus.EDITING)
                        .summary("Proposal for a street art installation in the city center.")
                        .content("The proposal envisions a captivating street art installation to be situated in the heart of our city center. This innovative project aims to enhance the urban landscape and foster a sense of community engagement and creativity. Spanning over 15 sentences, this proposal outlines the key details and benefits of this exciting endeavor.\n" +
                                "\n" +
                                "Firstly, the installation will feature a prominent mural spanning a large building facade. The mural will depict a vibrant and dynamic scene that reflects the city's diverse culture, heritage, and modern spirit. The design will be a collaborative effort between local artists and community members, ensuring it resonates with the people who call this city home. This participatory approach will also involve workshops and public events to engage residents in the creative process, fostering a deeper connection to the art.\n" +
                                "\n" +
                                "Secondly, the installation will not only be visually striking but also interactive. To engage passersby, elements of the artwork will incorporate augmented reality (AR) technology accessible via smartphones. This immersive aspect will allow viewers to interact with the mural, uncovering hidden details, stories, and information about the city's history and culture. It serves as a bridge between traditional art and cutting-edge technology, making it appealing to a wide range of demographics.\n" +
                                "\n" +
                                "Thirdly, this street art installation will have multiple educational facets. It will be accompanied by informational panels and QR codes that provide historical context, artist biographies, and explanations of the artistic techniques employed. Local schools and educational institutions will be encouraged to include visits to the installation in their curricula, promoting art appreciation and cultural understanding among students.\n" +
                                "\n" +
                                "Lastly, the proposal highlights the economic and social benefits of such an installation. It will likely draw tourists and visitors, contributing to the local economy by boosting foot traffic in the city center. Moreover, it will serve as a point of pride for residents, fostering a stronger sense of community identity and engagement. By bringing people together through art and technology, this street art installation aims to transform our city center into a vibrant hub of creativity and cultural exchange, enriching the lives of its inhabitants and visitors alike.")
                        .build(),
                Project.builder()
                        .title("City Fountain Renovation")
                        .author(userRepository.findByUsername("OliviaMiller909").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(19))
                        .status(ProjectStatus.ACTIVE)
                        .summary("New proposal to renovate the city's iconic fountain.")
                        .content("A new proposal has emerged to renovate the city's iconic fountain, a landmark that has graced our urban landscape for generations. This ambitious project aims to not only restore the fountain to its former glory but also enhance its appeal and functionality. The proposal envisions a comprehensive overhaul of the fountain, addressing both its structural integrity and aesthetic aspects.\n" +
                                "\n" +
                                "The renovation proposal seeks to modernize the fountain's infrastructure by replacing outdated plumbing and electrical systems, ensuring greater efficiency and sustainability. This will reduce maintenance costs in the long run, making it a financially responsible investment for the city. Moreover, the project includes plans to install energy-efficient LED lighting, turning the fountain into a mesmerizing nighttime attraction, illuminating the surrounding area with a vibrant display of colors.\n" +
                                "\n" +
                                "In addition to the technical upgrades, the proposal incorporates a design element aimed at making the fountain more accessible and user-friendly. Wheelchair ramps and improved seating arrangements will be added, allowing visitors of all abilities to enjoy the fountain's beauty. Furthermore, a committee of local artists will be engaged to contribute to the artistic vision, ensuring that the renovated fountain becomes a symbol of cultural expression and community pride.\n" +
                                "\n" +
                                "To fund this ambitious project, a combination of public and private investments will be sought, ensuring that the burden does not fall solely on taxpayers. Public hearings and community input will also play a pivotal role in shaping the final design, ensuring that the renovated fountain truly reflects the desires and aspirations of the city's residents. Ultimately, this proposal to renovate the city's iconic fountain represents a visionary effort to rejuvenate a beloved landmark, bringing it into the 21st century while preserving its historical and cultural significance for generations to come.")
                        .build(),
                Project.builder()
                        .title("Pedestrian-Friendly Zones")
                        .author(userRepository.findByUsername("DanielParker616").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(20))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Designate certain streets or districts as pedestrian-friendly zones. Restrict vehicular traffic in these areas, allowing people to walk freely, shop, dine, and socialize without the noise and pollution of cars.")
                        .content("Designating specific streets or districts as pedestrian-friendly zones is a forward-thinking urban planning proposal aimed at enhancing the quality of life in cities. This concept involves strategically restricting vehicular traffic in selected areas, with the primary goal of creating spaces where people can freely walk, shop, dine, and socialize without the disturbances caused by cars. Such pedestrian-friendly zones have proven to be successful in numerous cities worldwide, promoting a more sustainable and healthier urban environment.\n" +
                                "\n" +
                                "Firstly, the benefits of these zones are manifold. By reducing car traffic, air pollution levels are significantly lowered, leading to improved air quality and better public health outcomes. The decreased noise pollution also contributes to a quieter and more peaceful urban atmosphere. Moreover, these zones encourage physical activity, as people are more inclined to walk, cycle, or use public transportation, promoting a healthier lifestyle for residents. Additionally, businesses in these areas tend to thrive due to increased foot traffic, and communities become more tightly-knit as people gather in these communal spaces.\n" +
                                "\n" +
                                "To implement this proposal effectively, careful planning and collaboration with local stakeholders are essential. City officials must assess which streets or districts are most suitable for pedestrianization, taking into account factors like existing foot traffic, public transportation access, and the impact on nearby businesses. Adequate infrastructure, such as wider sidewalks, bike lanes, and attractive street furniture, should be put in place to support pedestrians and cyclists. Moreover, it's crucial to engage with the local community to ensure their needs and concerns are addressed throughout the planning and implementation process.\n" +
                                "\n" +
                                "In conclusion, designating pedestrian-friendly zones by restricting vehicular traffic in specific streets or districts is a forward-looking approach to urban planning. This initiative offers numerous benefits, including improved air quality, reduced noise pollution, enhanced public health, and economic prosperity for local businesses. Effective implementation requires careful planning, infrastructure improvements, and community involvement. Ultimately, these pedestrian-friendly zones contribute to creating vibrant and sustainable cities where people can live, work, and socialize in harmony with their surroundings.")
                        .build(),
                Project.builder()
                        .title("Bike Lanes and Rentals")
                        .author(userRepository.findByUsername("GraceWalker717").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(21))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Expand the city's network of bike lanes and implement a bike-sharing program to promote eco-friendly transportation and encourage a healthier lifestyle.")
                        .content("The proposed plan aims to enhance the city's transportation infrastructure by expanding its network of bike lanes and introducing a bike-sharing program. This initiative responds to the growing concern for environmental sustainability and the need to reduce carbon emissions. By creating more dedicated bike lanes, the city not only encourages eco-friendly transportation but also fosters a safer environment for cyclists. It promotes a shift towards a more sustainable and healthier lifestyle for residents.\n" +
                                "\n" +
                                "Expanding the city's network of bike lanes will involve careful planning and strategic placement. This could include the addition of bike lanes on major roadways, within residential neighborhoods, and connecting key destinations such as schools, workplaces, and recreational areas. The goal is to create a comprehensive network that makes cycling a convenient and attractive option for daily commutes. By providing safer and dedicated spaces for cyclists, the proposal also aims to reduce the number of accidents and improve overall road safety.\n" +
                                "\n" +
                                "Complementing the bike lane expansion, the introduction of a bike-sharing program would further incentivize eco-friendly transportation. This program would allow residents and visitors to easily access bicycles for short-term use, making cycling a viable option for those who may not own a bike. Such programs have been successful in many cities globally, reducing the reliance on cars and mitigating traffic congestion. Additionally, by encouraging cycling, the city can improve public health outcomes, reduce air pollution, and contribute to a more sustainable urban environment.\n" +
                                "\n" +
                                "In summary, the proposal to expand the city's network of bike lanes and implement a bike-sharing program is a multifaceted approach to promote eco-friendly transportation and healthier living. It seeks to enhance the city's infrastructure, reduce carbon emissions, improve road safety for cyclists, and provide convenient and accessible alternatives to car travel. This initiative not only addresses environmental concerns but also aligns with the global trend toward sustainable urban development, making the city a more attractive and livable place for its residents.")
                        .build(),
                Project.builder()
                        .title("Cultural Festivals")
                        .author(userRepository.findByUsername("MichaelBennett818").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(22))
                        .status(ProjectStatus.REJECTED)
                        .summary("Organize regular cultural festivals celebrating the diversity of the city's population. These events can showcase music, dance, food, and traditions from various cultures, fostering unity and understanding among residents.")
                        .content("The proposal suggests the implementation of regular cultural festivals as a means to celebrate the rich diversity within the city's population. These festivals would serve as platforms to showcase the vibrant tapestry of cultures present in the community. Such events could feature a wide array of cultural elements, including music, dance, cuisine, and traditional customs. The primary goal of these festivals would be to promote unity and foster a deeper understanding among the city's residents.\n" +
                                "\n" +
                                "Cultural festivals have the potential to bring together people from different backgrounds, creating opportunities for cross-cultural interactions and dialogue. By celebrating the unique traditions and practices of various communities, these events can help break down barriers and promote inclusivity. They provide a space for residents to learn about one another's cultures, which can lead to greater tolerance and appreciation for diversity. Moreover, these festivals could be a source of education, allowing attendees to gain insights into the customs and history of their fellow citizens.\n" +
                                "\n" +
                                "Furthermore, the proposal emphasizes the importance of regularity in these cultural festivals. By scheduling them at regular intervals throughout the year, the city can create a sense of anticipation and expectation among its residents. This can lead to increased participation and engagement, as people look forward to these celebrations as a highlight of their community life. The consistent presence of cultural festivals can also help instill a sense of unity and belonging among the city's diverse population, reinforcing the idea that everyone is a valued member of the community.\n" +
                                "\n" +
                                "In conclusion, the proposal to organize regular cultural festivals is a commendable initiative for any city seeking to embrace and celebrate its diversity. Through music, dance, food, and traditions, these festivals can promote unity, understanding, and cross-cultural interactions among residents. They can serve as powerful tools for breaking down barriers, fostering inclusivity, and nurturing a sense of belonging within the community. By making these festivals a regular part of the city's cultural calendar, the city can further strengthen its commitment to embracing diversity and building a more harmonious and connected society.")
                        .build(),
                Project.builder()
                        .title("Street Food Vendors")
                        .author(userRepository.findByUsername("LilyEvans919").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(23))
                        .status(ProjectStatus.PROPOSED)
                        .summary("Encourage street food vendors to operate in designated areas, offering a variety of culinary delights. Street food adds a unique charm and provides affordable dining options.")
                        .content("This proposal aims to create a vibrant street food culture that not only enriches the culinary landscape but also boosts local economies and promotes entrepreneurship.\n" +
                                "\n" +
                                "Firstly, the proposal suggests establishing designated areas where street food vendors can operate safely and comfortably. These areas would be strategically located in high-traffic zones like parks, pedestrian streets, and marketplaces to attract a diverse range of customers. Providing designated spaces would address issues related to hygiene, safety, and traffic congestion often associated with unregulated street vending.\n" +
                                "\n" +
                                "Secondly, this initiative encourages culinary diversity by inviting a wide array of vendors offering various cuisines. From traditional local dishes to international flavors, such a setup would cater to diverse tastes and preferences, transforming these areas into gastronomic hubs. This culinary variety not only attracts food enthusiasts but also fosters cultural exchange and appreciation.\n" +
                                "\n" +
                                "Furthermore, the proposal seeks to promote entrepreneurship by simplifying the licensing and registration processes for street food vendors. Lower entry barriers would empower individuals with culinary skills to start their businesses, leading to economic growth and job creation. Moreover, it encourages innovation as vendors compete to offer unique and delicious creations, driving culinary creativity.\n" +
                                "\n" +
                                "In conclusion, this proposal aims to harness the potential of street food as a catalyst for economic growth, cultural enrichment, and entrepreneurial empowerment. By providing designated areas, encouraging culinary diversity, and simplifying the vendor registration process, we can create thriving street food scenes that benefit both consumers and the local economy. This approach not only makes dining more accessible but also fosters a sense of community and celebrates the rich tapestry of flavors found in our society.")
                        .build(),
                Project.builder()
                        .title("Outdoor Movie Nights")
                        .author(userRepository.findByUsername("ChristopherReed020").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(24))
                        .status(ProjectStatus.EDITING)
                        .summary("Host outdoor movie nights in parks or public squares during the summer months, allowing residents to enjoy classic and contemporary films under the stars.")
                        .content("The proposal suggests hosting outdoor movie nights in parks or public squares during the summer months as a means of providing entertainment and fostering community engagement. This initiative aims to create a memorable experience for residents by offering them the opportunity to enjoy classic and contemporary films under the open sky.\n" +
                                "\n" +
                                "Firstly, these outdoor movie nights can serve as a great way to bring people together, enhancing social cohesion within the community. Such events often attract families, friends, and neighbors, providing a platform for people to connect and interact in a relaxed and enjoyable setting. This can help strengthen the sense of community, promote neighborly relationships, and build a positive communal atmosphere.\n" +
                                "\n" +
                                "Secondly, outdoor movie nights can be a fantastic way to promote local businesses and boost the local economy. Vendors can set up stalls or food trucks at these events, offering snacks, beverages, and other products to attendees. This not only enhances the overall experience but also generates revenue for local businesses, contributing to the economic development of the area.\n" +
                                "\n" +
                                "Moreover, hosting outdoor movie nights aligns with the broader trend of encouraging outdoor activities and promoting a healthy lifestyle. People can bring their own blankets or chairs, sit under the stars, and enjoy a cinematic experience in a natural environment. This can encourage physical activity and an appreciation for the outdoors, which can have long-term benefits for the health and well-being of the community.\n" +
                                "\n" +
                                "In summary, the proposal to host outdoor movie nights during the summer months is a community-building initiative that promotes social interaction, supports local businesses, and encourages a healthy lifestyle. It offers a unique and enjoyable experience for residents to come together, relax, and enjoy films in a beautiful outdoor setting, contributing to the overall well-being and vibrancy of the community.")
                        .build(),
                Project.builder()
                        .title("Historical Walking Tours:")
                        .author(userRepository.findByUsername("AbigailMartin121").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(25))
                        .status(ProjectStatus.ACTIVE)
                        .summary("Develop guided historical walking tours that highlight the city's heritage, architecture, and landmarks, educating residents and tourists about its rich history.")
                        .content("The proposal aims to create a series of guided historical walking tours that focus on showcasing the city's rich heritage, stunning architecture, and iconic landmarks. These tours will serve as an engaging and educational experience for both residents and tourists, allowing them to delve deep into the city's history. By offering guided tours, we aim to provide a well-structured and informative way for people to explore the city's cultural treasures.\n" +
                                "\n" +
                                "These walking tours will be meticulously designed to cover various historical periods, architectural styles, and significant events in the city's past. They will feature knowledgeable guides who are passionate about history and capable of delivering engaging narratives. The tours will not only highlight well-known landmarks but also uncover hidden gems that hold historical significance, giving participants a comprehensive understanding of the city's evolution.\n" +
                                "\n" +
                                "To ensure accessibility, the tours will be available in multiple languages and cater to different interests, such as architecture enthusiasts, history buffs, and families. In addition, we will offer a range of tour durations to accommodate different schedules and preferences, from short one-hour tours to comprehensive half-day expeditions.\n" +
                                "\n" +
                                "Moreover, the proposal includes a robust marketing strategy to promote these tours to both locals and tourists, including partnerships with hotels, online booking platforms, and social media campaigns. By offering these guided historical walking tours, we aim to not only boost tourism but also foster a deeper appreciation for the city's heritage and architecture, creating a sense of pride among residents and enriching the overall cultural experience of the city.")
                        .build(),
                Project.builder()
                        .title("Street Performer Zones")
                        .author(userRepository.findByUsername("AndrewMurphy222").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(26))
                        .status(ProjectStatus.ACTIVE)
                        .summary("Designate specific areas for street performers, such as musicians, jugglers, and magicians, to showcase their talents and entertain passersby.")
                        .content("The proposal suggests the creation of designated areas for street performers, encompassing musicians, jugglers, and magicians, with the intention of providing a platform for these artists to showcase their talents and entertain the public. This idea is rooted in the belief that street performance not only adds vibrancy to urban environments but also offers a unique form of cultural expression that enhances the overall ambiance of a city. By allocating specific spaces for street performers, cities can harness the positive aspects of street art while also addressing potential concerns related to public safety and accessibility.\n" +
                                "\n" +
                                "These designated areas would serve as a win-win solution, benefiting both the performers and the community. Street artists would have access to a controlled and supportive environment where they can practice their craft without the fear of obstructing pedestrians or facing legal issues. This could lead to the emergence of a diverse range of talented artists, promoting cultural diversity and artistic innovation within the city. Simultaneously, residents and tourists would have the opportunity to enjoy impromptu performances and the enriching experience of interacting with local artists.\n" +
                                "\n" +
                                "Furthermore, such designated zones could be strategically placed in areas with high foot traffic, tourist attractions, or cultural districts, thereby boosting the local economy by attracting more visitors. These spaces could also be adaptable, allowing for rotation of performers, ensuring a dynamic and ever-changing street performance scene. It's important, however, that guidelines and regulations are established to maintain order, allocate time slots fairly, and manage noise levels to prevent disruptions for nearby businesses and residents.\n" +
                                "\n" +
                                "In conclusion, the proposal to designate specific areas for street performers aims to harness the creative energy of these artists while enhancing the urban experience for residents and visitors alike. By providing a structured and supportive environment for street performances, cities can promote cultural expression, stimulate tourism, and boost their local economies, all while maintaining order and addressing potential concerns. This initiative has the potential to transform public spaces into vibrant hubs of artistic expression and cultural exchange, ultimately contributing to the enrichment of urban life.")
                        .build(),
                Project.builder()
                        .title("Riverfront Development")
                        .author(userRepository.findByUsername("KatherineGreen323").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(27))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Invest in the waterfront of the city with riverside promenades, cafes, and recreational facilities like paddleboats and kayaks, creating a picturesque destination for leisure.")
                        .content("The proposal aims to revitalize the city's waterfront by investing in various amenities and recreational facilities. One key aspect of this plan is the development of riverside promenades. These promenades would offer scenic views of the river and serve as a pleasant place for residents and visitors to take leisurely strolls. They would also provide an opportunity for local businesses to thrive, with potential for street vendors, artists, and performers to contribute to the vibrant atmosphere.\n" +
                                "\n" +
                                "Cafes would be strategically placed along the waterfront, providing both refreshments and a gathering place for the community. These cafes could offer a diverse range of culinary options, from coffee and pastries to gourmet dining, further enhancing the appeal of the area. Additionally, recreational facilities like paddleboats and kayaks would be available for rent, encouraging people to actively engage with the river. These activities would not only promote physical fitness but also bring a sense of adventure and excitement to the waterfront.\n" +
                                "\n" +
                                "By creating this picturesque destination for leisure, the city would not only boost its tourism potential but also improve the overall quality of life for its residents. The revitalized waterfront would become a hub for social interaction, fostering a sense of community among its inhabitants. Moreover, it could attract investment and drive economic growth as businesses, both large and small, seek opportunities in this thriving waterfront district. Overall, this proposal presents a compelling vision for a vibrant, attractive, and economically prosperous waterfront that benefits both residents and visitors alike.")
                        .build(),
                Project.builder()
                        .title("Green Spaces and Pocket Parks")
                        .author(userRepository.findByUsername("JosephMitchell424").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(28))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Develop more green spaces, pocket parks, and community gardens throughout the city. These areas can provide residents with serene spots to relax, exercise, or enjoy picnics.")
                        .content("The proposal aims to enhance urban living by creating additional green spaces, pocket parks, and community gardens within the city. These initiatives are vital for several reasons. First and foremost, they offer residents a respite from the hustle and bustle of city life. The presence of green spaces provides serene, natural environments where people can unwind, de-stress, and reconnect with nature. These spaces can become essential for mental and physical well-being, serving as places for relaxation, meditation, and recreational activities.\n" +
                                "\n" +
                                "Moreover, the introduction of more green spaces and pocket parks promotes a healthier lifestyle for the city's inhabitants. These areas encourage outdoor activities such as jogging, yoga, or playing sports, contributing to increased physical fitness. Additionally, community gardens offer opportunities for residents to engage in gardening, fostering a sense of ownership and responsibility for the environment. This not only promotes sustainable practices but also strengthens community bonds as neighbors come together to cultivate these shared spaces.\n" +
                                "\n" +
                                "Furthermore, the proposal has several long-term benefits for the city as a whole. The introduction of more green spaces can help mitigate the urban heat island effect, where cities become significantly warmer than surrounding rural areas due to concrete and asphalt. This can have positive effects on the city's overall climate, reducing energy consumption for cooling and improving air quality. Additionally, these green spaces can increase property values and attract more residents and businesses, ultimately boosting economic growth and making the city more attractive as a place to live and invest.\n" +
                                "\n" +
                                "In conclusion, the proposal to develop more green spaces, pocket parks, and community gardens throughout the city has multifaceted benefits. It improves the quality of life for residents by offering serene and recreational areas, promotes healthier lifestyles, fosters community engagement, and contributes to long-term environmental and economic sustainability. As cities continue to grow, such initiatives become increasingly important for the well-being and prosperity of urban communities.")
                        .build(),
                Project.builder()
                        .title("Public Art Installations")
                        .author(userRepository.findByUsername("NicholasAdams626").orElseThrow())
                        .datePublished(LocalDateTime.now().minusMonths(29))
                        .status(ProjectStatus.ACCEPTED)
                        .summary("Create a program to commission and display public art throughout the city. These installations can include sculptures, murals, and interactive art pieces, adding visual appeal and cultural vibrancy.")
                        .content("The proposed program aims to enhance the cultural vibrancy and visual appeal of our city by commissioning and displaying public art. This initiative would introduce a diverse range of artistic installations, such as sculptures, murals, and interactive art pieces, throughout the urban landscape. Public art has the potential to transform mundane urban spaces into captivating and thought-provoking environments, engaging residents and visitors alike.\n" +
                                "\n" +
                                "By implementing this program, the city would create a unique identity and a sense of place. Public art has the power to tell stories, reflect the community's values, and celebrate its heritage. These installations can also serve as landmarks, helping residents and tourists navigate the city more easily. Furthermore, public art encourages creativity and innovation, fostering a sense of pride and ownership among local artists and the community as a whole.\n" +
                                "\n" +
                                "Moreover, the program could provide a platform for emerging artists to showcase their talent, fostering a supportive ecosystem for the arts. It would also stimulate the local economy by attracting art enthusiasts and tourists who are interested in exploring the city's cultural offerings. Businesses in the vicinity of these art installations may experience increased foot traffic, further contributing to the city's economic growth.\n" +
                                "\n" +
                                "In conclusion, the proposal to create a program for commissioning and displaying public art throughout the city has the potential to enrich the urban environment, stimulate the local economy, and celebrate the creativity of the community. It represents an investment in cultural vibrancy and a means to enhance the city's identity and visual appeal, making it a more attractive place to live, work, and visit.")
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
