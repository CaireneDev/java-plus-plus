package com.cairenedev.javaplusplus;

/**
 *  The <tt>TransactionStack</tt> class represents a stack with transactions.
 *  <p>
 *  Push, pop, and get top element of the stack.
 *  Begin, commit or rollback a transaction.
 *  Nested transaction blocks
 *  
 *  <em>Initialization</em>
 *  ** Constant (1)
 *  
 *  <em>push(int x)</em>
 *  ** Constant (1)
 *  
 *  <em>pop()</em>
 *  ** Constant (1)
 *  
 *  <em>top()</em>
 *  ** Constant (1)
 *  
 *  <em>begin()</em>
 *  **  Constant (1)
 *  
 *  <em>rollback()</em>
 *  **  Linear (n)
 *  
 *  <em>commit()</em>
 *  **  Linear (n)
 *     
 *  @author Mohamed Osama <mohamed@cairenedev.com>
 */

import java.util.*;

public class TransactionStack {
    ArrayList<Integer> stack;
    ArrayList<Integer> transactions;
    ArrayList<Integer> beginLocations;
    
    public TransactionStack() {
        stack = new ArrayList<Integer>();
        transactions = new ArrayList<Integer>();
        beginLocations = new ArrayList<Integer>();
    }

    public void push(int value) {
        stack.add(value);
        if(!beginLocations.isEmpty()) {
            transactions.add(-1);
        }
    }

    public int top() {
        if(stack.size() > 0)
            return stack.get(stack.size() - 1);
        return 0;
    }

    public void pop() {
        if(stack.size() == 0)
            return;
        int value = stack.remove(stack.size() - 1);
        if(!beginLocations.isEmpty()) {
            transactions.add(value);
        }
    }

    public void begin() {
        transactions.add(0);
        beginLocations.add(transactions.size() - 1);
    }

    public boolean rollback() {
        if(beginLocations.isEmpty())
            return false;
        int operation;
        int i;
        for(i = transactions.size() - 1; i > beginLocations.get(beginLocations.size() - 1); i --) {
            operation = transactions.remove(i);
            if(operation == -1) {
                stack.remove(stack.size() - 1);
            }
            else {
                stack.add(operation);
            }
        }
        beginLocations.remove(beginLocations.size() - 1);
        if(beginLocations.isEmpty())
            transactions.clear();
        return true;
    }

    public boolean commit() {
        if(beginLocations.isEmpty())
            return false;
        transactions.remove(beginLocations.remove(beginLocations.size() - 1));
        if(beginLocations.isEmpty())
            transactions.clear();
        return true;
    }
};