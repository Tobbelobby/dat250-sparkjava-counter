package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import java.util.Collection;

public class TodoDAO implements TodoSer {

    Collection<Todo> todos = new ArrayList<>();

    @Override
    public void add(Todo todo) {
        todos.add(todo);
    }

    @Override
    public Collection<Todo> get() {
        return todos;
    }

    @Override
    public Todo getTodo(Long id) {
        Todo todo = null;
        for (Todo newId : todos)
            if (newId.getId().equals(id)) {
                todo = newId;
            }
        return todo;

    }

    @Override
    public Todo edit(Todo todo) {
        Todo newTodo = getTodo(todo.getId());
        todos.remove(todo);
        if (newTodo.getId() != null){
            newTodo.setDescription(todo.getDescription());
            newTodo.setSummary(todo.getSummary());
            todos.add(newTodo);

        }
        return newTodo;
    }


    @Override
    public void delete(Long id) {
        todos.remove(getTodo(id));
    }

    @Override
    public boolean todoExist(Long id) {
        Todo todo = getTodo(id);
        return (todo!=null);
    }


}
