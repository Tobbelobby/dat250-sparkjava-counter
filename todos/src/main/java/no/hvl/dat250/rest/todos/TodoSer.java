package no.hvl.dat250.rest.todos;

import java.util.Collection;
import java.util.HashMap;

public interface TodoSer{

        public void add(Todo todo);
        public Todo getTodo(Long id);
        public Collection<Todo> get();
        public Todo edit(Todo todo);
        public void delete(Long id);
        public boolean todoExist(Long id);
}
