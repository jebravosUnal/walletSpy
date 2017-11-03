package com.wallet.repository;

import com.wallet.entity.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

@Repository
public class FakeRepository implements PagingAndSortingRepository<Transaction, String>, QueryByExampleExecutor<Transaction> {

    private List<Transaction> transactionsCollections = newLinkedList();

    private Predicate<Transaction> isLabel(String label) {
        return transaction -> transaction.getLabel().equals(label);
    }

    private Predicate<Transaction> isId(String id) {
        return transaction -> transaction.getId().equals(id);
    }

    //    @Override
    public Transaction findByLabel(String label) {
        return transactionsCollections.stream().filter(isLabel(label)).findAny().orElse(null);
    }

    public <S extends Transaction> S insert(S s) {
        transactionsCollections.add(s);
        return s;
    }

    public <S extends Transaction> List<S> insert(Iterable<S> iterable) {
        List<S> inserted = newArrayList();
        iterable.forEach(s -> {
            inserted.add(insert(s));
        });
        return inserted;
    }

    @Override
    public <S extends Transaction> List<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends Transaction> S save(S s) {
        transactionsCollections.add(s);
        return s;
    }

    @Override
    public Transaction findOne(String s) {
        return transactionsCollections.stream().filter(isId(s)).findAny().orElseGet(null);
    }

    @Override
    public boolean exists(String s) {
        return transactionsCollections.stream().filter(isId(s)).findAny().isPresent();
    }

    @Override
    public List<Transaction> findAll() {
        return newArrayList(transactionsCollections);
    }

    @Override
    public Iterable<Transaction> findAll(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return transactionsCollections.size();
    }

    @Override
    public void delete(String s) {
        Transaction toDelete = findOne(s);
        if (toDelete != null) {
            transactionsCollections.remove(toDelete);
        }
    }

    @Override
    public void delete(Transaction transaction) {
        if (transaction != null) {
            transactionsCollections.remove(transaction);
        }
    }

    @Override
    public void delete(Iterable<? extends Transaction> iterable) {

    }

    @Override
    public void deleteAll() {
        transactionsCollections.clear();
    }

    @Override
    public List<Transaction> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Transaction> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Transaction> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Transaction> S findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Transaction> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Transaction> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Transaction> boolean exists(Example<S> example) {
        return false;
    }

//    public static FakeRepository getInstance(){
//        if(instance == null){
//            synchronized (instance){
//                if(instance == null){
//                    instance = new FakeRepository();
//                }
//            }
//        }
//        return instance;
//    }

}
