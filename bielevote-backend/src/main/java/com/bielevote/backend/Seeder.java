package com.bielevote.backend;

import com.bielevote.backend.news.Category;
import com.bielevote.backend.news.NewsArticle;
import com.bielevote.backend.news.NewsArticleRepository;
import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    NewsArticleRepository newsArticleRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedProjects();
        seedNewsArticles();
    }

    private void seedProjects() {
        long count = projectRepository.count();
        if (count == 0) {
            List<Project> projects = List.of(
                    new Project("Park", "new park"),
                    new Project("Swimming pool", "new swimming pool")
            );
            projectRepository.saveAll(projects);
            count = projectRepository.count();
        }
        System.out.println(count + " projects seeded");
    }

    private void seedNewsArticles() {
        long count = newsArticleRepository.count();
        if (count == 0) {
            List<NewsArticle> articles = List.of(
                    new NewsArticle(1L, "Zet uw afval eerder buiten",
                            "In de zomermaanden halen we uw afval eerder op als het erg warm is. Zet uw afval in augustus en september voor 7.00 uur 's ochtends buiten. De avond ervoor mag ook.",
                            "Regels voor aanbieden afval. Herinnering voor ophaaldag en aangepaste dagen. Wilt u niet meer vergeten om uw afval buiten te zetten? Via de app of e-mail krijgt u een herinnering. Die komt op de dag voordat we het afval ophalen of wanneer de ophaaldag is veranderd.",
                            "Marijke Janssen", LocalDateTime.now(),
                            Category.OTHER),
                    new NewsArticle(2L, "Nieuwe Regels voor Afvalinzameling",
                            "Belangrijke wijzigingen in de manier waarop we afval ophalen. Lees verder om te weten wat er is veranderd en hoe dit van invloed is op uw afvalinzameling.",
                            "Vanaf deze maand zijn er nieuwe regels voor het aanbieden van uw afval. De ophaaltijden zijn aangepast, en er zijn strengere voorschriften voor het scheiden van afval. Zorg ervoor dat u op de hoogte bent van deze veranderingen om boetes te voorkomen.",
                            "Peter de Vries", LocalDateTime.now().plusMinutes(1),
                            Category.OTHER),
                    new NewsArticle(3L, "Afvalverwerkingstechnologieën van de Toekomst",
                            "Een kijkje in de innovatieve technologieën die de toekomst van afvalverwerking zullen vormgeven. Ontdek hoe we afval op een duurzamere en efficiëntere manier kunnen verwerken.",
                            "De traditionele methoden voor afvalverwerking evolueren snel, en er zijn veelbelovende technologieën op de horizon. Van recyclingrobots tot geavanceerde composteringssystemen, de toekomst van afvalverwerking is spannend en milieuvriendelijker dan ooit tevoren.",
                            "Sophie Anderson", LocalDateTime.now().plusMinutes(2),
                            Category.SUSTAINABILITY));
            newsArticleRepository.saveAll(articles);
            count = newsArticleRepository.count();
        }
        System.out.println(count + " news articles seeded...");
    }
}
