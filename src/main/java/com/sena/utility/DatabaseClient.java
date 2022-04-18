package com.sena.utility;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

public class DatabaseClient<T> {
    private Session session;
    private Transaction transaction;

    private void openSession() {
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void closeCommit() {
        transaction.commit();
        session.close();
    }

    private void closeRollback() {
        transaction.rollback();
        session.close();
    }

    public void save(T t) {
        try {
            openSession();
            session.save(t);
            closeCommit();
        } catch (Exception e) {
            closeRollback();
        }
    }

    public void update(T t) {
        try {
            openSession();
            session.update(t);
            closeCommit();
        } catch (Exception exception) {
            closeRollback();
        }
    }

    public void delete(T t) {
        try {
            openSession();
            session.delete(t);
            closeCommit();
        } catch (Exception exception) {
            closeRollback();
        }
    }

    public T findById(long id, T t) {
        openSession();
        T result;
        Criteria criteria = session.createCriteria(t.getClass());
        criteria.add(Restrictions.eq("id", id));
        if (criteria.list().size() > 0) {
            result = (T) criteria.list().get(0);
        } else result = null;
        closeCommit();
        return result;
    }

    public List<T> findByColumn(String columnName, String value, boolean isEquals, T t) {

        openSession();
        Criteria criteria = session.createCriteria(t.getClass());
        if (isEquals)
            criteria.add(Restrictions.eq(columnName, value));
        else
            criteria.add(Restrictions.ilike(columnName, "%" + value + "%"));
        List<T> result = criteria.list();
        return result;
    }

    public List<T> findAll(T t) {
        openSession();
        Criteria criteria = session.createCriteria(t.getClass());
        List<T> result = criteria.list();
        closeCommit();
        return result;
    }

    public List<T> findAnyItem(T t) {
        Class cl = t.getClass();

        Field[] fields = cl.getDeclaredFields();
        openSession();
        Criteria criteria = session.createCriteria(t.getClass());
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (fields[i].getType().equals("long")) {
                    if ((long) fields[i].get(t) > 0) {
                        criteria.add(Restrictions.eq(fields[i].getName(), (long) fields[i].get(t)));
                    }
                } else if (fields[i].getType().equals("class java.lang.String")) {
                    if (fields[i].get(t) != null)
                        criteria.add(Restrictions.ilike(fields[i].getName(), "%" + fields[i].get(t) + "%"));
                }
            }
        } catch (Exception exception) {
            System.out.println("Exception : " + exception);
        }
        List<T> result = criteria.list();
        closeCommit();
        return result;
    }

    /*
     * The createCriteria() method is deprecated.
     * current example:
     *
     * public List<T> findAll(T t) {
     *     openSession();
     *     CriteriaQuery<?> criteriaQuery = session.getCriteriaBuilder().createQuery(t.getClass());
     *     criteriaQuery.from(t.getClass());
     *     List<T> result = (List<T>) session.createQuery(criteriaQuery).getResultList();
     *     closeCommit();
     *     return result;
     * }
     *
     */

}
