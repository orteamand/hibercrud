package com.orteamand.hibercrud.dao;

import com.orteamand.hibercrud.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;
    private static final int usersOnPage = 7;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully added");
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully updated");
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);

        if (user != null) {
            session.delete(user);
        }
        logger.info("User successfully removed");
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(Long page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        query.setFirstResult((int) (page - 1) * usersOnPage);
        query.setMaxResults(usersOnPage);
        List<User> users = query.list();
        for (User user : users) {
            logger.info("User details: " + user);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where NAME = :name");
        query.setParameter("name", name);
        List<User> users = query.list();
        for (User user : users) {
            logger.info("User details: " + user);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(int age) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where AGE = :age");
        query.setParameter("age", age);
        List<User> users = query.list();
        for (User user : users) {
            logger.info("User details: " + user);
        }
        return users;
    }
}
