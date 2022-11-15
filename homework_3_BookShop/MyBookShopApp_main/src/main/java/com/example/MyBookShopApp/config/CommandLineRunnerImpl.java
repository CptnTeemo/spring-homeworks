//package com.example.MyBookShopApp.config;
//
//import com.example.MyBookShopApp.data.*;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManagerFactory;
//import java.util.List;
//import java.util.logging.Logger;
//
//@Configuration
//public class CommandLineRunnerImpl implements CommandLineRunner {
//
//    TestEntityCrudRepository testEntityCrudRepository;
//    BookRepository bookRepository;
//
//    @Autowired
//    public CommandLineRunnerImpl(TestEntityCrudRepository testEntityCrudRepository, BookRepository bookRepository) {
//        this.testEntityCrudRepository = testEntityCrudRepository;
//        this.bookRepository = bookRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 5; i++) {
//            createTestEntity(new TestEntity());
//        }
//
//        TestEntity readTestEntity = readTestEntityById(3L);
//        if (readTestEntity != null) {
//            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("read: "+readTestEntity.toString());
//        } else {
//            throw new NullPointerException();
//        }
//
//        TestEntity updateTestEntity = updateTestEntityById(5L);
//        if (updateTestEntity != null) {
//            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update: "+updateTestEntity.toString());
//        } else {
//            throw new NullPointerException();
//        }
//
//        deleteTestEntityById(4L);
//
//        List<Book> books = bookRepository.findBooksByAuthor_FirstName("Debi");
//        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(books.toString());
//        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(bookRepository.customFindAllBooks().toString());
//    }
//
//    private void deleteTestEntityById(Long id) {
//        TestEntity testEntity = readTestEntityById(id);
//        testEntityCrudRepository.delete(testEntity);
//    }
//
//    private TestEntity updateTestEntityById(Long id) {
//        TestEntity testEntity = testEntityCrudRepository.findById(id).get();
//        testEntity.setData("NEW DATA");
//        testEntityCrudRepository.save(testEntity);
//        return testEntity;
//    }
//
//    private TestEntity readTestEntityById(Long id) {
//        return testEntityCrudRepository.findById(id).get();
//    }
//
//    private void createTestEntity(TestEntity entity) {
//        entity.setData(entity.getClass().getSimpleName()+entity.hashCode());
//        testEntityCrudRepository.save(entity);
//    }
//}
