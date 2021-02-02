package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.Repositories.AuthorRepo;
import guru.springframework.spring5webapp.Repositories.BookRepo;
import guru.springframework.spring5webapp.Repositories.PublisherRepo;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;


    public BootStrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("Leather-Bound Publishing");
        publisher.setCity("Wilmington");
        publisher.setState("Delaware");

        publisherRepo.save(publisher);
        System.out.println("Publisher Count: " + publisherRepo.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepo.save(eric);      //Saves into h2 database
        bookRepo.save(ddd);         //Saves into h2 database
        //publisherRepo.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB","123456");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        authorRepo.save(rod);
        bookRepo.save(noEJB);
//        publisherRepo.save(publisher);


        System.out.println("Number of Books: " + bookRepo.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
